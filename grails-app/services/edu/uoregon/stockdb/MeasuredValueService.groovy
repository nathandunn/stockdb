package edu.uoregon.stockdb

class MeasuredValueService {

    def serviceMethod() {

    }

    def addMeasuredValueToExperimentIfNotNull(String name, String value, Experiment experiment,MeasuredValueTypeEnum measuredValueTypeEnum = MeasuredValueTypeEnum.STRING_TYPE) {
        if (!value) return null
        MeasuredValue measuredValue = MeasuredValue.findOrSaveByNameAndValue(name,value)
        measuredValue.experiment = experiment
        experiment.addToMeasuredValues(measuredValue)
        return measuredValue
    }
}
