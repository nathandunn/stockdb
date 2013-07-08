package edu.uoregon.stockdb

/**
 * The Host animal of the strain
 * TODO: age species (<genotype>) anatomy
 */
class HostOrigin {

    public static Integer UNKNOWN_STAGE = -3

    static constraints = {
        stage nullable: true
        anatomy nullable: true
        hostFacility nullable: false
        species nullable: false
        daysPastFertilization min: 1, max: 720
    }

    static hasMany = [
            strains: Strain
            , genotypes: HostGenotype
    ]

    static mapping = {
        strains sort: "name", order: "asc"
    }

    Species species
//    Phylum phylum
    HostFacility hostFacility

    String stage // stage . . . typically larval . . zfin correlate
    Integer daysPastFertilization//
    String anatomy  // zfin anatomy
    String anatomyUrl  // zfin anatomy

//    String name
    String notes

    Boolean hasStage(){
        return stage!=null && stage!="null"
    }

    String getDisplay() {
        String returnString = "${species?.commonName ?: ''} (${genotypes?.name ?: ''}) "
        if (daysPastFertilization >= 0) {
            returnString += daysPastFertilization + " DPF "
        } else {
            returnString += (stage ?: '') + " "
        }

        returnString += anatomy ?: ""
        return returnString

    }

    void setStageAndDpf(String stage) {
        if (!stage || stage?.length() == 0) {
            this.stage = null
            return
        }

        this.stage = stage

        int index = stage?.indexOf("dpf")
        if (!daysPastFertilization) {
            if (index > 0) {
                String stageSubString = stage.substring(0, index)
                daysPastFertilization = Integer.parseInt(stageSubString)
            } else if (stage == "Larval") {
                daysPastFertilization = 0
            } else if (stage.length() == 0) {
                daysPastFertilization = UNKNOWN_STAGE
            } else if (stage == "Adult") {
                daysPastFertilization = 90
            } else {
                daysPastFertilization = 360
            }
        }
    }

//    String getRenderStageAndDpf() {
//        String returnString = ""
//        if(hasStage()){
//            returnString += "${stage} (${daysPastFertilization} DPF)"
//        }
//        else{
//            returnString += "${daysPastFertilization} DPF"
//        }
//        return returnString
//    }

    String getRenderStageAndDpf() {
        String returnString = ""
        if(hasStage()){
            if(!stage.contains("dpf")){
                returnString += "${stage}"
                if(daysPastFertilization){
                    returnString += " (${daysPastFertilization} DPF)"
                }
            }
            else{
                returnString += " ${daysPastFertilization} DPF"
            }
        }
        else
        if(daysPastFertilization && daysPastFertilization >0 && daysPastFertilization<360)
        {
            returnString += "${daysPastFertilization} DPF"
        }
        else{
            returnString += "??"
        }
        return returnString
    }
}
