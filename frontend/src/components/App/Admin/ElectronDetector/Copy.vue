<!--
  - Copyright 2018 Genentech Inc.
  -
  - Licensed under the Apache License, Version 2.0 (the "License");
  - you may not use this file except in compliance with the License.
  - You may obtain a copy of the License at
  -
  -     http://www.apache.org/licenses/LICENSE-2.0
  -
  - Unless required by applicable law or agreed to in writing, software
  - distributed under the License is distributed on an "AS IS" BASIS,
  - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  - See the License for the specific language governing permissions and
  - limitations under the License.
  -->

<template>
  <form-component>
    <actions-header slot="action-header" :title="'Copying electron detector'" @actionConfirm="submitBaseFormBy('mainForm')"></actions-header>
    <base-form slot="base-form" ref="baseForm" :entity="entity"></base-form>
  </form-component>
</template>

<script>
  import FormComponent from '@/components/App/FormComponent'
  import ActionsHeader from '@/components/App/ActionsHeader'
  import Commons from '@/components/App/Admin/ElectronDetector/Commons'
  import BaseForm from '@/components/App/Admin/ElectronDetector/BaseForm'
  import Service from '@/services/ElectronDetectorService'
  import FormStateManager from '@/components/mixins/FormStateManager'

  const service = new Service()

  export default {
    name: 'electron-detector-copy',

    props: ['useSlug', 'id'],

    mixins: [
      FormStateManager, Commons
    ],

    components: {
      BaseForm, FormComponent, ActionsHeader
    },

    methods: {
      saveForm () {
        return service.createEntity(this.entity)
                      .then(result => {
                        this.openViewForm('admin-equipment-electron_detectors', result.data, this.useSlug)
                      })
      },

      loadFormData (slugOrId) {
        return service.getEntityBySlugOrId(slugOrId)
               .then(result => {
                 this.entity = {
                   id: null,
                   slug: null,
                   label: 'Copy of ' + result.data.label,
                   manufacturer: result.data.manufacturer,
                   model: result.data.model,
                   microscope: result.data.microscope,
                   countsPerElectronsFactor: result.data.countsPerElectronsFactor,
                   countingModeAvailable: result.data.countingModeAvailable,
                   pixelLinearDimensionUm: result.data.pixelLinearDimensionUm,
                   numberOfPixelColumns: result.data.numberOfPixelColumns,
                   numberOfPixelRows: result.data.numberOfPixelRows,
                   doseFractionationAvailable: result.data.doseFractionationAvailable,
                   superResolutionAvailable: result.data.superResolutionAvailable,
                   availableMagnifications: result.data.availableMagnifications || []
                 }
                 // Removing IDs from magnifications.
                 this.entity.availableMagnifications.forEach(item => item.id = null)
               })
      }
    }
  }
</script>

<style scoped>

</style>

