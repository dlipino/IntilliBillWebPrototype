package com.intellibilling.ibill.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class Patients implements EntryPoint {

	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
	    PatientsServiceAsync rpcService = GWT.create(PatientsService.class);    
	    HandlerManager eventBus = new HandlerManager(null);
	    AppIBillController appViewer = new AppIBillController(rpcService, eventBus);
	    appViewer.go(RootPanel.get());

	}

}
