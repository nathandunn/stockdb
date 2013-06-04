<%@ page import="edu.uoregon.stockdb.MeasuredValue" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'measuredValue.label', default: 'MeasuredValue')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-measuredValue" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                    default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>

        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="show-measuredValue" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
    </g:if>
    <ol class="property-list measuredValue">

        <g:if test="${measuredValueInstance?.value}">
            <li class="fieldcontain">
                <span id="value-label" class="property-label"><g:message code="measuredValue.value.label"
                                                                         default="Value"/></span>

                <span class="property-value" aria-labelledby="value-label"><g:fieldValue bean="${measuredValueInstance}"
                                                                                         field="value"/></span>

            </li>
        </g:if>

        <li class="fieldcontain">
            <span id="experiment-label" class="property-label"><g:message code="measuredValue.experiment.label"
                                                                          default="Category"/></span>

            <span class="property-value" aria-labelledby="experiment-label">
                <g:link controller="category" action="show" id="${measuredValueInstance?.category?.id}">
                    ${measuredValueInstance?.category?.name}
                </g:link></span>

        </li>


        <li class="fieldcontain">
            <span id="type-label" class="property-label"><g:message code="measuredValue.type.label"
                                                                    default="Strain"/></span>

            <span class="property-value" aria-labelledby="type-label">
                <g:link controller="strain" action="show" id="${measuredValueInstance.strain.id}">
                    ${measuredValueInstance.strain.name}
                </g:link>
            </span>

        </li>

        <g:if test="${measuredValueInstance?.category?.units}">
            <li class="fieldcontain">
                <span id="units-label" class="property-label"><g:message code="measuredValue.units.label"
                                                                         default="Units"/></span>

                <span class="property-value" aria-labelledby="units-label"><g:fieldValue bean="${measuredValueInstance}"
                                                                                         field="category.units"/></span>

            </li>
        </g:if>

    </ol>
    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${measuredValueInstance?.id}"/>
            <g:link class="edit" action="edit" id="${measuredValueInstance?.id}"><g:message
                    code="default.button.edit.label" default="Edit"/></g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
