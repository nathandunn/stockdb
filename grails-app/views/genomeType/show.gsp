<%@ page import="edu.uoregon.stockdb.GenomeType" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'genomeType.label', default: 'GenomeType')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-genomeType" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-genomeType" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list genomeType">

        <g:if test="${genomeTypeInstance?.baseUrl}">
            <li class="fieldcontain">
                <span id="baseUrl-label" class="property-label"><g:message code="genomeType.baseUrl.label"
                                                                           default="Base Url"/></span>

                <span class="property-value" aria-labelledby="baseUrl-label"><g:fieldValue bean="${genomeTypeInstance}"
                                                                                           field="baseUrl"/></span>

            </li>
        </g:if>

        <g:if test="${genomeTypeInstance?.organizationName}">
            <li class="fieldcontain">
                <span id="organizationName-label" class="property-label"><g:message
                        code="genomeType.organizationName.label" default="Organization Name"/></span>

                <span class="property-value" aria-labelledby="organizationName-label"><g:fieldValue
                        bean="${genomeTypeInstance}" field="organizationName"/></span>

            </li>
        </g:if>

        <g:if test="${genomeTypeInstance?.genomes}">
            <li class="fieldcontain">
                <span id="genomes-label" class="property-label"><g:message code="genomeType.genomes.label"
                                                                           default="Genomes"/></span>

                <g:each in="${genomeTypeInstance.genomes.sort { a,b -> a.externalId.compareTo(b.externalId)}}" var="g">
                    <span class="property-value" aria-labelledby="genomes-label"><g:link controller="genome"
                                                                                         action="show"
                                                                                         id="${g.id}">${g?.externalId}</g:link></span>
                </g:each>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${genomeTypeInstance?.id}"/>
            <g:link class="edit" action="edit" id="${genomeTypeInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
