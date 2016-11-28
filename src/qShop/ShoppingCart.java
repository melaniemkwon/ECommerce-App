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

@WebServlet("/ShoppingCart")
public class ShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
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
		List<cartInventory> cartInventory = new ArrayList<cartInventory>();
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = " ";
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from cart" );
            while(rs.next()){
            	cartInventory items = new cartInventory(rs.getInt("id"), rs.getString("itemName"), rs.getString("itemImage"), rs.getInt("itemQuantity"),rs.getDouble("itemPrice"));
            	cartInventory.add(items);
       
            }
            Statement stmt1 = c.createStatement();
            ResultSet rs1 = stmt1.executeQuery( "select sum(itemPrice) from cart" );
            while(rs1.next()){
            	String totalPrice = rs1.getString("sum(itemPrice)");
            	request.setAttribute("totalPrice", totalPrice);
            }
            Statement stmt2 = c.createStatement();
            ResultSet rs2 = stmt2.executeQuery( "select count(*) from cart" );
            while(rs2.next() ){
            	String count = rs2.getString("count(*)");
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
        request.setAttribute("cartInventory", cartInventory);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/store/cart.jsp");
		dispatcher.forward(request, response);
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = " ";
            
            c = DriverManager.getConnection( url, username, password );
            String sql = "delete from cart where id = ?";
            PreparedStatement pstmt = c.prepareStatement( sql );
            pstmt.setInt(1, id);
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
       
        response.sendRedirect("ShoppingCart");
		//doGet(request, response);
	}

}

