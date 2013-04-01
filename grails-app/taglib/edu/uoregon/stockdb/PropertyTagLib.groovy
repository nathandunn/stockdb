package edu.uoregon.stockdb

class PropertyTagLib {

    def grailsLinkGenerator

    def showId = { attrs, body ->
        if (attrs.instance) {
            attrs.id = attrs.instance.id
            String className = attrs.instance.getClass()
            attrs.controller = grailsApplication.getControllerClass(className + "Controller")
        }
        def id = attrs.id
        def controller = attrs.controller
        out << "<a href=\"" + grailsLinkGenerator.link([absolute: false, action: "show", id: id, controller: controller]) + "\">"
        out << attrs.label ?: formatNumber([number: id])
        out << "</a>"
    }

}
