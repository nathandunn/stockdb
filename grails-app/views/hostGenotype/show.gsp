<%@ page import="edu.uoregon.stockdb.HostGenotype" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'hostGenotype.label', default: 'HostGenotype')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-hostGenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                   default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>

        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-hostGenotype" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
    </g:if>
    <ol class="property-list hostGenotype">

        <g:if test="${hostGenotypeInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label" class="property-label"><g:message code="hostGenotype.name.label"
                                                                        default="Name"/></span>

                <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${hostGenotypeInstance}"
                                                                                        field="name"/></span>

            </li>
        </g:if>

        <g:if test="${hostGenotypeInstance?.zfinId}">
            <li class="fieldcontain">
                <span id="zfinId-label" class="property-label"><g:message code="hostGenotype.zfinId.label"
                                                                          default="Zfin Id"/></span>

                <span class="property-value" aria-labelledby="zfinId-label"><g:fieldValue bean="${hostGenotypeInstance}"
                                                                                          field="zfinId"/></span>

            </li>
        </g:if>

        <g:if test="${hostOrigins}">
            <li class="fieldcontain">
                <span id="zfinId-label" class="property-label"><g:message code="hostGenotype.zfinId.label"
                                                                          default="Origins"/></span>

                <span class="property-value" aria-labelledby="zfinId-label">
                    <g:each in="${hostOrigins}" var="hostOrigin">
                        <table >
                            <g:link controller="hostOrigin" action="show"
                                    id="${hostOrigin.key.id}">${hostOrigin.key.display}</g:link>
                            <g:each in="${hostOrigin.value}" var="strain">
                                <tr class="small-table">
                                    <td class="small-table">
                                        <g:link controller="strain" action="show" id="${strain.id}">
                                            ${strain.name}
                                        </g:link>
                                    </td>
                                </tr>
                            </g:each>
                        </table>
                    </g:each>
                </span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${hostGenotypeInstance?.id}"/>
            <g:link class="edit" action="edit" id="${hostGenotypeInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
