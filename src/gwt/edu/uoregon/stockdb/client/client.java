package edu.uoregon.stockdb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class client implements EntryPoint {
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        RootPanel.get().add(new Label("its kind of working"));

    }
}
