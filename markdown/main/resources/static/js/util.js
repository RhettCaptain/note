$.fn.jsonify = function()  {  
   var o = {};  
   var a = this.serializeArray();  
   $.each(a, function() {  
       if (o[this.name]) {  
           if (!o[this.name].push) {  
               o[this.name] = [o[this.name]];  
           }  
           o[this.name].push(this.value || '');  
       } else {  
           o[this.name] = this.value || '';  
       }  
   });  
   return o;  
}
function debug(){
	return "deu";
}

function checkLogin(){
	var name = "null";
	$.ajax({   
	       type: "post",   
	       url: "/login/checkSession",   
	       dataType: "json",
	       async: false,
	       success: function (data) {
	           if(data.loginOk != "true"){
	        	   window.location.href="/";
	           }else{
	        	   name = data.userName;
	           }           
	       },   
	       error: function () {  
	    	   alert("err");
	    	   window.location.href="/";
	       }   
	 });  
	 return name;
}


