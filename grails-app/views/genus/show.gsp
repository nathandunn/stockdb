
<%@ page import="edu.uoregon.stockdb.Genus" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'genus.label', default: 'Genus')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-genus" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-genus" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list genus">
			
				<g:if test="${genusInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="genus.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${genusInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${genusInstance?.phylum}">
				<li class="fieldcontain">
					<span id="phylum-label" class="property-label"><g:message code="genus.phylum.label" default="Phylum" /></span>
					
						<span class="property-value" aria-labelledby="phylum-label"><g:link controller="phylum" action="show" id="${genusInstance?.phylum?.id}">${genusInstance?.phylum?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${genusInstance?.id}" />
					<g:link class="edit" action="edit" id="${genusInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
