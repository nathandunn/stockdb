
<%@ page import="edu.uoregon.stockdb.Origin" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'origin.label', default: 'Origin')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-origin" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-origin" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list origin">
			
				<g:if test="${originInstance?.age}">
				<li class="fieldcontain">
					<span id="age-label" class="property-label"><g:message code="origin.age.label" default="Age" /></span>
					
						<span class="property-value" aria-labelledby="age-label"><g:fieldValue bean="${originInstance}" field="age"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.arbitraryData}">
				<li class="fieldcontain">
					<span id="arbitraryData-label" class="property-label"><g:message code="origin.arbitraryData.label" default="Arbitrary Data" /></span>
					
						<span class="property-value" aria-labelledby="arbitraryData-label"><g:fieldValue bean="${originInstance}" field="arbitraryData"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.genus}">
				<li class="fieldcontain">
					<span id="genus-label" class="property-label"><g:message code="origin.genus.label" default="Genus" /></span>
					
						<span class="property-value" aria-labelledby="genus-label"><g:link controller="genus" action="show" id="${originInstance?.genus?.id}">${originInstance?.genus?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.hostFacility}">
				<li class="fieldcontain">
					<span id="hostFacility-label" class="property-label"><g:message code="origin.hostFacility.label" default="Host Facility" /></span>
					
						<span class="property-value" aria-labelledby="hostFacility-label"><g:link controller="hostFacility" action="show" id="${originInstance?.hostFacility?.id}">${originInstance?.hostFacility?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.partOfFish}">
				<li class="fieldcontain">
					<span id="partOfFish-label" class="property-label"><g:message code="origin.partOfFish.label" default="Part Of Fish" /></span>
					
						<span class="property-value" aria-labelledby="partOfFish-label"><g:fieldValue bean="${originInstance}" field="partOfFish"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.phenotypes}">
				<li class="fieldcontain">
					<span id="phenotypes-label" class="property-label"><g:message code="origin.phenotypes.label" default="Phenotypes" /></span>
					
						<g:each in="${originInstance.phenotypes}" var="p">
						<span class="property-value" aria-labelledby="phenotypes-label"><g:link controller="phenotype" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.phylum}">
				<li class="fieldcontain">
					<span id="phylum-label" class="property-label"><g:message code="origin.phylum.label" default="Phylum" /></span>
					
						<span class="property-value" aria-labelledby="phylum-label"><g:link controller="phylum" action="show" id="${originInstance?.phylum?.id}">${originInstance?.phylum?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${originInstance?.strains}">
				<li class="fieldcontain">
					<span id="strains-label" class="property-label"><g:message code="origin.strains.label" default="Strains" /></span>
					
						<g:each in="${originInstance.strains}" var="s">
						<span class="property-value" aria-labelledby="strains-label"><g:link controller="strain" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${originInstance?.id}" />
					<g:link class="edit" action="edit" id="${originInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
