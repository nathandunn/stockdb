package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class client implements EntryPoint {

    private static QuickEntryServiceAsync myService = GWT.create(QuickEntryService.class);

    private Button button = new Button("click me!");
    private Label label = new Label("no call made yet");

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        RootPanel.get().add(label);
        RootPanel.get().add(button);

        GWT.log(GWT.getModuleBaseURL());

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("clicked");
                myService.doit(new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        label.setText("click failed - " + caught.getMessage());
                    }

                    public void onSuccess(Object result) {
                        label.setText("click succeed - " + result);
                    }
                });
            }
        });

        myService.doit(new AsyncCallback() {
            public void onFailure(Throwable caught) {
                label.setText("failed - " + caught.getMessage());
            }

            public void onSuccess(Object result) {
                label.setText("succeed - " + result);
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
