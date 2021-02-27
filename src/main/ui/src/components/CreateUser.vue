<template>
  <div class="container">
    <div class="row">
        <div class="col-md-7 mrgnbtm">
        <h2>Create User</h2>
            <form>
                <div class="row">
                    <div class="form-group col-md-6" >
                        <label htmlFor="exampleInputEmail1">First Name</label>
                        <input type="text" class="form-control"
                         v-model="firstName" name="firstname" id="firstname"
                          aria-describedby="emailHelp" placeholder="First Name"
                     />

                    </div>
                    <div class="form-group col-md-6">
                        <label htmlFor="exampleInputPassword1">Last Name</label>
                        <input type="text" class="form-control" v-model="lastName" name="lastname" id="lastname" placeholder="Last Name"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-12">
                        <label htmlFor="exampleInputEmail1">Email</label>
                        <input type="text" class="form-control" v-model="email" name="email" id="email" aria-describedby="emailHelp" placeholder="Email" />
                    </div>
                </div>
                <button type="button" @click='createUser()' class="btn btn-danger">Create</button>

            </form>
        </div>
    </div>

    </div>

</template>

<script>
import {  minLength } from 'vuelidate/lib/validators'

export default {
  name: 'CreateUser',
  data() {
    return {
      firstName: '',
      lastName: '',
      email: ''
    }
  },
    validations: {
      firstName: {
        minLength: minLength(2)
      }
    },
  methods: {
      createUser() {
            this.$v.$touch()
            if (this.$v.$invalid) {
              this.submitStatus = 'ERROR'
            } else {
              // do your submit logic here
              this.submitStatus = 'PENDING'
              setTimeout(() => {
                this.submitStatus = 'OK'
              }, 500)
            }
          console.log(this.firstName)
          const payload = {
              firstName: this.firstName,
              lastName: this.lastName,
              email: this.email
          }
          this.$emit('createUser', payload)
          this.clearForm();
      },
      clearForm() {
          this.firstName = "";
          this.lastName = "";
          this.email = "";
      }
  }
}
</script>