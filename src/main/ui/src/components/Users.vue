<template>
    <div class="container">
        <h2>Users</h2>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>User Id</th>
                <th>Firstname</th>
                <th>Lastname</th>
                <th>Email</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
              <tr v-for="item in users" :key="item.id">
                  <td id="itemid" >{{ item.id }}</td>
                  <td>{{ item.firstName }}</td>
                  <td>{{ item.lastName }}</td>
                  <td>{{ item.email }}</td>
                  <td><button id="deletebutton" type="button" @click='deleteUsers(item.id)'>Delete</button></td>
              </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
import {
    deleteUser
} from '../services/UserService'

export default {
        name: 'Users',
        props: ['users'],
         methods: {

        deleteUsers(userid) {
       // var userid = document.getElementById("itemid").innerHTML;
        console.log(userid);
            deleteUser(userid).then(response => {
console.log(response);
            });

        }
 }
}



</script>