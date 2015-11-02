<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Payment Information</title>

	<!-- Bootstrap core CSS -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="../css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="../css/theme.css" rel="stylesheet">
	<script src="../lib/ie-emulation-modes-warning.js"></script>
	<script src="../lib/jquery-2.1.4.min.js"></script>
	<script src="../js/host.js"></script>
	<script src="../lib/bootstrap.min.js"></script>
	<script src="../lib/ie10-viewport-bug-workaround.js"></script>
	<script>
		//change the form's action dynamically on page load such that there is
		var changeAction = function () {
			$("#form").attr("action", name + Server.paymentService.url);
		};

		var error = "";

		$(function () {
			//change the form action dynamically
			changeAction();

			//access error message and display them
			<% 
				String error = (String)session.getAttribute("error");
			%>
			error = "<%=error %>"
			

			displayError(error);
		});
	</script>
</head>

<body role="document">

	<div class="container">

		<form class="form-signin" id="form" action="" method="post">
			<h2 class="form-signin-heading">Payment Method</h2>
			<!-- Error messages display here -->
			<font color="red" id="msg-error">
		  	 
		  </font>

			<div class="form-group">
				<label for="Full name ">Full name:</label>
				<input type="Full name" class="form-control" id="Full name" placeholder="Your full name" name="cardholdername" style="width:500px" required>
			</div>

			<div class="control-group">
				<div class="controls">
					<label class="radio">
						<input type="radio" id="" name="paymentmethod" id="visa" value="visa" checked required>
						<image id="visaimage" src="../image/stock/visa.png">
					</label>
					<label class="radio">
						<input type="radio" id="" name="paymentmethod" id="mc" value="mc" required>
						<image id="mcimage" src="../image/stock/mc.png">
					</label>
					<label class="radio">
						<input type="radio" id="" name="paymentmethod" id="amex" value="amex" required>
						<image id="ameximage" src="../image/stock/amex.jpg">
					</label>
				</div>
			</div>


			<div class="form-group" style="width:200px; height:100px;">
				<h4><label for="Card Number">Card Number:</label></h4>
				<input type="Card Number" class="form-control" id="Card Number" placeholder="Enter Card Number" id="" name="cardnumber" required>
			</div>

			<div class="form-group">
				<label for="number">Card Expiration (Year/Month): </label>
				<select name="expyear" class="selectpicker" style="width:75px; height:36px;" required>
					<option></option>
					<option>15</option>
					<option>16</option>
					<option>17</option>
					<option>18</option>
					<option>19</option>
					<option>20</option>
					<option>21</option>
					<option>22</option>
					<option>23</option>
					<option>24</option>
				</select>

				<select id="" name="expmonth" class="selectpicker" style="width:75px; height:30px;" required>
					<option>1</option>
					<option>2</option>
					<option>3</option>
					<option>4</option>
					<option>5</option>
					<option>6</option>
					<option>7</option>
					<option>8</option>
					<option>9</option>
					<option>10</option>
					<option>11</option>
					<option>12</option>
				</select>

				<div class="form-group" style="width:200px; height:100px;">
					<h4>
			<label for="Security Code">Security Code:</label>
		<h4>
			
			<input name="securitycode" type="Security Code" class="form-control" required></h4></input>

					</h4>


				</div>

				<br>
				<button class="btn btn-lg btn-primary btn-block" type="submit" style="width:200px; height:50px; margin-left:600px;">Pay Now</button>
		</form>

		</div>

	</div>
	<!-- /container -->
	<br>
	<br>
	<hr>
	<div id="footer">
		kill -9 &copyCopyright 2015 - Do not reproduce or plagarize.
	</div>
</body>

</html>