<template>
  <div>
    <div class="d-flex">
      <v-checkbox
        v-model="disabled"
        label="Disabled"
      ></v-checkbox>
    </div>

    <v-expansion-panels
      v-model="panel"
      :disabled="disabled"
      multiple
    >
      <v-expansion-panel>
        <v-expansion-panel-header>意见反馈</v-expansion-panel-header>
        <v-expansion-panel-content>
            意见反馈
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>帐号安全问题</v-expansion-panel-header>
        <v-expansion-panel-content>
          帐号安全问题
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>其他</v-expansion-panel-header>
        <v-expansion-panel-content>
          其他
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>
</template>
<script>
  export default {
    data: () => ({
      panel: [0, 1],
      disabled: false,
      readonly: false,
    }),
  }
</script>