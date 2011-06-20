package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditPatientCancelledEvent extends GwtEvent<EditPatientCancelledEventHandler>{
  public static Type<EditPatientCancelledEventHandler> TYPE = new Type<EditPatientCancelledEventHandler>();
  
  @Override
  public Type<EditPatientCancelledEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditPatientCancelledEventHandler handler) {
    handler.onEditContactCancelled(this);
  }
}
