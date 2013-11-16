<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.HostOrigin" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'hostOrigin.label', default: 'HostOrigin')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-hostOrigin" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>

        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </shiro:hasRole>
    </ul>
</div>

<div id="show-hostOrigin" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list hostOrigin">

        <li class="fieldcontain">
            <span id="stage-label" class="property-label"><g:message code="hostOrigin.stage.label"
                                                                     default="Stage"/></span>

            <span class="property-value" aria-labelledby="stage-label">
                ${hostOriginInstance.renderStageAndDpf}
                %{--${hostOriginInstance.stage!='null' ? hostOriginInstance.stage: ""}--}%
                %{--<g:fieldValue bean="${hostOriginInstance}" field="stage"/>--}%
                %{--<g:if test="${!hostOriginInstance?.stage?.contains("dpf") && hostOriginInstance.stage}">--}%
                    %{--(${hostOriginInstance.daysPastFertilization} DPF)--}%
                %{--</g:if>--}%
            </span>

        </li>


        <g:if test="${hostOriginInstance?.anatomy}">
            <li class="fieldcontain">
                <span id="anatomy-label" class="property-label"><g:message code="hostOrigin.anatomy.label"
                                                                           default="Anatomy"/></span>

                <g:if test="${hostOriginInstance.anatomyUrl}">
                    <span class="property-value" aria-labelledby="anatomy-label">
                        <g:link url="${hostOriginInstance.anatomyUrl}">${hostOriginInstance.anatomy}</g:link>
                    </span>
                </g:if>
                <g:else>
                    <span class="property-value" aria-labelledby="anatomy-label"><g:fieldValue
                            bean="${hostOriginInstance}" field="anatomy"/></span>
                </g:else>

            </li>
        </g:if>

        <g:if test="${hostOriginInstance?.hostFacility}">
            <li class="fieldcontain">
                <span id="hostFacility-label" class="property-label"><g:message code="hostOrigin.hostFacility.label"
                                                                                default="Host Facility"/></span>

                <span class="property-value" aria-labelledby="hostFacility-label"><g:link controller="hostFacility"
                                                                                          action="show"
                                                                                          id="${hostOriginInstance?.hostFacility?.id}">${hostOriginInstance?.hostFacility?.name}</g:link></span>

            </li>
        </g:if>

        <g:if test="${hostOriginInstance?.genotypes}">
            <li class="fieldcontain">
                <span id="genotype-label" class="property-label"><g:message code="hostOrigin.genotype.label"
                                                                            default="Genotype"/></span>

                <g:each var="genotype" in="${hostOriginInstance.genotypes}">
                    <span class="property-value" aria-labelledby="genotype-label">
                        <g:link controller="hostGenotype" action="show"
                                id="${genotype?.id}">${genotype?.name}</g:link>
                    </span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${hostOriginInstance?.population}">
            <li class="fieldcontain">
                <span id="population-label" class="property-label"><g:message code="hostOrigin.population.label"
                                                                                default="Population"/></span>

                <span class="property-value" aria-labelledby="population-label"><g:link controller="population"
                                                                                          action="show"
                                                                                          id="${hostOriginInstance?.population?.id}">${hostOriginInstance?.population?.name}</g:link></span>

            </li>
        </g:if>

        <g:if test="${hostOriginInstance?.species}">
            <li class="fieldcontain">
                <span id="species-label" class="property-label"><g:message code="hostOrigin.species.label"
                                                                           default="Species"/></span>

                <span class="property-value" aria-labelledby="species-label"><g:link controller="species" action="show"
                                                                                     id="${hostOriginInstance?.species?.id}">${hostOriginInstance?.species?.commonName} <i>(${hostOriginInstance?.species?.displayName})</i></g:link>
                </span>

            </li>
        </g:if>


        <g:if test="${hostOriginInstance?.strains}">
            <li class="fieldcontain">
                <span id="strains-label" class="property-label"><g:message code="hostOrigin.strains.label"
                                                                           default="Strains"/></span>

                <g:each in="${hostOriginInstance.strains}" var="s">
                    <span class="property-value" aria-labelledby="strains-label">
                        <g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link>
                    </span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${hostOriginInstance?.notes}">
            <li class="fieldcontain">
                <span id="notes-label" class="property-label"><g:message code="hostOrigin.notes.label"
                                                                         default="Notes"/></span>

                <span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${hostOriginInstance}"
                                                                                         field="notes"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${hostOriginInstance?.id}"/>
            <g:link class="edit" action="edit" id="${hostOriginInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
