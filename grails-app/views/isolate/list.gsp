
<%@ page import="edu.uoregon.stockdb.Isolate" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'isolate.label', default: 'Isolate')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-isolate" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-isolate" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="isolate.isolatedBy.label" default="Isolated By" /></th>
					
						<g:sortableColumn property="isolatedWhen" title="${message(code: 'isolate.isolatedWhen.label', default: 'Isolated When')}" />
					
						<g:sortableColumn property="media" title="${message(code: 'isolate.media.label', default: 'Media')}" />
					
						<g:sortableColumn property="notes" title="${message(code: 'isolate.notes.label', default: 'Notes')}" />
					
						<g:sortableColumn property="oxygenCondition" title="${message(code: 'isolate.oxygenCondition.label', default: 'Oxygen Condition')}" />
					
						<th><g:message code="isolate.researcher.label" default="Researcher" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${isolateInstanceList}" status="i" var="isolateInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${isolateInstance.id}">${fieldValue(bean: isolateInstance, field: "isolatedBy")}</g:link></td>
					
						<td><g:formatDate date="${isolateInstance.isolatedWhen}" /></td>
					
						<td>${fieldValue(bean: isolateInstance, field: "media")}</td>
					
						<td>${fieldValue(bean: isolateInstance, field: "notes")}</td>
					
						<td>${fieldValue(bean: isolateInstance, field: "oxygenCondition")}</td>
					
						<td>${fieldValue(bean: isolateInstance, field: "researcher")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${isolateInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
