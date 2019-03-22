# Vendors-API

### Database Design

````

CREATE DATABASE TLC_EXERCISE;

USE TLC_EXERCISE;

--VENDORS Table

CREATE TABLE Vendors(
	ID INT AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,
	PHONE VARCHAR(50),
	REGION VARCHAR(50),
	PRIMARY KEY (ID)
);

--BRANDS table

CREATE TABLE Brands(
	NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY (NAME)
);

--ASSOCIATIVE ENTITY

CREATE TABLE vendor_brands(
	VENDOR_ID INT NOT NULL,
	BRAND VARCHAR(50) NOT NULL,
	
	PRIMARY KEY (VENDOR_ID, BRAND),
	FOREIGN KEY (VENDOR_ID) REFERENCES Vendors (ID),
	FOREIGN KEY (BRAND) REFERENCES BRANDS (NAME)
)

````

### API ENDPOINTS

| URL  | METHOD | Function | Username/Password
| ------------- | ------------- | ------------- | ------------- |
| http://localhost:8080/addVendor  | POST | Add Vender using UI  | admin/access
| http://localhost:8080/api/vendors  | GET | List All Vendors  | admin/access
| http://localhost:8080/api/vendors  | POST | Add New Vendor  | admin/access
| http://localhost:8080/api/vendors/brand/{brand}  | GET | List all vendors that have a specific brand  | user/secret
| http://localhost:8080/api/vendors/region/{region}  | GET | List all vendors for a specific region  | user/secret
| http://localhost:8080/api/vendors/{id}/brand  | PUT | Add a brand to a vendor  | user/secret
| http://localhost:8080/api/vendors/{id}  | DELETE | delete Vendor  | user/secret
| http://localhost:8080/api/vendors/report/count  | GET | Total Count of Vendors  | 


# HOW TO RUN

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat installation is necessary. You run it using the java -jar command.

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:

```
  
 mvn spring-boot:run


```
* Visit http://localhost:8080

### Developer
* **Alemberhan Getahun**
