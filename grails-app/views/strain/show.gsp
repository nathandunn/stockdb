
<%@ page import="edu.uoregon.stockdb.Strain" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-strain" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-strain" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list strain">
			
				<g:if test="${strainInstance?.arbitraryData}">
				<li class="fieldcontain">
					<span id="arbitraryData-label" class="property-label"><g:message code="strain.arbitraryData.label" default="Arbitrary Data" /></span>
					
						<span class="property-value" aria-labelledby="arbitraryData-label"><g:fieldValue bean="${strainInstance}" field="arbitraryData"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.genus}">
				<li class="fieldcontain">
					<span id="genus-label" class="property-label"><g:message code="strain.genus.label" default="Genus" /></span>
					
						<span class="property-value" aria-labelledby="genus-label"><g:link controller="genus" action="show" id="${strainInstance?.genus?.id}">${strainInstance?.genus?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.origin}">
				<li class="fieldcontain">
					<span id="origin-label" class="property-label"><g:message code="strain.origin.label" default="Origin" /></span>
					
						<span class="property-value" aria-labelledby="origin-label"><g:link controller="origin" action="show" id="${strainInstance?.origin?.id}">${strainInstance?.origin?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.phylum}">
				<li class="fieldcontain">
					<span id="phylum-label" class="property-label"><g:message code="strain.phylum.label" default="Phylum" /></span>
					
						<span class="property-value" aria-labelledby="phylum-label"><g:link controller="phylum" action="show" id="${strainInstance?.phylum?.id}">${strainInstance?.phylum?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.sequence}">
				<li class="fieldcontain">
					<span id="sequence-label" class="property-label"><g:message code="strain.sequence.label" default="Sequence" /></span>
					
						<span class="property-value" aria-labelledby="sequence-label"><g:fieldValue bean="${strainInstance}" field="sequence"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.stockLocations}">
				<li class="fieldcontain">
					<span id="stockLocations-label" class="property-label"><g:message code="strain.stockLocations.label" default="Stock Locations" /></span>
					
						<g:each in="${strainInstance.stockLocations}" var="s">
						<span class="property-value" aria-labelledby="stockLocations-label"><g:link controller="stockLocation" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${strainInstance?.id}" />
					<g:link class="edit" action="edit" id="${strainInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
