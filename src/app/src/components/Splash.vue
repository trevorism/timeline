<script setup>
import WorkHistories from "./WorkHistories.vue";
import Timeline from "./Timeline.vue";
</script>

<script>
  export default {
    data() {
      return {
        loadTimeline: false,
        timelineData: {}
      }
    },
    methods: {
      timelineCallback(x) {
        this.loadTimeline = true
        this.timelineData = x
      }
    }
  }
</script>

<template>
  <va-navbar color="#bad2c7" textColor="#000000">
    <va-navbar-item class="navbar-item-slot">
        <h2>Trevorism Timeline</h2>
    </va-navbar-item>
  </va-navbar>

  <work-histories @timeline="timelineCallback"></work-histories>

  <timeline :loaded="loadTimeline" :timelineData="timelineData" @timelineHide="loadTimeline = false"></timeline>

</template>

<style>
.va-navbar {
  --va-navbar-height: 30px;
  --va-navbar-padding-x: 0rem;
  --va-navbar-padding-y: 0rem;
  --va-navbar-item-margin-side: 30px;
  --va-navbar-item-padding-x: 0.5rem;
}
.indent {
  margin-left: 30px;
}

</style>
