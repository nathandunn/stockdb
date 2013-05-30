package edu.uoregon.stockdb.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface QuickEntryServiceAsync {
    void doit(AsyncCallback callback);

    void getMeasuredValuesForExperiment(Integer experimentId,AsyncCallback asyncCallback);
}
