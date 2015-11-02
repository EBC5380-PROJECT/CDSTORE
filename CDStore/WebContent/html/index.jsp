<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Home</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../lib/ie-emulation-modes-warning.js"></script>
    <link href="../css/carousel.css" rel="stylesheet">
	<script src="../lib/jquery-2.1.4.min.js"></script>
    <script src="../lib/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../lib/ie10-viewport-bug-workaround.js"></script>
	  
	<!-- Test -->
	<script src="../js/host.js"></script>
	<script src="../js/cart.js"></script>
	  
	<script>
		$(function(){
			//get session variables
			var username = null;
			
			//TODO: uncomment this
			<% 
				String un = (String)session.getAttribute("username");
			%>
			
			username = "<%=un %>";
			
			//top nav bar
			getNavBarLinks(!(username == "null" || username == null));
			
			//retrieve data Category List service
			var dom = '<div class="col-lg-4"><img class="img-circle" src="@imgsrc" alt="Generic placeholder image" width="140" height="140" id="@catID"><h2><a href="@link">@catName</a></h2><p></p><p><a class="btn btn-default" href="@link" role="button">View details &raquo;</a></p></div>';
			
			$.ajax({type: "GET", url: (Service.categoryListService.address), 
				success: function(d, status){
				var data = JSON.parse(d);
					
				//insert category in DOM
				for(var i = 0; i < data.length; i++){
					var newDom = dom;
					//replace category ID
					newDom = newDom.replace("@catID", data[i].categoryId);
					//replace category name
					newDom = newDom.replace("@catName", data[i].categoryName);
					//replace links
					newDom = newDom.replaceAll("@link", Client.category.address + "?" + Client.category.parameter + "=" + data[i].categoryId);
					//TO DO: uncomment this after image for categories has been placed
					console.log(Client.imgstockdir + data[i].img);
					newDom = newDom.replace("@imgsrc", Client.imgstockdir + data[i].imageName);
					
					//add element to "categories" in the dom
					$("#categories").append(newDom);
					
					//carousel
					$("#carousel-category").attr("href", Client.category.address);
					$("#carousel-signup").attr("href", Client.register.address);
					
				}},
				error: function(xhr){
					$("#categories").append("<strong>The database is currently unavailable.</strong>")
				}
	  		});
		});
	</script>
  </head>
<!-- NAVBAR
================================================== -->
  <body>
    <div class="navbar-wrapper">
      <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a id="fixed-top-cd_store" class="navbar-brand" href="#">Kill-9 STORE</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav">
				  <li><a id="fixed-top-category" href="">Browse</a></li>
				<li><a id="fixed-top-register" href="">Register</a></li>
				<li><a id="fixed-top-cart" href="">Your cart</a></li>
				  <li><a onclick="" id="fixed-top-sign" href="">Signin/Signout</a></li>
				
              </ul>
            </div>
          </div>
        </nav>

      </div>
    </div>

	<script>
		  
	  
	</script>
	  

    <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      </ol>
      <div class="carousel-inner" role="listbox" >
       
        <div class="item active">
          <div class="container">
            <div class="carousel-caption">
              <h1>One way to get music CDs</h1>
              <p></p>
              <p><a class="btn btn-lg btn-primary" id="carousel-category" href="" role="button">Browse gallery</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Categories filled by JQuery -->
      <div class="row" id="categories"></div>


      <!-- FOOTER -->
      <footer>
        <p class="pull-right"><a href="#">Back to top</a></p>
      </footer>

    </div><!-- /.container -->
  </body>
</html>
