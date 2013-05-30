package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Dictionary;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class client implements EntryPoint {

    private static QuickEntryServiceAsync quickEntryServiceAsync = GWT.create(QuickEntryService.class);

    private Button button = new Button("click me!");
    private Label label = new Label("no call made yet");
    private Label experimentLabel = new Label("call made yet");
    private FlexTable flexTable = new FlexTable();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        Dictionary properties = Dictionary.getDictionary("properties");
        Integer experimentId = Integer.parseInt(properties.get("experimentId"));
//        Window.alert("experiment Id: "+experimentId);


        RootPanel.get().add(label);
        RootPanel.get().add(experimentLabel);
        RootPanel.get().add(flexTable);
        flexTable.setHTML(0,0,"<b>Strain</b>");
        flexTable.setHTML(0,1,"<b>Value</b>");
        flexTable.setHTML(0,2,"<b>Categry</b>");
        flexTable.setHTML(0,3,"<b>Action</b>");


        quickEntryServiceAsync.doit(new AsyncCallback() {
            public void onFailure(Throwable caught) {
                label.setText("failed - " + caught.getMessage());
            }

            public void onSuccess(Object result) {
                label.setText("succeed - " + result);
            }
        });

        quickEntryServiceAsync.getMeasuredValuesForExperiment(experimentId, new AsyncCallback() {
            public void onFailure(Throwable caught) {
                experimentLabel.setText("failed - " + caught.getMessage());
            }

            public void onSuccess(Object strainResults) {
                experimentLabel.setText("succeed - " + strainResults);
                JSONValue value = JSONParser.parseStrict((String) strainResults);
                GWT.log(value.toString());
            }
        });


//        RootPanel.get().add(button);
//        button.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
////                myService.doit(new MyAsyncCallback(label));
////                Window.alert("clicked");
//                myService.doit(new AsyncCallback() {
//                    public void onFailure(Throwable caught) {
//                        label.setText("failed - "+caught.getMessage());
//                    }
//
//                    public void onSuccess(Object result) {
//                        label.setText("succeed - "+result);
//                    }
//                });
//            }
//        } );


    }
}
