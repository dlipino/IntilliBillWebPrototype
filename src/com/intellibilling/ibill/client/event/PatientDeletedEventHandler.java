package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface PatientDeletedEventHandler extends EventHandler {
  void onContactDeleted(PatientDeletedEvent event);
}
