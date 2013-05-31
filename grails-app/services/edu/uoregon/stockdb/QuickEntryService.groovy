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

        List<MeasuredValueDTO> measuredValueDTOList = new ArrayList<MeasuredValueDTO>();

        for(MeasuredValue measuredValue in experiment.measuredValues){
            MeasuredValueDTO measuredValueDTO = new MeasuredValueDTO()
            measuredValueDTO.category = measuredValue.category.name
            measuredValueDTO.strain = measuredValue.strain.name
            measuredValueDTO.value = measuredValue.value
            measuredValueDTO.id = measuredValue.id
            measuredValueDTOList.add(measuredValueDTO)
        }

        MeasuredValuesDTO measuredValuesDTO = new MeasuredValuesDTO()
        measuredValuesDTO.categories = Category.listOrderByName().collect{ it.name }
        measuredValuesDTO.strains = Strain.listOrderByName().collect { it.name }
        measuredValuesDTO.experiments = measuredValueDTOList

        measuredValuesDTO.experimentId = experimentId;

//        return experimentDTOList as JSON
        return measuredValuesDTO as JSON
//        return experiment.measuredValues as JSON
    }

    String createMeasuredValue(Integer experimentId, String strainString, String valueString, String categoryString){
        Experiment experiment = Experiment.get(experimentId)
        Strain strain = Strain.findByName(strainString)
        Category category = Category.findByName(categoryString)
        MeasuredValue measuredValue = new MeasuredValue(
                experiment: experiment
                ,strain: strain
                ,category: category
                ,value: valueString
        ).save(insert: true,flush: true,failOnError: true)
        return measuredValue.id as String
    }

    String removeMeasuredValue(Integer measuredValueId){
        MeasuredValue measuredValue = MeasuredValue.findById(measuredValueId)
//        MeasuredValue.deleteAll(measuredValue)
        measuredValue.delete(flush: true)
        return measuredValue.id
    }

    String saveMeasuredValue(Integer measuredValueId, String field, String oldValue, String newValue){
        MeasuredValue measuredValue = MeasuredValue.findById(measuredValueId)
        measuredValue."$field" = newValue
        measuredValue.save(insert: false,flush: true,failOnError: true)
        return measuredValue.id
    }
}
