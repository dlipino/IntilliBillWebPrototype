package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.GwtEvent;
import com.intellibilling.ibill.shared.Patient;

public class PatientUpdatedEvent extends GwtEvent<PatientUpdatedEventHandler>{
  public static Type<PatientUpdatedEventHandler> TYPE = new Type<PatientUpdatedEventHandler>();
  private final Patient updatedPatient;
  
  public PatientUpdatedEvent(Patient updatedPatient) {
    this.updatedPatient = updatedPatient;
  }
  
  public Patient getUpdatedPatient() { return updatedPatient; }
  

  @Override
  public Type<PatientUpdatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PatientUpdatedEventHandler handler) {
    handler.onPatientUpdated(this);
  }
}
