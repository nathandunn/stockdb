
<%@ page import="edu.uoregon.stockdb.HostOrigin" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'hostOrigin.label', default: 'HostOrigin')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-hostOrigin" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-hostOrigin" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="stage" title="${message(code: 'hostOrigin.stage.label', default: 'Stage')}" />
					
						<g:sortableColumn property="days" title="${message(code: 'hostOrigin.days.label', default: 'Days')}" />
					
						<g:sortableColumn property="anatomy" title="${message(code: 'hostOrigin.anatomy.label', default: 'Anatomy')}" />
					
						<th><g:message code="hostOrigin.hostFacility.label" default="Host Facility" /></th>
					
						<th><g:message code="hostOrigin.genotype.label" default="Genotype" /></th>
					
						<th><g:message code="hostOrigin.genus.label" default="Genus" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${hostOriginInstanceList}" status="i" var="hostOriginInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${hostOriginInstance.id}">${fieldValue(bean: hostOriginInstance, field: "stage")}</g:link></td>
					
						<td>${fieldValue(bean: hostOriginInstance, field: "days")}</td>
					
						<td>${fieldValue(bean: hostOriginInstance, field: "anatomy")}</td>
					
						<td>${fieldValue(bean: hostOriginInstance, field: "hostFacility")}</td>
					
						<td>${fieldValue(bean: hostOriginInstance, field: "genotype")}</td>
					
						<td>${fieldValue(bean: hostOriginInstance, field: "genus")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${hostOriginInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
