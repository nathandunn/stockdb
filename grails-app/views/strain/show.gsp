
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
			
				<g:if test="${strainInstance?.sequence}">
				<li class="fieldcontain">
					<span id="sequence-label" class="property-label"><g:message code="strain.sequence.label" default="Sequence" /></span>
					
						<span class="property-value" aria-labelledby="sequence-label"><g:fieldValue bean="${strainInstance}" field="sequence"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.values}">
				<li class="fieldcontain">
					<span id="values-label" class="property-label"><g:message code="strain.values.label" default="Values" /></span>
					
						<span class="property-value" aria-labelledby="values-label"><g:fieldValue bean="${strainInstance}" field="values"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.formAlias}">
				<li class="fieldcontain">
					<span id="formAlias-label" class="property-label"><g:message code="strain.formAlias.label" default="Form Alias" /></span>
					
						<span class="property-value" aria-labelledby="formAlias-label"><g:fieldValue bean="${strainInstance}" field="formAlias"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.genus}">
				<li class="fieldcontain">
					<span id="genus-label" class="property-label"><g:message code="strain.genus.label" default="Genus" /></span>
					
						<span class="property-value" aria-labelledby="genus-label"><g:link controller="genus" action="show" id="${strainInstance?.genus?.id}">${strainInstance?.genus?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.isolatedBy}">
				<li class="fieldcontain">
					<span id="isolatedBy-label" class="property-label"><g:message code="strain.isolatedBy.label" default="Isolated By" /></span>
					
						<span class="property-value" aria-labelledby="isolatedBy-label"><g:link controller="user" action="show" id="${strainInstance?.isolatedBy?.id}">${strainInstance?.isolatedBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.isolatedWhen}">
				<li class="fieldcontain">
					<span id="isolatedWhen-label" class="property-label"><g:message code="strain.isolatedWhen.label" default="Isolated When" /></span>
					
						<span class="property-value" aria-labelledby="isolatedWhen-label"><g:formatDate date="${strainInstance?.isolatedWhen}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.motility}">
				<li class="fieldcontain">
					<span id="motility-label" class="property-label"><g:message code="strain.motility.label" default="Motility" /></span>
					
						<span class="property-value" aria-labelledby="motility-label"><g:fieldValue bean="${strainInstance}" field="motility"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="strain.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${strainInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.origin}">
				<li class="fieldcontain">
					<span id="origin-label" class="property-label"><g:message code="strain.origin.label" default="Origin" /></span>
					
						<span class="property-value" aria-labelledby="origin-label"><g:link controller="hostOrigin" action="show" id="${strainInstance?.origin?.id}">${strainInstance?.origin?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.parentStrain}">
				<li class="fieldcontain">
					<span id="parentStrain-label" class="property-label"><g:message code="strain.parentStrain.label" default="Parent Strain" /></span>
					
						<span class="property-value" aria-labelledby="parentStrain-label"><g:link controller="strain" action="show" id="${strainInstance?.parentStrain?.id}">${strainInstance?.parentStrain?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.phylum}">
				<li class="fieldcontain">
					<span id="phylum-label" class="property-label"><g:message code="strain.phylum.label" default="Phylum" /></span>
					
						<span class="property-value" aria-labelledby="phylum-label"><g:link controller="phylum" action="show" id="${strainInstance?.phylum?.id}">${strainInstance?.phylum?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${strainInstance?.stocks}">
				<li class="fieldcontain">
					<span id="stocks-label" class="property-label"><g:message code="strain.stocks.label" default="Stocks" /></span>
					
						<g:each in="${strainInstance.stocks}" var="s">
						<span class="property-value" aria-labelledby="stocks-label"><g:link controller="stock" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
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
