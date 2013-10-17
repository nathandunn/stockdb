
<%@ page import="edu.uoregon.stockdb.Population" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'population.label', default: 'Population')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-population" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-population" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'population.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="captureDate" title="${message(code: 'population.captureDate.label', default: 'Capture Date')}" />
					
						%{--<g:sortableColumn property="externalId" title="${message(code: 'population.externalId.label', default: 'External Id')}" />--}%
					
						<g:sortableColumn property="latitude" title="${message(code: 'population.latitude.label', default: 'Latitude')}" />
					
						<g:sortableColumn property="longitude" title="${message(code: 'population.longitude.label', default: 'Longitude')}" />
					
						%{--<g:sortableColumn property="notes" title="${message(code: 'population.notes.label', default: 'Notes')}" />--}%
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${populationInstanceList}" status="i" var="populationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${populationInstance.id}">${fieldValue(bean: populationInstance, field: "name")}</g:link></td>
					
						<td><g:formatDate date="${populationInstance.captureDate}" /></td>
					
						%{--<td>${fieldValue(bean: populationInstance, field: "externalId")}</td>--}%
					
						<td>${fieldValue(bean: populationInstance, field: "latitude")}</td>
					
						<td>${fieldValue(bean: populationInstance, field: "longitude")}</td>
					
						%{--<td>${fieldValue(bean: populationInstance, field: "notes")}</td>--}%
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${populationInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
