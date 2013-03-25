<%@ page import="edu.uoregon.stockdb.Phenotype" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'phenotype.label', default: 'Phenotype')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-phenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-phenotype" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'phenotype.name.label', default: 'Name')}"/>

            <g:sortableColumn property="url" title="${message(code: 'phenotype.url.label', default: 'Url')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${phenotypeInstanceList}" status="i" var="phenotypeInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${phenotypeInstance.id}">${fieldValue(bean: phenotypeInstance, field: "name")}</g:link></td>

                <td>
                    <g:link url="${phenotypeInstance.url}">
                        ${fieldValue(bean: phenotypeInstance, field: "url")}
                    </g:link>
                </td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${phenotypeInstanceTotal}"/>
    </div>
</div>
</body>
</html>
