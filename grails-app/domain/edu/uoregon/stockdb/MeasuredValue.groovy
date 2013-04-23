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

    MeasuredValueTypeEnum type = MeasuredValueTypeEnum.TEXT// a possible string / float

    Experiment experiment
    Category category
    Strain strain

    String checkValid() {
        switch (type){
            case MeasuredValueTypeEnum.INTEGER:
                try {
                    Integer.valueOf(value)
                    return null
                } catch (e) {
                    return "${value} is not a valid integer"
                }
                break
            case MeasuredValueTypeEnum.DECIMAL:
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
