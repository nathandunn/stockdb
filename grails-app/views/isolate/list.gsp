<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.IsolateCondition" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'isolate.label', default: 'Isolate')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-isolate" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
<div class="nav" role="navigation">
    <ul>

        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>
    </shiro:hasRole>

<div id="list-isolate" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="media" title="${message(code: 'isolate.media.label', default: 'Media')}"/>

            <g:sortableColumn property="oxygenCondition"
                              title="${message(code: 'isolate.oxygenCondition.label', default: 'Oxygen Condition')}"/>
            <g:sortableColumn property="temperature"
                              title="${message(code: 'isolate.temperature.label', default: 'Temperature (C)')}"/>

            %{--<th><g:message code="isolate.isolatedBy.label" default="Isolated By" /></th>--}%

            <g:sortableColumn property="isolatedWhen"
                              title="${message(code: 'isolate.isolatedWhen.label', default: 'When')}"/>

            <g:sortableColumn property="isolatedBy"
                              title="${message(code: 'isolate.isolatedBy.label', default: 'By')}"/>

            %{--<g:sortableColumn property="id" title="ID"/>--}%
            <th>

            </th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${isolateInstanceList}" status="i" var="isolateInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


                <td>${fieldValue(bean: isolateInstance, field: "media")}</td>

                <td>${fieldValue(bean: isolateInstance, field: "oxygenCondition")}</td>
                <td>${fieldValue(bean: isolateInstance, field: "temperature")}</td>

                <td><g:formatDate date="${isolateInstance.isolatedWhen}" type="date" style="MEDIUM"/></td>
                <td>
                    <g:link action="show" id="${isolateInstance?.isolatedBy?.id}" controller="researcher">
                        ${isolateInstance?.isolatedBy?.fullName}
                    </g:link>
                </td>

                <td>
                    <g:link action="show" id="${isolateInstance.id}">
                        %{--${fieldValue(bean: isolateInstance, field: "id")}--}%
                        Details
                    </g:link>
                </td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${isolateInstanceTotal}"/>
    </div>
</div>
</body>
</html>
