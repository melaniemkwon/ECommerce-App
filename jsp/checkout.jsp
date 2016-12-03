<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <title>Q-Shop Checkout</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">    
    <%-- <link rel="stylesheet" href="https://bootswatch.com/paper/bootstrap.min.css"> --%>
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
  </style>
</head>
<body>
    <div class="page-header">
      <div class="container text-center">
        <h2>Q-Shop Checkout</h2>      
      </div>
    </div>
    <div class="container panel panel-default">
    <h4>1   Enter Your Information</h4>
    <form id="checkoutForm" action="Checkout" method="post" class="bottom-align-text">
        <div class="row">
        
           <div class="col-md-5">   
               <input type="text" class="form-control" name="firstName" placeholder="First Name"><br>
               <c:if test="${not empty error_firstName}">
                    <p class="well-sm bg-danger">${error_firstName}</p>
               </c:if>
               <input type="text" class="form-control" name="lastName" placeholder="Last Name""><br>
               <c:if test="${not empty error_lastName}">
                    <p class="well-sm bg-danger">${error_lastName}</p>
               </c:if>
               <input type="text" class="form-control" name="email" placeholder="Email""><br>
               <c:if test="${not empty error_email}">
                    <p class="well-sm bg-danger">${error_email}</p>
               </c:if>
           </div>
           
           <div class="col-md-4 col-md-offset-2">
              <div class="panel panel-default">
                   <div class="row">
                      <div class="col-md-10 col-md-offset-1">
                      <!-- <input type="submit" form="myform" class="btn btn-warning btn-block" style="padding-top:5px;" value="Place your order"/> -->
                      
                      <h4>Order Summary</h4>
                      <table class="table">
                      		<tr>
                      			<th>Qty</th>
                      			<th>Item Name</th>
                      			<th>Price</th>
                      		</tr>
                      <c:forEach items="${cartInventory}" var="item">
                      		<tr>
                      			<td>${item.quantity}</td>
                      			<td>( ${item.itemName} )</td>
                      			<td>${item.price }</td>
                      		</tr>
                      		
                      </c:forEach>
                      		<tr>
                      			<td>Total Price</td>
                      			<td></td>
                      			<td>$ ${requestScope["totalPrice"]}.00</td>
                      		</tr>
                      </table>
                      </div>
                   </div>
                   
                  <button type="submit" class="btn btn-warning btn-block">Place your order</button>
               </div>
           </div>
           
       </div> 
   </form>
   
   <hr />
   
    </div>
</body>
</html>