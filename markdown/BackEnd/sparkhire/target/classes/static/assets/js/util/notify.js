var notify_error = function(text){
	$.notify({
        icon: "error_outline",
        message: "Error: "+text
    },{
        type: 'danger',
        timer: 4000,
        placement: {
            from: "top",
            align: "right"
        }
    });
}

var notify_success = function(text){
	$.notify({
        icon: "add_alert",
        message: "Result: "+text
    },{
        type: 'success',
        timer: 4000,
        placement: {
            from: "top",
            align: "right"
        }
    });
}