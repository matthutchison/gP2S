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
    <actions-header slot="action-header" :title="'Copying processing session'"
                    @actionConfirm="submitBaseFormBy('mainForm')"></actions-header>
    <base-form slot="base-form" ref="baseForm" :entity="entity" :projectId="projectId"></base-form>
  </form-component>
</template>

<script>
  import FormComponent from '@/components/App/FormComponent'
  import Commons from '@/components/App/ProcessingSession/Commons'
  import BaseForm from '@/components/App/ProcessingSession/BaseForm'
  import ActionsHeader from '@/components/App/ActionsHeader'
  import ProjectResidentCommons from '@/components/mixins/ProjectResidentCommons'
  import FormStateManager from '@/components/mixins/FormStateManager'

  export default {
    name: 'processing-session-copy',

    props: ['useSlug'],

    mixins: [
      Commons, FormStateManager, ProjectResidentCommons
    ],

    components: {
      BaseForm, FormComponent, ActionsHeader
    },

    methods: {
      saveForm () {
        if (this.projectId) {
          return this._service.createEntity(this.projectId, this.entity)
            .then(result => this.openViewForm('processing_session', result.data, this.useSlug))
        }
        this.alertNoProject()
        return false
      },

      loadFormData (id) {
        return this._service.getEntityBy(id)
          .then(result => {
            const entity = result.data
            entity.label = 'Copy of ' + entity.label
            entity.id = null
            entity.slug = null
            this.entity = entity
          })
          .then(this.$refs.baseForm.loadMicroscopySessions)
      }
    }
  }
</script>

<style scoped>

</style>

