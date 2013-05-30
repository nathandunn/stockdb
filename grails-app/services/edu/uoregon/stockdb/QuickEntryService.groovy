package edu.uoregon.stockdb

import grails.converters.JSON

class QuickEntryService {

    static expose = ['gwt:edu.uoregon.stockdb.client']

    String doit() {
        println "somethign back from doit "
        return "returning stuff from doit"
    }

    String getMeasuredValuesForExperiment(Integer experimentId){
        Experiment experiment = Experiment.findById(experimentId)
        return experiment.measuredValues as JSON
    }
}
