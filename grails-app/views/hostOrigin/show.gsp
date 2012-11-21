
<%@ page import="edu.uoregon.stockdb.HostOrigin" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'hostOrigin.label', default: 'HostOrigin')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-hostOrigin" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-hostOrigin" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list hostOrigin">
			
				<g:if test="${hostOriginInstance?.values}">
				<li class="fieldcontain">
					<span id="values-label" class="property-label"><g:message code="hostOrigin.values.label" default="Values" /></span>
					
						<span class="property-value" aria-labelledby="values-label"><g:fieldValue bean="${hostOriginInstance}" field="values"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostOriginInstance?.age}">
				<li class="fieldcontain">
					<span id="age-label" class="property-label"><g:message code="hostOrigin.age.label" default="Age" /></span>
					
						<span class="property-value" aria-labelledby="age-label"><g:fieldValue bean="${hostOriginInstance}" field="age"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostOriginInstance?.partOfFish}">
				<li class="fieldcontain">
					<span id="partOfFish-label" class="property-label"><g:message code="hostOrigin.partOfFish.label" default="Part Of Fish" /></span>
					
						<span class="property-value" aria-labelledby="partOfFish-label"><g:fieldValue bean="${hostOriginInstance}" field="partOfFish"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${hostOriginInstance?.hostFacility}">
				<li class="fieldcontain">
					<span id="hostFacility-label" class="property-label"><g:message code="hostOrigin.hostFacility.label" default="Host Facility" /></span>
					
						<span class="property-value" aria-labelledby="hostFacility-label"><g:link controller="hostFacility" action="show" id="${hostOriginInstance?.hostFacility?.id}">${hostOriginInstance?.hostFacility?.name}</g:link></span>
					
				</li>
				</g:if>

                <g:if test="${hostOriginInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="hostOrigin.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${hostOriginInstance}" field="name"/></span>

                    </li>
                </g:if>

				<g:if test="${hostOriginInstance?.genus}">
				<li class="fieldcontain">
					<span id="genus-label" class="property-label"><g:message code="hostOrigin.genus.label" default="Genus" /></span>
					
						<span class="property-value" aria-labelledby="genus-label"><g:link controller="genus" action="show" id="${hostOriginInstance?.genus?.id}">${hostOriginInstance?.genus?.name}</g:link></span>
					
				</li>
				</g:if>

                <g:if test="${hostOriginInstance?.phylum}">
                    <li class="fieldcontain">
                        <span id="phylum-label" class="property-label"><g:message code="hostOrigin.phylum.label" default="Phylum" /></span>

                        <span class="property-value" aria-labelledby="phylum-label"><g:link controller="phylum" action="show" id="${hostOriginInstance?.phylum?.id}">${hostOriginInstance?.phylum?.name}</g:link></span>

                    </li>
                </g:if>


                <g:if test="${hostOriginInstance?.phenotypes}">
				<li class="fieldcontain">
					<span id="phenotypes-label" class="property-label"><g:message code="hostOrigin.phenotypes.label" default="Phenotypes" /></span>
					
						<g:each in="${hostOriginInstance.phenotypes}" var="p">
						<span class="property-value" aria-labelledby="phenotypes-label"><g:link controller="phenotype" action="show" id="${p.id}">${p?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${hostOriginInstance?.strains}">
				<li class="fieldcontain">
					<span id="strains-label" class="property-label"><g:message code="hostOrigin.strains.label" default="Strains" /></span>
					
						<g:each in="${hostOriginInstance.strains}" var="s">
						<span class="property-value" aria-labelledby="strains-label"><g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${hostOriginInstance?.id}" />
					<g:link class="edit" action="edit" id="${hostOriginInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
