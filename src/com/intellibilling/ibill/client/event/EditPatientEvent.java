package com.intellibilling.ibill.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class EditPatientEvent extends GwtEvent<EditPatientEventHandler>{
  public static Type<EditPatientEventHandler> TYPE = new Type<EditPatientEventHandler>();
  private final String id;
  
  public EditPatientEvent(String id) {
    this.id = id;
  }
  
  public String getId() { return id; }
  
  @Override
  public Type<EditPatientEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditPatientEventHandler handler) {
    handler.onEditPatient(this);
  }
}
