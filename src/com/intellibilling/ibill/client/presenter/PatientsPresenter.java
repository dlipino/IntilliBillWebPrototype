package com.intellibilling.ibill.client.presenter;


import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.intellibilling.ibill.client.PatientsServiceAsync;
import com.intellibilling.ibill.client.common.ColumnDefinition;
import com.intellibilling.ibill.client.common.SelectionModel;
import com.intellibilling.ibill.client.event.AddPatientEvent;
import com.intellibilling.ibill.client.event.EditPatientEvent;
import com.intellibilling.ibill.client.view.PatientsView;
import com.intellibilling.ibill.shared.PatientDetails;

import java.util.ArrayList;
import java.util.List;

public class PatientsPresenter implements Presenter, 
  PatientsView.Presenter<PatientDetails> {  

  private List<PatientDetails> patientDetails;
  private final PatientsServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final PatientsView<PatientDetails> view;
  private final SelectionModel<PatientDetails> selectionModel;
  
  public PatientsPresenter(PatientsServiceAsync rpcService, 
      HandlerManager eventBus, PatientsView<PatientDetails> view,
      List<ColumnDefinition<PatientDetails>> columnDefinitions) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.view = view;
    this.selectionModel = new SelectionModel<PatientDetails>();
    this.view.setPresenter(this);
    this.view.setColumnDefinitions(columnDefinitions);
  }
  
  public void onAddButtonClicked() {
    eventBus.fireEvent(new AddPatientEvent());
  }
  
  public void onDeleteButtonClicked() {
    deleteSelectedContacts();
  }
  
  public void onItemClicked(PatientDetails contactDetails) {
    eventBus.fireEvent(new EditPatientEvent(contactDetails.getId()));
  }

  public void onItemSelected(PatientDetails contactDetails) {
    if (selectionModel.isSelected(contactDetails)) {
      selectionModel.removeSelection(contactDetails);
    }
    else {
      selectionModel.addSelection(contactDetails);
    }
  }
  
  public void go(final HasWidgets container) {
    container.clear();
    container.add(view.asWidget());
    fetchContactDetails();
  }

  public void sortContactDetails() {
    
    // Yes, we could use a more optimized method of sorting, but the 
    //  point is to create a test case that helps illustrate the higher
    //  level concepts used when creating MVP-based applications. 
    //
    for (int i = 0; i < patientDetails.size(); ++i) {
      for (int j = 0; j < patientDetails.size() - 1; ++j) {
        if (patientDetails.get(j).getDisplayName().compareToIgnoreCase(patientDetails.get(j + 1).getDisplayName()) >= 0) {
        	PatientDetails tmp = patientDetails.get(j);
        	patientDetails.set(j, patientDetails.get(j + 1));
        	patientDetails.set(j + 1, tmp);
        }
      }
    }
  }

  public void setPatientDetials(List<PatientDetails> contactDetails) {
    this.patientDetails = contactDetails;
  }
  
  public List<PatientDetails> getContactDetails() {
    return patientDetails;
  }
  
  public PatientDetails getContactDetail(int index) {
    return patientDetails.get(index);
  }
  
  private void fetchContactDetails() {
    rpcService.getPatientDetails(new AsyncCallback<ArrayList<PatientDetails>>() {
      public void onSuccess(ArrayList<PatientDetails> result) {
          patientDetails = result;
          sortContactDetails();
          view.setRowData(patientDetails);
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error fetching contact details");
      }
    });
  }

  private void deleteSelectedContacts() {
    List<PatientDetails> selectedContacts = selectionModel.getSelectedItems();
    ArrayList<String> ids = new ArrayList<String>();
    
    for (int i = 0; i < selectedContacts.size(); ++i) {
      ids.add(selectedContacts.get(i).getId());
    }
    
    rpcService.deletePatients(ids, new AsyncCallback<ArrayList<PatientDetails>>() {
      public void onSuccess(ArrayList<PatientDetails> result) {
        patientDetails = result;
        sortContactDetails();
        view.setRowData(patientDetails);
      }
      
      public void onFailure(Throwable caught) {
        System.out.println("Error deleting selected contacts");
      }
    });
  }
}
