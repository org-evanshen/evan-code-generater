$(function(){
	var selectAll = $('#selectAll');
	
	selectAll.click(function(){	
		$('input[name=tables]').attr('checked',this.checked);
	});
	
	$('#btnBack').click(function(){
		history.go(-1);
	});
	
	$('form.form').bind('submit',function(){
		var checkeds = $('input[name=tables]').filter('input[checked=true]');
		if(checkeds.size()==0){
			alert('閿欒锛氳閫夋嫨闇€瑕佺敓鎴愮殑琛紒');
			return false;
		}else{
			return true;
		}		
	});
})