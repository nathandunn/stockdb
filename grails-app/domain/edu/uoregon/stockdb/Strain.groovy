package edu.uoregon.stockdb

class Strain {

    static constraints = {
//        values nullable: true
        formerCloneAlias nullable: true
    }

    static hasMany = [
            stocks: Stock
            ,experiments: Experiment
    ]

    static belongsTo = [
            Experiment
    ]

    // will be one or the other .. . .
    Strain parentStrain
    // more like a capture
    HostOrigin origin
    Date dateEntered

//    HostFacility hostFacility
    StrainGenotype strainGenotype

    Genome genome

//    // JSON
//    String values


    String name
    String formerCloneAlias


    // properties
    String motility
    String notes

    Genus genus
    Phylum phylum

//    Experiment isolation
    Isolate isolate
}
