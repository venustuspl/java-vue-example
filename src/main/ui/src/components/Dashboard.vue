<template>
  <div class="hello">
    <Header />
    <div class="container mrgnbtm">
          <div class="row">
            <div class="col-md-8">
                       <div class="text">
                       {{saveErrors}}
                       </div>
              <FormComponent @createUser="userCreate($event)" />
            </div>
            <div class="col-md-4">
                         <DisplayBoard :numberOfUsers="numberOfUsers" @getAllUsers="getAllUsers()" />
            </div>

                    <div class="mx-auto" style="width: 100%;">
                    <input type="text" class="input" id="filterByEmail" placeholder="Email">
                         <button type="button" @click='getAllUsersByEmail()' class="btn btn-danger">Filter</button>
                        <Users v-if="users.length > 0" :users="users" />
                    </div>
          </div>
    </div>

  </div>
</template>

        <script>
import Header from './Header.vue'
import DisplayBoard from './DisplayBoard.vue'
import Users from './Users.vue'
import FormComponent from './FormComponent.vue'
import {
    getAllUsers,
    getAllUsersByEmail,
    createUser
} from '../services/UserService'

export default {
    name: 'Dashboard',
    components: {
        Header,
        DisplayBoard,
        Users,
        FormComponent
    },
    data() {
        return {
            users: [],
            numberOfUsers: 0,
            saveErrors: ""
          }
    },

    methods: {
        getAllUsers() {
            getAllUsers().then(response => {
                this.users=response
                this.numberOfUsers=this.users.length
            })
        },

        getAllUsersByEmail() {
        var email = document.getElementById("filterByEmail").value;
        console.log(email);
        if (email.length > 0){
            getAllUsersByEmail(email).then(response => {
        this.users=response
                            this.numberOfUsers=this.users.length
                            console.log(response);
            })
            }
            else{
                        getAllUsers().then(response => {
                        console.log('pusty filtr');
                            //this.users=response
                            this.numberOfUsers=this.users.length
                           console.log(response);
            })
            }
        },
        userCreate(data) {
            this.saveErrors="";
            data.id=this.numberOfUsers + 1
            createUser(data).then((response) => {
               if (response.status == 403) {
                response.text().then((text) => {
                    console.log(text);
                    this.saveErrors=text;
                });
                }
                this.getAllUsers();
            });
        }
    },
    mounted() {
        this.getAllUsers();
    }
}
 </script>