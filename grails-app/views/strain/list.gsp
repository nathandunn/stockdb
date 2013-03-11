
<%@ page import="edu.uoregon.stockdb.Strain" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-strain" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-strain" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="formerCloneAlias" title="${message(code: 'strain.formerCloneAlias.label', default: 'Former Clone Alias')}" />
					
						<g:sortableColumn property="dateEntered" title="${message(code: 'strain.dateEntered.label', default: 'Date Entered')}" />
					
						<th><g:message code="strain.genome.label" default="Genome" /></th>
					
						<th><g:message code="strain.genus.label" default="Genus" /></th>
					
						<th><g:message code="strain.isolate.label" default="Isolate" /></th>
					
						<g:sortableColumn property="motility" title="${message(code: 'strain.motility.label', default: 'Motility')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${strainInstanceList}" status="i" var="strainInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${strainInstance.id}">${fieldValue(bean: strainInstance, field: "formerCloneAlias")}</g:link></td>
					
						<td><g:formatDate date="${strainInstance.dateEntered}" /></td>
					
						<td>${fieldValue(bean: strainInstance, field: "genome")}</td>
					
						<td>${fieldValue(bean: strainInstance, field: "genus")}</td>
					
						<td>${fieldValue(bean: strainInstance, field: "isolate")}</td>
					
						<td>${fieldValue(bean: strainInstance, field: "motility")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${strainInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
