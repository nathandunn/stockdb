package edu.uoregon.stockdb

class MeasuredValue {

    static constraints = {
        name nullable: false
        value nullable: false
    }

    String name
    String value
    String units

    MeasuredValueTypeEnum type = MeasuredValueTypeEnum.STRING_TYPE // a possible phenotype or string / float
    Phenotype phenotype // if exists

    Experiment experiment
}
