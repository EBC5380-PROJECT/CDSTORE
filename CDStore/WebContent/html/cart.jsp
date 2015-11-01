<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Order Summary</title>
	<!-- Bootstrap core CSS -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="../css/theme.css" rel="stylesheet">
	<script src="../lib/ie-emulation-modes-warning.js"></script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="../lib/jquery-2.1.4.min.js"></script>
	<script src="../lib/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../lib/ie10-viewport-bug-workaround.js"></script>
	<script src="../js/host.js"></script>
	<script src="../js/cart.js"></script>
	
	<script>
		var username = null;
		//TODO: make this null in integration testing/production
		var cart = null;
		
		$(function(){			
			//get session
			//TODO: remove comment in JSP
			<% 
				String ca = session.getAttribute("cart");
			%>
			cart = "<%=ca %>";
			cart = JSON.parse(cart);
			
			
			//load top nav-bar
			getNavBarLinks(!(username == "null" || username == null));
			
			var dom = '<tr id="row-@itemId"><td id="@itemId">@itemId</td><td id="@itemId-name"><a href="' + Client.info.page() + "@itemId" + '">';
			dom += '@name</a></td><td id="@itemId-price">@price</td>';
			dom += '<td><input value="@quantity" type="text" id="@itemId-quantity"';
			//update subtotal for item in dom
			dom += 'onchange="$(\'#subtotal-@itemId\').html(Number($(\'#@itemId-quantity\').val()) * Number($(\'#@itemId-price\').html()));';
			//update cart
			dom += 'if(isNaN(Number($(\'#@itemId-quantity\').val())) == false){ cart[findById(cart, @itemId)].quantity = Number($(\'#@itemId-quantity\').val())};';
			//update grand total in dom
			dom += "var total = getTotal(cart);";
			dom += "$('#total').html(total);";
			dom += "$('#totaltax').html((total*tax).toFixed(2));";
			//update JSP cart with <% session.setAttribute("cart", JSON.stringify(cart)); %>
			//TODO: remove comment this in JSP
			dom += "<% session.setAttribute(&quot;cart&quot;, JSON.stringify(cart)); %>;
			
			dom += '"style="width:25px ; height:25px ;"></input>';
			
			dom += '</td><td id="subtotal-@itemId">@subtotal</td></tr>';
			
			//read cart and prepend to #cart-table
			for(var i = 0; i < cart.length; i++){
				var newdom = dom;
				
				newdom = newdom.replaceAll("@itemId", cart[i].itemId);
				newdom = newdom.replaceAll("@name", cart[i].itemName);
				newdom = newdom.replaceAll("@price", cart[i].price);
				newdom = newdom.replaceAll("@quantity", cart[i].quantity);
				newdom = newdom.replace("@subtotal", cart[i].price * cart[i].quantity);
				
				$("#cart-table").prepend(newdom);
			}
			
			//display onload total
			$("#total").html(getTotal(cart));
			$("#totaltax").html((getTotal(cart) * tax).toFixed(2));
			
			//checkout button link
			//if not signed in, go to sign in page
			//else go to shipping page
			if(username == "null" || username == null)
				$("#checkout").attr("href", Client.signin.address);
			else
				$("#checkout").attr("href", Client.shipping.address);
		});
		
	</script>

</head>

<body role="document">

	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
		   <a class="navbar-brand" id="fixed-top-cd_store" href="#">CD Store</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
		   </ul>
       
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a id="fixed-top-cart" href="#">Your Cart</a></li>
			  <li><a id="fixed-top-sign" href="#">Login/Logout</a></li>
			  <li><a id="fixed-top-register" href="#">Register</a></li>
          </ul>
        </div>
      </div>
	 </div>
    </nav>

	<div class="container theme-showcase" role="main">

		<div class="page-header">
			<h1>Your Cart</h1>
		</div>


		<table class="table table-striped">
			<thead>
				<tr>
					<th>Item ID</th>
					<th>Item Name</th>
					<th>Unit Price</th>
					<th>Quantity</th>
					<th>Total</th>
					<th>Total with Tax</th>
				</tr>
			</thead>
			<tbody id="cart-table">
				
				

				<tr>
					<td>total:</td>
					<td></td>
					<td></td>
					<td></td>
					<td id="total"></td>
					<td id="totaltax"></td>

			</tbody>
		</table>
		<a href="" id="checkout" class="label label-primary">Checkout</a>
	</div>
	</div>
	<br>
	<br>
	<hr>
	<div id="footer" style="margin-left:500px ;">
		kill -9 &copyCopyright 2015 - Do not reproduce or plagarize.
	</div>

	</div>
</body>

</html>