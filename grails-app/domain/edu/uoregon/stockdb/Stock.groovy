package edu.uoregon.stockdb

/**
 * This is the physical location of the Strain
 */
class Stock {

    static constraints = {
        strain nullable:  true
//        value nullable:  true
//        physicalLocation nullable: false
//        generalLocation nullable: false
        boxNumber nullable: false
        boxIndex nullable: false
    }

//    String physicalLocation

    Integer boxNumber
    Integer boxIndex
//    String value

    Location generalLocation

    Strain strain

    Integer getBoxValue(){
        return boxNumber  * 10000  + boxIndex
    }

    def getDisplay() {
//        String returnString =  (generalLocation?.name ?:"") + " "
        String returnString =  ""
        if(boxNumber && boxIndex){
            returnString += "Box "+ boxNumber + " - " + boxIndex
        }
        else{
            returnString += " ? "
        }
        return returnString
    }
}
