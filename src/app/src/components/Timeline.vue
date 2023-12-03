<script>
import { nextTick } from 'vue'

export default {

  data() {
    return {
      index: 0,
      imageMode: false,
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
      this.imageMode = true
    },
    hide() {
      this.$emit('timelineHide')
      this.imageMode = false
    },
    canvas() {
      const canvas = this.$refs.canvas
      const ctx = canvas.getContext("2d");
      const image = this.$refs.image
      ctx.clearRect(0,0,1400,400)
      ctx.drawImage(image, 0,0,1400,400)
      image.style.display = "none"
    },
    download() {
      const a = document.createElement('a');
      a.href = this.$refs.canvas.toDataURL()
      a.download = "timeline.png";
      a.click();
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
    <div class="row" v-if="imageMode">
      <img ref="image" style="position: absolute; top: -5000px" width="1400" height="400" :src="url" @load="canvas">
      <canvas ref="canvas" width="1400" height="400"> </canvas>
    </div>
    <div class="row" v-if="!imageMode">
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
        </va-card>
      </div>
    </div>
    <div class="row">
      <va-spacer></va-spacer>
      <va-button color="warning" @click="hide" class="va-text-warning">Hide</va-button> &nbsp;&nbsp;
      <va-button v-if="!imageMode" color="info" @click="convert" class="va-text-warning">Convert To PNG</va-button> &nbsp;&nbsp;
      <va-button v-if="imageMode" color="primary" @click="download" class="va-text-warning">Download Image</va-button>
    </div>
  </div>
</template>

<style scoped>

</style>
