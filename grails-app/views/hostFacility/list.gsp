<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.HostFacility" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'hostFacility.label', default: 'HostFacility')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-hostFacility" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                   default="Skip to content&hellip;"/></a>
<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
    <div class="nav" role="navigation">
        <ul>

            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </ul>
    </div>
</shiro:hasRole>
<div id="list-hostFacility" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'hostFacility.name.label', default: 'Name')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${hostFacilityInstanceList}" status="i" var="hostFacilityInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${hostFacilityInstance.id}">${fieldValue(bean: hostFacilityInstance, field: "name")}</g:link></td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${hostFacilityInstanceTotal}"/>
    </div>
</div>
</body>
</html>
