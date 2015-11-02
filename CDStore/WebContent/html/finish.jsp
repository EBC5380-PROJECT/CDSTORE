<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
		<meta name="description" content="">
		<meta name="author" content="">
		<link rel="icon" href="../../favicon.ico">
		<title>Order Confirmation</title>
		<!-- Bootstrap core CSS -->
		<link href="../css/bootstrap.min.css" rel="stylesheet">
		<!-- Bootstrap theme -->
		<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
		<link href="../css/theme.css" rel="stylesheet">
		<script src="../lib/ie-emulation-modes-warning.js"></script>
		<script src="../lib/jquery-2.1.4.min.js"></script>
		<script src="../js/host.js"></script>
		<script src="../js/cart.js"></script>
		<script>
			$(function(){
				getNavBarLinks();
			});
			
			var finalcart = null;
			$(function(){
				<% String finalcart = (String)session.getAttribute("finalCart");%>
				finalcart = <%=finalcart %>;
				console.log(finalcart);
				//finalcart = JSON.parse(finalcart);
				
				$("#finalmessage").html("Thank you for spending $" + getTotal(finalcart) + ". Have a nice day.");
			});
			
		</script>
	</head>

  <body role="document">

    <!-- Fixed navbar -->
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

      <div class="page-header" id="thank">
        <h1 id="finalmessage">Thank you for shopping with us.</h1>
		<br>
		<h3>Please come again.</h3>
		
      </div>
<br>
<br>
<hr>
<div id="footer" style="margin-left:500px ;">
				kill -9 &copyCopyright 2015 - Do not reproduce or plagarize.
		</div>

    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="../lib/bootstrap.min.js"></script>
    <script src="../lib/docs.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../lib/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
