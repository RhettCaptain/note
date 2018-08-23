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

function jsonify(ele){
	var arr = ele.find("input");
	arr.each(function(){
		$(this).attr("value",$(this).val());
	});
		
	var tmpForm=$('<form id="_tmpForm"></form>');
	var con = ele.html();
	tmpForm.html(con);
	return tmpForm.jsonify();
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

function getUserId(){
	var res = getJson("/login/getUserId");
	if(res.loginOk == "true"){
		return res.userId;
	}else{
		window.location.href="/";
		return null;
	}
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); 
    var r = window.location.search.substr(1).match(reg);  
    if (r != null) return unescape(r[2]); return null; 
}

function getJson(url){
	var res = null;
	$.ajax({
        type:"POST",
        url:url,
        dataType:"json",
        contentType:"application/json",
        async: false,
        cache:false,
        success:function(data){
        	res = data;
        }
    });
	return res;
}

function sendJson(url,data){
	var res = null;
	$.ajax({
        type:"POST",
        url:url,
        data: JSON.stringify(data),
        dataType:"json",
        contentType:"application/json",
        async: false,
        cache:false,
        success:function(databack){
        	res = databack;
        }
    });
	return res;
}

function showDay(day){
	switch(day){
	case 1:
		return "Mon";
	case 2:
		return "Tue";
	case 3:
		return "Wed";
	case 4:
		return "Thu";
	case 5:
		return "Fri";
	case 6:
		return "Sat";
	case 0:
		return "Sun";
	}
}

function rollBack(){
	window.onbeforeunload = function(){
	    var scrollPos;    
	    if (typeof window.pageYOffset != 'undefined') {
	        scrollPos = window.pageYOffset;
	    }
	    else if (typeof document.compatMode != 'undefined' &&
	        document.compatMode != 'BackCompat') {
	        scrollPos = document.documentElement.scrollTop;
	    }
	    else if (typeof document.body != 'undefined') {
	        scrollPos = document.body.scrollTop;
	    }
	    document.cookie="scrollTop="+scrollPos; //存储滚动条位置到cookies中
	}

	window.onload = function()
	{ 
	    if(document.cookie.match(/scrollTop=([^;]+)(;|$)/)!=null){
	        var arr=document.cookie.match(/scrollTop=([^;]+)(;|$)/); //cookies中不为空，则读取滚动条位置
	        document.documentElement.scrollTop=parseInt(arr[1]);
	        document.body.scrollTop=parseInt(arr[1]);
	    }
	}
}