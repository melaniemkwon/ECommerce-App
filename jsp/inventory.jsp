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
      
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li ><a href="Store">Home</a></li>
        <li class="active"><a href="Inventory">Administrator</a></li>
        <li><a href="History">Transaction</a></li>
        	<li id="search-bar"><form action="Store" method="get"><input type="text" name="query" placeholder="Search Item" >
      	    <button type="submit" class="btn btn-default btn-xs glyphicon glyphicon-search" ></button></form>  </li> 
          
      </ul>
      
    </div>
  </div>
</nav>

<div class="container">

    <c:if test="${empty itemInventory}">
        <div class="jumbotron">
            <h1><small>There are no items in the inventory</small></h1>
        </div>
    </c:if>
    
    <c:if test="${not empty itemInventory}">
    <table class="table table-hover table-striped table-bordered" >
        <thead>
            <tr>
                
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                
                <th>Remove</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${itemInventory}" var="item">
                <tr>                    
                    
                    <td>
                    	${item.itemName}            
                    </td>
                    <td>
                    	 ${item.itemDescription}            
                    </td>
                    
                    <td class="col-xs-1">
                    	<span>$ </span>${item.itemPrice } 
                    </td>
                    <td>
                   		 ${item.quantity}            
                    </td>
                    
                  
                    
                    <td>
                  	  <a href="Inventory?id=${item.id}" class="btn btn-danger" role="button"><span class="glyphicon glyphicon-remove"></span></a>           
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </c:if>
    <div class="page-header">
	  <div class="container text-center">
	        
	    <h4>Add item to The Store</h4>
	  </div>
	</div>
    <div class="container">
    	<form action="Inventory" method="post">
        	<div class="form-group row">
						  <label for="example-text-input" class="col-xs-2 col-form-label">Name of Product</label>
						  <div class="col-xs-10">
						    <input class="form-control" type="text" placeholder="Enter Item Name" name="itemName">
						    <c:if test="${not empty nameError}">
                   				 <p class="well-sm bg-danger">${nameError}</p>
               				</c:if>
               				
						  </div>
					</div>
			<div class="form-group row">
						  <label for="example-text-input" class="col-xs-2 col-form-label">Image Url</label>
						  <div class="col-xs-10">
						    <input class="form-control" type="text" placeholder="Enter Image Url" name="itemImage">
						    <c:if test="${not empty imageError}">
                   				 <p class="well-sm bg-danger">${imageError}</p>
               				</c:if>
						  </div>
					</div>
			<div class="form-group row">
						  <label for="example-text-input" class="col-xs-2 col-form-label">Description</label>
						  <div class="col-xs-10">
						    <input class="form-control" type="text" placeholder="Enter Item Description" name="itemDescription">
						    <c:if test="${not empty descriptionError}">
                   				 <p class="well-sm bg-danger">${descriptionError}</p>
               				</c:if>
						  </div>
					</div>
			<div class="form-group row">
						  <label for="example-text-input" class="col-xs-2 col-form-label">Quantity</label>
						  <div class="col-xs-10">
						    <input class="form-control" type="text" placeholder="Enter Item Quanity" name="itemQuantity">
						    <c:if test="${not empty quantityError}">
                   				 <p class="well-sm bg-danger">${quantityError}</p>
               				</c:if>
						  </div>
					</div>
			<div class="form-group row">
						  <label for="example-text-input" class="col-xs-2 col-form-label">Price</label>
						  <div class="col-xs-10">
						    <input class="form-control" type="text" placeholder="Enter Item Price" name="itemPrice">
						    <c:if test="${not empty priceError}">
                   				 <p class="well-sm bg-danger">${priceError}</p>
               				</c:if>
						  </div>
					</div>
			<div class="form-group row">
			  <label for="example-search-input" class="col-xs-2 col-form-label"></label>
			  <div class="col-xs-10">
			    <button class="btn btn-primary" typy="submit">Add to Inventory</button>
			  </div>
			</div>
        
        </form>
    
    
    </div>
        
        

    </div>
    
 </div> 

</body>
</html>