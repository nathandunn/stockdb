<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.StrainGenotype" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'strainGenotype.label', default: 'StrainGenotype')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-strainGenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                     default="Skip to content&hellip;"/></a>
<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
    <div class="nav" role="navigation">
        <ul>

            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </ul>
    </div>
</shiro:hasRole>
<div id="list-strainGenotype" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'strainGenotype.name.label', default: 'Name')}"/>

            <g:sortableColumn property="note" title="${message(code: 'strainGenotype.note.label', default: 'Note')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${strainGenotypeInstanceList}" status="i" var="strainGenotypeInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${strainGenotypeInstance.id}">${fieldValue(bean: strainGenotypeInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: strainGenotypeInstance, field: "note")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${strainGenotypeInstanceTotal}"/>
    </div>
</div>
</body>
</html>
