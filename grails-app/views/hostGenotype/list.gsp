
<%@ page import="edu.uoregon.stockdb.HostGenotype" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'hostGenotype.label', default: 'HostGenotype')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-hostGenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-hostGenotype" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'hostGenotype.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="zfinId" title="${message(code: 'hostGenotype.zfinId.label', default: 'Zfin Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${hostGenotypeInstanceList}" status="i" var="hostGenotypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${hostGenotypeInstance.id}">${fieldValue(bean: hostGenotypeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: hostGenotypeInstance, field: "zfinId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${hostGenotypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
