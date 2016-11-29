package qShop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/Inventory"}, loadOnStartup = 0)
public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e ) {
            throw new ServletException( e );
        }
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> itemInventory = new ArrayList<Item>();
		String tempId = request.getParameter("id");
		if(tempId != null ){
			Connection c = null;
	        try
	        {
	        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
	            String username = "cs3220stu28";
	            String password = "DUMMY123";
	            
	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            
            	int id = Integer.parseInt(tempId);
            	
                String sql = "delete from itemInventory where id= ?";
                PreparedStatement pstmt = c.prepareStatement( sql );
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                //Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "select * from itemInventory" );
                while(rs.next()){
                		Item items = new Item(rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"), rs.getString("description"),rs.getInt("quantity"), rs.getDouble("itemPrice"));
                    	itemInventory.add(items);

                }
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		} else {
			Connection c = null;
	        try
	        {
	        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
	            String username = "cs3220stu28";
	            String password = "DUMMY123";
	            
	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            
	                
	                ResultSet rs = stmt.executeQuery( "select * from itemInventory" );
	                while(rs.next()){
	                		Item items = new Item(rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"), rs.getString("description"),rs.getInt("quantity"), rs.getDouble("itemPrice"));
	                    	itemInventory.add(items);

	                }

	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		}
		
        request.setAttribute("itemInventory", itemInventory);
		request.getRequestDispatcher( "/WEB-INF/store/inventory.jsp" ).forward( request, response );		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String itemName = request.getParameter("itemName");
		String itemImage = request.getParameter("itemImage");
		String itemDescription = request.getParameter("itemDescription");
		Integer itemQuantity = null;
		Double itemPrice = null;
		boolean hasError = false;
		
		// sticky form
		request.setAttribute("itemName", itemName);
		request.setAttribute("itemImage", itemImage);
		request.setAttribute("itemDescription", itemDescription);
		
		try {
			itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
			request.setAttribute("itemQuantity", itemQuantity);
		}
		catch (NumberFormatException e) {
			request.setAttribute("quantityError", "Please enter a valid quantity.");
			hasError = true;
		}
		
		try {
			itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
			request.setAttribute("itemPrice", itemPrice);
		}
		catch (NumberFormatException e) {
			request.setAttribute("priceError", "Please enter a valid price.");
			hasError = true;
		}
		
		if(itemName == null || itemName.trim().length() == 0){
			request.setAttribute("nameError", "Please enter name");
			hasError = true;
		}
		if(itemImage == null || itemImage.trim().length() == 0){
			request.setAttribute("imageError", "Please enter an image url");
			hasError = true;
		}
		if(itemDescription == null || itemDescription.trim().length() == 0){
			request.setAttribute("descriptionError", "Please enter name");
			hasError = true;
		}
		if(hasError){
			doGet(request, response);
            return;
		}
		else{
			Connection c = null;
	        try
	        {
	        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
	            String username = "cs3220stu28";
	            String password = "DUMMY123";
	            
	            c = DriverManager.getConnection( url, username, password );
	            Statement stmt = c.createStatement();
	            String sql = "insert into itemInventory values(0,?,?,?,?,?)";
	            PreparedStatement pstmt = c.prepareStatement( sql );
	            pstmt.setString(1, itemName);
	            pstmt.setString(2, itemImage);
	            pstmt.setString(3, itemDescription);
	            pstmt.setInt(4, itemQuantity);
	            pstmt.setDouble(5, itemPrice);
	            pstmt.executeUpdate();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
			doGet(request, response);
		}		
	}
}
	      
