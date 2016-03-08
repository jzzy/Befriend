$(function(){
	$(document).ready(function() {
		var hheight = $("#header").height();
		var fheight = $("#footer").height();
		var wheight = $(window).height() - hheight;
		$("#container").css("padding-top",hheight)
		$("#container").css("padding-bottom",fheight)
		$("#mainiframe").css("height",wheight + "px");
	});
});