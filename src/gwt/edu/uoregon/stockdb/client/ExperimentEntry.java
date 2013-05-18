package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
//import org.codehaus.groovy.grails.plugins.gwt.client.GwtActionServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ExperimentEntry implements EntryPoint {


    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
//        GWT.log("Hello World!", null);
//
//        Button b = new Button("Click me", new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                Window.alert("Hello, AJAX");
//            }
//        });

//        RootPanel.get().add(b);

//        GwtActionServiceAsync experimentEntryGwtService = (GwtActionServiceAsync) GWT.create(edu.uoregon.stockdb.client.GwtActionService.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) experimentEntryGwtService;
//        String moduleRelativeUrl = GWT.getModuleBaseURL() + "rpc";
//        endpoint.setServiceEntryPoint(moduleRelativeUrl);

        GwtActionServiceAsync experimentEntryGwtService = GWT.create(GwtActionService.class);
        experimentEntryGwtService.execute(new GetExperimentAction(), new AsyncCallback<GetExperimentResponse>() {
            @Override
            public void onFailure(Throwable caught) {
                GWT.log("boo!" + caught);
            }

            @Override
            public void onSuccess(GetExperimentResponse result) {
                GWT.log("yeah!" + result);
            }
        });
//        experimentEntryGwtService.getMeasuredValues(
//                1, new AsyncCallback() {
//
//            public void onFailure(Throwable arg0) {
//
//            }
//
//            public void onSuccess(Object author) {
//                GWT.log("digiity");
////                JSONValue value = JSONParser.parse((String)author);
////                tree.removeItems();
////                tree.setVisible(true);
////                TreeItem item = tree.addItem("Response");
////                addChildren(item, value);
//
//            }
//        });


        FlexTable flexTable = new FlexTable();
        flexTable.setHTML(0, 0, "Strain");
        flexTable.setHTML(0, 1, "Value");
        flexTable.setHTML(0, 2, "Category");
        flexTable.setHTML(0, 3, "Action");

        flexTable.setHTML(1, 0, "Z123");
        flexTable.setHTML(1, 1, "12");
        flexTable.setHTML(1, 2, "Motility");
        flexTable.setHTML(1, 3, "Remove");


        RootPanel.get().add(flexTable);

    }
}
