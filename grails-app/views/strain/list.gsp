<%@ page import="edu.uoregon.stockdb.Genus; edu.uoregon.stockdb.HostOrigin; edu.uoregon.stockdb.Strain" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:require module="jquery"/>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div class="list-filter">
    <h3>Filter</h3>
    <g:form>
        <table>
            <tr>
                <td>
                    <strong>Genus</strong>
                </td>
                <td>
                    <g:select name="genus" from="${edu.uoregon.stockdb.Genus.listOrderByName()}"
                              optionValue="name"
                              optionKey="id"
                              noSelection="[null: '- All -']"
                        value="${strainFilters?.get('genus')}"
                              onchange="
                              ${remoteFunction(
                                      action: 'addFilter'
                                      , controller: 'strain'
                                      , params: '\'strainFilter=genus:\' + this.value '
                                      , method: 'POST'
                                      , onSuccess: 'window.location =\'' + request.contextPath + '/strain/list/\';'
                                      , onError: 'alert(\'error\');'
                              )}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <strong>Phylum</strong>
                </td>
                <td>
                    <g:select name="phylum" from="${edu.uoregon.stockdb.Phylum.listOrderByName()}"
                              optionValue="name"
                              optionKey="id"
                              noSelection="[null: '- All -']"
                              value="${strainFilters?.get('phylum')}"
                              onchange="
                              ${remoteFunction(
                                      action: 'addFilter'
                                      , controller: 'strain'
                                      , params: '\'strainFilter=phylum:\' + this.value '
                                      , method: 'POST'
                                      , onSuccess: 'window.location =\'' + request.contextPath + '/strain/list/\';'
                                      , onError: 'alert(\'error\');'
                              )}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <strong>Host Species</strong>
                </td>
                <td>
                    <g:select name="host.species" from="${edu.uoregon.stockdb.Species.all}"
                              noSelection="[null: '- All -']"
                              optionValue="commonName"
                              optionKey="id"
                              value="${strainFilters?.get('host.species')}"
                              onchange="
                              ${remoteFunction(
                                      action: 'addFilter'
                                      , controller: 'strain'
                                      , params: '\'strainFilter=host.species:\' + this.value '
                                      , method: 'POST'
                                      , onSuccess: 'window.location =\'' + request.contextPath + '/strain/list/\';'
                                      , onError: 'alert(\'error\');'
                              )}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <strong>Host Anatomy</strong>
                </td>
                <td>
                    <g:select name="host.anatomy"
                              from="${edu.uoregon.stockdb.HostOrigin.executeQuery('select ho.anatomy from HostOrigin ho where ho.anatomy != null group by ho.anatomy')}"
                              noSelection="[null: '- All -']"
                              value="${strainFilters?.get('host.anatomy')}"
                              onchange="
                              ${remoteFunction(
                                      action: 'addFilter'
                                      , controller: 'strain'
                                      , params: '\'strainFilter=host.anatomy:\' + this.value '
                                      , method: 'POST'
                                      , onSuccess: 'window.location =\'' + request.contextPath + '/strain/list/\';'
                                      , onError: 'alert(\'error\');'
                              )}"/>
                </td>
            </tr>
        </table>
    </g:form>

</div>

<div id="list-strain" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" title="${message(code: 'strain.name.label', default: 'Index')}"/>

            %{--<th>Genus / Phylum</th>--}%
            <g:sortableColumn property="genus" title="${message(code: 'strain.genus.label', default: 'Genus')}"/>
            <g:sortableColumn property="genus.phylum"
                              title="${message(code: 'strain.phylum.label', default: 'Phylum')}"/>

            %{--<th>Host</th>--}%
            <g:sortableColumn property="hostOrigin.species.commonName"
                              title="${message(code: 'hostOrigin.anatomy.label', default: 'Host')}"/>
            %{--<th>Host Anatomy</th>--}%
            <g:sortableColumn property="hostOrigin.anatomy"
                              title="${message(code: 'hostOrigin.anatomy.label', default: 'Host Anatomy')}"/>

            %{--<g:sortableColumn property="hostOrigin.stage"--}%
            %{--title="${message(code: 'hostOrigin.stage.label', default: 'Host Stage')}"/>--}%
            <g:sortableColumn property="hostOrigin.daysPastFertilization"
                              title="${message(code: 'hostOrigin.stage.label', default: 'Host Age (DPF)')}"/>
            <th>Genome</th>

        </tr>
        </thead>
        <tbody>
        <g:each in="${strainInstanceList}" status="i" var="strainInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                %{--<td>${fieldValue(bean: strainInstance, field: "index")}</td>--}%
                <td>
                    <g:showId instance="${strainInstance}" label="${strainInstance.name}"/>
                </td>

                <td><i>${strainInstance.genus.name}</i></td>
                <td><i>${strainInstance.genus.phylum.name}</i></td>

                <td>
                    <g:link action="show" id="${strainInstance?.hostOrigin?.id}" controller="hostOrigin">
                        ${strainInstance?.hostOrigin?.species?.commonName}
                    </g:link>
                </td>

                <td>
                    <g:link action="show" controller="hostOrigin" id="${strainInstance?.hostOrigin?.id}">
                        ${strainInstance?.hostOrigin?.anatomy}
                    </g:link>

                    <g:if test="${strainInstance?.hostOrigin?.anatomy}">
                        <a href="${strainInstance?.hostOrigin?.anatomyUrl}">[Reference]</a>
                    </g:if>
                </td>

                <td>
                    <g:if test="${strainInstance.hostOrigin?.stage == 'Adult'}">
                        Adult
                    </g:if>
                    <g:elseif
                            test="${strainInstance.hostOrigin?.daysPastFertilization >= 0 && strainInstance.hostOrigin?.daysPastFertilization < 360}">
                        ${strainInstance.hostOrigin?.daysPastFertilization}
                    </g:elseif>
                    <g:else>
                        One Year
                    </g:else>
                </td>

                <td>
                    <g:if test="${strainInstance.genome}">
                        <g:link action="show" id="${strainInstance.genome.id}" controller="genome">Detail</g:link>
                        <a href="${strainInstance.genome.url}">Sequence</a>
                    </g:if>
                </td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${strainInstanceTotal}"/>
    </div>
</div>
</body>
</html>
