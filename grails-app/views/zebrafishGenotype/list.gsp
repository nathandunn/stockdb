
<%@ page import="edu.uoregon.stockdb.ZebrafishGenotype" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-zebrafishGenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-zebrafishGenotype" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'zebrafishGenotype.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="zfinId" title="${message(code: 'zebrafishGenotype.zfinId.label', default: 'Zfin Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${zebrafishGenotypeInstanceList}" status="i" var="zebrafishGenotypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${zebrafishGenotypeInstance.id}">${fieldValue(bean: zebrafishGenotypeInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: zebrafishGenotypeInstance, field: "zfinId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${zebrafishGenotypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
