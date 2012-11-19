
<%@ page import="edu.uoregon.stockdb.Origin" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'origin.label', default: 'Origin')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-origin" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-origin" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="age" title="${message(code: 'origin.age.label', default: 'Age')}" />
					
						<g:sortableColumn property="arbitraryData" title="${message(code: 'origin.arbitraryData.label', default: 'Arbitrary Data')}" />
					
						<th><g:message code="origin.genus.label" default="Genus" /></th>
					
						<th><g:message code="origin.hostFacility.label" default="Host Facility" /></th>
					
						<g:sortableColumn property="partOfFish" title="${message(code: 'origin.partOfFish.label', default: 'Part Of Fish')}" />
					
						<th><g:message code="origin.phylum.label" default="Phylum" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${originInstanceList}" status="i" var="originInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${originInstance.id}">${fieldValue(bean: originInstance, field: "age")}</g:link></td>
					
						<td>${fieldValue(bean: originInstance, field: "arbitraryData")}</td>
					
						<td>${fieldValue(bean: originInstance, field: "genus")}</td>
					
						<td>${fieldValue(bean: originInstance, field: "hostFacility")}</td>
					
						<td>${fieldValue(bean: originInstance, field: "partOfFish")}</td>
					
						<td>${fieldValue(bean: originInstance, field: "phylum")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${originInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
