<template>
  <div class="form pt-6">
    <div class="summary text-red" v-if="$v.form.$error">
      Form has errors
    </div>
    <form @submit.prevent="submit" novalidate>
      <div class="col-md-7 mrgnbtm">
        <div class="px-4">
           <first-name-component v-model="form.firstName" :v="$v.form.firstName"/>
        </div>
        <div class="px-4">
           <last-name-component v-model="form.lastName" :v="$v.form.lastName"/>
        </div>
        <div class="px-4">
          <email-component v-model="form.email" :v="$v.form.email" />
        </div>
      </div>
      <div class="text-center">
  <button type="button" @click='createUser()' class="btn btn-danger">Create</button>
      </div>
    </form>
  </div>
</template>

<script>
import { required, email , minLength, maxLength } from "vuelidate/lib/validators";
import FirstNameComponent from "./FirstNameComponent.vue";
import LastNameComponent from "./LastNameComponent.vue";
import EmailComponent from "./EmailComponent.vue";

export default {
  name: "FormComponent",

  components: { FirstNameComponent, LastNameComponent, EmailComponent },

  data() {
    return {
      form: {
        firstName: "",
        lastName: "",
        email: ""
      }
    };
  },

  validations: {
    form: {
      firstName: { required, minLength: minLength(2), maxLength: maxLength(32) },
      lastName: { required, minLength: minLength(2), maxLength: maxLength(32) },
      email: { required, email }
    }
  },

  methods: {
      createUser() {
          console.log(this.form.firstName)
          const payload = {
              firstName: this.form.firstName,
              lastName: this.form.firstName,
              email: this.form.firstName
          }
          this.$emit('createUser', payload)

      },
      clearForm() {
          this.firstName = "";
          this.lastName = "";
          this.email = "";
      }
  }
};
</script>
