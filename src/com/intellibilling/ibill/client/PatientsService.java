package com.intellibilling.ibill.client;

import com.google.gwt.user.client.rpc.RemoteService; 
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.ArrayList;

import com.intellibilling.ibill.shared.Patient;
import com.intellibilling.ibill.shared.PatientDetails;

@RemoteServiceRelativePath("patientsService")
public interface PatientsService extends RemoteService {
	
  Patient addPatient(Patient contact);
  Boolean deletePatient(String id); 
  ArrayList<PatientDetails> deletePatients(ArrayList<String> ids);
  ArrayList<PatientDetails> getPatientDetails();
  Patient getPatient(String id);
  Patient updatePatient(Patient contact);
}
