package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface AddPatientEventHandler extends EventHandler {
  void onAddContact(AddPatientEvent event);
}
