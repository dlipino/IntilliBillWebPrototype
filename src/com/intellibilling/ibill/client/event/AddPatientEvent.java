package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddPatientEvent extends GwtEvent<AddPatientEventHandler> {
  public static Type<AddPatientEventHandler> TYPE = new Type<AddPatientEventHandler>();
  
  @Override
  public Type<AddPatientEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddPatientEventHandler handler) {
    handler.onAddContact(this);
  }
}
