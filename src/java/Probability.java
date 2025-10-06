/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dbServices.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class Probability extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            Connection con=new DB().Con();
            PreparedStatement pst=con.prepareStatement("truncate table prob");
            pst.executeUpdate();
            PreparedStatement query=con.prepareStatement("select * from user_rep");
            ResultSet rs=query.executeQuery();
            String id="", tweet="",type="";int pos=0,neg=0,wl=0,cl=0;int up=0,n=0 ,word=0,ch=0,q=0,ex=0,at=0,has=0,ur=0,upp=0,p=0;
            while(rs.next())
            {
                id=rs.getString("id");
                tweet=rs.getString("tweet");
                type=rs.getString("type");
                pos=rs.getInt("pos");
                neg=rs.getInt("neg");
                String w[]=tweet.split(" ");
                 wl=w.length;
                
                String c[]=tweet.split("");
                 cl=c.length;
                
                if(neg>=1)
                {
                    n=1;
                }
                else
                {
                    n=0;
                }
                if(wl>11)
                {
                    word=1;
                }
                else
                {
                    word=0;
                }
                if(cl>100)
                {
                     ch=1;
                }
                else
                {
                    ch=0;
                }
                if(tweet.contains("?"))
                {
                    q=1; 
                }
                else
                {
                    q=0;
                }
                if(tweet.contains("!"))
                {
                    ex=1;
                }
                else
                {
                    ex=0;
                }
                if(tweet.contains("@"))
                {
                    at=1;
                }
                else
                {
                    at=0;
                }
                if(tweet.contains("#"))
                    
                {
                    has=1;
                }
                else
                {
                    has=0;
                }
                if(tweet.contains("http")||tweet.contains("https"))
                {
                    ur=1;
                }
                else
                {
                   ur=0;
                }
                
                for(int i=0;i<tweet.length();i++)
                {
                    if(Character.isUpperCase(tweet.charAt(i)))
                    {
                      up++;  
                    }
                }
                
                if(up>10)
                {
                    upp=1;
                }
                else
                {
                    upp=0;
                }
                if(pos>11)
                {
                     p=1;
                }
                else
                {
                    p=0;
                }
                PreparedStatement ps=con.prepareStatement("insert into prob(id,tweet,status,nwords,nchar,q,ex,at,has,url,upp,p,n)values('"+id+"','"+tweet+"','"+type+"','"+word+"','"+ch+"','"+q+"','"+ex+"','"+at+"','"+has+"','"+ur+"','"+upp+"','"+p+"','"+n+"')");
                ps.executeUpdate();
               
            }
                out.println("<script type=\"text/javascript\">"); 			
		out.println("alert(\"Calculated\")");
		out.println("</script>");
		RequestDispatcher rd=request.getRequestDispatcher("Probability.jsp");
		rd.include(request,response); 
           
            
        } catch (SQLException ex) {
            Logger.getLogger(Probability.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
