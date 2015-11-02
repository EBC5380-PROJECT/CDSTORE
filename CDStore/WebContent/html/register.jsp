<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Registration - kill-9 store</title>

	<!-- Bootstrap core CSS -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="../css/signin.css" rel="stylesheet">
	<script src="../lib/ie-emulation-modes-warning.js"></script>
	<script src="../lib/jquery-2.1.4.min.js"></script>
	<script src="../js/host.js"></script>
	<script src="../js/cart.js"></script>
	<script>
		//change the form's action dynamically on page load such that there is
		var changeAction = function () {
			$("#form").attr("action", name + Server.registerService.url)
		};

		//check password sameness
		var checkPassword = function () {
			if ($("#password").val() == $("#passwordconfirm").val())
				return true;
			else {
				displayError("Passwords do not match.");
				return false;
			}
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


<body style="background:#f5f5f5">

	<div class="container" style="padding:20px 400px 10px 400px !important; ">

		<form id="form" class="form-register" method="post" action="" onsubmit="return checkPassword();">
			<h1 class="form-register-heading">Register with kill-9</h1>
			<!-- Error messages display here -->
			<font color="red" id="msg_error">
		  	
		  </font>
			<h3>Account information</h3>
			<div class="form-group">
				<label for="Username">Username:</label>
				<input type="text" class="form-control" id="username" placeholder="Your desired username" name="username" required>
			</div>

			<div class="form-group">
				<label for="First name">First name:</label>
				<input type="text" class="form-control" id="firstname" placeholder="Your first name" name="firstname" required>
			</div>

			<div class="form-group">
				<label for="last name">Last name:</label>
				<input type="text" class="form-control" id="lastname" placeholder="Your last name" name="lastname" required>
			</div>

			<hr>

			<div class="form-group">
				<label for="Email address">Email address:</label>
				<input type="text" class="form-control" id="email" placeholder="Your email address" name="email" required>
			</div>

			<div class="form-group">
				<label for="Create your password">Create your password:</label>
				<input class="form-control" id="password" placeholder="password" name="password" type="password" required>
			</div>

			<div class="form-group">
				<label for="Confirm your password">Confirm your password:</label>
				<input class="form-control" name="passwordconfirm" id="passwordconfirm" placeholder="password" type="password" required>
			</div>
			<br>


			<hr>

			<h3>Shipping information</h3>

			<div class="form-group">
				<label for="Address Line 1">Shipping Address:</label>
				<input type="Address Line 1" class="form-control" id="Address Line 1" placeholder="Your shipping address" name="shipping-address" required>
			</div>

			<div class="form-group">

				<div class="form-group">
					<label for="City">City: </label>
					<input type="City" class="form-control" id="City" placeholder="Shipping City" name="shipping-city" required>
				</div>

				<div class="form-group">
					<label for="Province">Province: </label>
					<select class="selectpicker" style="width:375px; height:36px;" name="shipping-province" required>
						<option></option>
						<option>Alberta</option>
						<option>British Columbia</option>
						<option>New Brunswick</option>
						<option>Newfoundland</option>
						<option>Northwest Territories</option>
						<option>Nova Scotia</option>
						<option>Nunavut</option>
						<option>Ontario</option>
						<option>Prince Edward Island</option>
						<option>Quebec</option>
						<option>Saskatchewan</option>
						<option>Yukon</option>
					</select>
				</div>

				<div class="form-group">
					<label for="Postal Code">Postal Code: </label>
					<input type="Postal Code" class="form-control" id="Postal Code" placeholder="Shipping postal code" name="shipping-postalcode" required>
				</div>

				<div class="form-group">
					<label for="Telephone number">Telephone number: </label>
					<input type="Telephone number" class="form-control" id="Telephone number" placeholder="Shipping phone number" name="shipping-phone" required>
				</div>

				<h3>Billing information</h3>

				<div class="form-group">
					<label for="Address Line 1">Shipping Address:</label>
					<input type="Address Line 1" class="form-control" id="Address Line 1" placeholder="Billing address" name="billing-address" required>
				</div>

				<div class="form-group">

					<div class="form-group">
						<label for="City">City: </label>
						<input type="City" class="form-control" id="City" placeholder="Billing City" name="billing-city" required>
					</div>

					<div class="form-group">
						<label for="Province">Province: </label>
						<select class="selectpicker" style="width:375px; height:36px;" name="billing-province" required>
							<option></option>
							<option>Alberta</option>
							<option>British Columbia</option>
							<option>New Brunswick</option>
							<option>Newfoundland</option>
							<option>Northwest Territories</option>
							<option>Nova Scotia</option>
							<option>Nunavut</option>
							<option>Ontario</option>
							<option>Prince Edward Island</option>
							<option>Quebec</option>
							<option>Saskatchewan</option>
							<option>Yukon</option>
						</select>
					</div>

					<div class="form-group">
						<label for="Postal Code">Postal Code: </label>
						<input type="Postal Code" class="form-control" id="Postal Code" placeholder="Billing postal code" name="billing-postalcode" required>
					</div>

					<div class="form-group">
						<label for="Telephone number">Telephone number: </label>
						<input type="Telephone number" class="form-control" id="Telephone number" placeholder="Billing phone number" name="billing-phone" required>
					</div>



					<button class="btn  btn-success btn-block" type="submit">Submit</button>

		</form>
		</div>

		<script src="../lib/ie10-viewport-bug-workaround.js"></script>
</body>

</html>