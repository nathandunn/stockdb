package edu.uoregon.stockdb

/**
 * The host animal of the strain
 */
class HostOrigin {

    static constraints = {
        values nullable: true
        age nullable: true
        partOfFish nullable: true
        hostFacility nullable: true
    }

    static hasMany = [
            phenotypes: Phenotype,
            strains: Strain
    ]

    Genus genus
    Phylum phylum
    HostFacility hostFacility

    Long age
    String partOfFish
    String name

    // JSON
    String values
}
