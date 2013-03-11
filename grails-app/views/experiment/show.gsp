
<%@ page import="edu.uoregon.stockdb.Experiment" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'experiment.label', default: 'Experiment')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-experiment" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-experiment" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list experiment">
			
				<g:if test="${experimentInstance?.measuredValues}">
				<li class="fieldcontain">
					<span id="measuredValues-label" class="property-label"><g:message code="experiment.measuredValues.label" default="Measured Values" /></span>
					
						<g:each in="${experimentInstance.measuredValues}" var="m">
						<span class="property-value" aria-labelledby="measuredValues-label"><g:link controller="measuredValue" action="show" id="${m.id}">${m?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${experimentInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="experiment.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${experimentInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${experimentInstance?.phenotypes}">
				<li class="fieldcontain">
					<span id="phenotypes-label" class="property-label"><g:message code="experiment.phenotypes.label" default="Phenotypes" /></span>
					
						<g:each in="${experimentInstance.phenotypes}" var="p">
						<span class="property-value" aria-labelledby="phenotypes-label"><g:link controller="phenotype" action="show" id="${p.id}">${p?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${experimentInstance?.researcher}">
				<li class="fieldcontain">
					<span id="researcher-label" class="property-label"><g:message code="experiment.researcher.label" default="Researcher" /></span>
					
						<span class="property-value" aria-labelledby="researcher-label"><g:link controller="researcher" action="show" id="${experimentInstance?.researcher?.id}">${experimentInstance?.researcher?.name}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${experimentInstance?.strains}">
				<li class="fieldcontain">
					<span id="strains-label" class="property-label"><g:message code="experiment.strains.label" default="Strains" /></span>
					
						<g:each in="${experimentInstance.strains}" var="s">
						<span class="property-value" aria-labelledby="strains-label"><g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${experimentInstance?.whenPerformed}">
				<li class="fieldcontain">
					<span id="whenPerformed-label" class="property-label"><g:message code="experiment.whenPerformed.label" default="When Performed" /></span>
					
						<span class="property-value" aria-labelledby="whenPerformed-label"><g:formatDate date="${experimentInstance?.whenPerformed}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${experimentInstance?.id}" />
					<g:link class="edit" action="edit" id="${experimentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
