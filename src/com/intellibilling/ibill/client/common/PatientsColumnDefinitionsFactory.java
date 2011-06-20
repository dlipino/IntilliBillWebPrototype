package com.intellibilling.ibill.client.common;

import java.util.ArrayList;
import java.util.List;

import com.intellibilling.ibill.shared.PatientDetails;

public class PatientsColumnDefinitionsFactory<T> {
  public static List<ColumnDefinition<PatientDetails>> 
      getContactsColumnDefinitions() {
    return PatientsColumnDefinitionsImpl.getInstance();
  }

  public static List<ColumnDefinition<PatientDetails>>
      getTestContactsColumnDefinitions() {
    return new ArrayList<ColumnDefinition<PatientDetails>>();
  }
}
