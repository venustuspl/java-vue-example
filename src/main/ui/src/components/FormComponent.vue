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
                  <login-component v-model="form.login" :v="$v.form.login" />
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
import LoginComponent from "./LoginComponent.vue";
import EmailComponent from "./EmailComponent.vue";

export default {
  name: "FormComponent",

  components: { FirstNameComponent, LastNameComponent, LoginComponent, EmailComponent },

  data() {
    return {
      form: {
        firstName: "",
        lastName: "",
        login: "",
        email: ""
      }
    };
  },

  validations: {
    form: {
      firstName: { required, minLength: minLength(2), maxLength: maxLength(32) },
      lastName: { required, minLength: minLength(2), maxLength: maxLength(32) },
      login: { required, minLength: minLength(2), maxLength: maxLength(32) },
      email: { required, email }
    }
  },

  methods: {
      createUser() {
          this.$v.form.$touch();
            // if its still pending or an error is returned do not submit
          if (this.$v.form.$pending || this.$v.form.$error) return;
          console.log(this.form.firstName)
          const payload = {
              firstName: this.form.firstName,
              lastName: this.form.lastName,
              login: this.form.login,
              email: this.form.email
          }
          this.$emit('createUser', payload)

      },
      clearForm() {
          this.firstName = "";
          this.lastName = "";
          this.login = "";
          this.email = "";
      }
  }
};
</script>
