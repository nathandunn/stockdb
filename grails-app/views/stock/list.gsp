<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Stock" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-stock" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
<div class="nav" role="navigation">
    <ul>

        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>
    </shiro:hasRole>

<div id="list-stock" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            %{--<th><g:message code="stock.strain.label" default="Strain" /></th>--}%
            <g:sortableColumn property="boxNumber"
                              title="${message(code: 'stock.boxNumber.label', default: 'Box Number')}"/>
            <g:sortableColumn property="boxIndex"
                              title="${message(code: 'stock.boxIndex.label', default: 'Box Index')}"/>

            %{--<th><g:message code="stock.generalLocation.label" default="General Location" /></th>--}%
            <g:sortableColumn property="generalLocation.name"
                              title="${message(code: 'stock.generalLocation.label', default: 'General Location')}"/>

            <g:sortableColumn property="strain.name" title="${message(code: 'stock.strain.label', default: 'Strain')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${stockInstanceList}" status="i" var="stockInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                %{--<td><g:link action="show" id="${stockInstance.id}">${fieldValue(bean: stockInstance, field: "strain")}</g:link></td>--}%
                <td>
                    <g:link action="show" id="${stockInstance.id}">
                        ${stockInstance.boxNumber}
                    </g:link>
                </td>
                <td>
                    <g:link action="show" id="${stockInstance.id}">
                        ${stockInstance.boxIndex}
                    </g:link>
                </td>

                %{--<td>${fieldValue(bean: stockInstance, field: "generalLocation")}</td>--}%
                <td>
                    <g:link action="show" controller="location" id="${stockInstance.generalLocation?.id}">
                        ${stockInstance.generalLocation?.name}
                    </g:link>
                </td>

                <td>
                    <g:link action="show" controller="strain"
                            id="${stockInstance.strain?.id}">${stockInstance.strain?.name}</g:link>
                </td>



                %{--<td>${fieldValue(bean: stockInstance, field: "physicalLocation")}</td>--}%

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${stockInstanceTotal}"/>
    </div>
</div>
</body>
</html>
