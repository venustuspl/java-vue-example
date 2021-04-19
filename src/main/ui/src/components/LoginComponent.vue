<template>
  <div class="form-group" :class="{ 'hasError': v.$error }">
    <label class="mr-2 font-bold text-grey">Login</label>
    <input type="text" class="input" v-model="login" placeholder="login" @input="v.$touch()">
    <div class="text-sm mt-2 text-red" v-if="v.$error">
      <div v-if="!v.required">Login is required</div>
      <div v-if="!v.login">Login is not a properly formatted</div>
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
    login: {
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
