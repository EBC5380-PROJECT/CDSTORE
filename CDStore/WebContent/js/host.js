var name = "http://localhost:8080/";

String.prototype.replaceAll = function(search, replace) {
    if (replace === undefined) {
        return this.toString();
    }
    return this.split(search).join(replace);
}

//Service class
var ServiceObject = function (url, q) {
	this.address = name + url;
	this.queryAddress = this.address + "?" + q;
	this.query = q;
};

var page = function () {
	return this.address + "?" + this.parameter + "=";
};

//To change file or parameter names, change
//address: the name of the html file
//parameter: GET url parameters
var Client = {
	"index": {
		"address": "index.html",
		parameter: ""
	},
	"category": {
		"address": "category.html",
		parameter: "c",
		page: page
	},
	"info": {
		"address": "info.html",
		parameter: "productid",
		page: page
	},
	"cart": {
		"address": "cart.html",
		parameter: ""
	},
	"finish": {
		"address": "finish.html",
		parameter: ""
	},
	"payment": {
		"address": "payment.html",
		parameter: ""
	},
	"register": {
		"address": "register.html",
		parameter: ""
	},
	"signin": {
		"address": "signin.html",
		parameter: ""
	},
	//these directories are all relative to the htmldir
	"imgdir": "../image/album/",
	"imgstockdir": "../image/stock/",
	"jsdir": "../js/",
	"libdir": "../lib/",
	"imgx": 130,
	"imgy": 130
};

//REST-like services for query database.
//To change service location or parameter names, change this object
var Service = {
	//GET
	"categoryListService": new ServiceObject("CDStore/CategoryAction", ""),

	"productListByCategoryService": new ServiceObject("CDStore/ProductListAction", "category"),

	"productInfoService": new ServiceObject("CDStore/ProductDetailAction", "productid"),


};

//POST authentication services at server
var Server = {
	//POST
	"signInService": {
		"url": "Test/SignIn"
	},
	"registerService": {
		"url": "Test/Register"
	},
	"paymentService": {
		"url": "Test/Payment"
	},
	"shippingService": {
		"url": "Test/Shipping"
	},
	
	//TODO: Check on this
	//Might want to implement this server side.
	//Give new session
	//Redirect back to home page
	"signoutService": {
		"url": "Test/Signout"	
	},

	//TEST service: returns all key/value
	"testService": {
		"url": "Test/PostService"
	}
};

//Retrieve category data for left sidebar and select the current category
var retrieveCategory = function (currentCategoryID) {
	//retrieve category data for left sidebar
	$.ajax({
		type: "GET",
		url: (Service.categoryListService.address),
		success: function (d, status) {
			var data = JSON.parse(d);

			var dom = '<li id="@catID" class="inactive"><a href="@link">@catName</a></li>'

			//insert category in DOM
			for (var i = 0; i < data.length; i++) {
				var newDom = dom;
				//replace link
				newDom = newDom.replace("@link", Client.category.address + "?" + Client.category.parameter + "=" + data[i].categoryId);
				//replace category name
				newDom = newDom.replace("@catName", data[i].categoryName);
				//replace catID
				newDom = newDom.replace("@catID", "catID-" + data[i].categoryId);

				//add element to sidebar
				$("#categories").append(newDom);

				//highlight the current category
				if (currentCategoryID != null) {
					$("#catID-" + currentCategoryID).attr("class", "active");
				} else {
					$("#catID-ALL").attr("class", "active");
				}
			}
		},
		error: function (xhr) {
			$("#categories").append("<strong>Category database can't be contacted.</strong>")
		}
	});
};

//display error message on the page
var displayError = function (message) {
	$("#msg_error").html(message);
};

//dynamically set the links for the top nav-bar
var getNavBarLinks = function (loginstatus) {
	//"CD Store" - left
	$("#fixed-top-cd_store").attr("href", Client.index.address);

	//Cart
	$("#fixed-top-cart").attr("href", Client.cart.address);

	//Register
	$("#fixed-top-register").attr("href", Client.register.address);

	//Browse all categories
	$("#fixed-top-category").attr("href", Client.category.address);

	//Signin/out
	if (loginstatus == false) {
		//log in
		$("#fixed-top-sign").attr("href", Client.signin.address);
		$("#fixed-top-sign").html("Signin");
	} else {
		//change to sign out
		//set username in session to null
		//probably won't work
		//TODO: uncomment and test this
		//$("#fixed-top-sign").attr("onclick", '<% session.setAttribute("username", null); %");
		$("#fixed-top-sign").html("Sign out");
		$("#fixed-top-sign").attr("href", Server.signoutService.url);
	}

};