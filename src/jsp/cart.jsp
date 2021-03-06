<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>Q-Shop Store</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default rounded borders and increase the bottom margin */
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
    /* Remove the jumbotron's default bottom margin */
     .jumbotron {
      margin-bottom: 0;
    }
   
    /* Add a gray background color and some padding to the footer */
    footer {
      background-color: #f2f2f2;
      padding: 25px;
    }
    #search-bar{
    	margin-top:0.80em;
    }
  </style>
</head>
<body>
	<div class="jumbotron">
  <div class="container text-center">
    <h1>Q-Shop Store</h1>
    <p>Mission, Vission & Values</p>
  </div>
</div>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="Store">Home</a></li>
        <li><a href="Inventory">Administrator</a></li>
        
        	<li id="search-bar"><form action="qShop" method="get"><input type="text" name="query" placeholder="Search Item" >
      	    <button type="submit" class="btn btn-default btn-xs glyphicon glyphicon-search" ></button></form>  </li> 
          
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="ShoppingCart"><span class="glyphicon glyphicon-shopping-cart"></span> Cart (${requestScope["cartLength"]})</a></li>
      </ul>
    </div>
  </div>
</nav>

<div class="container">
<div class="page-header">
 	<h1>Cart List</h1>
 	
 </div>
 <table class="table table-bordered table-striped table-hover">
 	<tr><th>ItemName</th><th>Quantity</th><th>Price</th><th>Action</th></tr>
 	<c:forEach items="${cartInventory}" var="carts">
 		<tr>
 			<form action="ShoppingCart" method="post">
 				<td><img src="${carts.itemImage}" width="42" height="42">&nbsp ${carts.itemName}</td>
 			<td>${carts.quantity }</td>
 			<td>${carts.price }</td>
 			<input type="hidden" name="id" value="${carts.id }">
 			<td><button class="btn"><span class="glyphicon glyphicon-trash"></span></button></td>
 			
 			</form>
 			
 		</tr>
 	
 	</c:forEach>
 	<tr><th></th><th>Total Price</th><th align="center">$ ${requestScope["totalPrice"]}.00</th><th></th></tr>
 	<tr><td colspan="3"></td><td><a class="btn btn-success" href="Checkout">Proceed to Checkout</a></td></tr>
 </table>


</div>
 
</body>

<footer class="container-fluid text-center">
  <p>&copy Q-Online Shop Store</p>
</footer>
</html>
