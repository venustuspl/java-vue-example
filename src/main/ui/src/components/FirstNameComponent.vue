<template>
  <div class="form-group" :class="{ 'hasError': v.$error }">
    <label class="mr-2 font-bold text-grey">First name</label>
    <input type="text" class="input" v-model="firstName" placeholder="First name" @input="v.$touch()">
    <div class="text-sm mt-2 text-red" v-if="v.$error">
      <div v-if="!v.required">First name is a required</div>
      <div v-if="!v.minLength">First name length must be mor than  2 signs</div>
      <div v-if="!v.maxLength">First name length must less than  32 signs</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    value: {
      type: String,
      default: ""
    },
    v: {
      type: Object,
      required: true
    }
  },
  computed: {
    firstName: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      }
    }
  }
};
</script>
