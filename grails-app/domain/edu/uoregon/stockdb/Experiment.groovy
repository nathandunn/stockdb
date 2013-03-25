package edu.uoregon.stockdb

class Experiment {

    static constraints = {
        name nullable: false,unique: true
    }

    static hasMany = [
            strains:Strain
            ,measuredValues: MeasuredValue
    ]

    String name
    Researcher researcher
    Date whenPerformed
    String note

    List<String> getMeasuredValueNames(){
        def returnList = []
        measuredValues.each {
            returnList << it.name
        }
        return returnList
    }

    List<String> getValuesForName(String name){
        def returnList = []
        measuredValues.each {
            if(it.name == name){
                returnList << it.value
            }
        }
        return returnList
    }

    List<MeasuredValue> getMeasuredValuesForName(String name){
        def returnList = []
        measuredValues.each {
            if(it.name == name){
                returnList << it
            }
        }
        return returnList
    }

    Map<String, List<String>> createValuesMap() {
        Map<String,List<String>> values = new TreeMap<String,List<String>>()
        getMeasuredValueNames().each {
            List<String> v = getValuesForName(it)
            values.put(it,v)
        }
        return values
    }

    Map<String, List<MeasuredValue>> createMeasuredValuesMap() {
        Map<String,List<MeasuredValue>> values = new TreeMap<String,List<MeasuredValue>>()
        getMeasuredValueNames().each {
            List<MeasuredValue> v = getMeasuredValuesForName(it)
            values.put(it,v)
        }
        return values
    }

}
