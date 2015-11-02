<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>kill-9 CD catalogue</title>
    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">
    <script src="../lib/ie-emulation-modes-warning.js"></script>
	<script src="../lib/jquery-2.1.4.min.js"></script>
	<script src="../lib/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../lib/ie10-viewport-bug-workaround.js"></script>
	<!-- Test -->
	<script src="../js/host.js"></script>
	<script src="../js/cart.js"></script>
	  
	<script>
		var username = null;
		
		$(function(){
			
			//load left sidebar
			$('#left-all-category-link').attr('href', Client.category.address);
			var currentCategoryId = (document.URL).split("?" + Client.category.parameter + "=")[1];
			
			
			//session variables
			//TODO: remove comment in JSP
			
			<% 
				String un = (String)session.getAttribute("username");
			%>
			username = "<%=un %>";
			
			//load top nav-bar
			if(username == null)
				getNavBarLinks(false);
			else
				getNavBarLinks(true);
			
			
			
			//TODO: remove this after JSP is tested
			console.log("username: " + username);
			
			//retrieve category data for left sidebar
			var retCat = retrieveCategory(currentCategoryId);
			
			var dom = '<div class="col-xs-6 col-sm-3 placeholder><a href="@link"><img src="@image" height="@imgx" width="@imgy" class="img-responsive" alt="Generic placeholder thumbnail"></a><a href="@link"><h4>@title</h4></a><span class="text-muted">@description</span><p class="lead">$@price</p><a class="btn btn-success"';
			
			dom += 'onclick="';
			
			//convert cart object to string and update the session
			//TODO: remove comment
			dom += '$.get(\'' + Service.cartUpdateService.address + '?itemId=@cdID&itemName=@encodedtitle&price=@price\', function(data, status){ alert(\'Item added to cart.\'); });"';

			
			dom += ' href=\"#\" id="@cdID" title="@title" price="@price">Add to cart</a></div>';
			
			//extract url query parameters, if any
			//if none then use the all products service
			//else use productListByCategoryService
			var service = null;
			if(currentCategoryId == null)
				service = Service.productListByCategoryService.queryAddress;
			else
				service = Service.productListByCategoryService.queryAddress + "=" + currentCategoryId;
			
			$.ajax({type: "GET", url: (service), 
				success: function(d, status){
					var data = JSON.parse(d);

					//insert category in DOM
					for(var i = 0; i < data.length; i++){
						var newDom = dom;
						
						newDom = newDom.replaceAll("@encodedtitle", encodeURIComponent(data[i].title));
						
						//id for onclick action
						newDom = newDom.replaceAll("@cdID", data[i].cdId);
						
						//@title
						newDom = newDom.replaceAll("@title", data[i].title);
						
						//@description
						newDom = newDom.replaceAll("@description", data[i].description);
						//@price
						console.log(data[i].price);
						newDom = newDom.replaceAll("@price", data[i].price);
						//@link: replace the two instances of links: one for image one for title
						newDom = newDom.replaceAll("@link", Client.info.page() + data[i].cdId);
						
						newDom = newDom.replace("@image", Client.imgdir + data[i].image);
						newDom = newDom.replace("@imgx", Client.imgx);
						newDom = newDom.replace("@imgy", Client.imgy);
						newDom = newDom.replace("@cart", encodeURIComponent())

						//add element to "categories" in the dom
						$("#items").append(newDom);
					}
					
					//highlight the current category
				},
				error: function(xhr){
					$("#items").append("<strong>The database is currently unavailable.</strong>")
				}
	  		});
		});
	</script>
	  
  </head>

  <body>

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

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar" id="categories">
			  <li id="catID-ALL" class="inactive"><a href="" id="left-all-category-link">ALL</a></li>
			  <!-- JQuery fills here -->
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">CD Store</h1>
			<div id="items">
				<!-- JQuery fills this area with CD data retrieved from service -->
			</div>
        </div>
		</div>
		</div>

<div id="footer" style="margin-left:300px;">
				kill -9 &copyCopyright 2015 - Do not reproduce or plagarize.
		</div>
  </body>
</html>
