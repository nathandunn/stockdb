<%@ page import="edu.uoregon.stockdb.Lab" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'lab.label', default: 'Lab')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-lab" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                          default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>

        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-lab" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>
            <g:sortableColumn property="name" title="${message(code: 'lab.name.label', default: 'Name')}"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${labInstanceList}" status="i" var="labInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>
            <g:link action="show" id="${labInstance.id}">${labInstance.name}</g:link>
            </td>
        </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${labInstanceTotal}"/>
    </div>
</div>
</body>
</html>
