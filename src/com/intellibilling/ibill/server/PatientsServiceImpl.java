package com.intellibilling.ibill.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.intellibilling.ibill.client.PatientsService;
import com.intellibilling.ibill.shared.Patient;
import com.intellibilling.ibill.shared.PatientDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("serial")
public class PatientsServiceImpl extends RemoteServiceServlet implements
    PatientsService {

  private static final String[] patientsFirstNameData = new String[] {
      "Hollie", "Emerson", "Healy", "Brigitte", "Elba", "Claudio",
      "Dena", "Christina", "Gail", "Orville", "Rae", "Mildred",
      "Candice", "Louise", "Emilio", "Geneva", "Heriberto", "Bulrush", 
      "Abigail", "Chad", "Terry", "Bell"};
  
  private final String[] patientsLastNameData = new String[] {
      "Voss", "Milton", "Colette", "Cobb", "Lockhart", "Engle",
      "Pacheco", "Blake", "Horton", "Daniel", "Childers", "Starnes",
      "Carson", "Kelchner", "Hutchinson", "Underwood", "Rush", "Bouchard", 
      "Louis", "Andrews", "English", "Snedden"};
  
  private final String[] patientsEmailData = new String[] {
      "mark@example.com", "hollie@example.com", "boticario@example.com",
      "emerson@example.com", "healy@example.com", "brigitte@example.com",
      "elba@example.com", "claudio@example.com", "dena@example.com",
      "brasilsp@example.com", "parker@example.com", "derbvktqsr@example.com",
      "qetlyxxogg@example.com", "antenas_sul@example.com",
      "cblake@example.com", "gailh@example.com", "orville@example.com",
      "post_master@example.com", "rchilders@example.com", "buster@example.com",
      "user31065@example.com", "ftsgeolbx@example.com"};
      
  private final HashMap<String, Patient> patients = new HashMap<String, Patient>();

  public PatientsServiceImpl() {
    initPatients();
  }
  
  private void initPatients() {
    // TODO: Create a real UID for each contact
    //
    for (int i = 0; i < patientsFirstNameData.length && i < patientsLastNameData.length && i < patientsEmailData.length; ++i) {
    	Patient contact = new Patient(String.valueOf(i), patientsFirstNameData[i], patientsLastNameData[i], patientsEmailData[i]);
      patients.put(contact.getId(), contact); 
    }
  }
  
  public Patient addPatient(Patient contact) {
    contact.setId(String.valueOf(patients.size()));
    patients.put(contact.getId(), contact); 
    return contact;
  }

  public Patient updatePatient(Patient contact) {
    patients.remove(contact.getId());
    patients.put(contact.getId(), contact); 
    return contact;
  }

  public Boolean deletePatient(String id) {
    patients.remove(id);
    return true;
  }
  
  public ArrayList<PatientDetails> deletePatients(ArrayList<String> ids) {

    for (int i = 0; i < ids.size(); ++i) {
      deletePatient(ids.get(i));
    }
    
    return getPatientDetails();
  }
  
  public ArrayList<PatientDetails> getPatientDetails() {
    ArrayList<PatientDetails> patientDetails = new ArrayList<PatientDetails>();
    
    Iterator<String> it = patients.keySet().iterator();
    while(it.hasNext()) { 
    	Patient contact = patients.get(it.next());          
      patientDetails.add(contact.getLightWeightContact());
    }
    
    return patientDetails;
  }

  public Patient getPatient(String id) {
    return patients.get(id);
  }
}
