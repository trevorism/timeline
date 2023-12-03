<script setup>
import WorkHistoryRow from "./WorkHistoryRow.vue";
import ServerButton from "./ServerButton.vue";
</script>

<script>

export default {
  emits: ['timeline'],
  data() {
    return {
      histories: [],
    }
  },
  methods: {
    addRow() {
      let obj = {"color": "#000000"}
      this.histories.push(obj)
    },
    success(x) {
      this.$emit("timeline", x.data)
    },
    removeMe(index) {
      this.histories.splice(index, 1)
    }
  }
}
</script>

<template>
  <div class="row">
    <div class="flex lg12">
      <va-card stripe stripe-color="primary" gradient>
        <va-card-title>Timeline Items</va-card-title>
        <va-card-content v-for="(history, index) in histories">
          <work-history-row @remove="removeMe(index)"
                            v-model:employer="history.employer"
                            v-model:startDate="history.startDate"
                            v-model:endDate="history.endDate"
                            v-model:color="history.color"/>
        </va-card-content>

        <va-card-actions align="right">
          <va-button @click="addRow">Add Row</va-button>
          <server-button wakeupBackendOnLoad
                         color="success"
                         endpoint="timeline"
                         :requestBody="histories"
                         :successCallback="success">
            Generate Timeline
          </server-button>
        </va-card-actions>
      </va-card>
    </div>
  </div>
</template>

<style scoped>
.va-card {
  --va-card-padding: 14px;
}
</style>