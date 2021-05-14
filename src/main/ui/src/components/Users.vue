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
                  <td>{{ item.firstName }}</td>
                  <td>{{ item.lastName }}</td>
                  <td>{{ item.login }}</td>
                  <td>{{ item.email }}</td>
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
        props: ['users'],
         methods: {
updateUsers(userid) {
        console.log(userid);
            updateUser(userid).then(response => {
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