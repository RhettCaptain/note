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