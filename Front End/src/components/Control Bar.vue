<template>
  <div id="controlBar">
    <div id="mycheck"><input type="checkbox" value="all" id="selectAll" @click="selectAll">&nbsp;Select all</div>
    <div id="trash" @click="$emit('getCheckedMails', true)"><i class="fas fa-trash-alt"></i>&nbsp;Delete</div>
    <div id="move" @click="$emit('openMoveTo')"><i class="fas fa-folder-open"></i>&nbsp;Move E-mails</div>
  </div>
</template>

<script>
export default {
  name: 'ControlBar',
  components: {
  },
  props: {
  },
  data(){
    return{
    }
  },
  mounted() {
    
  },
  methods:{
   selectAll()
   {
     var all = document.getElementById("selectAll").checked;
     console.log(all);
     var array = document.getElementsByClassName("check-boxes");
     for(var i=0; i<array.length; i++)
     {
       array[i].checked = all;
     }
   }
  }
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
#controlBar {
  position: relative;
  display: flex;
  width:100%;
  height:25px;
  left: 0;
  top: 20.6%;
  color: white;
  background-color: #6f6d72;
  font-family: 'Open sans', serif;
}
#controlBar > div {
  position: absolute;
}
#trash {
  cursor: pointer;
  left: 300px;
}
#mycheck {
  left: 50px;
}
#move {
  left: 550px;
  cursor: pointer;
}
</style>
