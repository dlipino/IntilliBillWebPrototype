package com.intellibilling.ibill.client.view;


import com.google.gwt.user.client.ui.Widget;
import com.intellibilling.ibill.client.common.ColumnDefinition;

import java.util.List;

public interface PatientsView<T> {

  public interface Presenter<T> {
    void onAddButtonClicked();
    void onDeleteButtonClicked();
    void onItemClicked(T clickedItem);
    void onItemSelected(T selectedItem);
  }
  
  void setPresenter(Presenter<T> presenter);
  void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
  void setRowData(List<T> rowData);
  Widget asWidget();
}
