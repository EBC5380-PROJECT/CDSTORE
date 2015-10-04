/* Category
 * categoryId:	category id
 * categoryName:	name of category
 */
DROP TABLE IF EXISTS Category;
CREATE TABLE Category (
 categoryId INT UNSIGNED NOT NULL AUTO_INCREMENT,
 categoryName VARCHAR(40) NOT NULL,
 PRIMARY KEY(CategoryId)
);
INSERT INTO Category(categoryId,categoryName) VALUES (1,'COUNTRY');
INSERT INTO Category(categoryId,categoryName) VALUES (2,'ROCK');
INSERT INTO Category(categoryId,categoryName) VALUES (3,'POP');


/* CD 
 * cdid:     	unique identifier of CD (like ISBN for books)
 * title:    	title of CD
 * price:    	unit price
 * categoryId: 	categoryId in Category table
 * description:	description of this CD
 */
DROP TABLE IF EXISTS CD;
CREATE TABLE CD (
 cdid		VARCHAR(20) NOT NULL,
 title		VARCHAR(60) NOT NULL,
 price		DECIMAL UNSIGNED NOT NULL,
 categoryId	INT UNSIGNED NOT NULL,
 description VARCHAR(2000),
 PRIMARY KEY(cdid),
 FOREIGN KEY(CategoryId) REFERENCES Category (CategoryId)
) ;

#
# Dumping data for table 'CD'
#

INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd001', 'Johnny Cash Greatest Hits', 1599, 1, "Greatest Hits, Vol. 1 is a compilation album by country singer Johnny Cash, released in 1967 on Columbia Records. It is notable in that it marks the first appearance of 'Jackson', Cash's famous duet with his future wife, June Carter");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd002','16 Biggest Hits', 1599, 1, "16 Biggest Hits is a compilation album by country singer Willie Nelson. It was released on July 14, 1998");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd003','Patsy Cline Im So Lonely' ,1599,1,"");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd004', 'Tragically Hip Fully Completely', 2000, 2,"");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd005', 'Beatles White Album', 2000, 2,"");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd006', 'Rolling Stones Forty Licks', 2000, 2,"");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd007', 'Madonna Greatest Hits', 1799, 3,"");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd008', 'Alannis Morissette Jagged Little Pill', 1799, 3,"");
INSERT INTO CD (cdid, title, price, categoryId,description) VALUES ('cd009', 'Video Killed the Radio Star', 1799, 3,"");
#
#


/* Account
 * accountId:	unique identifier for user account
 * lastName:	last name
 * firstName:	first name
 * password:	account password hashed by MD5 or SHA1
 * lastLogin:	last login time
 */ 
DROP TABLE IF EXISTS Account;
CREATE TABLE Account (
 accountId 	VARCHAR(20) NOT NULL,
 lastName	VARCHAR(100) NOT NULL,
 firstName	VARCHAR(100) NOT NULL,
 password	VARCHAR(500) NOT NULL,
 lastLogin	DATETIME,
 PRIMARY KEY (accountId)
) ;

/* Adress
 * addressId:	address id
 * accountId:	related accountId
 * name: 		reciepian name
 * street:		street
 * province:	province
 * country:		country
 * zip:			zip
 * phone:		phone
 */
DROP TABLE IF EXISTS Address;
CREATE TABLE Address (
 addressId INT UNSIGNED NOT NULL AUTO_INCREMENT,
 accountId VARCHAR(20) NOT NULL,
 name	   VARCHAR(200) NOT NULL,
 street    VARCHAR(200) NOT NULL,
 province  VARCHAR(20)  NOT NULL,
 country   VARCHAR(100)  NOT NULL,
 zip       VARCHAR(20)  NOT NULL,
 phone     VARCHAR(20),
 PRIMARY KEY(addressId),
 FOREIGN KEY(accountId) REFERENCES Account(accountId) ON DELETE CASCADE
) ;


/* purchase order status
 * statusId:	status id
 * statusName:	status name
 */
DROP TABLE IF EXISTS POStatus;
CREATE TABLE POStatus (
 statusId   INT UNSIGNED NOT NULL,
 statusName VARCHAR(50) NOT NULL,
 PRIMARY KEY(statusId)
) ;
INSERT INTO POStatus(statusId,statusName) VALUES (1,'ORDERED');
INSERT INTO POStatus(statusId,statusName) VALUES (2,'PROCESSED');
INSERT INTO POStatus(statusId,statusName) VALUES (3,'DENIED');


/* purchase order
 * poid:	    purchase order id
 * accountId:	user account id
 * statusId:	status of purchase
 * addressId:	address of purchase
 * purchaseDate: purchase date and time
 * totalPrice:  total price of this order
 * paymentCard: Payment card number with only last four digits
 * paymentCardName: Name on payment card
 */
DROP TABLE IF EXISTS PO;
CREATE TABLE PO (
 poId         INT UNSIGNED NOT NULL AUTO_INCREMENT,
 accountId 	  VARCHAR(20) NOT NULL,
 statusId     INT UNSIGNED NOT NULL,
 addressId    INT UNSIGNED NOT NULL,
 purchaseDate DATETIME NOT NULL,
 totalPrice   DECIMAL NOT NULL,
 paymentCard  VARCHAR(30),
 paymentCardName VARCHAR(200),
 PRIMARY KEY(poId),
 FOREIGN KEY (accountId) REFERENCES Account (accountId) ON DELETE CASCADE,
 FOREIGN KEY (statusId) REFERENCES POStatus (statusId),
 FOREIGN KEY (addressId) REFERENCES Address (addressId)
);

/* Items on order
 * id :	  purchase order id
 * cdid:  unique identifier of CD	
 * price: unit price WHEN ordered
 * quantity: quantity of this CD
 */
DROP TABLE IF EXISTS POItem;
CREATE TABLE POItem (
 poId     INT UNSIGNED NOT NULL,
 cdid     VARCHAR(20) NOT NULL,
 price    DECIMAL NOT NULL,
 quantity INT UNSIGNED NOT NULL,
 PRIMARY KEY(poId,cdid),
 INDEX (poId),
 FOREIGN KEY(poId) REFERENCES PO(poId) ON DELETE CASCADE,
 INDEX (cdid),
 FOREIGN KEY(cdid) REFERENCES CD(cdid)
);



