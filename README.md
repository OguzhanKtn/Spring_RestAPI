# Spring_RestAPI

<p> This repo has some restApi works that get data from url and parse it to Json and also parsing Xml to Json. </p>

## Technologies :
### Java 8
### Spring 2.17.13
### MySql

## Explanation :
```
The user must first login to be able to make a request. For this used filterConfig middleware. A session occurs after login.
When an user registers in the system, his/her password is encrypted and saved in the database. For this used GoogleTinkEncryption.
ResponseEntity used for response to requests from client. 
```
