package edu.uoregon.stockdb

class Strain {

    static constraints = {
//        values nullable: true
        formerCloneAlias nullable: true
        index nullable: false, unique: true
    }

    static hasMany = [
            stocks: Stock
            ,experiments: Experiment
    ]

    static belongsTo = [
            Experiment
    ]

    String index

    Genus genus
//    Phylum phylum // implied by the genus
    HostOrigin hostOrigin

    // will be one or the other .. . .
    Strain parentStrain
    // more like a capture
    Date dateEntered

//    HostFacility hostFacility
    StrainGenotype strainGenotype

    Genome genome

    String formerCloneAlias

    Isolate isolate

    String notes

}
