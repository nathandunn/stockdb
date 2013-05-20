package edu.uoregon.stockdb.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc")
public interface ExperimentService extends RemoteService {
    java.lang.String execute(java.lang.String arg0);
//    java.util.Map createValuesMap(edu.uoregon.stockdb.Experiment arg0);
//    java.util.Map createMeasuredValuesMap(edu.uoregon.stockdb.Experiment arg0);
//    java.util.List getMeasuredValueNames(edu.uoregon.stockdb.Experiment arg0);
//    java.util.List getMeasuredValuesForName(edu.uoregon.stockdb.Experiment arg0, java.lang.String arg1);
}
