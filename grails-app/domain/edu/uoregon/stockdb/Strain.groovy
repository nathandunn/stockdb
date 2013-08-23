package edu.uoregon.stockdb

class Strain {

    static constraints = {
//        values nullable: true
        formerCloneAlias nullable: true
        name nullable: false, unique: true,blank: false
    }

    static hasMany = [
            stocks: Stock
            ,measuredValues: MeasuredValue
            ,genomes:Genome
    ]


    static mapping = {
        notes type: "text"
    }
//    static mapping = {
//        measuredValues sort: 'value', order:'asc'
//    }

    String name

    Genus genus
//    Phylum phylum // implied by the genus
    HostOrigin hostOrigin

    // will be one or the other .. . .
    Strain parentStrain
    // more like a capture
    Date dateEntered

//    HostFacility hostFacility
    StrainGenotype strainGenotype

    String formerCloneAlias

    IsolateCondition isolateCondition

    String notes


}
