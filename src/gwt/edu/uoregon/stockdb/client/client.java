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
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        RootPanel.get().add(new Label("its kind of working"));


        RootPanel.get().add(button);
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
//                myService.doit(new MyAsyncCallback(label));
                Window.alert("clicked");
                myService.doit(new AsyncCallback() {
                    public void onFailure(Throwable caught) {
                        Window.alert("fail:\n"+caught);
                    }

                    public void onSuccess(Object result) {
                        Window.alert("suceed:\n"+result);
                    }
                });
            }
        }
        );


    }
}
