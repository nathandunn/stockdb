
<%@ page import="edu.uoregon.stockdb.GenomeType" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'genomeType.label', default: 'GenomeType')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-genomeType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-genomeType" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="organizationName" title="${message(code: 'genomeType.organizationName.label', default: 'Organization Name')}" />

                        <g:sortableColumn property="baseUrl" title="${message(code: 'genomeType.baseUrl.label', default: 'Base Url')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${genomeTypeInstanceList}" status="i" var="genomeTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>
                            <g:link action="show" id="${genomeTypeInstance.id}">${fieldValue(bean: genomeTypeInstance, field: "organizationName")}</g:link>
                        </td>

                        <td>
                            <g:link url="${genomeTypeInstance.baseUrl}">${fieldValue(bean: genomeTypeInstance, field: "baseUrl")}</g:link>
                        </td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${genomeTypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
