<template>
  <div id="home">
  <add-contact v-if="addContact" @close="addContact = false"  ></add-contact>
    <div id="brand">
        <h1>COMET</h1>
        <img src="..\assets\1.jpg" id="image">
        <div id="right-side">
          <div id="account-menu">
            <div id="account" @mouseover="display" @mouseout="hide"></div>
            <div id="account-info">
              <div>
                <div>Name</div>
                <div id="name">Aya Elsayed</div>
              </div>
              <div>
                <div>Email</div>
                <div id="email">ayaelsayed5209297897896896767676786@comet.com</div>
              </div>
            </div>
          </div>
          <div id="sign-out"><router-link id="sign-out-link" to="/register">Sign out</router-link>&nbsp; <i class="fas fa-sign-out-alt"></i></div>
        </div>
    </div>
    <div id="upper-bar">
      <div id="sort">
        <div id="sortBy"><i class="fas fa-sort"></i>  SORT BY</div> <i class="fas fa-caret-down list-icon" id="list-icon"></i>
        <div id="mySortList">
          <div id="first-item" @click="clickSort">Date</div>
          <ul id="sortList">
            <li @click="clickItem" v-if="component !== 'contact-view'">Senders</li>
            <li @click="clickItem" v-if="component !== 'contact-view'">Receivers</li>
            <li @click="clickItem" v-if="component !== 'contact-view'">Importance</li>
            <li @click="clickItem" v-if="component !== 'contact-view'">Subject</li>
            <li @click="clickItem" v-if="component !== 'contact-view'">Body</li>
            <li @click="clickItem" v-if="component !== 'contact-view'">Attachments</li>
            <li @click="clickItem" v-if="component === 'contact-view'">Email</li>
            <li @click="clickItem" v-if="component === 'contact-view'">Name</li>
          </ul>
        </div>
      </div>
      <div id="filtering" v-if="component !== 'contact-view'">
        <div id="filter" @click="clickFilter">Filter Mails&nbsp;<i class="fas fa-caret-down check-icon" id="check-icon"></i></div>
        <div id="check-menu">
          <div>
          <label >Sender :  </label>
          <input type="text" id="sender-filter">
          </div>
          <div>
          <label>Subject :  </label>
          <input type="text" id="subject-filter">
          </div>
          <div id="filter-btn" @click="getFilteredMail()">Filter</div>
        </div>
      </div>
      <div id="search">
        <input key="text" id="targetText" value="Search..." @click="searchBar">
        <i class="fas fa-search searching" v-on:click="search"></i>
      </div>
    </div>
    <ControlBar  @getCheckedMails="getCheckedMails" @openMoveTo="openMoveTo" ></ControlBar>

    <a href="#" class="float" id = "compose" @click="component = 'compose'" v-on:click="hideComposeBtn" >
    <i class="fas fa-plus my-float"></i> Compose
    </a>
    <div id="body">
      <div id="menu">
        <div id="userFoldersList" v-if="openUserFolders">
        <div id="backToMenu" @click="openUserFolders = false"><i class="fas fa-arrow-alt-circle-left"></i>  Back</div>
        <li id="users-list" class="menu-styling" v-for="folder in userFoldersList" v-bind:key="folder">
          <ul><span @click="setFolder(folder)"><i class="far fa-folder-open"></i>  {{folder}}</span>
          <div id="options-folder">
          <div @click="setRenameFolder(folder)" id="rename-folder">Rename</div>
          <div @click="deleteFolder(folder)" id="delete-folder">Delete</div>
          </div>
          </ul>
        </li>
        </div>
        <li id="menuList" class="menu-styling" v-if="openUserFolders === false">
          <ul @click="setFolder('inbox')"><span><i class="fas fa-inbox"></i> Inbox</span></ul>
          <ul @click="setFolder('draft')"><span><i class="fas fa-archive"></i> Draft</span></ul>
          <ul @click="setFolder('sent')"><span><i class="fas fa-share-square"></i> Sent</span></ul>
          <ul @click="setFolder('trash')"><span><i class="fas fa-trash-alt"></i> Trash</span></ul>
          <ul @click="addFolder = true"><span><i class="fas fa-plus"></i> Add New Folder</span></ul>
          <ul @click="getUserFolders"><span><i class="fas fa-star"></i> My Folders<i class="fas fa-angle-right icon-arrow"></i></span></ul>
        </li>
        <div id="contacts-menu" v-if="openUserFolders === false">
          <div id="add-contact" @click="addContact = true"><i class="fas fa-user-plus icon"></i>  ADD CONTACT</div>
          <div id="my-contacts" @click="getContacts"><i class="fas fa-user-friends icon"></i>  MY CONTACTS</div>
        </div>
      </div>
      <AddFolder v-if="addFolder" @sendFolder="sendFolder"></AddFolder>
      <RenameFolder v-if="renameFolderPanel" @renameFolder="renameFolder"></RenameFolder>
      <MoveToFolder v-if="moveFolderPanel" @setMoveToFolder="setMoveToFolder"></MoveToFolder>
      <div id="content" >
        <component :key="componentKey" :is="component" v-bind:maillist="Mails" :currentFolder="currentFolder" :searchResults="searchResults" :contactlist="contactlist" @paging='setpage'></component>
      </div>
    </div>
  </div>
</template>

<script>
import MailView from '../components/MailView.vue'
import ContactView from '../components/ContactView.vue'
import Compose from '../components/Compose.vue'
import AddFolder from '../components/Add Folder.vue'
import RenameFolder from '../components/Rename Folder.vue'
import ControlBar from '../components/Control Bar.vue'
import MoveToFolder from '../components/Move To Folder.vue'
import AddContact from '../components/Add Contact.vue'

import axios from 'axios'
let apiUrl = 'http://localhost:8085'
let pageNumber=1;
export default {
  name: 'Home',
  components: {
    'mail-view':MailView,
    'compose':Compose,
    AddFolder,
    RenameFolder,
    ControlBar,
    MoveToFolder,
    'contact-view':ContactView,
    AddContact,
  },
  data()
  {
    return{
      component:'mail-view',
      Mails:[],
      searchResults:[],
      prop:this.Mails,
      currentFolder:"inbox",
      beforeMount : true,
      addFolder: false,
      folderName:String,
      userFoldersList:[],
      openUserFolders: false,
      renameFolderPanel:false,
      userFolderName : String,
      folderForChecked: String,
      moveFolderPanel : false,
      contact:false,
      contactlist:[],
      // addContact:false,
      componentKey : 0,
      addContact:false,
      // target:""
    }
  },
  beforeMount(){
    if(this.beforeMount)
      this.getMails()
    this.contact = this.component === 'contact-view';
    console.log(this.contact)
  },
  methods : {
    openMoveTo(){
      this.moveFolderPanel = true;
    },
    setMoveToFolder(folder)
    {
      var message = document.getElementById("message-folder-move")
      message.innerHTML = "";
      if(folder === '')
      {
        this.moveFolderPanel = false;
        return;
      }
      this.folderForChecked = folder;
      this.getCheckedMails(false);
    },
     getCheckedMails(bool)
    {
        var checkedMails = [];
        var count = 0;
        var i = 0;
        var checkBoxes = document.getElementsByClassName("check-boxes");
        console.log(this.Mails)
        for(i=0; i<this.Mails.length; i++){
          console.log(checkBoxes[i])
          var box = checkBoxes[i]
            if(box.checked === true)
            {
              console.log("iiiiii:" + i + "  id : " + this.Mails[i]);
              checkedMails[count] = this.Mails[i].id.toString();
              console.log("hiiiiiii" +checkedMails[count]);
              ++count;
            }
        }
        if(bool)
          this.moveChecked(checkedMails, "trash");
        else
          {
            this.moveChecked(checkedMails, this.folderForChecked);
          }
    },
    moveChecked(checkedMails, folderName)
    {
      if(folderName !== "trash")
      {
        var message = document.getElementById("message-folder-move")
        message.innerHTML = "";
      }
      axios.post(apiUrl + "/move",
      {
        id : checkedMails,
        folderName : folderName
      }
      ).then(Response => {
          if(Response.data)
          {
            this.moveFolderPanel = false;
            this.getMails();
          }
          else{
            var message = document.getElementById("message-folder-move")
            message.innerHTML = "Invalid Folder Name!"
          }
      })
    },
    setRenameFolder(name)
    {
      this.renameFolderPanel = true;
      this.userFolderName = name;
    },
    renameFolder(newfoldername)
    {
      var message = document.getElementById("message-folder-rename");
      message.innerHTML = "";
      console.log(newfoldername);
      console.log(this.userFolderName);
      if(newfoldername === '')
      {
        this.renameFolderPanel = false;
        console.log(this.userFolderName);
        this.getUserFolders();
        return;
      }
      axios.get(apiUrl + "/renameFolder", {
        params:{
          folderName : this.userFolderName,
          newFolderName : newfoldername
        }
      }).then(Response => {
        if(Response.data === false)
        {
          message.innerHTML = "This folder name is invalid!"

        }
        else{
          this.renameFolderPanel= false;
          this.getUserFolders();
        }
      })
    },
    deleteFolder(foldername)
    {
       axios.get(apiUrl + "/deleteFolder", {params:{
         folderName : foldername
       }}
      ).then(Response => {
        if(!Response.data)
        {
          alert("faild to delete folder!");
        }
        else{
          this.openUserFolders = false;
          this.currentFolder = "inbox";
          this.getMails();
        }
      })
    },
    getUserFolders()
    {
      this.userFoldersList = []
      axios.get(apiUrl + "/getUserFolders"
      ).then(Response => {
        let indices = Object.keys( Response.data )
        console.log("index" + indices);
        for(let i = 0 ;i < indices.length ; i++){
          this.userFoldersList[i] = Response.data[indices[i]];
        }
      })
      console.log(this.userFoldersList.toString())
      this.openUserFolders = true;
    },
    hideComposeBtn(e)
    {
      e.target.style.display = "none";
    },
    sendFolder(name)
    {
      const message = document.getElementById("message-folder");
      message.innerHTML = ""
      if(name === '')
      {
        this.addFolder = false;
        return;
      }
      axios.get(apiUrl + "/addFolder", {
        params:{
          folderName : name
        }
      }).then(Response => {
        if(Response.data === false)
        {
          message.innerHTML = "This folder name is invalid!"

        }
        else{
          this.folderName = name;
          this.addFolder = false;
        }
      })
    },
    setFolder(folder)
    {
      this.currentFolder = folder;
      this.component = 'mail-view';
      this.componentKey+=1
      const btn = document.getElementById("compose");
      btn.style.display = "block";
      this.getMails();
    },
    getMails(){
      console.log(this.pageNumber)
      axios.get(apiUrl + "/getMails", {
        params:{
          folderName : this.currentFolder,
          pageNumber :pageNumber

        }
      }).then(Response => this.updateMails(Response))
      this.beforeMount = false
    },
updateMails(Response){
  this.Mails = [];
  let indices = Object.keys( Response.data )
  for(let i = 0 ;i < indices.length ; i++){
    this.Mails[i] = {
      id : Response.data[indices[i]].id,
      subject : Response.data[indices[i]].subject,
      sender : Response.data[indices[i]].sender,
      body : Response.data[indices[i]].body,
      date : Response.data[indices[i]].date,
      receiver : Response.data[indices[i]].receiver
    }
    console.log(this.Mails[i])
  }
},updateContacts(Response){
      this.contactlist = [];
      let indices = Object.keys( Response.data )
      for(let i = 0 ;i < indices.length ; i++){
        this.contactlist[i] = {
          emails : Response.data[indices[i]].email,
          name:Response.data[indices[i]].name
        }
        console.log(this.Mails[i])
      }
    }, async getContacts() {
      console.log("HAAAA")
      await axios.get(apiUrl + "/getContacts")
          .then(Response => {
            this.contactlist = [];
            let indices = Object.keys(Response.data)
            for (let i = 0; i < indices.length; i++) {
              this.contactlist[i] = {
                emails: Response.data[indices[i]].email,
                name: Response.data[indices[i]].name
              }
              console.log(this.contactlist[i])
              console.log(Response.data)
            }
          })
      // this.beforeMount = false
      this.component = 'contact-view'
    },
 setpage(){
     var header = document.getElementById("pagination");
    var btns = header.getElementsByClassName("btn");

     for (var i = 0; i < btns.length; i++) {
         btns[i].addEventListener("click", function() {
           const current = document.getElementsByClassName("active");
           current[0].className = current[0].className.replace(" active", "");
            this.className += " active";
            pageNumber=current[0].id;
            });
            }

            this.getMails();
  },

    searchBar() {
      const myvalue = document.getElementById("targetText").value;
      if(myvalue === "Search..."){
        document.getElementById("targetText").value = "";
      }
    },async search() {
      console.log("HELLO SARSOURAAA")
      const target = document.getElementById("targetText").value;
      if(this.component !== 'contact-view') {
        await axios.get(apiUrl + "/searchMails", {
          params: {target: target}
        }).then(Response => {
          this.searchResults = []
          this.Mails = []
          let indices = Object.keys(Response.data)
          for (let i = 0; i < indices.length; i++) {
            this.Mails[i] = Response.data[indices[i]].source
            this.searchResults[i] = {
              subjectOccurrences: JSON.parse(JSON.stringify(Response.data[indices[i]].subjectOccurrences)),
              bodyOccurrences: JSON.parse(JSON.stringify(Response.data[indices[i]].bodyOccurrences)),
              senderOccurrences: JSON.parse(JSON.stringify(Response.data[indices[i]].senderOccurrences)),
              priorityOccurrences: JSON.parse(JSON.stringify(Response.data[indices[i]].priorityOccurrences)),
              dateOccurrences: JSON.parse(JSON.stringify(Response.data[indices[i]].dateOccurrences))
            }

          }

        })
        this.searchResults = JSON.parse(JSON.stringify(this.searchResults));
      }else{
        await axios.get(apiUrl + "/searchContacts", {
          params: {target: target}
        }).then(Response => {
      this.updateContacts(Response)
      console.log("BABA")
          console.log(this.contactlist)
        })
      }
      // console.log(this.searchResults)
      console.log("6a")
      console.log(this.Mails)

      this.componentKey += 1;
    },

    clickItem(e){
      const listItem = e.target.innerHTML;
      const firstItem = document.getElementById("first-item");
      e.target.innerHTML = firstItem.innerHTML;
      firstItem.innerHTML = listItem;
      if(listItem==="Date" || listItem==="Importance" || listItem==="Subject" || listItem==="Body" || listItem==="Attachments" ){
        axios.get(apiUrl + "/sortMails", {
          params:{ sort : listItem }
        }).then(Response =>this.updateMails(Response))
      }
      else if(listItem==="Email"||listItem==="Name"){
        axios.get(apiUrl + "/sortContacts", {
          params:{ sort : listItem }
        }).then(Response =>this.updateContacts(Response))
      }

    },
    clickSort(){
      const list = document.getElementById("sortList");
      const icon = document.getElementById("list-icon");
      if(list.style.display === "none")
      {
        list.style.display = "block";
        icon.className = "fas fa-caret-up list-icon";
      }
      else
      {
        list.style.display = "none";
        icon.className = "fas fa-caret-down list-icon";
      }
    },
    clickFilter(){
      const box = document.getElementById("check-menu");
      const icon = document.getElementById("check-icon");
      if(box.style.display === "none")
      {
        box.style.display = "block";
        icon.className = "fas fa-caret-up check-icon";
      }
      else
      {
        box.style.display = "none";
        icon.className = "fas fa-caret-down check-icon"
      }
    },
    display()
    {
      const acc = document.getElementById("account-info");
      acc.style.display = "block";
    },
    hide()
    {
      const acc = document.getElementById("account-info");
      acc.style.display = "none";
    },


    getFilteredMail(){
      const sender = document.getElementById("sender-filter").value;
      const subject = document.getElementById("subject-filter").value;
      axios.get(apiUrl + '/filter', {
        params:
        {
          sender: sender,
          subject: subject 
        }
      })
      .then(response => {
          console.log("hello"+response.data.length)
          console.log(response.data)
           this.Mails = [];
        
        for(let i = 0 ;i < response.data.length ; i++){
          this.Mails[i] = {
            id : response.data[i].id,
            subject : response.data[i].subject,
            sender : response.data[i].sender,
            body : response.data[i].body,
            date : response.data[i].date,
            receiver : response.data[i].receiver
          }
        }
           
      })
     

    }
  }
}



</script>

<style scoped>
#home {
  margin: 0;
  padding: 0;
}
#brand {
    object-fit: fill;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100px;
    font-weight: 700;
    font-size: 16px;
    padding: 12px;
    width: 98.7vw;
    margin-left: -0.6%;
    margin-top: -1.3%;
    color: white;
    background-color: black;
    font-family: 'Ubuntu', sans-serif;
    overflow: hidden;
}
#image {
    flex-shrink: 0;
    width:1710px;
    height: 1300px;
    display: block;
    margin-top: -17%;
}
#brand h1 {
    position: absolute;
    margin-left: -80%;
    letter-spacing: 5px;

}
#right-side {
  position: absolute;
  display: flex;
  left: 75%;
  top: 7%;
  font-family: 'Open sans', serif;
  font-weight: 100;
  letter-spacing: 3px;
}
#right-side > div {
    margin-left: 40px;
    cursor: pointer;
}
#sign-out {
  font-family: 'Open sans', serif;
  font-weight: 100;
  letter-spacing: 1px;
  margin-top: 8px;
}
#sign-out-link {
  text-decoration: none;
  color: white;
}
#account {
  height: 50px;
  width: 50px;
  background-image: url(../assets/pic.jpg);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-color: black;
  border: 1px solid transparent;
  border-radius: 50px;
  margin-top: -7px;
  margin-right: -15px;
}
.float{
  display: block;
	position:fixed;
	width:130px;
	height:40px;
  top: 85%;
  left: 87%;
  background-color: #fabaa0;
  color: white;
	border-radius:50px;
	box-shadow: 2px 2px 3px rgb(31, 28, 28);
  text-decoration: none;
  font-size: 20px;
  padding-left: 15px;
  padding-top: 10px;
  font-weight: 600;
  font-family: 'Open sans', serif;
  text-shadow: 1px 2px #3f3e3e;
}
.my-float {
  margin-right: 6px;
  margin-top: 5px;
}
.float:hover {
  color: #fabaa0;
	background-color:white;
}
#upper-bar {
  display: flex;
  width: 100vw;
  margin-left: -0.5%;
  height: 55px;
  background-color: #081448;
}

#search {
  transform: translate(200%, 20%);
}
#targetText {
  width: 330px;
  height: 23px;
  border: 5px solid transparent;
  border-radius: 30px;
  padding-left: 15px;
  color: #6b6464;
  font-size: 15px;
  font-family: 'Open sans', serif;
  outline: none;
}
.searching {
  font-size: 20px;
  margin-left: -40px;
  color: #6b6464;
}
#body {
  display: flex;
  margin-left: -1%;
  width: 102%;
}

#content {
  background-color: white;
  height: 650px;
  width: 1500px;
  padding-top: 0;
}

#sort {
  transform: translate(100%, 20%);
  display: flex;
  font-family: 'Open sans', serif;
  z-index: 9999999999999;

}
#sortBy {
  color: white;
  margin-right: 20px;
  letter-spacing: 3px;
  font-weight: 100;
  font-size: 16px;
  margin-top: 5px;
}
#sortList {
  margin-top: 0;
  border: 2px solid transparent;
  background-color: white;
  color: #081448;
  text-transform: uppercase;
  letter-spacing: 1px;
  list-style: none;
  height: 240px;
  font-size: 17px;
  width: 160px;
  border-bottom-right-radius: 5px;
  border-bottom-left-radius: 5px;
  box-shadow: 2px 2px 3px #999;
  padding-top: 3px;
  display: none;
}
#sortList li {
  margin-left: -40px;
  cursor: pointer;
  width: 175px;
  height: 35px;
  padding-left: 25px;
  padding-top: 5px;
}
#sortList li:hover {
  background-color: #081448;
  color: white;
}
#first-item {
  height: 30px;
  background-color: white;
  color: #081448;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-size: 17px;
  padding-left: 22px;
  padding-top: 5px;
  border: 2px solid transparent;
  border-radius: 5px;
  cursor: pointer;
  width: 180px;
}
.list-icon {
  color: #081448;
  position: absolute;
  left: 310px;
  font-size: 18px;
  top: 7px;
}
#filtering {
  transform: translate(420%, 25%);
  z-index: 9999999999999;
}
#filter {
  background-color: #fabaa0;
  width: 145px;
  height: 25px;
  font-size: 18px;
  font-family: 'Open sans', serif;
  letter-spacing: 1px;
  color: white;
  padding-top: 3px;
  padding-left: 10px;
  border: 1px solid transparent;
  border-radius: 50px;
  cursor: pointer;
}
#check-menu {
  position: absolute;
  background-color: white;
  width: 300px;
  height: 150px;
  margin-top: 15px;
  box-shadow: 2px 2px 3px #999;
  color: #081448;
  font-family: 'Open sans', serif;
  letter-spacing: 1px;
  display: none;
  padding-left: 10px;
  padding-top: 10px;
}
#filter-btn {
  width: 60px;
  height: 25px;
  color: white;
  background-color: #fabaa0;
  padding-top: 5px;
  padding-left: 10px;
  font-weight: 600;
  cursor: pointer;
  margin-left: 180px;
}
#sender-filter, #subject-filter {
  width: 200px;
}
#account-info {
  font-size: 15px;
  position: absolute;
  background-color: white;
  z-index: 99999999;
  padding: 10px;
  width: 200px;
  color: rgb(119, 111, 111);
  min-height: 100%;
  letter-spacing: 1px;
  left: 70px;
  border: 1px solid white;
  border-radius: 0 20px 20px 20px;
  word-wrap: break-word;
  display: none;
  box-shadow: 2px 2px 3px #999;
}
#account-info > div {
  margin-top: 10px;
}
#email, #name {
  color: #081448;
  font-weight: 600;
}

#menu {
  background: linear-gradient(180deg,  #030b36 0%,rgb(173, 105, 126) 100%);
  height: 650px;
  width: 290px;
  font-family: 'Open sans', serif;
  color: white;
  font-weight: 100;
  letter-spacing: 1px;
  overflow-y: auto;
  overflow-x: initial;
}
.menu-styling {
  overflow: hidden;
}
.menu-styling  > ul{
  width: 270px;
  height: 30px;
  font-size: 18px;
  border-bottom: 1px solid rgb(104, 99, 99);
  padding-bottom: 10px;
  cursor: pointer;
}
#users-list > ul{
  height: 50%;
  margin-bottom: 0px;
  padding-bottom: 0;
  font-size: 20px;
}
.menu-styling ul > span:hover {
  color:  #fabaa0;
  text-shadow: 1px 2px #3f3e3e;
}
#add-contact, #my-contacts {
  margin-left: 45px;
  border: 1px solid white;
  width: 160px;
  height: 35px;
  border-radius: 50px;
  font-family: 'Open sans', serif;
  padding-left: 12px;
  padding-top: 8px;
  letter-spacing: 1px;
  font-weight: 600;
  margin-top: 30px;
  cursor: pointer;
}
#add-contact:hover, #my-contacts:hover {
  background-color:  #fabaa0;
  color: #030b36;
}
.icon-arrow {
  margin-left: 70px;
}
#backToMenu {
  margin-left: 180px;
  margin-top: 15px;
  font-size: 18px;
  cursor: pointer;
}

#rename-folder, #delete-folder {
  color: white;
  font-size: 17px;
  cursor: pointer;
  margin-right: 15px;
}
#rename-folder:hover, #delete-folder:hover {
  color: #fabaa0;
}
#options-folder {
  display: flex;
  padding-top: 5px;
}
</style>
