package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;

/**
 */
public class StrainEditBox extends SuggestBox {

    private Integer measuredValueId ;
    private String strain ;

    private static QuickEntryServiceAsync quickEntryServiceAsync = GWT.create(QuickEntryService.class);

    public StrainEditBox(MultiWordSuggestOracle strainOracle, Integer measuredValueId, String strain) {

        super(strainOracle);
        this.measuredValueId = measuredValueId ;
        this.strain = strain ;


        setLimit(10);
        setText(this.strain);
        setHeight(ExperimentTable.ROW_HEIGHT);
        setStyleName("quick-entry-table");
        addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {
            public void onSelection(SelectionEvent<SuggestOracle.Suggestion> event) {
                saveNewStrainValue(event.getSelectedItem().getReplacementString());
            }

        });

        addValueChangeHandler(new ValueChangeHandler<String>() {
            public void onValueChange(ValueChangeEvent<String> event) {
                setEnabled(false);
                saveNewStrainValue(getText());
            }
        });
    }

    private void saveNewStrainValue(String replacementString) {
        quickEntryServiceAsync.saveMeasuredValue(getMeasuredValueId(), "strain", getStrain(), replacementString, new AsyncCallback() {
            public void onFailure(Throwable caught) {
                Window.alert("Error saving strain: " + caught);
                getElement().getStyle().setProperty("color", "red");
                // must be camel-cased
//                        getElement().getStyle().setProperty("backgroundColor", "red");
                setEnabled(true);
            }

            public void onSuccess(Object result) {
                if (result.toString().startsWith("error:")) {
                    Window.alert("Error saving strain: " + result.toString().substring("error:".length()));
                    getElement().getStyle().setProperty("color", "red");
                } else {
//                            getElement().getStyle().setProperty("backgroundColor", "white");
                    getElement().getStyle().setProperty("color", "black");
                }
                setEnabled(true);
            }
        });
    }

    public String getStrain() {
        return strain;
    }

    public Integer getMeasuredValueId() {
        return measuredValueId;
    }
}
