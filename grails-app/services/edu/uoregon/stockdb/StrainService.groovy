package edu.uoregon.stockdb

class StrainService {




    String createStrainName(Species species,HostFacility hostFacility) {
//        Strain maxStrain = Strain.executeQuery("select s from Strain s where s.name like :strain order by s.name desc ", [strain: "ZOR%", max: 1]).get(0)
        String prefix = species.prefix + hostFacility.prefix
        List<Strain> strainList = Strain.createCriteria().list{
            like("name","${prefix}%")
            order("name","desc")
            maxResults(1)
        }
        Strain maxStrain = strainList ? strainList.get(0) : null
        String maxStrainName = maxStrain?.name?.substring(3)
        Integer maxInteger = Integer.parseInt(maxStrainName)
        ++maxInteger

//        String returnString = "ZOR" + String.pa(maxInteger+1).

        String returnString = prefix + maxInteger.toString().padLeft(4,"0")

        return returnString
    }
}
