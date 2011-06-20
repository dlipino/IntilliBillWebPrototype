package com.intellibilling.ibill.client.common;


import java.util.ArrayList;

import com.intellibilling.ibill.shared.PatientDetails;

@SuppressWarnings("serial")
public class PatientsColumnDefinitionsImpl extends 
    ArrayList<ColumnDefinition<PatientDetails>> {
  
  private static PatientsColumnDefinitionsImpl instance = null;
  
  public static PatientsColumnDefinitionsImpl getInstance() {
    if (instance == null) {
      instance = new PatientsColumnDefinitionsImpl();
    }
    
    return instance;
  }
  
  protected PatientsColumnDefinitionsImpl() {
    this.add(new ColumnDefinition<PatientDetails>() {
      public void render(PatientDetails c, StringBuilder sb) {
        sb.append("<input type='checkbox'/>");
      }

      public boolean isSelectable() {
        return true;
      }
    });

    this.add(new ColumnDefinition<PatientDetails>() {
      public void render(PatientDetails c, StringBuilder sb) {        
        sb.append("<div id='" + c.getDisplayName() + "'>" + c.getDisplayName() + "</div>");
      }

      public boolean isClickable() {
        return true;
      }
    });
  }
}
