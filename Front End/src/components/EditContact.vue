<template>
  <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="header">
            Edit Contact
          </div>
            <label for="name"></label>
            <input type="text"  name="name" id="new_name" :value="name"  required>
          <div id="new_mails">
            <label for="email"></label>
            <input type="text" name="email" v-for="email in emails" :key="email.id" :value="email" class="new_email" required>
          </div>
          <button class="add-secondary-mail" v-on:click="addMail">
            + Add secondary mail
          </button>
            <button class="modal-default-button" v-on:click="$emit('close'), sendContact()">
                OK
              </button>
          </div>
      </div>
    </div>
  </transition>
</template>

<script>
let contact= {};
import axios from 'axios'
export default {
  name : "AddContact",
  props:{
      emails:Array,
      name:String
  },
  data() {
    return {
      secondaryMailsCount : 0,
    }
  },
  methods:{
    addMail(){
      const mailsContainer = document.getElementById("new_mails")
      const input = document.createElement("input");
      input.type = "text";
      input.name = "email" ;
      input.id= "Secondary Mail "+ (parseInt(this.secondaryMailsCount) + 1 );
      input.placeholder = "Secondary Mail "+ (parseInt(this.secondaryMailsCount) + 1 )
      this.secondaryMailsCount ++
      mailsContainer.appendChild(input);
      mailsContainer.appendChild(document.createElement("br"));
    },

    
    sendContact(){
        var email =[];
        var name = document.getElementById("new_name").value,
            emails = document.getElementsByClassName("new_email");
        var j ; 
        for(j = 0 ; j < emails.length;j++){
            email[j] = emails[j].value
        } 
        for(var i = 0;i<this.secondaryMailsCount;i++){
            email[i+j]=document.getElementById("Secondary Mail "+ (parseInt(i) + 1 )).value;

        }
        contact={
            name:name,
            email:email

        }
        console.log(contact)
        axios.post('http://localhost:8085//addContact',contact)
      .then(response => {
        console.log(response.data)
        
      })
      .catch(function (error) {
          console.log(error);
      });
    }  
  }
}
</script>


<style>
.modal-mask {
position: fixed;
z-index: 9998;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: rgba(0, 0, 0, 0.5);
display: table;
transition: opacity 0.3s ease;
}

.modal-wrapper {
display: table-cell;
vertical-align: middle;
}

.modal-container {
width: 300px;
margin: 0 auto;
padding: 20px 30px;
background-color: #fff;
border-radius: 30px;
box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
transition: all 0.3s ease;
font-family: Helvetica, Arial, sans-serif;
}


.modal-default-button {
  background: linear-gradient(to right,#210535,#7b337d);
  color: white;
  font-size: 15px;
  padding: 16px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
  opacity: 0.9;
  outline: none;
  border-radius: 30px;

}

/*
* The following styles are auto-applied to elements with
* transition="modal" when their visibility is toggled
* by Vue.js.
*
* You can easily play with the modal transition by editing
* these styles.
*/

.modal-enter {
opacity: 0;
}

.modal-leave-active {
opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
-webkit-transform: scale(1.1);
transform: scale(1.1);
}

/* Full-width input fields */
input[type=text] {
  width: 100%;
  padding: 5px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  border-bottom: grey 1px solid;
}

input[type=text]:focus {
  background-color: #ddd;
  outline: none;
}
.add-secondary-mail{
  outline: none;
  background: transparent;
  border: none;
  cursor: pointer;
  color: #430D4B;
  font-size: 17px;


}
.header{
  text-align: center;
  font-size: 20px;
  font-family: "Arial Rounded MT Bold",sans-serif;
  font-weight: bold;
}

</style>