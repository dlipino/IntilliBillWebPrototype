package com.intellibilling.ibill.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class TreatmentSelectionView extends Composite {

	private static TreatmentSelectionViewUiBinder uiBinder = GWT
			.create(TreatmentSelectionViewUiBinder.class);

	interface TreatmentSelectionViewUiBinder extends
			UiBinder<Widget, TreatmentSelectionView> {
	}

	public TreatmentSelectionView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
