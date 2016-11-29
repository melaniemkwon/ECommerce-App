<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="en">
<head>
    <title>Inventory Manager</title>
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
      background: linear-gradient(141deg, #0fb8ad 0%, #1fc8db 51%, #2cb5e8 75%);
      color: white;
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
        <h1>Q-Shop Inventory</h1>      
        <h4>Welcome, administrator.</h4>
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
          <a class="navbar-brand" href="#">Q-Shop Inventory Manager - Edit Inventory</a>
        </div>
      </div>
    </nav>

<div class="container">
    
   <form action="EditInventory" method="post">
        <div class="row">
           <div class="col-md-10">   
               <input type="hidden" name="id" value="${item.id}">
               <input type="text" class="form-control" name="itemName" value="${item.itemName}">
               <input type="text" class="form-control" name="itemImage" value="${item.itemImage}">
               <input type="text" class="form-control" name="itemDescription" value="${item.itemDescription}">
               <input type="text" class="form-control" name="itemPrice" value="${item.itemPrice}">
               <input type="text" class="form-control" name="quantity" value="${item.quantity}">
           </div>
       </div> 
       <div class="row">
         <div class="col-md-2"> 
             <button type="submit" class="btn btn-info btn-block col-sm-3">Confirm Edits</button>
         </div>
         <div class="col-md-2"> 
             <a href="Inventory" class="btn btn-default" role="button">Cancel</a> 
         </div>
       </div>
   </form>
    
 </div> 

</body>
</html>