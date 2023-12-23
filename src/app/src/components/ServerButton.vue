<script>
import axios from 'axios'

export default {
  props: {
    baseUrl: { type: String, default: 'api' },
    endpoint: String,
    requestBody: Object,
    successCallback: { type: Function, default: () => {} },
    failureCallback: { type: Function, default: () => {} },
    wakeupBackendOnLoad: Boolean,
    color: String
  },
  data() {
    return {
      callingServer: false
    }
  },
  methods: {
    invoke() {
      let self = this
      self.callingServer = true
      axios
        .post(self.baseUrl + '/' + self.endpoint, self.requestBody)
        .then((x) => {
          self.successCallback(x)
          self.callingServer = false
        })
        .catch((y) => {
          self.failureCallback(y)
          self.callingServer = false
        })
    }
  },
  mounted() {
    if (this.wakeupBackendOnLoad) {
      axios.get(this.baseUrl + '/ping')
    }
  }
}
</script>

<template>
  <va-button :color="color" @click="invoke" :disabled="callingServer" :loading="callingServer"
    ><slot>Submit</slot></va-button
  >
</template>

<style scoped></style>
