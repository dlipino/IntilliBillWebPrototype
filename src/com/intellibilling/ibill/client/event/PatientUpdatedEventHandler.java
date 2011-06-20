package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PatientUpdatedEventHandler extends EventHandler{
  void onPatientUpdated(PatientUpdatedEvent event);
}
