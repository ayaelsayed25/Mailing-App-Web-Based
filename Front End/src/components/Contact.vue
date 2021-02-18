<template>
    <div class="chip" id = "chip_content">
        <edit-contact v-if="editMode" :emails="contact.emails" :name="contact.name" @close="editMode = false"></edit-contact>
        <div class = "Menu">
            <button id="delete" @click="remove()" ><i class="material-icons myicon">delete</i></button>
            <button id="edit" @click="editMode = true;remove()"><i class="material-icons myicon">edit</i></button>
        </div>   
        <img src="../assets/1041_boy_c-512.png" alt="Contact" width="96" height="96">
        <b>{{contact.name}}</b>
        <div class = "emails" >
            <div  v-for="mail in contact.emails" :key="mail.id" >{{mail}}</div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import EditContact from './EditContact.vue'
let data = {}
export default {
  components: { EditContact },
    name:'Contact',
    props:{
        contact:Object,
    },
    data(){
        return{
            id:this.contact.name,
            editMode:false
        }
    },
    methods:{
        remove(){
            console.log(data)
            axios.get('http://localhost:8085//removeContact',{params:{
                id: this.id
            }})
            .then(Response =>{
                console.log(Response.data)
            })
            .catch(function(error){
                console.log(error)
            })
        },
        
    },
    mounted(){
        var delet = document.getElementById("delete");
        delet.id = this.contact.name + "_delete"
        var edit = document.getElementById("edit");
        edit.id = this.contact.name + "edit"
        
    },

}
</script>


<style scoped>

.chip {
    display: inline-block;
    padding: 0 25px;
    margin:10px ;
    font-size: 16px;
    height: 100px;
    line-height: 50px;
    border-radius: 25px;
    background-color: #f1f1f1;
    font-family: 'Open sans', serif ;
    box-shadow: 2px 2px 3px rgb(68, 38, 68);
}
.emails{
    margin: 0px;
    overflow: auto;
    height: 50px;
    padding: 0;
}
.chip img {
    float: left;
    margin: 0 10px 0 -25px;
    height: 50px;
    width: 50px;
    border-radius: 50%;
}
button{
  background-color:#f1f1f1;
  border: none;
  height: 30px;
  width: 30px;
  margin:3px 7px;
  outline: none;
  top: 0%;
  border-radius: 50%;
}
button:hover{
  background-color:#dadada;
}
.Menu{
  margin: 0px;
  padding: 0px;
  float: right;
}
i{
    font-size: 19px;
}

</style>