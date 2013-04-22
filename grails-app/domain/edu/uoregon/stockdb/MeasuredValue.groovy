package edu.uoregon.stockdb

class MeasuredValue {

    static constraints = {
        name nullable: false
        value nullable: false
        type nullable: false
    }

    String name
    String value
    String units

    MeasuredValueTypeEnum type = MeasuredValueTypeEnum.STRING_TYPE // a possible string / float

    Experiment experiment

    String checkValid() {
        switch (type){
            case MeasuredValueTypeEnum.INTEGER_TYPE:
                try {
                    Integer.valueOf(value)
                    return null
                } catch (e) {
                    return "${value} is not a valid integer"
                }
                break
            case MeasuredValueTypeEnum.FLOAT_TYPE:
                try {
                    Float.valueOf(value)
                    return null
                } catch (e) {
                    return "${value} is not a valid float "
                }
                break
            default:
                return null
        }
    }
}
