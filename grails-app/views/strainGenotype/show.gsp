
<%@ page import="edu.uoregon.stockdb.StrainGenotype" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'strainGenotype.label', default: 'StrainGenotype')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-strainGenotype" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-strainGenotype" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list strainGenotype">
			
				<g:if test="${strainGenotypeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="strainGenotype.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${strainGenotypeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainGenotypeInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="strainGenotype.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${strainGenotypeInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainGenotypeInstance?.strains}">
				<li class="fieldcontain">
					<span id="strains-label" class="property-label"><g:message code="strainGenotype.strains.label" default="Strains" /></span>
					
						<g:each in="${strainGenotypeInstance.strains}" var="s">
						<span class="property-value" aria-labelledby="strains-label"><g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${strainGenotypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${strainGenotypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
