//template for cart object

/*
		get session:
var cart = '<%=(String)session.getAttribute("cart") %>';

	or

	var updateCart = function(){
		<% String cart = (String) session.getAttribute("cart");%>
		var cart = '<%=cart  %>';
	}
	</script>

	or

	<script language="javascript">
	var updateCart = function(){
		var cart = ${sessionScope.userlist};
	}
	</script>


	set session:
	<script language="javascript">
	var updateCart = function(){
		<%session.getAttribute("cart", %> cart <%); %>;

	}
	</script>

	or

	<script language="javascript">
	var updateCart = function(){
		${sessionScope.cart} = cart;

	}
	
	//access JSP variables
	var i = null;
	<% String j = "hello"; %>
	i = "<%=j %>";
*/



var tax = 1.13;

var cartItem = function (itemId, itemName, price, quantity) {
	this.itemId = itemId;
	this.itemName = itemName;
	this.price = price;
	this.quantity = quantity;
};

/*var cart = {
	username: "",
	error: "",
	cart: [
		"itemId": 1, "itemName": "Johnny Cash", "price": 12.99
	]
};*/

//Add an item to the cart and return the cart
var addToCart = function (cart, id, name, pr){
	//increment quantity by 1 if it is there
	for(var i = 0; i < cart.length; i++){
		if(cart[i].itemId == id){
			cart[i].quantity++;
			return cart;
		}
	}
	
	//add new entry
	cart.push({itemId: id, itemName: name, price: pr, quantity: 1});
	return cart;
}

//remove all quantities of item from cart
var removeFromCart = function(cart, id){
	for(var i = 0; i < cart.length; i++){
		if(cart[i].itemId == id){
			cart.splice(i,1);
			return cart;
		}
	}
	
	return cart;
}

//Return grand total of cart
var getTotal = function(cart){
	var t = 0;
	for(var i = 0; i < cart.length; i++){
		t += (cart[i].quantity * cart[i].price);
	}
	return t;
}

//return array indice from cart with given id
var findById = function(cart, id){
	for(var i = 0; i < cart.length; i++){
		if(cart[i].itemId == id){
			return i;
		}
	}
	return -1;
}

var testCart = JSON.parse('[{"itemId":3,"itemName":"Patsy Cline Im So Lonely","price":15.99,"quantity":2},{"itemId":9,"itemName":"Video Killed the Radio Star","price":13.99,"quantity":5}]');