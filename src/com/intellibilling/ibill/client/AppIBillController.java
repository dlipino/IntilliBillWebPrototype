package com.intellibilling.ibill.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.intellibilling.ibill.client.common.PatientsColumnDefinitionsFactory;
import com.intellibilling.ibill.client.event.AddPatientEvent;
import com.intellibilling.ibill.client.event.AddPatientEventHandler;
import com.intellibilling.ibill.client.event.EditPatientCancelledEvent;
import com.intellibilling.ibill.client.event.EditPatientCancelledEventHandler;
import com.intellibilling.ibill.client.event.EditPatientEvent;
import com.intellibilling.ibill.client.event.EditPatientEventHandler;
import com.intellibilling.ibill.client.event.PatientUpdatedEvent;
import com.intellibilling.ibill.client.event.PatientUpdatedEventHandler;
import com.intellibilling.ibill.client.presenter.EditPatientPresenter;
import com.intellibilling.ibill.client.presenter.PatientsPresenter;
import com.intellibilling.ibill.client.presenter.Presenter;
import com.intellibilling.ibill.client.view.EditPatientView;
import com.intellibilling.ibill.client.view.PatientsViewImpl;
import com.intellibilling.ibill.shared.PatientDetails;


public class AppIBillController implements Presenter, ValueChangeHandler<String> {
  private final HandlerManager eventBus;
  private final PatientsServiceAsync rpcService; 
  private HasWidgets container;
  private PatientsViewImpl<PatientDetails> patientsView = null;
  private EditPatientView editPatientView = null;
  
  public AppIBillController(PatientsServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  private void bind() {
    History.addValueChangeHandler(this);

    eventBus.addHandler(AddPatientEvent.TYPE,
        new AddPatientEventHandler() {
          public void onAddContact(AddPatientEvent event) {
            doAddNewContact();
          }
        });  

    eventBus.addHandler(EditPatientEvent.TYPE,
        new EditPatientEventHandler() {
          public void onEditPatient(EditPatientEvent event) {
            doEditContact(event.getId());
          }
        });  

    eventBus.addHandler(EditPatientCancelledEvent.TYPE,
        new EditPatientCancelledEventHandler() {
          public void onEditContactCancelled(EditPatientCancelledEvent event) {
            doEditContactCancelled();
          }
        });  

    eventBus.addHandler(PatientUpdatedEvent.TYPE,
        new PatientUpdatedEventHandler() {
          public void onPatientUpdated(PatientUpdatedEvent event) {
            doContactUpdated();
          }
        });  
  }
  
  private void doAddNewContact() {
    History.newItem("add");
  }
  
  private void doEditContact(String id) {
    History.newItem("edit", false);
    Presenter presenter = new EditPatientPresenter(rpcService, eventBus, new EditPatientView(), id);
    presenter.go(container);
  }
  
  private void doEditContactCancelled() {
    History.newItem("list");
  }
  
  private void doContactUpdated() {
    History.newItem("list");
  }
  
  public void go(final HasWidgets container) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem("list");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      if (token.equals("list")) {
        GWT.runAsync(new RunAsyncCallback() {
          public void onFailure(Throwable caught) {
          }
      
          public void onSuccess() {
            // lazily initialize our views, and keep them around to be reused
            //
            if (patientsView == null) {
              patientsView = new PatientsViewImpl<PatientDetails>();
              
            }
            new PatientsPresenter(rpcService, eventBus, patientsView, 
                PatientsColumnDefinitionsFactory.getContactsColumnDefinitions()).go(container);
          }
        });
      }
      else if (token.equals("add") || token.equals("edit")) {
        GWT.runAsync(new RunAsyncCallback() {
          public void onFailure(Throwable caught) {
          }
      
          public void onSuccess() {
            // lazily initialize our views, and keep them around to be reused
            //
            if (editPatientView == null) {
              editPatientView = new EditPatientView();
              
            }
            new EditPatientPresenter(rpcService, eventBus, editPatientView).
            go(container);
          }
        });
      }
    }
  } 
}
