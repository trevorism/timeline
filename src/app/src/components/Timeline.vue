<script>

import {Canvas} from "jsdom/lib/jsdom/utils";

export default {

  data() {
    return {
      index: 0,
      url: ''
    }
  },

  methods: {
    convert() {
      let svg = this.$refs.timelineSvg
      let preface = '<?xml version="1.0" standalone="no"?>\r\n';
      let svgData = new XMLSerializer().serializeToString(svg)
      let svgBlob = new Blob([preface,svgData], {type: "image/svg+xml;charset=utf-8"})
      let svgUrl = URL.createObjectURL(svgBlob);
      this.url = svgUrl
    }
  }

}

</script>

<script setup>

import {VaSpacer} from "vuestic-ui";

defineProps({
  loaded: Boolean,
  timelineData: Object
})

</script>

<template>
  <div v-if="loaded">
    <div class="row">
      <div class="flex lg12">
        <va-card stripe stripe-color="primary" gradient>
          <va-card-title>Timeline</va-card-title>
          <svg xmlns="http://www.w3.org/2000/svg" width="1400" height="400" ref="timelineSvg">
            <rect v-for="item in timelineData.items" :x="65 + item.beginPixel" y="10"
                  :width="item.endPixel - item.beginPixel" height="40" :fill="item.color" stroke="grey"
                  stroke-width="1"/>
            <g v-for="item in timelineData.timelineDates">
              <line :x1="item.pixelLocation + 65" y1="50" :x2="item.pixelLocation + 65" y2="80" stroke="black"></line>
              <text :x="item.pixelLocation + 55" y="95">{{ item.dateString }}</text>
            </g>
            <g v-for="(item, index) in timelineData.items">
              <g v-if="item.employer != 'GAP'">
                <rect x="400" :y="130 + (index * 30)" width="20" height="20" :fill="item.color" stroke="black"
                      stroke-width="1"/>
                <text x="440" :y="145 + (index * 30)">
                  {{ item.employer }}&nbsp;&nbsp;&nbsp;&nbsp;{{ item.beginMonthYear }} - {{ item.endMonthYear }}
                </text>
              </g>
            </g>
          </svg>
          <va-button color="warning" @click="$emit('timelineHide')" class="va-text-warning">Hide</va-button>
          &nbsp;&nbsp;
          <a :href="url" target="_blank">
            <va-button color="info" @click="convert" class="va-text-warning">Open in New Tab</va-button>
          </a>

        </va-card>
      </div>
    </div>
  </div>
</template>

<style scoped>
.white-color {
  background-color: white;
}
</style>
