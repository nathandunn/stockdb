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

        List<ExperimentDTO> experimentDTOList = new ArrayList<ExperimentDTO>();

        for(MeasuredValue measuredValue in experiment.measuredValues){
            ExperimentDTO experimentDTO = new ExperimentDTO()
            experimentDTO.category = measuredValue.category.name
            experimentDTO.strain = measuredValue.strain.name
            experimentDTO.value = measuredValue.value
            experimentDTOList.add(experimentDTO)
        }

        List<String> strainList = new ArrayList<String>()
        strainList = Strain.listOrderByName().collect { it.name }

        List<String> categoryList = new ArrayList<String>()
        categoryList = Category.listOrderByName().collect{ it.name }

        MeasuredValuesDTO measuredValuesDTO = new MeasuredValuesDTO()
        measuredValuesDTO.categories = categoryList
        measuredValuesDTO.strains = strainList
        measuredValuesDTO.experiments = experimentDTOList

//        return experimentDTOList as JSON
        return measuredValuesDTO as JSON
//        return experiment.measuredValues as JSON
    }
}