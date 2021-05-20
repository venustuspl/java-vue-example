<template>
    <div class="container">
        <h2>Users</h2>

        <table class="table table-bordered">
<caption></caption>
            <thead>
            <tr>
                <th id="uid"> User Id</th>
                <th id="ufirstname">Firstname</th>
                <th id="ulastname">Lastname</th>
                <th id="ulogin">Login</th>
                <th id="uemail">Email</th>
                <th id="udelete">Delete</th>
            </tr>
            </thead>
            <tbody>

              <tr v-for="item in users" :key="item.id">

                  <td id="itemid" >{{ item.id }}</td>
                  <td><input type="text" class="input" v-model="firstName" :placeholder=item.firstName @input="v.$touch()"></td>
                  <td><input type="text" class="input" v-model="lastName" :placeholder=item.lastName @input="v.$touch()"></td>
                  <td><input type="text" class="input" v-model="login" :placeholder=item.login @input="v.$touch()"></td>
                  <td><input type="text" class="input" v-model="email" :placeholder=item.email @input="v.$touch()"> </td>
                  <td><button id="deletebutton" type="button" @click='deleteUsers(item.id)'>Delete</button>
                  <button id="updatebutton" type="button" @click='updateUsers()'>Update</button>
                  </td>

              </tr>

            </tbody>
        </table>
    </div>
</template>

<script>
import {
    updateUser,
    deleteUser
} from '../services/UserService'



export default {
        name: 'Users',

         props: {
                 value: {
                       type: String,
                       default: ""
                     },

                     v: {
                       type: Object,
                       required: true
                     },
         users:{}
          },
          computed: {
          firstName: {
                          get() {
                            return this.value;
                          },

                          set(value) {
                            this.$emit("input", value);
                          }
                        },
          lastName: {
                          get() {
                            return this.value;
                          },

                          set(value) {
                            this.$emit("input", value);
                          }
                        },
              login: {
                get() {
                  return this.value;
                },

                set(value) {
                  this.$emit("input", value);
                }
              },
              email: {
                              get() {
                                return this.value;
                              },

                              set(value) {
                                this.$emit("input", value);
                              }

            }
            },
         methods: {
updateUsers() {
          this.$v.$touch();
            // if its still pending or an error is returned do not submit
          if (this.$v.$pending || this.$v.$error) return;
          console.log(this.firstName)
          const payload = {
              firstName: this.firstName,
              lastName: this.lastName,
              login: this.login,
              email: this.email
          }
        console.log(payload);
            updateUser(payload).then(response => {
console.log(response);
this.$emit('getAllUsers');
this.$forceUpdate();
            });
        },
        deleteUsers(userid) {
        console.log(userid);
            deleteUser(userid).then(response => {
console.log(response);
this.$emit('getAllUsers');
this.$forceUpdate();
            });
        }
 }
}
</script>