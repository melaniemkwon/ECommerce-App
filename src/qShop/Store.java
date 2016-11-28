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

import todoList.servlet.TodoListEntry;


@WebServlet("/Store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Store() {
        super();
        
    }

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
		List<itemInventory> itemInventory = new ArrayList<itemInventory>();
		String query = request.getParameter("query");
		request.setAttribute("query", query);
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = " ";
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from itemInventory" );
            while(rs.next()){
            	
            	if(query == null || rs.getString("itemName").contains(query)){
            		itemInventory items = new itemInventory(rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"),rs.getString("description"),rs.getInt("quantity"), rs.getDouble("itemPrice"));
                	itemInventory.add(items);
            	}
            	
            }
            Statement stmt1 = c.createStatement();
            ResultSet rs1 = stmt1.executeQuery( "select count(*) from cart" );
            while(rs1.next() ){
            	String count = rs1.getString("count(*)");
            	request.setAttribute("cartLength", count);
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
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/store/qShop.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String itemName = request.getParameter("itemName");
		String itemImage = request.getParameter("itemImage");
		int id = Integer.parseInt(request.getParameter("id"));
		int qty = Integer.parseInt(request.getParameter("quantity"));
		int itemQuantity = Integer.parseInt(request.getParameter("itemQuantity"));
		int availableQty = itemQuantity - qty;
		double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
		double totalPrice = qty*itemPrice;
		
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = " ";
            
            c = DriverManager.getConnection( url, username, password );
            
            String sql = "insert into cart values (0, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setString(1, itemName);
            pstmt.setString(2, itemImage);
            pstmt.setInt(3, qty);
            pstmt.setDouble(4, totalPrice);
            pstmt.executeUpdate();
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select count(*) from cart" );
            while(rs.next() ){
            	String count = rs.getString("count(*)");
            	request.setAttribute("cartLength", count);
            }
            String sql1 = "update itemInventory set quantity = ? where id= ?";
            PreparedStatement pstmt1 = c.prepareStatement( sql1 );
            pstmt1.setInt(1, availableQty);
            pstmt1.setInt(2, id);
            pstmt1.executeUpdate();
           

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

