package edu.uoregon.stockdb

/**
 * The Host animal of the strain
 * TODO: age species (<genotype>) anatomy
 */
class HostOrigin {

    static constraints = {
        stage nullable: true
        anatomy nullable: true
        hostFacility nullable: true
    }

    static hasMany = [
            phenotypes: Phenotype,
            strains: Strain
    ]

    Genus genus
//    Phylum phylum
    HostFacility hostFacility

    ZebrafishGenotype genotype

    String stage // stage . . . typically larval . . zfin correlate
//    Long days //
    String anatomy  // zfin anatomy
    String anatomyUrl  // zfin anatomy

//    String name
    String notes


    Integer getDpf(){
        int index = stage?.indexOf("dfp")
        if(index>0){
            return Integer.parseInt(stage.substring(0,index-1))
        }
        return -1
    }
}
