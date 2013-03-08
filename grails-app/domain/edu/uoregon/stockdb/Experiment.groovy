package edu.uoregon.stockdb

class Experiment {

    static constraints = {
    }

    static hasMany = [
            researchers:Researcher
            ,strains:Strain
            ,phenotypes:Phenotype
    ]
}
