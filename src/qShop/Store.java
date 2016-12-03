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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
 
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Item> itemInventory = new ArrayList<Item>();
		String query = request.getParameter("query");
		request.setAttribute("query", query);
		
		ArrayList<cartInventory> cart = (ArrayList<cartInventory>) request.getSession().getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<cartInventory>();
		}

		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = "";
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from itemInventory" );
            while(rs.next()){
            	
            	if(query == null || query.isEmpty() || rs.getString("itemName").toLowerCase().contains(query.toLowerCase())){
            		Item items = new Item(rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"),rs.getString("description"),rs.getInt("quantity"), rs.getDouble("itemPrice"));
                	itemInventory.add(items);
            	}
            	
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
        request.setAttribute("itemInventory", itemInventory);
        
        request.getSession().setAttribute("cart", cart);
        request.setAttribute("cartLength", cart.size());
        
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/store/qShop.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String itemName = request.getParameter("itemName");
		String itemImage = request.getParameter("itemImage");	
		int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
		double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
		
		int qty = Integer.parseInt(request.getParameter("quantity"));
		int availableQty = itemQuantity - qty;	
		double totalPrice = qty*itemPrice;
		
		ArrayList<cartInventory> cart = (ArrayList<cartInventory>) request.getSession().getAttribute("cart");
		
		cart.add(new cartInventory(id, itemName, itemImage, itemQuantity, itemPrice));
		
    	request.setAttribute("cartLength", cart.size());
		
//		Connection c = null;
//        try
//        {
//        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
//            String username = "cs3220stu28";
//            String password = "";
//            
//            c = DriverManager.getConnection( url, username, password );
//            
//            String sql = "insert into cart values (0, ?, ?, ?, ?)";
//            PreparedStatement pstmt = c.prepareStatement( sql );
//            pstmt.setString(1, itemName);
//            pstmt.setString(2, itemImage);
//            pstmt.setInt(3, qty);
//            pstmt.setDouble(4, totalPrice);
//            pstmt.executeUpdate();
//            Statement stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "select count(*) from cart" );
//            while(rs.next() ){
//            	String count = rs.getString("count(*)");
//            	request.setAttribute("cartLength", count);
//            }
//            String sql1 = "update itemInventory set quantity = ? where id= ?";
//            PreparedStatement pstmt1 = c.prepareStatement( sql1 );
//            pstmt1.setInt(1, availableQty);
//            pstmt1.setInt(2, id);
//            pstmt1.executeUpdate();
//
//        }
//        catch( SQLException e )
//        {
//            throw new ServletException( e );
//        }
//        finally
//        {
//            try
//            {
//                if( c != null ) c.close();
//            }
//            catch( SQLException e )
//            {
//                throw new ServletException( e );
//            }
//        }
    	
    	request.getSession().setAttribute("cart", cart);
    	
		doGet(request, response);
	}

}

