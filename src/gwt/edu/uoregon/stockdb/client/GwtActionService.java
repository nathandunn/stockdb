package edu.uoregon.stockdb.client;

import com.google.gwt.user.client.rpc.RemoteService;

public interface GwtActionService extends RemoteService {
    //    Response execute(grails.plugins.gwt.shared.Action arg0);
    grails.plugins.gwt.shared.Response execute(GetExperimentAction arg0);
//    String getMeasuredValues(Integer experimentId);
}
