<script setup>
import WorkHistories from "./WorkHistories.vue";
</script>

<template>
  <va-navbar color="#bad2c7" textColor="#000000">
    <va-navbar-item class="navbar-item-slot">
        <h2>Trevorism Timeline</h2>
    </va-navbar-item>
  </va-navbar>

  <work-histories></work-histories>
</template>

<style scoped>
.va-navbar {
  --va-navbar-height: 56px;
}
</style>
