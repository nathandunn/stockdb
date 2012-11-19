package edu.uoregon.stockdb

/**
 * The host animal of the strain
 */
class Origin {

    static constraints = {
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

    PropertyValue arbitraryData
}
