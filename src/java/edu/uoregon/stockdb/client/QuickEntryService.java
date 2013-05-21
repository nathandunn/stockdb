package edu.uoregon.stockdb.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("rpc")
public interface QuickEntryService extends RemoteService {
    java.lang.String doit();
}
