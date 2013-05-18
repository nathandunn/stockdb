package edu.uoregon.stockdb.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtActionServiceAsync {
    //    Response execute(grails.plugins.gwt.shared.Action arg0);
        void execute(GetExperimentAction arg0, AsyncCallback<GetExperimentResponse> async);


    //    Response execute(grails.plugins.gwt.shared.Action arg0);
//    void execute(GetExperimentAction arg0, AsyncCallback<Response> async);
//    void execute(grails.plugins.gwt.shared.Action arg0, AsyncCallback<Response> async);

//    void getMeasuredValues(Integer experimentId, AsyncCallback<String> async);
}
