package com.intellibilling.ibill.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback; 
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Window;
import com.intellibilling.ibill.client.PatientsServiceAsync;
import com.intellibilling.ibill.client.event.EditPatientCancelledEvent;
import com.intellibilling.ibill.client.event.PatientUpdatedEvent;
import com.intellibilling.ibill.shared.Patient;

public class EditPatientPresenter implements Presenter{  
  
	public interface Display {
		
    HasClickHandlers getSaveButton();
    HasClickHandlers getCancelButton();
    HasValue<String> getFirstName();
    HasValue<String> getLastName();
    HasValue<String> getEmailAddress();
    Widget asWidget();
  }
  
  private Patient patient;
  private final PatientsServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public EditPatientPresenter(PatientsServiceAsync rpcService, HandlerManager eventBus, Display display) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.patient = new Patient();
    this.display = display;
    bind();
  }
  
  public EditPatientPresenter(PatientsServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = display;
    bind();
    
    rpcService.getPatient(id, new AsyncCallback<Patient>() {
      public void onSuccess(Patient result) {
        patient = result;
        EditPatientPresenter.this.display.getFirstName().setValue(patient.getFirstName());
        EditPatientPresenter.this.display.getLastName().setValue(patient.getLastName());
        EditPatientPresenter.this.display.getEmailAddress().setValue(patient.getEmailAddress());
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error retrieving contact");
      }
    });
    
  }
  
  public void bind() {
    this.display.getSaveButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        doSave();
      }
    });

    this.display.getCancelButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new EditPatientCancelledEvent());
      }
    });
  }

  public void go(final HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
  }

  private void doSave() {
    patient.setFirstName(display.getFirstName().getValue());
    patient.setLastName(display.getLastName().getValue());
    patient.setEmailAddress(display.getEmailAddress().getValue());
    
    rpcService.updatePatient(patient, new AsyncCallback<Patient>() {
        public void onSuccess(Patient result) {
          eventBus.fireEvent(new PatientUpdatedEvent(result));
        }
        public void onFailure(Throwable caught) {
          Window.alert("Error updating contact");
        }
    });
  }
  
}
