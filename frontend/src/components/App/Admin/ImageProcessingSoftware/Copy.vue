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
    <actions-header slot="action-header" :title="'Copying Image Processing Software'"
                    @actionConfirm="submitBaseFormBy('mainForm')"></actions-header>
    <base-form slot="base-form" ref="baseForm" :entity="entity"></base-form>
  </form-component>
</template>

<script>
  import FormComponent from '@/components/App/FormComponent'
  import ActionsHeader from '@/components/App/ActionsHeader'
  import Commons from '@/components/App/Admin/ImageProcessingSoftware/Commons'
  import BaseForm from '@/components/App/Admin/ImageProcessingSoftware/BaseForm'
  import FormStateManager from '@/components/mixins/FormStateManager'

  export default {
    name: 'image-processing-copy',

    props: ['useSlug', 'id'],

    mixins: [
      FormStateManager, Commons
    ],

    components: {
      BaseForm, FormComponent, ActionsHeader
    },

    methods: {
      saveForm () {
        return this._imageProcessingSoftwareService.createEntity(this.entity)
          .then(result => {
            this.openViewForm('admin-software-image_processing', result.data, this.useSlug)
          })
      },

      loadFormData (slugOrId) {
        return this._imageProcessingSoftwareService.getEntityBySlugOrId(slugOrId)
          .then(result => {
            this.entity = {
              id: '',
              label: 'Copy of ' + result.data.label,
              softwareVersions: result.data.softwareVersions
            }
          })
      }
    }
  }
</script>
