<script>
import axios from 'axios'

export default {
  data() {
    return {
      count: 0,
      val: "zzz"
    }
  },
  mounted() {
    axios.get("api/ping").then((x) => {
      this.val = x.data
    }).catch(() => {
      this.val = "Error"
    });
  }

}

</script>

<script setup>

defineProps({
  msg: String
})

</script>

<template>

  <div class="pa-4">
    Create your timeline here
  </div>
  <h1>{{ msg }}</h1>
  <h1>{{ val }}</h1>

</template>

<style scoped>
</style>
