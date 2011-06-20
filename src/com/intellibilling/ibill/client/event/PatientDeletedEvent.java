package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class PatientDeletedEvent extends GwtEvent<PatientDeletedEventHandler>{
  public static Type<PatientDeletedEventHandler> TYPE = new Type<PatientDeletedEventHandler>();
  
  @Override
  public Type<PatientDeletedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(PatientDeletedEventHandler handler) {
    handler.onContactDeleted(this);
  }
}
