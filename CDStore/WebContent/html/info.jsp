<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Info</title>

    <!-- Bootstrap core CSS -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">
    <script src="../lib/ie-emulation-modes-warning.js"></script>
	<script src="../lib/jquery-2.1.4.min.js"></script>
	  <!-- Test -->
	<script src="../js/host.js"></script>
	<script src="../js/cart.js"></script>
	  
	<script>
		var username = null;
		
		$(function(){
			//left sidebar
			$('#catID-ALL').attr('href', Client.category.address);
			//retrieve category data for left sidebar
			var retCat = retrieveCategory(null);
			
			//top nav bar
			getNavBarLinks(!(username == "null" || username == null));
			
			//session variables
			//TODO: uncomment this
			
			<% 
				String un = (String)session.getAttribute("username");
			%>
			username = "<%=un %>";
			
			
			//TODO: remove this after JSP is tested
			console.log("username: " + username);
			
			
			var dom = '<h1>@title</h1><h2> <span class="text-muted">@description</span></h2><br><p class="lead">price:$@price</p><h2><p></p></h2><a class="btn btn-success"';
			
			dom += 'onclick="';
			
			dom += '$.get(\'' + Service.cartUpdateService.address + '?itemId=@cdID&itemName=@encodedtitle&price=@price\', function(data, status){ alert(\'Item added to cart.\'); });"';

			
			dom += ' href=\"#\" id="@cdID" title="@title" price="@price">Add to cart</a></div>';
			
			//convert cart object to string and update the session
			//TODO: remove comment
			
			dom += ';"';
			
			//extract url query parameters, if any
			//if none then use the all products service
			//else use productListByCategoryService
			var parameter = (document.URL).split("?" + Service.productInfoService.query + "=")[1];
			
			//function for giving error message and removing CD image
			var fail = function(msg){
				dom = '<h1>' + msg + '</h1>'
				$("#info").append(dom);
				$("#image").remove();
			};
			
			//if no item is in parameter, then remove
			if(parameter == null || parameter == ''){
				fail('No or incorrect URL parameter.')
			} else {
				$.ajax({type: "GET", url: (Service.productInfoService.queryAddress + "=" + parameter), 
					success: function(d, status){
						var data = JSON.parse(d);
						
						//database doesn't have this item
						//if(data.length != 1){
						//	fail('Item ID ' + parameter + ' does not exist.');
						//} else {
							//insert category in DOM
							var newDom = dom;
							
							newDom = newDom.replaceAll("@encodedtitle", encodeURIComponent(data.title));	
						
							//@cdID
							newDom = newDom.replaceAll("@cdID",  data.cdId);
							//@title
							newDom = newDom.replaceAll("@title", data.title);
							//@description
							newDom = newDom.replace("@description", data.description);
							//@price
							newDom = newDom.replaceAll("@price", data.price);

							//add element to "categories" in the dom
							$("#info").append(newDom);
							$("title").html(data.title);

							//append the image div
							var imgdom = '<img id="img" src="' + Client.imgdir + data[0].image + '" class="img-responsive" alt="Generic placeholder thumbnail">';
							$("#image_div").append(imgdom);
						
						//}
					},
					error: function(xhr){
						fail('Failed to contact database. Are you sure the url parameters are correct? (' + Service.productInfoService.query + 'X, where X is the product ID)');
					}
				});
			}
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
			  <li class="inactive"><a id="catID-ALL" href="">ALL</a></li>
			  <!-- JQuery fills this part -->
          </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">CD Store</h1>

          <div class="row placeholders">
              <div class="col-xs-8 col-sm-4 placeholder" id="image_div">
             
            </div>
         
		
		<div class="col-xs-16 col-sm-8 placeholder" id="info">
			<!-- JQuery fills this part -->	
  		</div>

		</div>
        

<div id="footer" style="margin-left:300px ;">
				kill -9 &copyCopyright 2015 - Do not reproduce or plagarize.
		</div>
    <script src="../lib/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../lib/ie10-viewport-bug-workaround.js"></script>
			
	
  </body>
</html>
