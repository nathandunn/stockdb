
<%@ page import="edu.uoregon.stockdb.Stock" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'stock.label', default: 'Stock')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-stock" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-stock" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list stock">
			
				<g:if test="${stockInstance?.strain}">
				<li class="fieldcontain">
					<span id="strain-label" class="property-label"><g:message code="stock.strain.label" default="Strain" /></span>
					
						<span class="property-value" aria-labelledby="strain-label"><g:link controller="strain" action="show" id="${stockInstance?.strain?.id}">${stockInstance?.strain?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${stockInstance?.generalLocation}">
				<li class="fieldcontain">
					<span id="generalLocation-label" class="property-label"><g:message code="stock.generalLocation.label" default="General Location" /></span>
					
						<span class="property-value" aria-labelledby="generalLocation-label"><g:link controller="location" action="show" id="${stockInstance?.generalLocation?.id}">${stockInstance?.generalLocation?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${stockInstance?.physicalLocation}">
				<li class="fieldcontain">
					<span id="physicalLocation-label" class="property-label"><g:message code="stock.physicalLocation.label" default="Physical Location" /></span>
					
						<span class="property-value" aria-labelledby="physicalLocation-label"><g:fieldValue bean="${stockInstance}" field="physicalLocation"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${stockInstance?.id}" />
					<g:link class="edit" action="edit" id="${stockInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
