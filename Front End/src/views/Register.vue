<template>
  <div id="background">
    <div class="container">
      <h1>Register</h1>
      <hr>

      <label for="email"></label>
      <input type="text" placeholder="Email" name="email" id="email" required>

      <label for="username"></label>
      <input type="text" placeholder="User Name" name="username" id="username" required>

      <label for="psw"></label>
      <input type="password" placeholder="Password" name="psw" id="psw" required>

      <label for="psw-repeat"></label>
      <input type="password" placeholder="Repeat Password" name="psw-repeat" id="psw-repeat" required>
      <hr>

      <button type="submit" class="registerbtn" id="register" @click="createAccount">Register</button>
      <div id="message"></div>
    </div>

    <div class="container signin">
      <p>Already have an account?<router-link to="/">Sign in</router-link>.</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
const apiLink = 'http://localhost:8085'
export default {
  name: "Register",
  data(){
    return{
    }
  },
  methods :{
    createAccount(){
      var password = document.getElementById("psw").value;
      var repeatPass = document.getElementById("psw-repeat").value;
      var name = document.getElementById("username").value;
      var email = document.getElementById("email").value;
      var text = document.getElementById("message");
      if(password ==='' || name === '' || email ==='')
      {
        text.innerHTML = "You should fill all fields!";
        return;
      }
      else if(password !== repeatPass)
      {
        text.innerHTML = "The two passwords are not identical!"
        return;
      }
      axios.get(apiLink + '/createAccount', {params:{ name : name,
        email: email,
        password: password }})
      .then(response => {
          if(!response.data)
          {
            text.innerHTML = "The Email Already Exists! Try another one."
          }
          else
          {
            this.$router.push({ name: 'Home' });
          }
      })
      .catch(function (error) {
          console.log(error);
      });
    },
  }      
}
</script>

<style scoped>

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  color: white;
  z-index: 99999999999;
  }

#background{
  position: absolute;
  top: 0; right: 0; bottom: 0; left: 0;
  background-image: linear-gradient(rgba(0,0,0,0.1),rgba(0,0,0,4)),url('login.jpg');
  background-size:cover;
  background-position: center;
}
/* Add padding to containers */
.container {
  text-align: center;
  /*height: 10%;*/
  width: 20%;
  margin: 50px auto auto;
  padding: 16px;
  /*background: white;*/
  position: relative;
}

/* Full-width input fields */
input[type=text], input[type=password] {
  width: 100%;
  padding: 10px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  border-bottom: white 1px solid;
  background: transparent;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

/* Overwrite default styles of hr */
hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}
h1{
  margin-bottom: 10px;
}
/* Set a style for the submit/register button */
.registerbtn {
  background: linear-gradient(to right,#210535,#7b337d);
  color: white;
  font-size: 15px;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  border-radius: 30px;
}

.registerbtn:hover {
  opacity:1;
}

#message {
  color: white;
  font-family: 'Open sans', serif;
  font-size: 14px;
  margin-top: 30px;
}

/* Add a blue text color to links */
a {
  color: #f5d5e0;
}

/* Set a grey background color and center the text of the "sign in" section */
.signin {
  text-align: center;
}

</style>