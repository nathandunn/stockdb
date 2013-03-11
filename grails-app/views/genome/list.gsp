
<%@ page import="edu.uoregon.stockdb.Genome" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'genome.label', default: 'Genome')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-genome" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-genome" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="url" title="${message(code: 'genome.url.label', default: 'Url')}" />
					
						<g:sortableColumn property="note" title="${message(code: 'genome.note.label', default: 'Note')}" />
					
						<g:sortableColumn property="quality" title="${message(code: 'genome.quality.label', default: 'Quality')}" />
					
						<g:sortableColumn property="size" title="${message(code: 'genome.size.label', default: 'Size')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${genomeInstanceList}" status="i" var="genomeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${genomeInstance.id}">${fieldValue(bean: genomeInstance, field: "url")}</g:link></td>
					
						<td>${fieldValue(bean: genomeInstance, field: "note")}</td>
					
						<td>${fieldValue(bean: genomeInstance, field: "quality")}</td>
					
						<td>${fieldValue(bean: genomeInstance, field: "size")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${genomeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
