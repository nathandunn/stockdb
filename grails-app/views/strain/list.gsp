<%@ page import="edu.uoregon.stockdb.ResearcherService; edu.uoregon.stockdb.Phylum; edu.uoregon.stockdb.Genus; edu.uoregon.stockdb.HostOrigin; edu.uoregon.stockdb.Strain" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'strain.label', default: 'Strain')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
    <r:require module="jquery"/>
</head>

<body>

<shiro:hasRole name="${ResearcherService.ROLE_ADMINISTRATOR}">
    <div class="nav" role="navigation">
        <ul>

            <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                                  args="[entityName]"/></g:link></li>
        </ul>
    </div>
</shiro:hasRole>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div class="list-filter">
    <h3>Filter</h3>
    <g:form action="showFilter">
        <table>
            <tr><td>
                <b>Go to Strain</b>
            </td>
                <td>
                    <g:textField name="strainName" value="${strainName}"/>
                </td></tr>
        </table>
    </g:form>
    <g:form>
        <table>
            <tr>
                <td>
                    <strong>Strain Genus</strong>
                </td>
                <td>
                    <g:select name="genus" from="${Genus.findAllByHost(false, [sort: 'name', order: 'asc'])}"
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
                    <strong>Strain Phylum</strong>
                </td>
                <td>
                    <g:select name="phylum" from="${Phylum.findAllByHost(false, [sort: 'name', order: 'asc'])}"
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
            <tr>
                <td>
                    <strong>Genome Available</strong>
                </td>
                <td>
                    <g:checkBox name="genome.available" checked="${strainFilters?.get('genome.available')}"
                                onchange="
                                ${remoteFunction(
                                        action: 'addFilter'
                                        , controller: 'strain'
                                        , params: '\'strainFilter=genome.available:\' + this.checked'
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
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="name" defaultOrder="asc"
                              title="${message(code: 'strain.name.label', default: 'Index')}"/>

            %{--<th>Genus / Phylum</th>--}%
            <g:sortableColumn property="genus.name" title="${message(code: 'strain.genus.label', default: 'Genus')}"/>

            <g:sortableColumn property="genus.phylum.name"
                              title="${message(code: 'strain.phylum.label', default: 'Phylum')}"/>
            %{--<th>Phylum</th>--}%

            %{--<th>Host</th>--}%
            <g:sortableColumn property="hostOrigin.species"
                              title="${message(code: 'hostOrigin.anatomy.label', default: 'Host')}"/>
            %{--<th>Host Anatomy</th>--}%
            <g:sortableColumn property="hostOrigin.anatomy"
                              title="${message(code: 'hostOrigin.anatomy.label', default: 'Host Anatomy')}"/>

            %{--<g:sortableColumn property="hostOrigin.stage"--}%
            %{--title="${message(code: 'hostOrigin.stage.label', default: 'Host Stage')}"/>--}%
            %{--<th>Host Age (DPF)</th>--}%
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

                <td><i>${strainInstance?.genus?.name}</i></td>
                <td><i>${strainInstance?.genus?.phylum?.name}</i></td>

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
                    ${strainInstance.hostOrigin?.renderStageAndDpf}
                </td>

                <td>
                    <g:each var="genome" in="${strainInstance.genomes}">
                        <g:link action="show" id="${genome.id}" controller="genome">${genome.display}</g:link>
                    </g:each>
                    %{--<g:if test="${strainInstance.genomes}">--}%
                    %{--<g:link action="show" id="${strainInstance.genome.id}" controller="genome">Detail</g:link>--}%
                    %{--<a href="${strainInstance.genome.url}">Sequence</a>--}%
                    %{--</g:if>--}%
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
