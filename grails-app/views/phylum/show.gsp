<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Phylum" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'phylum.label', default: 'Phylum')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-phylum" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
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

<div id="show-phylum" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list phylum">

        <li class="fieldcontain">
            <span id="name-label" class="property-label"><g:message code="phylum.name.label" default="Name"/></span>

            <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${phylumInstance}"
                                                                                    field="name"/></span>
        </li>

        <g:if test="${genuses}">
            <li class="fieldcontain">
                <span id="genuses-label" class="property-label"><g:message code="phylum.genuses.label"
                                                                           default="Genuses"/></span>

                <g:each in="${genuses}" var="g">
                    <span class="property-value" aria-labelledby="genuses-label">
                        <g:link controller="genus" action="show" id="${g.id}">${g?.name}</g:link>
                    </span>
                </g:each>

            </li>
        </g:if>


        <li class="fieldcontain">
            <span id="strains-label" class="property-label"><g:message code="strains.name.label"
                                                                       default="Strains"/></span>

            <span class="property-value" aria-labelledby="strains-label">
                <ul>
                    <g:each in="${strains}" var="strain">
                        <li>
                            <g:link action="show" controller="strain" id="${strain.id}">
                                ${strain.name}
                            </g:link>
                            &nbsp;
                            &nbsp;
                            <g:link action="show" controller="genus" id="${strain.genus.id}">
                                ${strain.genus.name}
                            </g:link>
                        </li>
                    </g:each>
                </ul>
                %{--<g:fieldValue bean="${phylumInstance}" field="name"/>--}%
            </span>

        </li>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${phylumInstance?.id}"/>
            <g:link class="edit" action="edit" id="${phylumInstance?.id}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
