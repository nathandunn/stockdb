package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;

/**
 */
public class CategoryListBox extends ListBox{

    private MultiWordSuggestOracle oracle ;
    private Integer measuredValueId ;
    private String category;

    private static QuickEntryServiceAsync quickEntryServiceAsync = GWT.create(QuickEntryService.class);

    public CategoryListBox(Integer measuredValueId, String category) {

        this.measuredValueId = measuredValueId ;
        this.category = category;

        setHeight(ExperimentTable.ROW_HEIGHT);
        setStyleName("quick-entry-table");
//        strainBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
//            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
//                Window.alert("selection changed: " + event.getSelectedItem().getDisplayString());
//            }
//        });
//        addValueChangeHandler(new ValueChangeHandler<String>() {
//            public void onValueChange(ValueChangeEvent<String> event) {
//                quickEntryServiceAsync.saveMeasuredValue(getMeasuredValueId(),"category", getCategory(),event.getValue(),new AsyncCallback(){
//                    public void onFailure(Throwable caught) {
//                        Window.alert("error saving string: "+caught);
//                    }
//
//                    public void onSuccess(Object result) {
//                        Window.alert("save successful : "+result);
//                    }
//                });
//                Window.alert("value changed: " + event.getValue());
//            }
//        });
    }

    public String getCategory() {
        return category;
    }

    public Integer getMeasuredValueId() {
        return measuredValueId;
    }
}
