package qShop.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Details
 */
@WebServlet("/Details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(request.getParameter("id"));
		List<Item> itemInventory = new ArrayList<Item>();
		Connection c = null;
        try
        {
        	String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";
            String username = "cs3220stu28";
            String password = "j.V*CiT.";
            
            c = DriverManager.getConnection( url, username, password );
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "select * from itemInventory " );
            while(rs.next()){
            	
            	if(id == rs.getInt("id")){
            		request.setAttribute("id", rs.getInt("id"));
            		request.setAttribute("itemName", rs.getString("itemName"));
            		request.setAttribute("itemImage", rs.getString("itemImage"));
            		request.setAttribute("itemDescription", rs.getString("description"));
            		request.setAttribute("quantity", rs.getInt("quantity"));
            		request.setAttribute("itemPrice", rs.getDouble("itemPrice"));
            		
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/store/Details.jsp");
		dispatcher.forward(request, response);
        
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		doGet(request, response);
	}

}

