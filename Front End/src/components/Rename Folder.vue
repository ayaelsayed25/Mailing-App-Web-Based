<template>
  <div id="renameFolder">
    <div class="modal-mask">
      <div class="modal-wrapper">
        <div class="modal-container">
          <div class="header">
            Rename Folder
          </div>
            <label for="name"></label>
            <input type="text" placeholder="Folder Name" name="name" id="folder-rename">
            <div id="message-folder-rename"></div>
            <button class="modal-default-button" id="okBtn-rename" @click="addFolderName">
                Ok
            </button>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RenameFolder',
  components: {
  },
  data(){
    return{
      newFolderName :''
    }
  },
  mounted() {
    
  },
  methods:{
    addFolderName()
    {
      var text = document.getElementById("folder-rename");
      this.newFolderName = text.value;
      console.log(this.newFolderName);
      this.$emit('renameFolder', this.newFolderName);
    }
  }
}
</script>

<style scoped>
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

.header{
  text-align: center;
  font-size: 20px;
  font-family: "Arial Rounded MT Bold",sans-serif;
  font-weight: bold;
  padding-bottom: 20px;
}
</style>
