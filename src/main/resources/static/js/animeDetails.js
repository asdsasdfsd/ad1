$(document).ready(function(){
		var num=$('#rating').text();
		var w=num * 10 +'%';
		$('#star').css('width',w);
	})
	
function tagline(event,id) {
	$('.tag').find('div').hide();
	$(event).find('div').show();
	$('.box').hide();
	$('#'+id).show();
}

$(function(){
	$(".textarea").Huitextarealength({
		minlength:0,
		maxlength:200.
	});
});

function select(value) {
	$('#circle').text(value);
}

function create(id) {
	$('#newF1').hide();
	$('#' + id).css('display','table');
}

function afterCreate() {
	$('#newF2').hide();
	$('#newF1').css('display','table');
}

function follow(a) {
	$(a).addClass('followBtn_active');
	var change = $(a).find('i').data('icon');
	if(change == 1)
	{	
		$(a).html('<i data-icon="0" class="icon Hui-iconfont">&#xe6a8;</i>Followed');
	}
	else
	{
		$(a).html('<i data-icon="1" class="icon Hui-iconfont">&#xe648;</i>Follow' );
		$(a).removeClass('followBtn_active');
	}
}

function score() {
	document.getElementById('scoreWindow').style.display = "block";
	document.getElementById('overlay').style.display = "block";
}

function confirm_score() {
	document.getElementById('scoreWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function cancel_score() {
	document.getElementById('scoreWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function favourite() {
	document.getElementById('favouriteWindow').style.display = "block";
	document.getElementById('overlay').style.display = "block";
}

function confirm_favourite() {
	document.getElementById('favouriteWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}

function cancel_favourite() {
	document.getElementById('favouriteWindow').style.display = "none";
	document.getElementById('overlay').style.display = "none";
}