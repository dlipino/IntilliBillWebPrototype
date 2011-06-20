package com.intellibilling.ibill.shared;

import java.io.Serializable;


public class PatientDetails implements Serializable {
	  private String id;
	  private String displayName;
	  
	  public PatientDetails() {
	    new PatientDetails("0", "");
	  }

	  public PatientDetails(String id, String displayName) {
	    this.id = id;
	    this.displayName = displayName;
	  }
	  
	  public String getId() { return id; }
	  public void setId(String id) { this.id = id; }
	  public String getDisplayName() { return displayName; }
	  public void setDisplayName(String displayName) { this.displayName = displayName; } 
}
