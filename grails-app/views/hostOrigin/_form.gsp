<%@ page import="edu.uoregon.stockdb.StagesEnum; edu.uoregon.stockdb.HostGenotype; edu.uoregon.stockdb.HostOrigin" %>



<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'stage', 'error')} ">
    <label for="stage">
        <g:message code="hostOrigin.stage.label" default="Stage"/>
    </label>
    %{--<g:textField name="stage" value="${hostOriginInstance?.stage}"/>--}%
    <g:select name="stage" from="${StagesEnum.values()}" value="${hostOriginInstance?.stage}"
    noSelection="['null':'- None -']"
    />
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'daysPastFertilization', 'error')} ">
    <label for="days">
        <g:message code="hostOrigin.days.label" default="Days"/>
    </label>
    <g:field name="daysPastFertilization" type="number" value="${hostOriginInstance.daysPastFertilization}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'anatomy', 'error')} ">
    <label for="anatomy">
        <g:message code="hostOrigin.anatomy.label" default="Anatomy"/>
    </label>
    <g:textField name="anatomy" value="${hostOriginInstance?.anatomy}" size="60"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'anatomyUrl', 'error')} ">
    <label for="anatomy">
        <g:message code="hostOrigin.anatomyUrl.label" default="Anatomy Url"/>
    </label>
    <g:textField name="anatomyUrl" value="${hostOriginInstance?.anatomyUrl}" size="80"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'hostFacility', 'error')} ">
    <label for="hostFacility">
        <g:message code="hostOrigin.hostFacility.label" default="Host Facility"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="hostFacility" name="hostFacility.id" from="${edu.uoregon.stockdb.HostFacility.list()}" optionKey="id"
              value="${hostOriginInstance?.hostFacility?.id}" class="many-to-one"
              optionValue="name"
              noSelection="['null': '- Choose Existing -']"/>
    <g:link action="create" controller="hostFacility">Create Host Facility</g:link>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'genotypes', 'error')} ">
    <label for="genotype">
        <g:message code="hostOrigin.genotype.label" default="Genotype"/>
    </label>
    <g:select name="genotypes" from="${HostGenotype.listOrderByName()}" optionKey="id"
              value="${hostOriginInstance?.genotypes?.id}" class="many-to-one" multiple="true"
              optionValue="name"
    />
    <g:link action="create" controller="hostGenotype">Create Host Genotype</g:link>

</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'species', 'error')} ">
    <label for="species">
        <g:message code="hostOrigin.species.label" default="Species"/>
        <span class="required-indicator">*</span>
    </label>
    <g:select id="species" name="species.id" from="${edu.uoregon.stockdb.Species.list()}" optionKey="id"
              value="${hostOriginInstance?.species?.id}" class="many-to-one"
              optionValue="commonName"
              noSelection="['null': '- Choose Existing -']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'strains', 'error')} ">
    <label for="strains">
        <g:message code="hostOrigin.strains.label" default="Strains"/>
    </label>

    %{--<g:select id="strain" name="addstrainid" from="${edu.uoregon.stockdb.Strain.listOrderByName()}" optionKey="id"--}%
    %{--<g:select id="strain" name="addstrainid" from="${edu.uoregon.stockdb.Strain.findAllByHostOriginIsNull() + hostOriginInstance?.strains}" optionKey="id"--}%
    <g:select id="strains" name="strains" from="${strains}" optionKey="id"
              class="many-to-one"
              optionValue="name"
        value="${hostOriginInstance?.strains?.id}"
        multiple="true"
    />
    %{--noSelection="['null': '- Add Existing -']" --}%
    <g:link action="create" controller="strain">Create Strain</g:link>

</div>

%{--<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'strains', 'error')} ">--}%
    %{--<label for="strains">--}%
        %{--<g:message code="hostOrigin.strains.label" default="Existing Strains"/>--}%

    %{--</label>--}%

    %{--<ul class="one-to-many">--}%
        %{--<g:each in="${hostOriginInstance?.strains ?}" var="s">--}%
            %{--<li>--}%
                %{--<g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link>--}%
                %{--<g:link controller="hostOrigin" action="removeStrainFromHostOrigin" params="[strainId:s.id,hostOriginId:hostOriginInstance.id]">[remove]</g:link>--}%

            %{--</li>--}%
        %{--</g:each>--}%
    %{--</ul>--}%


%{--</div>--}%


<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'notes', 'error')} ">
    <label for="notes">
        <g:message code="hostOrigin.notes.label" default="Notes"/>

    </label>
    <g:textArea name="notes" value="${hostOriginInstance?.notes}" rows="3" cols="40"/>
</div>
