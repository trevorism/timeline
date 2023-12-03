<script setup>
import {VaButton} from "vuestic-ui";
</script>

<script>
export default {
  props: {
    employer: String,
    startDate: String,
    endDate: String,
    color: String,
  },
  emits: ['update:employer','update:startDate','update:endDate','update:color','remove'],
  data() {
    return {}
  },
  methods: {
    emitStartDate(x){
      if(typeof x.toLocaleDateString === 'function')
        this.$emit("update:startDate", x.toLocaleDateString())
      else{
        this.$emit("update:startDate", x)
      }
    },
    emitEndDate(x){
      if(typeof x.toLocaleDateString === 'function')
        this.$emit("update:endDate", x.toLocaleDateString())
      else{
        this.$emit("update:endDate", x)
      }
    },

  }
}
</script>

<template>

  <div class="row">
    <div class="flex">
      <span class="flex md1 mx-2 mt-1"> Employer: </span> <span class="flex md1"> <va-input :model-value="employer" @update:model-value="$emit('update:employer', $event)"/> </span>
      <span class="flex md1 mx-2 mt-1"> Start Date: </span> <span class="flex md1"> <va-date-input :model-value="startDate" @update:model-value="emitStartDate" manual-input mode="single"/> </span>
      <span class="flex md1 mx-2 mt-1"> End Date: </span> <span class="flex md1"> <va-date-input :model-value="endDate" @update:model-value="emitEndDate" manual-input mode="single"/> </span>
      <span class="flex md1 mx-2 mt-1"> Color: </span> <span class="flex md1">  <va-color-input :model-value="color" @input="$emit('update:color', $event.target.value)"/>  </span>
      <span class="flex md1 mx-2"> <va-button @click="$emit('remove')" color="danger">Delete</va-button></span>
    </div>
  </div>
</template>


<style scoped>
.va-color-input {
  display: inline;
}
</style>