<script setup>
import WorkHistoryRow from "./WorkHistoryRow.vue";
</script>

<script>
import axios from "axios";

export default {
  data() {
    return {
      histories: [],
    }
  },
  methods: {
    addRow() {
      let obj = {"color":"#000000"}
      this.histories.push(obj)
    },
    generate() {
      axios.post("api/timeline", this.histories).then((x) => {
        console.log("Success" + JSON.stringify(x))
      }).catch((y) => {
        console.log("Error" + JSON.stringify(y))
      });
    },
    removeMe(index){
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
          <work-history-row @remove="removeMe(index)" v-model:employer="history.employer" v-model:startDate="history.startDate" v-model:endDate="history.endDate"
                            v-model:color="history.color"></work-history-row>
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