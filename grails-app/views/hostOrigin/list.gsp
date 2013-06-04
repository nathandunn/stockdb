
<%@ page import="edu.uoregon.stockdb.HostOrigin" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'hostOrigin.label', default: 'Host Origin')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>

				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-hostOrigin" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>

						<g:sortableColumn property="daysPastFertilization" title="${message(code: 'hostOrigin.stage.label', default: 'Stage')}" />


						<g:sortableColumn property="anatomy" title="${message(code: 'hostOrigin.anatomy.label', default: 'Anatomy')}" />
					
						%{--<th><g:message code="hostOrigin.hostFacility.label" default="Host Facility" /></th>--}%
                        <g:sortableColumn property="hostFacility" title="${message(code: 'hostOrigin.hostFacility.label', default: 'Host Facility')}" />

						<th><g:message code="hostOrigin.genotypes.label" default="Genotypes" /></th>
					
						%{--<th><g:message code="hostOrigin.genus.label" default="Genus" /></th>--}%
                        %{--<g:sortableColumn property="id" title="${message(code: 'hostOrigin.details.label', default: 'Details')}" />--}%
                        <th></th>

					</tr>
				</thead>
				<tbody>
				<g:each in="${hostOriginInstanceList}" status="i" var="hostOriginInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>
                            %{--${hostOriginInstance.stage}--}%
                            <g:fieldValue bean="${hostOriginInstance}" field="stage"/>
                            <g:if test="${!hostOriginInstance.stage.contains("dpf")}">
                                (${hostOriginInstance.daysPastFertilization} DPF)
                            </g:if>
						</td>
					
						%{--<td>${fieldValue(bean: hostOriginInstance, field: "daysPastFertilization")}</td>--}%
					
						<td>
                            <g:if test="${hostOriginInstance.anatomyUrl && hostOriginInstance.anatomy}">
                                <g:link url="${hostOriginInstance.anatomyUrl}">${hostOriginInstance.anatomy}</g:link>
                            </g:if>
                            <g:else>
                                ${hostOriginInstance.anatomy ?:""}
                            </g:else>
                        </td>
					
						<td>
                            <g:link action="show" controller="hostFacility" id="${hostOriginInstance.hostFacility?.id}">
                                ${hostOriginInstance.hostFacility?.name}
                            </g:link>
                        </td>
					
						<td>
                            <g:each in="${hostOriginInstance.genotypes}" var="genotype">
                                <g:link controller="hostGenotype" action="show" id="${genotype.id}">
                                    ${genotype?.name}
                                </g:link>
                            </g:each>
                        </td>

                        <td>
                            <g:link action="show" id="${hostOriginInstance.id}">
                                Details
                                %{--${fieldValue(bean: hostOriginInstance, field: "id")}--}%
                            </g:link>
                        </td>
						%{--<td>${fieldValue(bean: hostOriginInstance, field: "genus")}</td>--}%
                        %{--<td>${fieldValue(bean: hostOriginInstance, field: "species")}</td>--}%

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${hostOriginInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
