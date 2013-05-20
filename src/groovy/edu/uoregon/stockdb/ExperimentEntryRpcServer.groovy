package edu.uoregon.stockdb

/**
 */
class ExperimentEntryRpcServer {
//    http://localhost:8080/metagenomicsdb/?gwt.codesvr=127.0.0.1:9997
    static expose = ['gwt:edu.uoregon.stockdb.client']

    // TODO: get this service to work!
    String execute(String inputArg) {
        println " trying to execute something "
//        GetExperimentResponse getExperimentResponse = new GetExperimentResponse();

//        return getExperimentResponse;
        return "yes: " + inputArg
    }

}
