var Toolbox = {
	contextPath : null,	
	load:function(){	
		var inputs = $('input[type=text],input[type=password],input[type=file],textarea')
			.not('[disabled=true]');
		
		inputs.addClass('input');
		
		inputs.bind('focus', function() {			
			$(this).addClass('inputFocus');
		}).bind('blur', function() {
			$(this).removeClass('inputFocus');
		});
	
		$('input[readOnly=true],input[disabled=true],textarea[readOnly=true],textarea[disabled=true]')
			.removeClass('input').addClass('readOnly');
	}
};