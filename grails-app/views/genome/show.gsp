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

        <g:if test="${genomeInstance?.url}">
            <li class="fieldcontain">
                <span id="url-label" class="property-label"><g:message code="genome.url.label" default="Url"/></span>

                <span class="property-value" aria-labelledby="url-label">
                    <g:link class="external-link" url="${genomeInstance.url}" target="_blank">
                        ${genomeInstance?.url?.startsWith("http://rast")?"Rast":""}
                        Sequence
                    </g:link>
                    %{--<g:fieldValue bean="${genomeInstance}" field="url"/>--}%
                </span>

            </li>
        </g:if>

        <g:if test="${genomeInstance?.note}">
            <li class="fieldcontain">
                <span id="note-label" class="property-label"><g:message code="genome.note.label" default="Note"/></span>

                <span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${genomeInstance}"
                                                                                        field="note"/></span>

            </li>
        </g:if>

        <g:if test="${genomeInstance?.quality}">
            <li class="fieldcontain">
                <span id="quality-label" class="property-label"><g:message code="genome.quality.label"
                                                                           default="Quality"/></span>

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

        <g:if test="${genomeInstance?.strains}">
            <li class="fieldcontain">
                <span id="strains-label" class="property-label"><g:message code="genome.strains.label"
                                                                           default="Strains"/></span>

                <g:each in="${genomeInstance.strains}" var="s">
                    <span class="property-value" aria-labelledby="strains-label"><g:link controller="strain"
                                                                                         action="show"
                                                                                         id="${s.id}">${s?.name}</g:link></span>
                </g:each>

            </li>
        </g:if>

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
