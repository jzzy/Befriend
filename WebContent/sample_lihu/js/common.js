$(function(){
	$(document).ready(function() {
		var hheight = $("#header").height();
		$("#container").css("padding-top",hheight)
		var fheight = $("#footer").height();
		$("#container").css("padding-bottom",fheight)
	});
});