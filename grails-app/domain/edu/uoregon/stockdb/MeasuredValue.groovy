package edu.uoregon.stockdb

class MeasuredValue {

    static constraints = {
    }

    String name
    String value

    String type // a posibble phenoetype
    Phenotype phenotype // if exists

    Experiment experiment
}
