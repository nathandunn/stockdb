package edu.uoregon.stockdb

class Strain {

    static constraints = {
        genoUrl nullable: true, url: true
        values nullable: true
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
    Origin origin

    HostFacility hostFacility
    StrainGenotype strainGenotype


    String genoUrl
    Integer genomeSize
    Integer genomeQuality
    Integer genomeNote

    // JSON
    String values


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
