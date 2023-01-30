<script setup>
import WorkHistoryRow from "./WorkHistoryRow.vue";
</script>

<script>
import axios from "axios";

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
    generate() {
      let self = this
      axios.post("api/timeline", this.histories).then((x) => {
        self.$emit("timeline", x.data)
      }).catch((y) => {
        console.log("Error" + JSON.stringify(y))
      });
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
        <va-card-title>Work History</va-card-title>
        <va-card-content v-for="(history, index) in histories">
          <work-history-row @remove="removeMe(index)"
                            v-model:employer="history.employer"
                            v-model:startDate="history.startDate"
                            v-model:endDate="history.endDate"
                            v-model:color="history.color"/>
        </va-card-content>

        <va-card-actions align="right">
          <va-button @click="addRow">Add Row</va-button>
          <va-button color="success" @click="generate">Generate Timeline</va-button>

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