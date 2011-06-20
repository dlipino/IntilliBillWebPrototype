package com.intellibilling.ibill.client;

import java.util.ArrayList;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.intellibilling.ibill.shared.Patient;
import com.intellibilling.ibill.shared.PatientDetails;

public interface PatientsServiceAsync {
	  public void addPatient(Patient patient, AsyncCallback<Patient> callback);
	  public void deletePatient(String id, AsyncCallback<Boolean> callback);
	  public void deletePatients(ArrayList<String> ids, AsyncCallback<ArrayList<PatientDetails>> callback);
	  public void getPatientDetails(AsyncCallback<ArrayList<PatientDetails>> callback);
	  public void getPatient(String id, AsyncCallback<Patient> callback);
	  public void updatePatient(Patient contact, AsyncCallback<Patient> callback);
}
