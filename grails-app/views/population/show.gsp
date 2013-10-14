<%@ page import="edu.uoregon.stockdb.Population" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'population.label', default: 'Population')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-population" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-population" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list population">

        <g:if test="${populationInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="population.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${populationInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${populationInstance?.captureDate}">
            <li class="fieldcontain">
                <span id="captureDate-label" class="property-label"><g:message code="population.captureDate.label"
                                                                               default="Capture Date"/></span>

                <span class="property-value" aria-labelledby="captureDate-label"><g:formatDate
                        date="${populationInstance?.captureDate}"/></span>

            </li>
        </g:if>

        <g:if test="${populationInstance?.externalId}">
            <li class="fieldcontain">
                <span id="externalId-label" class="property-label"><g:message code="population.externalId.label"
                                                                              default="External Id"/></span>

                <span class="property-value" aria-labelledby="externalId-label"><g:fieldValue
                        bean="${populationInstance}" field="externalId"/></span>

            </li>
        </g:if>

        <g:if test="${populationInstance?.hostOrigins}">
            <li class="fieldcontain">
                <span id="hostOrigins-label" class="property-label"><g:message code="population.hostOrigins.label"
                                                                               default="Host Origins"/></span>

                <g:each in="${populationInstance.hostOrigins}" var="h">
                    <span class="property-value" aria-labelledby="hostOrigins-label"><g:link controller="hostOrigin"
                                                                                             action="show"
                                                                                             id="${h.id}">${h?.display}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${populationInstance?.latitude}">
            <li class="fieldcontain">
                <span id="latitude-label" class="property-label"><g:message code="population.latitude.label"
                                                                            default="Latitude"/></span>

                <span class="property-value" aria-labelledby="latitude-label"><g:fieldValue bean="${populationInstance}"
                                                                                            field="latitude"/></span>

            </li>
        </g:if>

        <g:if test="${populationInstance?.longitude}">
            <li class="fieldcontain">
                <span id="longitude-label" class="property-label"><g:message code="population.longitude.label"
                                                                             default="Longitude"/></span>

                <span class="property-value" aria-labelledby="longitude-label"><g:fieldValue
                        bean="${populationInstance}" field="longitude"/></span>

            </li>
        </g:if>

        <g:if test="${populationInstance?.wildtype}">
            <li class="fieldcontain">
                <span id="wildtype-label" class="property-label"><g:message code="population.wildtype.label"
                                                                              default="Is Wildtype"/></span>

                <span class="property-value" aria-labelledby="wildtype-label">
                    <g:fieldValue bean="${populationInstance}" field="wildtype"/>
                    ${populationInstance.wildtype ? "Wildtype":"Lab Raised"}
                </span>

            </li>
        </g:if>

        <g:if test="${populationInstance?.notes}">
            <li class="fieldcontain">
                <span id="notes-label" class="property-label"><g:message code="population.notes.label"
                                                                         default="Notes"/></span>

                <span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${populationInstance}"
                                                                                         field="notes"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${populationInstance?.id}"/>
            <g:link class="edit" action="edit" id="${populationInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
