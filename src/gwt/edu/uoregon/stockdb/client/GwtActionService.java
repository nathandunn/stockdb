package edu.uoregon.stockdb.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc")
public interface GwtActionService extends RemoteService {
    //    Response execute(grails.plugins.gwt.shared.Action arg0);
    GetExperimentResponse execute(GetExperimentAction arg0);
//    String getMeasuredValues(Integer experimentId);
}
