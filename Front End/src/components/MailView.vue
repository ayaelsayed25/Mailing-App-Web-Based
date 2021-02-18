<template>
  <div id="con">
    
    <div id = "mails_view" class="Mails"  v-for="male in maillist " :key="male.id" >
      <div class="checkAndBox">
      <input type="checkbox" v-if="mailContent === false" v-bind:value="male.id" class="check-boxes">
        <mail-box v-if="mailContent === false" :mail= "male" :id= "male.id" @click="setMail(male.id)"
                  :searchResults ="searchResults"></mail-box>
      </div>
    </div>

    <mails-content v-if="mailContent" :currentFolder="currentFolder" :id="id"></mails-content>

   <div id="pagination" @click="$emit('paging')" >
    <button class="left" @click="changePageNumber(-1)" > &laquo;</button> 
    <button id="1" class="btn active">1</button>
    <button id="2" class="btn ">2</button>
    <button id="3" class="btn">3</button>
    <button id="4" class="btn" >4</button>
 
     <button class="right" @click="changePageNumber(1)"> &raquo;</button> 
</div>
  </div>
</template>

<script>
import MailBox from './MailBox.vue'
import MailsContent from './MailsContent.vue'

export default {
  components: {
    'mail-box': MailBox ,
    'mails-content':MailsContent
   
  },
  name: 'MailView',
  props: {
    maillist: Array,
    currentFolder:String,
    searchResults:[]
  },
  data(){
    return{
      id:'',
      mailContent :false
      // component:'mail-box',

    }
  },
 
  methods:{
    setMail(id){
      this.mailContent = true
      this.id = id
    },
   changePageNumber(num){

     const header = document.getElementById("pagination");
     const btns = header.getElementsByClassName("btn");
     console.log(btns.length)
      for (let i = 0; i < btns.length; i++) {
        if(btns[i].id==="1"&&num===-1){ break}
       btns[i].innerHTML=String(parseInt(btns[i].innerHTML)+num);
       btns[i].id=String(parseInt(btns[i].id)+num);
       console.log(btns[i])
      }

   } 
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


.btn, .left,.right {
  border: none;
  outline: none;
  padding: 10px 16px;
  background-color: #f1f1f1;
  cursor: pointer;
  font-size: 18px;
}
.checkAndBox {
  display: flex;
}
.check-boxes {
  position: absolute;
  margin-top: 15px;
  margin-left: 10px;
}
/* Style the active class, and buttons on mouse-over */
.active, .btn:hover,.left:hover,.right:hover {
  background-color: #666;
  color: white;
}

#pagination{
  position: fixed;
  top:710px; 
}

</style>
