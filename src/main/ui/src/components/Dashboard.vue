<template>
  <div class="hello">
    <Header />
    <div class="container mrgnbtm">
          <div class="row">
            <div class="col-md-8">
              <FormComponent @createUser="userCreate($event)" />
            </div>
            <div class="col-md-4">
                <DisplayBoard :numberOfUsers="numberOfUsers" @getAllUsers="getAllUsers()" />
            </div>
           <div class="text">
           {{saveErrors}}
           </div>
                    <div class="mx-auto" style="width: 100%;">
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
import { getAllUsers, createUser } from '../services/UserService'

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
      })
    },
    userCreate(data) {
 this.saveErrors = "";
      data.id = this.numberOfUsers + 1
      createUser(data).then((response) => {
      console.log(response);
      console.log("***");
      console.log(response.header);
      console.log(response.body);
      console.log("00");
      if (response.status == 403) {
             this.saveErrors = response.body;
      }
        this.getAllUsers();
             });
           }
  },
  mounted () {
    this.getAllUsers();
  }
}
</script>