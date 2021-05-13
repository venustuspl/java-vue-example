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
                    <br>
  <br>
  <div class="form-group">
  <label class="mr-2 font-bold text-grey">User Filters</label>
  <br>
                    <button type="button" @click='getAllUsersByEmail()' class="btn btn-danger">Filter Users by part of Email</button>
                    <button type="button" @click='getAllUsersByLogin()' class="btn btn-danger">Filter Users by part of Login</button>

                    <input type="text" class="input" id="filterBy" placeholder="Input text">

          </div>


    </div>
    </div>
                             <Users v-if="users.length > 0" :users="users" @getAllUsers="getAllUsers()"/>
                        </div>
    <hr>
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
    getAllUsersByLogin,
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
                this.users = response
                this.numberOfUsers = this.users.length
            });
            console.log('Users have been displayed');
        },

        getAllUsersByEmail() {
            var email = document.getElementById("filterBy").value;
            this.saveErrors = '';
            this.users = [];
            this.numberOfUsers = 0;
            if (email.length > 0) {
                getAllUsersByEmail(email).then(response => {
                    if (response.status == 403) {
                        response.text().then((text) => {
                            console.log(text);
                            this.saveErrors = text;
                        });
                    } else {
                        this.users = response
                        this.numberOfUsers = this.users.length
                        console.log(response);
                    }
                })
            } else {
                getAllUsers().then(response => {
                    console.log('pusty filtr');
                    //this.users=response
                    this.numberOfUsers = this.users.length
                    console.log(response);
                })
            }
        },
        getAllUsersByLogin() {
                    var login = document.getElementById("filterBy").value;
                    this.saveErrors = '';
                    this.users = [];
                    this.numberOfUsers = 0;
                    if (login.length > 0) {
                        getAllUsersByLogin(login).then(response => {
                            if (response.status == 403) {
                                response.text().then((text) => {
                                    console.log(text);
                                    this.saveErrors = text;
                                });
                            } else {
                                this.users = response
                                this.numberOfUsers = this.users.length
                                console.log(response);
                            }
                        })
                    } else {
                        getAllUsers().then(response => {
                            console.log('pusty filtr');
                            this.numberOfUsers = this.users.length
                            console.log(response);
                        })
                    }
                },
        userCreate(data) {
            this.saveErrors = "";
                        console.log('user data');
                        console.log(data);
            data.id = this.numberOfUsers + 1
            createUser(data).then((response) => {
                if (response.status == 403) {
                    response.text().then((text) => {
                        console.log(text);
                        this.saveErrors = text;
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