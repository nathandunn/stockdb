<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Genome" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'genome.label', default: 'Genome')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-genome" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
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

<div id="show-genome" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list genome">

        <li class="fieldcontain">
            <span id="url-label" class="property-label"><g:message code="genome.url.label" default="Url"/></span>

            <span class="property-value" aria-labelledby="url-label">
                <g:link class="external-link" url="${genomeInstance.renderUrl()}" target="_blank">
                    ${genomeInstance.display}
                %{--Sequence--}%
                </g:link>
                %{--<g:fieldValue bean="${genomeInstance}" field="url"/>--}%
            </span>

        </li>

        <g:if test="${genomeInstance?.externalId}">
            <li class="fieldcontain">
                <span id="externalId-label" class="property-label"><g:message code="genome.externalId.label"
                                                                           default="External Id"/></span>

                <span class="property-value" aria-labelledby="externalId-label"><g:fieldValue bean="${genomeInstance}"
                                                                                           field="externalId"/></span>

            </li>
        </g:if>

        <g:if test="${genomeInstance?.quality}">
            <li class="fieldcontain">
                <span id="quality-label" class="property-label"><g:message code="genome.quality.label"
                                                                           default="Contigs"/></span>

                <span class="property-value" aria-labelledby="quality-label"><g:fieldValue bean="${genomeInstance}"
                                                                                           field="quality"/></span>

            </li>
        </g:if>

        <g:if test="${genomeInstance?.size}">
            <li class="fieldcontain">
                <span id="size-label" class="property-label"><g:message code="genome.size.label" default="Size"/></span>

                <span class="property-value" aria-labelledby="size-label">
                    %{--<g:fieldValue bean="${genomeInstance}" field="size"/>--}%
                    <g:formatNumber number="${genomeInstance.size}"/>
                </span>

            </li>
        </g:if>

        <g:if test="${genomeInstance?.genomeVersion}">
            <li class="fieldcontain">
                <span id="genomeVersion-label" class="property-label"><g:message code="genome.genomeVersion.label"
                                                                                 default="Genome Versin"/></span>

                <span class="property-value" aria-labelledby="genomeVersion-label">
                    %{--<g:fieldValue bean="${genomeInstance}" field="genomeVersion"/>--}%
                    %{--<g:formatNumber number="${genomeInstance.genomeVersion}" />--}%
                    ${genomeInstance.genomeVersion}
                </span>

            </li>
        </g:if>

        <li class="fieldcontain">
            <span id="strains-label" class="property-label"><g:message code="genome.strains.label"
                                                                       default="Strains"/></span>

                <span class="property-value" aria-labelledby="strains-label">
                    <g:link controller="strain" action="show" id="${genomeInstance.strain.id}">${genomeInstance.strain.name}</g:link>
                    %{--${genomeInstance.strain}--}%
                </span>

        </li>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${genomeInstance?.id}"/>
            <g:link class="edit" action="edit" id="${genomeInstance?.id}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
