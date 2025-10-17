

/*
CREATE TABLE Customer (
ID INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
Fname varchar(50) NOT NULL,
Lname varchar(50) NOT NULL,
Address varchar(50) NOT NULL,
PhoneNo varchar(50) NOT NULL,
Email varchar(50) NOT NULL,
Type INT NOT NULL,
Zipcode INT NOT NULL FOREIGN KEY REFERENCES ZipTable(Zip)
)
*/
CREATE TABLE ZipTable (
Zip INT PRIMARY KEY NOT NULL,
City varchar(50) NOT NULL
)





insert into ZipTable (Zip, City)
VALUES (9000, 'Aalborg');

insert into ZipTable (Zip, City)
VALUES (9800, 'Sæby');

insert into ZipTable (Zip, City)
VALUES (9830, 'Taars');





INSERT INTO Customer (Fname, Lname, Address, PhoneNo, Email, Type, Zipcode)
VALUES ('John', 'Doe', '123 Maple Street', '555-123-4567', 'john.doe@email.com', 1, 9000);

INSERT INTO Customer (Fname, Lname, Address, PhoneNo, Email, Type, Zipcode)
VALUES ('Sarah', 'Lee', '456 Oak Avenue', '555-987-6543', 'sarah.lee@email.com', 2, 9800);

INSERT INTO Customer (Fname, Lname, Address, PhoneNo, Email, Type, Zipcode)
VALUES ('Michael', 'Brown', '789 Pine Road', '555-321-9876', 'michael.brown@email.com', 1, 9830);



/*
CREATE TABLE [Product] (
ID INT IDENTITY (1,1) PRIMARY KEY NOT NULL,
ProductNumber INT UNIQUE NOT NULL,
ProductName varchar(50) NOT NULL,
MinStock int,
Description varchar (500),
SKU INT NOT NULL,
Type INT
)
*/

insert into [Product] (ProductNumber, ProductName, MinStock, Description, SKU, Type)
VALUES (1, 'Cowboy hat', 10, 'Lign en fra den vilde vest!', 11223344, 1);

insert into [Product] (ProductNumber, ProductName, MinStock, Description, SKU, Type)
VALUES (2, 'Pistol Bælte', 20, 'Gør "Livet" sjovere med et bælte', 11223355, 2);

insert into [Product] (ProductNumber, ProductName, MinStock, Description, SKU, Type)
VALUES (3, 'SeksLøber', 10, 'Vær den der vinder i en Duel!', 11223366, 3);








