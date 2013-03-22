<%@ page import="edu.uoregon.stockdb.Strain" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-strain" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-strain" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list strain">

        <g:if test="${strainInstance?.name}">
            <li class="fieldcontain">
                <span id="index-label" class="property-label"><g:message code="strain.name.label"
                                                                         default="Index"/></span>

                <span class="property-value" aria-labelledby="index-label"><g:fieldValue bean="${strainInstance}"
                                                                                         field="name"/></span>

            </li>
        </g:if>

        <li class="fieldcontain">
            <span id="genus-label" class="property-label"><g:message code="strain.genus.label"
                                                                     default="Phylum Genus"/></span>

            <span class="property-value" aria-labelledby="genus-label">
                <i>
                    <g:link controller="phylum" action="show"
                            id="${strainInstance?.genus?.phylum?.id}">${strainInstance?.genus?.phylum?.name}</g:link>
                    <g:link controller="genus" action="show"
                            id="${strainInstance?.genus?.id}">${strainInstance?.genus?.name}</g:link>
                </i>
            </span>

        </li>


        <g:if test="${strainInstance?.dateEntered}">
            <li class="fieldcontain">
                <span id="dateEntered-label" class="property-label"><g:message code="strain.dateEntered.label"
                                                                               default="Date Entered"/></span>

                <span class="property-value" aria-labelledby="dateEntered-label"><g:formatDate
                        date="${strainInstance?.dateEntered}"/></span>

            </li>
        </g:if>

        <g:if test="${strainInstance?.experiments}">
            <li class="fieldcontain">
                <span id="experiments-label" class="property-label"><g:message code="strain.experiments.label"
                                                                               default="Experiments"/></span>

                <g:each in="${strainInstance.experiments}" var="e">
                    <span class="property-value" aria-labelledby="experiments-label"><g:link controller="experiment"
                                                                                             action="show"
                                                                                             id="${e.id}">${e?.name}</g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${strainInstance?.genome}">
            <li class="fieldcontain">
                <span id="genome-label" class="property-label"><g:message code="strain.genome.label"
                                                                          default="Genome"/></span>

                <span class="property-value" aria-labelledby="genome-label">
                    <g:link controller="genome" action="show" id="${strainInstance?.genome?.id}">
                        ${strainInstance?.genome?.note}
                        ${strainInstance?.genome?.quality}
                    </g:link>

                    <g:if test="${strainInstance?.genome?.url}">
                        <g:link url="${strainInstance.genome.url}">[Sequence]</g:link>
                    </g:if>
                </span>

            </li>
        </g:if>


        <g:if test="${strainInstance?.hostOrigin}">
            <li class="fieldcontain">
                <span id="hostOrigin-label" class="property-label"><g:message code="strain.hostOrigin.label"
                                                                              default="Host Origin"/></span>

                <span class="property-value" aria-labelledby="hostOrigin-label">
                    <g:link controller="hostOrigin" action="show" id="${strainInstance?.hostOrigin?.id}">
                        ${strainInstance.hostOrigin.species.commonName}
                        (${strainInstance.hostOrigin?.genotype?.name})
                        <g:if test="${strainInstance.hostOrigin.daysPastFertilization>=0}">
                            ${strainInstance.hostOrigin.daysPastFertilization} DPF
                        </g:if>
                        <g:else>
                            ${strainInstance.hostOrigin?.stage}
                        </g:else>
                    </g:link>

                    &nbsp;
                    <g:if test="${strainInstance?.hostOrigin?.anatomy}">
                        <a href="${strainInstance?.hostOrigin?.anatomyUrl}">${strainInstance?.hostOrigin?.anatomy}</a>
                    </g:if>
                </span>

            </li>
        </g:if>

        <g:if test="${strainInstance?.isolate}">
            <li class="fieldcontain">
                <span id="isolate-label" class="property-label"><g:message code="strain.isolate.label"
                                                                           default="Isolate Conditions"/></span>

                <span class="property-value" aria-labelledby="isolate-label">
                    <g:link controller="isolate" action="show" id="${strainInstance?.isolate?.id}">
                        ${strainInstance.isolate.isolatedBy?.fullName}
                        <g:formatDate date="${strainInstance.isolate?.isolatedWhen}" type="DATE" style="MEDIUM"/>
                        ${strainInstance.isolate.oxygenCondition}
                        ${strainInstance.isolate.media}
                        ${strainInstance.isolate.temperature} C
                    %{--<g:link controller="isolate" action="show" id="${strainInstance?.isolate?.id}">${strainInstance?.isolate?.name}</g:link>--}%
                    </g:link>
                </span>

            </li>
        </g:if>

        <g:if test="${strainInstance?.formerCloneAlias}">
            <li class="fieldcontain">
                <span id="formerCloneAlias-label" class="property-label"><g:message code="strain.formerCloneAlias.label"
                                                                                    default="Former Clone Alias"/></span>

                <span class="property-value" aria-labelledby="formerCloneAlias-label"><g:fieldValue
                        bean="${strainInstance}" field="formerCloneAlias"/></span>

            </li>
        </g:if>

        <g:if test="${strainInstance?.notes}">
            <li class="fieldcontain">
                <span id="notes-label" class="property-label"><g:message code="strain.notes.label"
                                                                         default="Notes"/></span>

                <span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${strainInstance}"
                                                                                         field="notes"/></span>

            </li>
        </g:if>

        <g:if test="${strainInstance?.parentStrain}">
            <li class="fieldcontain">
                <span id="parentStrain-label" class="property-label"><g:message code="strain.parentStrain.label"
                                                                                default="Parent Strain"/></span>

                <span class="property-value" aria-labelledby="parentStrain-label"><g:link controller="strain"
                                                                                          action="show"
                                                                                          id="${strainInstance?.parentStrain?.id}">${strainInstance?.parentStrain?.name}</g:link></span>

            </li>
        </g:if>


        <g:if test="${strainInstance?.stocks}">
            <li class="fieldcontain">
                <span id="stocks-label" class="property-label"><g:message code="strain.stocks.label"
                                                                          default="Stocks"/></span>

                <g:each in="${strainInstance.stocks}" var="s">
                    <span class="property-value" aria-labelledby="stocks-label">
                        <g:link controller="stock" action="show" id="${s.id}">${s?.display}
                        </g:link></span>
                </g:each>

            </li>
        </g:if>

        <g:if test="${strainInstance?.strainGenotype}">
            <li class="fieldcontain">
                <span id="strainGenotype-label" class="property-label"><g:message code="strain.strainGenotype.label"
                                                                                  default="Strain Genotype"/></span>

                <span class="property-value" aria-labelledby="strainGenotype-label"><g:link controller="strainGenotype"
                                                                                            action="show"
                                                                                            id="${strainInstance?.strainGenotype?.id}">${strainInstance?.strainGenotype?.name}</g:link></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${strainInstance?.id}"/>
            <g:link class="edit" action="edit" id="${strainInstance?.id}"><g:message code="default.button.edit.label"
                                                                                     default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
