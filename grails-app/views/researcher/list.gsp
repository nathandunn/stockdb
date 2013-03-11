
<%@ page import="edu.uoregon.stockdb.Researcher" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'researcher.label', default: 'Researcher')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-researcher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-researcher" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="email" title="${message(code: 'researcher.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'researcher.firstName.label', default: 'First Name')}" />
					
						<g:sortableColumn property="lastName" title="${message(code: 'researcher.lastName.label', default: 'Last Name')}" />
					
						<th><g:message code="researcher.lab.label" default="Lab" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${researcherInstanceList}" status="i" var="researcherInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${researcherInstance.id}">${fieldValue(bean: researcherInstance, field: "email")}</g:link></td>
					
						<td>${fieldValue(bean: researcherInstance, field: "firstName")}</td>
					
						<td>${fieldValue(bean: researcherInstance, field: "lastName")}</td>
					
						<td>${fieldValue(bean: researcherInstance, field: "lab")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${researcherInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
