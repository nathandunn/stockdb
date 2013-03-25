<%@ page import="edu.uoregon.stockdb.MeasuredValue" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'measuredValue.label', default: 'MeasuredValue')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-measuredValue" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                    default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-measuredValue" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'measuredValue.name.label', default: 'Name')}"/>

            <g:sortableColumn property="value" title="${message(code: 'measuredValue.value.label', default: 'Value')}"/>

            <th><g:message code="measuredValue.experiment.label" default="Experiment"/></th>

            <th><g:message code="measuredValue.phenotype.label" default="Phenotype"/></th>

            <g:sortableColumn property="type" title="${message(code: 'measuredValue.type.label', default: 'Type')}"/>

            <g:sortableColumn property="units" title="${message(code: 'measuredValue.units.label', default: 'Units')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${measuredValueInstanceList}" status="i" var="measuredValueInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${measuredValueInstance.id}">${fieldValue(bean: measuredValueInstance, field: "name")}</g:link></td>

                <td>${fieldValue(bean: measuredValueInstance, field: "value")}</td>

                <td>
                    <g:link action="show" controller="experiment"
                            id="${measuredValueInstance?.experiment?.id}">${measuredValueInstance?.experiment?.name}</g:link>
                </td>

                %{--<td>${fieldValue(bean: measuredValueInstance, field: "phenotype")}</td>--}%
                <td>
                    <g:link action="show" controller="phenotype"
                            id="${measuredValueInstance?.phenotype?.id}">${measuredValueInstance?.phenotype?.name}</g:link>
                </td>

                <td>${fieldValue(bean: measuredValueInstance, field: "type")}</td>

                <td>${fieldValue(bean: measuredValueInstance, field: "units")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${measuredValueInstanceTotal}"/>
    </div>
</div>
</body>
</html>
