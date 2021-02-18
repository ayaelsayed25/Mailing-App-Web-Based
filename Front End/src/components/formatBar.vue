<template>
  <div  id="formatBar">
       <span class="space"></span>
        <span class="space"></span> <span class="space"></span>

      <span class="textFormat">
        <label for="colorpickerLine">
        <i  class="material-icons" style="color:#fabba2">format_color_text</i>
        </label>
        <input type="color" class="color_picker" id="colorpickerLine" value="#fabba2"  @change="$emit('setlinecolorat')" >
     </span>

 <span class="space"></span>
  <span class="space"></span>
  

 <span id="sending">
   <button class="Send" @click="$emit('send_mail','compose')">Send</button>
</span>

 <span class="space"></span>
  <span class="space"></span>
    <span id="Inputs">
        <input type ="number" id = "num_input"  max = "30" min = "0" value="30" @change="changeRange();$emit('setwidth',lineValue)">
            
    </span>

      <span class="image-upload">
  <label for="file-input">
    <i class="material-icons" style="color:#fabba2 " >attach_file</i>
  </label>
        <!--         @change="test"-->
  <input id="file-input" type="file" ref="file"
         multiple @change="$emit('attach_file',$refs.file.files[0])"

        />
</span>

 <span class="space"></span>
  <span class="space"></span>
   <span class="space"></span>

      <span>
         <select name="priority" id="priority" @change="$emit('importance')">
            <option value="Very Important">Very Important</option>
            <option value="Important">Important </option>
            <option value="Normal">Normal</option>
            <option value="Not Important">Not Important</option>
       </select>

      </span>
       <span class="space"></span>
        <span class="space"></span>

<span  id="delete" @click="$emit('send_mail','draft')">
  <span id="draft"><i style="margin-left:8px;" class="fas fa-archive"></i>  Save to Draft</span>
     
</span>
     
  </div>
</template>

<script>
//import ColorBar from './ColorBar.vue'
//import GetInput from './GetInput.vue'
export default {
  
  name: 'formatBar',
   data() {
        return {
            num_id:"numLineOpacity",
            range_id:"rangeLineOpacity",
            div_id:"divLineOpacity",
            lineValue:'',
            file:'',

        }
    },
     methods:{
    test(){
this.file = this.$refs.file.files[0];
    },
        rangeDisplay(){
        var range = document.getElementById(this.range_id),
            range_div = document.getElementById(this.div_id);
        console.log(range.id,range_div.id);
        if(range.style.opacity === "0")
        {
            range.style.opacity = "1";
            range_div.style.opacity = "1";
            range.disabled = false;
        }else{
            range.style.opacity = "0";
            range_div.style.opacity = "0";
            range.disabled = true;
        }
        },
        changeRange()
        {
        var num = document.getElementById(this.num_id),
            range = document.getElementById(this.range_id);
        range.value = num.value;
         this.lineValue=range.value
        console.log(this.lineValue)
        },
        changeNumber()
        {
        var num = document.getElementById(this.num_id),
            range = document.getElementById(this.range_id);
        num.value = range.value;
        this.lineValue=num.value
        console.log(this.lineValue)
        }
  },
  mounted() {
     
        var num = document.getElementById("num_input");
        num.id = this.num_id;
  },

}
</script>

<style  scoped>
#formatBar{
   
   height: 45px;
   width: 1300px;
   background-color: #faeeea;
  
}
 button, input[type=number], #Inputs{
  display: block;
  float: left;
}

input[type=number]{
  width: 50px;
  height: 25px;
  color: purple;
  border-color: #fabba2;
 
  padding: 0;
  background-color: #fabba2; 
   margin: 4px;
}
input[type=number]::-webkit-inner-spin-button, 
input[type=number]::-webkit-outer-spin-button {  

   opacity: 1;

}

#delete{
  margin-left:370px ;
}
#draft{
  font-family: 'Open sans', serif;
  font-size: 20px;
   border: 2px solid #fabba2;
  border-radius: 12px;
  
}
#draft:hover{
  background-color: #fabba2;
}
#Inputs{
  padding: 2px;
  margin: 0%;
  height: 40px;
  width:80px;

}
.space {
 margin-left: 10px
}
.image-upload>input {
  display: none;

}
.color_picker{
    position: absolute;
     margin: 9px;
      margin-top: 9px;
}
.textFormat>input{
    position: relative;
}
.button {
  border: none;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}

.Send{
  background-color: #fabba2; 
  color: black; 
  border: 2px solid purple;
  height: 40px;
  width: 100px;
  border-radius: 12px;
  margin-left: 9px;
  margin-right: 200px;

}
.switch input { 
  opacity: 0;
  width: 0;
  height: 0;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 18px;
  width: 18px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}

input:checked + .slider {
  background-color: #ba55d3;
}

input:focus + .slider {
  box-shadow: 0 0 1px #ba55d3;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}
.input_slider{
  padding:0 ;
  margin: 9px;
  margin-right: 50px;
}
.switch {
  position: relative;
  display: inline-block;
  width: 52px;
  height: 25px;
}
#priority{
  margin: 9px;
  margin-right: 0;
 
  background-color: #fabba2;
}

</style>