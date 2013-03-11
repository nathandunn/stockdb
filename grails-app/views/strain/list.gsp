<%@ page import="edu.uoregon.stockdb.Strain" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-strain" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-strain" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="index" title="${message(code: 'strain.index.label', default: 'Index')}"/>

            <th>Genus / Phylum</th>

            <th>Host</th>
            %{--<th>Host Anatomy</th>--}%
            <g:sortableColumn property="hostOrigin.anatomy"
                              title="${message(code: 'hostOrigin.anatomy.label', default: 'Host Anatomy')}"/>

            <g:sortableColumn property="hostOrigin.stage"
                              title="${message(code: 'hostOrigin.stage.label', default: 'Host Stage')}"/>
            <g:sortableColumn property="formerCloneAlias"
                              title="${message(code: 'strain.formerCloneAlias.label', default: 'Former Clone Alias')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${strainInstanceList}" status="i" var="strainInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                %{--<td>${fieldValue(bean: strainInstance, field: "index")}</td>--}%
                <td>
                    <g:showId instance="${strainInstance}" label="${strainInstance.index}"/>
                </td>

                <td><i>${strainInstance.genus.displayName}</i></td>

                <td>${strainInstance?.hostOrigin?.genus?.displayName}</td>

                <td>
                    <g:if test="${strainInstance?.hostOrigin?.anatomy}">
                        <a href="${strainInstance?.hostOrigin?.anatomyUrl}">${strainInstance?.hostOrigin?.anatomy}</a>
                    </g:if>
                </td>

                <td>
                    <g:if test="${strainInstance?.hostOrigin?.stage}">
                        <a href='http://zfin.org/zf_info/zfbook/stages/index.html'>${strainInstance.hostOrigin.stage}</a>
                    </g:if>
                </td>


                <td>${strainInstance.formerCloneAlias}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${strainInstanceTotal}"/>
    </div>
</div>
</body>
</html>
