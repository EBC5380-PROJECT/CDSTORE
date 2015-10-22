/* Table that contains the CD categories for the CD store
 *
 * Category
 * categoryId:		category id
 * categoryName:	name of category
 */

DROP TABLE IF EXISTS category;
CREATE TABLE category (
 categoryId INT UNSIGNED NOT NULL AUTO_INCREMENT,
 categoryName VARCHAR(40) NOT NULL,
 PRIMARY KEY(categoryId)
);
INSERT INTO category(categoryId,categoryName) VALUES (1,'COUNTRY');
INSERT INTO category(categoryId,categoryName) VALUES (2,'ROCK');
INSERT INTO category(categoryId,categoryName) VALUES (3,'POP');




/* Table that contains the account information of a user/client of the CD Store
 * account
 * accountId:		account id
 * accountName:		unique account name
 * password1:		password
 * billingAddressId:  	billing address of user
 * shippingAddressId: 	shipping address of user
 * email:		email address of user
 */

DROP TABLE IF EXISTS account;
CREATE TABLE account(
 accountId INT UNSIGNED NOT NULL AUTO_INCREMENT,
 accountName VARCHAR(50) NOT NULL,
 password1 VARCHAR(50) NOT NULL,
 billingAddressId INT UNSIGNED NOT NULL,
 shippingAddressId INT UNSIGNED NOT NULL,
 email VARCHAR (50) NOT NULL,
 PRIMARY KEY(accountId)
) ;



/* Table that contains the address details for a user/client of the CD store
 * address
 * addressId:	address id
 * accountId:	related accountId
 * name: 		reciepian name
 * street:		street
 * province:	province
 * country:		country
 * zip:			zip
 * phone:		phone
 */

DROP TABLE IF EXISTS address;
CREATE TABLE address (
 addressId INT UNSIGNED NOT NULL AUTO_INCREMENT,
 street    VARCHAR(200) NOT NULL,
 city	   VARCHAR(200) NOT NULL,
 province  VARCHAR(20)  NOT NULL,
 country   VARCHAR(100)  NOT NULL,
 postalCode VARCHAR(20)  NOT NULL,
 phone     VARCHAR(20),
 PRIMARY KEY(addressId)
) ;




/* Table that contains the order details of user/client of the CD Store
 * orders
 * orderId:		order id
 * accountId:		account id
 * shippingAddressId	address to ship order to
 * shippingCharge:	charge of shipping
 * taxes:		total taxes on order
 * totalCost:		total cost of order
 * status	:	order status
 */

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  orderId INT UNSIGNED NOT NULL AUTO_INCREMENT,
  accountId INT UNSIGNED NOT NULL,
  shippingAddressId INT UNSIGNED, 
  status ENUM('ORDERED','PROCESSED','DENIED') NOT NULL,
  shippingCharge DECIMAL(60,2) NOT NULL,
  taxes DECIMAL(60,2) NOT NULL,
  totalCost DECIMAL(60,2) NOT NULL,
  processedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (orderId),
  INDEX (orderId),
  KEY customerId_fk (accountId),
  CONSTRAINT accountId_fk FOREIGN KEY (accountId) REFERENCES account (accountId) on DELETE CASCADE
);




/* Table that contains the list of CDs that the CD Store has available for purchase
 * CD 
 * cdid:     	unique identifier of CD (like ISBN for books)
 * title:    	title of CD
 * price:    	unit price
 * categoryId: 	categoryId in Category table
 * description:	description of this CD
 */

DROP TABLE IF EXISTS CD;
CREATE TABLE CD (
 cdid		INT UNSIGNED NOT NULL AUTO_INCREMENT,
 title	VARCHAR(60) NOT NULL,
 artistName	VARCHAR(60) NOT NULL,
 description 	VARCHAR(2000),
 categoryId	INT UNSIGNED NOT NULL, 
 price	DECIMAL(60,2) UNSIGNED NOT NULL,
 image	VARCHAR(60),
 PRIMARY KEY(cdid),
 CONSTRAINT category_fk FOREIGN KEY(categoryId) REFERENCES category (categoryId)
) ;

#
# Dumping data for table 'CD'
#

INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (1, 'Johnny Cash Greatest Hits', 'Johnny Cash', "Greatest Hits, Vol. 1 is a compilation album by country singer Johnny Cash, released in 1967 on Columbia Records. It is notable in that it marks the first appearance of 'Jackson', Cash's famous duet with his future wife, June Carter",1,16.25,'image1');
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (2,'16 Biggest Hits', 'Various artists', "16 Biggest Hits is a compilation album by country singer Willie Nelson. It was released on July 14, 1998",1,15.99,'image2');
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (3,'Patsy Cline Im So Lonely','Patsy Cline',"", 1,15.99,"image3");
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (4, 'Tragically Hip Fully Completely', 'Tragically Hip', "", 2,15.99,"image4");
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (5, 'Beatles White Album', 'Beatles', "", 2,15.99,"image5");
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (6, 'Rolling Stones Forty Licks', 'Rolling Stones', "",  2,19.99,"image6");
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (7, 'Madonna Greatest Hits', 'Madonna',  "", 3,12.99,"image6");
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (8, 'Alannis Morissette Jagged Little Pill', 'Alannis Morissette', "",  2,15.99,"image8");
INSERT INTO CD (cdid, title, artistName, description, categoryId,price, image) VALUES (9, 'Video Killed the Radio Star', 'Roxy', "", 3,13.99,"image9");






/* Table that contains the item details of an order of a user/client of the CD Store
 * orderitem
 * orderitemId	:	ordertiem id
 * cdid:		id of cd
 * orderId	: 	order Id
 * quantity	:	quantity of item ordered
 * totalCost:		total cost of items
 */

DROP TABLE IF EXISTS orderitem;
CREATE TABLE orderitem (
  orderitemId INT UNSIGNED NOT NULL AUTO_INCREMENT,
  orderId INT UNSIGNED NOT NULL,
  cdid INT UNSIGNED NOT NULL,
  quantity INT UNSIGNED NOT NULL,
  PRIMARY KEY (orderitemId,cdid),
  INDEX (orderitemId),
  CONSTRAINT orderId_fk FOREIGN KEY (orderId) REFERENCES orders (orderId) ON DELETE CASCADE,
  INDEX (cdid),
  CONSTRAINT cdId_fk FOREIGN KEY (cdId) REFERENCES cd (cdId) ON DELETE CASCADE
);







/**********************************************
/* Sequence for dropping tables
/**********************************************


DROP TABLE `cd_db`.`orderitem`;
DROP TABLE `cd_db`.`orders`;
DROP TABLE `cd_db`.`account`;
DROP TABLE `cd_db`.`address`;
DROP TABLE `cd_db`.`cd`;
DROP TABLE `cd_db`.`category`;





// ***************************************************************************************************************************
// 					Store procedures
//****************************************************************************************************************************


/*
 *Stored proc for retrieving all rows in the category table
 *
 */



CREATE PROCEDURE `sp_getCategoryList`()

BEGIN
Select * from category;

END



/*
 *Stored proc for retrieving all rows in the cd table
 *
 */



CREATE PROCEDURE `sp_getCDList`()

BEGIN
Select * from cd;

END



/*
 *Store proc for retrieving the row in the cd table where cdid equals a specif value (i.e. productId)
 *
 */




CREATE PROCEDURE `sp_getDetailedProductInfo`(IN productId INT)

BEGIN
Select * from cd where cdid = productId;

END



/*
 *Stored proc for retrieving all rows in the cd table that are of the specified category
 *
 */


CREATE PROCEDURE `sp_getProductListByCategory`(IN Category INT)

BEGIN

Select * from cd where cd.categoryId = Category;

END




/*
 * Stored proc for retrieving a row in the account table for a specific user.
 *
 */


CREATE PROCEDURE `sp_getAccountInfo`(IN userName VARCHAR(50))

BEGIN

Select * from account where accountName=userName;

END


/*
 * Stored proc for updating the status of a specific order
 *
 */

CREATE PROCEDURE `sp_updateOrderStatus`(IN orderID INT(11), IN newStatus VARCHAR(50), IN accntId INT(11))

BEGIN

update orders
  
set orders.status = newStatus,
      
    orders.shippingAddressId = (SELECT account.shippingAddressId 
  
    FROM account
  
    WHERE account.accountId = accntId)
  
where orders.orderId = orderID;

END



/*
 * Stored proc for selecting a row in the account table for the specified userName and password.
 *
 */


CREATE PROCEDURE `sp_validateCredentials`(IN userName VARCHAR(50), IN password VARCHAR(50))

BEGIN

Select * from account where accountName = userName and password1 = password;

END


