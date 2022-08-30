$(document).ready(function(){
	$("#buttonAddToCart").on("click", function(evt){
		addToCart();
	});
});

function addToCart(){
	url = contextPath + "cart/buy/" + productId;
	
	$.ajax({
	type: "POST",
	url: url
	}).done(function(response){
	showModalDialog("Product was bought", response);
	}).fail(function() {
	showErrorModal("Error while buying product");
	});
	
}