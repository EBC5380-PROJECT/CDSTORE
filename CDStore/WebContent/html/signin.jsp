<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Signin </title>

	<!-- Bootstrap core CSS -->
	<link href="../css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="../css/signin.css" rel="stylesheet">

	<script src="../lib/ie-emulation-modes-warning.js"></script>
	<script src="../lib/jquery-2.1.4.min.js"></script>
	<script src="../js/host.js"></script>
	<script>
		//change the form's action dynamically on page load such that there is
		var changeAction = function () {
			$("#form").attr("action", name + Server.signInService.url)
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

<body>
	<div class="container">

		<form id="form" class="form-signin" action="" onsubmit="changeAction();" method="post">

			<h2 class="form-signin-heading">Please sign in</h2>
			<!-- Error messages display here -->
			<font color="red" id="msg_error">
		  	 
		  </font>
			<label class="sr-only">Email address</label>
			<!-- Error message read from session -->
			<div><font id="error" color="red"></font></div>
			<br>
			<input id="form_email" name="username" class="form-control" placeholder="Your Username" required autofocus>
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="form_password" class="form-control" placeholder="Your Password" name="password" required>
			<div class="checkbox">
				<label>
					<input type="checkbox" name="remember">Remember me </input>
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
		</form>

	</div>


	<script src="../lib/ie10-viewport-bug-workaround.js"></script>
</body>

</html>