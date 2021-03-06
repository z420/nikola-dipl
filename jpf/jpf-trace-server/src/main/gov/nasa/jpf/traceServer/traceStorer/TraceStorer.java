//
// Copyright (C) 2010 Igor Andjelkovic (igor.andjelkovic@gmail.com).
// All Rights Reserved.
//
// This software is distributed under the NASA Open Source Agreement
// (NOSA), version 1.3.  The NOSA has been approved by the Open Source
// Initiative.  See the file NOSA-1.3-JPF at the top of the distribution
// directory tree for the complete NOSA document.
//
// THE SUBJECT SOFTWARE IS PROVIDED "AS IS" WITHOUT ANY WARRANTY OF ANY
// KIND, EITHER EXPRESSED, IMPLIED, OR STATUTORY, INCLUDING, BUT NOT
// LIMITED TO, ANY WARRANTY THAT THE SUBJECT SOFTWARE WILL CONFORM TO
// SPECIFICATIONS, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR
// A PARTICULAR PURPOSE, OR FREEDOM FROM INFRINGEMENT, ANY WARRANTY THAT
// THE SUBJECT SOFTWARE WILL BE ERROR FREE, OR ANY WARRANTY THAT
// DOCUMENTATION, IF PROVIDED, WILL CONFORM TO THE SUBJECT SOFTWARE.
//
package gov.nasa.jpf.traceServer.traceStorer;

/**
 * Abstract base class for trace storers. It stores events generated by
 * {@link gov.nasa.jpf.traceEmitter.TraceEmitter TraceEmitter}. There is a
 * corresponding store method for each notification from
 * {@link gov.nasa.jpf.ListenerAdapter ListenerAdapter}. <br/>
 * <code>TraceStorer</code> implementation will decide how to store the event.
 * 
 * @author Igor Andjelkovic
 * 
 */
public abstract class TraceStorer {

  /**
   * Stores instructionExecuted <code>event</code>.
   * 
   * @param event
   *          event to be stored
   * 
   * @see gov.nasa.jpf.ListenerAdapter#instructionExecuted(gov.nasa.jpf.jvm.JVM)
   *      gov.nasa.jpf.ListenerAdapter.instructionExecuted(JVM vm)
   * 
   */
  public void storeInstructionExecuted(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeExecuteInstruction(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadStarted(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadWaiting(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadNotified(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadInterrupted(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadScheduled(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadBlocked(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeThreadTerminated(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeClassLoaded(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectCreated(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectReleased(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectLocked(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectUnlocked(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectWait(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectNotify(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeObjectNotifyAll(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeGcBegin(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeGcEnd(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeExceptionThrown(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeExceptionBailout(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeExceptionHandled(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeChoiceGeneratorSet(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeChoiceGeneratorRegistered(Event event) {
  }
  
  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeChoiceGeneratorAdvanced(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeChoiceGeneratorProcessed(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeMethodEntered(Event event) {
  }

  /**
   * @param event
   *          event to be stored
   * @see #storeInstructionExecuted(Event)
   */
  public void storeMethodExited(Event event) {
  }

  // from the SearchListener
  public void storeStateAdvanced(Event event) {
  }

  public void storeStateProcessed(Event event) {
  }

  public void storeStateBacktracked(Event event) {
  }

  public void storeStateStored(Event event) {
  }

  public void storeStateRestored(Event event) {
  }

  public void storePropertyViolated(Event event) {
  }

  public void storeSearchStarted(Event event) {
  }

  public void storeSearchConstraintHit(Event event) {
  }

  public void storeSearchFinished(Event event) {
  }

  public void storeStatePurged(Event event) {
  }

}
