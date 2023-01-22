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
  <va-navbar color="#111111" textColor="#BAFFC5">
    <va-navbar-item class="navbar-item-slot">Trevorism Timeline</va-navbar-item>
  </va-navbar>
  <div class="pa-4">
    Create your timeline here
  </div>
  <h1>{{ msg }}</h1>
  <h1>{{ val }}</h1>

</template>

<style scoped>
</style>
