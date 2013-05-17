package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;

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


        FlexTable flexTable = new FlexTable();
        flexTable.setHTML(0,0,"Strain");
        flexTable.setHTML(0,1,"Value");
        flexTable.setHTML(0,2,"Category");
        flexTable.setHTML(0,3,"Action");

        flexTable.setHTML(1,0,"Z123");
        flexTable.setHTML(1,1,"12");
        flexTable.setHTML(1,2,"Motility");
        flexTable.setHTML(1,3,"Remove");



        RootPanel.get().add(flexTable);

    }
}
