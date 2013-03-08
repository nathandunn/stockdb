package edu.uoregon.stockdb

/**
 * The Host animal of the strain
 * TODO: age species (<genotype>) anatomy
 */
class Origin {

    static constraints = {
        stage nullable: true
        days nullable: true
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

    ZebrafishGenotype genotype
    String zfinGenotype

    String stage // stage . . . typically larval . . zfin correlate
    Long days //
    String partOfFish  // zfin anatomy

//    String name
    String notes
}
