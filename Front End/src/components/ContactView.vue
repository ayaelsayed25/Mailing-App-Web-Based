<template>
    <div class ="container">
        <contact class="contact_view" v-for="contact in contactlist" :key="contact.id" :contact= "contact"  ></contact> 
    </div>
</template>

<script>
// import axios from 'axios'
// let apiUrl = 'http://localhost:8085'
import Contact from './Contact.vue'
export default {
    components: {
        Contact
    },props:{
      contactlist:[]
  },
    name: 'ContactView',
    data(){
    return{
        beforeMount:true,
    }
    },
    // beforeMount(){
    //     if(this.beforeMount)
    //         this.getContacts()
    // },
    methods:{

    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container{
    padding-top: 10px;
    padding-left: 15px;
    background: linear-gradient(180deg,  white 0%,rgb(173, 105, 126) 100%);
    height: 100%;
    overflow: auto;
    display: grid;
    grid-template-columns: repeat(3, 1fr);
    gap: -10px;
}

</style>
