package gov.nasa.jpf.symbc.heap;



import gov.nasa.jpf.jvm.BooleanFieldInfo;
import gov.nasa.jpf.jvm.ClassInfo;
import gov.nasa.jpf.jvm.DoubleFieldInfo;
import gov.nasa.jpf.jvm.ElementInfo;
import gov.nasa.jpf.jvm.FieldInfo;
import gov.nasa.jpf.jvm.FloatFieldInfo;
import gov.nasa.jpf.jvm.IntegerFieldInfo;
import gov.nasa.jpf.jvm.KernelState;
import gov.nasa.jpf.jvm.LongFieldInfo;
import gov.nasa.jpf.jvm.ReferenceFieldInfo;
import gov.nasa.jpf.jvm.StaticElementInfo;
import gov.nasa.jpf.jvm.ThreadInfo;
import gov.nasa.jpf.symbc.numeric.Comparator;
import gov.nasa.jpf.symbc.numeric.Expression;
import gov.nasa.jpf.symbc.numeric.IntegerConstant;
import gov.nasa.jpf.symbc.numeric.PathCondition;
import gov.nasa.jpf.symbc.numeric.SymbolicInteger;
import gov.nasa.jpf.symbc.numeric.SymbolicReal;
import gov.nasa.jpf.symbc.string.StringSymbolic;

public class Helper {

	public static SymbolicInteger SymbolicNull = new SymbolicInteger("SymbolicNull"); // hack for handling static fields

	public static Expression initializeInstanceField(FieldInfo field, ElementInfo eiRef,
			String refChain, String suffix){
		Expression sym_v = null;
		String name ="";

		name = field.getName();
		String fullName = refChain + "." + name + suffix;
		if (field instanceof IntegerFieldInfo || field instanceof LongFieldInfo) {
				sym_v = new SymbolicInteger(fullName);
		} else if (field instanceof FloatFieldInfo || field instanceof DoubleFieldInfo) {
			sym_v = new SymbolicReal(fullName);
		} else if (field instanceof ReferenceFieldInfo){
			if (field.getType().equals("java.lang.String"))
				sym_v = new StringSymbolic(fullName);
			else
				sym_v = new SymbolicInteger(fullName);
		} else if (field instanceof BooleanFieldInfo) {
				//	treat boolean as an integer with range [0,1]
				sym_v = new SymbolicInteger(fullName, 0, 1);
		}
		eiRef.setFieldAttr(field, sym_v);
		return sym_v;
	}

	public static void initializeInstanceFields(FieldInfo[] fields, ElementInfo eiRef,
			String refChain){
		for (int i=0; i<fields.length;i++)
			initializeInstanceField(fields[i], eiRef, refChain, "");
	}

	public static Expression initializeStaticField(FieldInfo staticField, ClassInfo ci,
			ThreadInfo ti, String suffix){

		Expression sym_v = null;
		String name ="";

		name = staticField.getName();
		String fullName = ci.getName() + "." + name + suffix;// + "_init";
		if (staticField instanceof IntegerFieldInfo || staticField instanceof LongFieldInfo) {
				sym_v = new SymbolicInteger(fullName);
		} else if (staticField instanceof FloatFieldInfo
				|| staticField instanceof DoubleFieldInfo) {
			sym_v = new SymbolicReal(fullName);
		}else if (staticField instanceof ReferenceFieldInfo){
			if (staticField.getType().equals("java.lang.String"))
				sym_v = new StringSymbolic(fullName);
			else
				sym_v = new SymbolicInteger(fullName);
		} else if (staticField instanceof BooleanFieldInfo) {
				//						treat boolean as an integer with range [0,1]
				sym_v = new SymbolicInteger(fullName, 0, 1);
		}
		StaticElementInfo sei = ci.getStaticElementInfo();
		if (sei == null) {
			ci.registerClass(ti);
			sei = ci.getStaticElementInfo();
		}
		if (sei.getFieldAttr(staticField) == null) {
			sei.setFieldAttr(staticField, sym_v);
		}
		return sym_v;
	}

	public static void initializeStaticFields(FieldInfo[] staticFields, ClassInfo ci,
			ThreadInfo ti){

		if (staticFields.length > 0) {
			for (int i = 0; i < staticFields.length; i++)
				initializeStaticField(staticFields[i], ci, ti, "");
		}
	}

	  //neha: added the code to intansiate an new heap in a separate procedure. There are multiple
	  //bytecodes that need access to the same code. Lazy initialization and uber-lazy initialization
	  // generate a new HeapNode in the same way. This can be used across different init algorithms.
	  public static int addNewHeapNode(ClassInfo typeClassInfo, ThreadInfo ti, int daIndex, Object attr,
			  KernelState ks, PathCondition pcHeap, SymbolicInputHeap symInputHeap,
			  int numSymRefs, HeapNode[] prevSymRefs ) {
		  daIndex = ks.heap.newObject(typeClassInfo, ti);
		  String refChain = ((SymbolicInteger) attr).getName() + "[" + daIndex + "]"; // do we really need to add daIndex here?
		  SymbolicInteger newSymRef = new SymbolicInteger( refChain);
		  ElementInfo eiRef = ti.getElementInfo(daIndex);

		  // neha: this change allows all the fields in the class hierarchy of the
		  // object to be initialized as symbolic and not just its instance fields

		  int numOfFields = eiRef.getNumberOfFields();
		  FieldInfo[] fields = new FieldInfo[numOfFields];
		  for(int fieldIndex = 0; fieldIndex < numOfFields; fieldIndex++) {
			  fields[fieldIndex] = eiRef.getFieldInfo(fieldIndex);
		  }

		  Helper.initializeInstanceFields(fields, eiRef,refChain);

		  //neha: this change allows all the static fields in the class hierarchy
		  // of the object to be initialized as symbolic and not just its immediate
		  // static fields
		  ClassInfo superClass = typeClassInfo;
		  while(superClass != null) {
			  FieldInfo[] staticFields = superClass.getDeclaredStaticFields();
			  Helper.initializeStaticFields(staticFields, superClass, ti);
			  superClass = superClass.getSuperClass();
		  }

		  // create new HeapNode based on above info
		  // update associated symbolic input heap
		  HeapNode n= new HeapNode(daIndex,typeClassInfo,newSymRef);
		  symInputHeap._add(n);
		  pcHeap._addDet(Comparator.NE, newSymRef, new IntegerConstant(-1));
		  //pcHeap._addDet(Comparator.EQ, newSymRef, (SymbolicInteger) attr);
		  for (int i=0; i< numSymRefs; i++)
			  pcHeap._addDet(Comparator.NE, n.getSymbolic(), prevSymRefs[i].getSymbolic());
		  return daIndex;
	  }
}
