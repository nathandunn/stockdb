
<%@ page import="edu.uoregon.stockdb.Species" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'species.label', default: 'Species')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-species" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-species" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="species.genus.label" default="Genus" /></th>
					
						<g:sortableColumn property="name" title="${message(code: 'species.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="commonName" title="${message(code: 'species.commonName.label', default: 'Common Name')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${speciesInstanceList}" status="i" var="speciesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${speciesInstance.id}">${fieldValue(bean: speciesInstance, field: "genus")}</g:link></td>
					
						<td>${fieldValue(bean: speciesInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: speciesInstance, field: "commonName")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${speciesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
