package edu.uoregon.stockdb

import org.apache.shiro.SecurityUtils

class ResearcherService {

    public static String ROLE_ADMINISTRATOR = "Administrator"
    public static String ROLE_USER = "User"

    Researcher getCurrentUser(){
        String currentUserName = SecurityUtils?.subject?.principal
        if(currentUserName){
            return Researcher.findByUsername(currentUserName)
        }
        return null
    }
}
