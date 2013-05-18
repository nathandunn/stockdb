package edu.uoregon.stockdb

import edu.uoregon.stockdb.client.GetExperimentAction
import edu.uoregon.stockdb.client.GetExperimentResponse

class ExperimentService {

    static expose = ['gwt:edu.uoregon.stockdb.client']

    // TODO: get this service to work!
//    GetExperimentResponse execute(GetExperimentAction arg0){
//        GetExperimentResponse getExperimentResponse = new GetExperimentResponse();
//
//        return getExperimentResponse;
//    }

    Map<Category, List<MeasuredValue>> createValuesMap(Experiment experiment) {
        TreeMap<Category,List<MeasuredValue>> values = new TreeMap<Category,List<MeasuredValue>>()

        List<MeasuredValue> sortedValues = experiment.measuredValues.sort(){
            it.strain.name
        }

        for(MeasuredValue measuredValue in sortedValues){
            if(values.containsKey(measuredValue.category)){
                List<MeasuredValue> measuredValues = values.get(measuredValue.category)
                measuredValues.add(measuredValue)
                values.put(measuredValue.category,measuredValues)
            }
            else{
                values.put(measuredValue.category,[measuredValue])
            }
        }

        return values
    }

    Map<String, List<MeasuredValue>> createMeasuredValuesMap(Experiment experiment) {
        Map<String,List<MeasuredValue>> values = new TreeMap<String,List<MeasuredValue>>()
        getMeasuredValueNames(experiment).each {
            List<MeasuredValue> v = getMeasuredValuesForName(it)
            values.put(it,v)
        }
        return values
    }

    List<String> getMeasuredValueNames(Experiment experiment){
        def returnList = []
        experiment.measuredValues.each {
            returnList << it.category.name
        }
        return returnList
    }

//    List<String> getValuesForName(String name){
//        def returnList = []
//        measuredValues.each {
//            if(it.name == name){
//                returnList << it.value
//            }
//        }
//        return returnList
//    }

    List<MeasuredValue> getMeasuredValuesForName(Experiment experiment,String name){
        def returnList = []
        experiment.measuredValues.each {
            if(it.name == name){
                returnList << it
            }
        }
        return returnList
    }
}
