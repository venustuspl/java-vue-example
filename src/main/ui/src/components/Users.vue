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

                  <td id="itemid" ><input type="text" disabled="disabled" v-model="userid" :placeholder=item.id @input="v.$touch()"></td>
                  <td><input type="text" class="input" v-model="firstName" :placeholder=item.firstName  ></td>
                  <td><input type="text" class="input" v-model="lastName" :placeholder=item.lastName ></td>
                  <td><input type="text" class="input" v-model="login" :placeholder=item.login ></td>
                  <td><input type="text" class="input" v-model="email" :placeholder=item.email > </td>
                  <td><button id="deletebutton" type="button" @click='deleteUsers(item.id)'>Delete</button>
                  <button id="updatebutton" type="button" @click='updateUsers(item.id)'>Update</button>
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
data(){
  return {
    userid:"",
   firstName:"",
   lastName:"",
   login:"",
   email:""
 }
},
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
          methods: {
updateUsers(userid) {
          console.log(this.firstName)
           const payload = {
          id: userid,
              firstName: this.firstName,
              lastName: this.lastName,
              login: this.login,
              email: this.email
          }
        console.log('start');
            updateUser(payload).then(response => {
console.log(response);
this.$emit('getAllUsers');

            });
            this.$emit('getAllUsers');
window.location.reload(true);
            console.log('end');
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