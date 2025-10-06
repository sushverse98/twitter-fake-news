/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dbServices.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class Credit extends HttpServlet {

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
            double rm=0,nrm=0;
            PreparedStatement p1=con.prepareStatement("SELECT STATUS,COUNT(*)tot FROM probability WHERE STATUS='rumor'");
            ResultSet r1=p1.executeQuery();
            while(r1.next())
            {
                rm=r1.getDouble("tot");
            }
            PreparedStatement p2=con.prepareStatement("SELECT STATUS,COUNT(*)tot FROM probability WHERE STATUS='non-rumor'");
            ResultSet r2=p2.executeQuery();
            while(r2.next())
            {
                nrm=r2.getDouble("tot");
            }
            double total=rm+nrm;
            PreparedStatement ps=con.prepareStatement("select * from probability");
            ResultSet rs=ps.executeQuery();
            double nwords=0,nchar=0,q=0,ex=0,at=0,has=0,url=0,upp=0,p=0,n=0;
            String xnwords="",xnchar="",xq="",xex="",xat="",xhas="",xupp="",xurl="",xp="",xn="";
            String id="",status="";double prob=0,probability=0;
            
            while(rs.next())
            {
              id=rs.getString("id");  
              status=rs.getString("status");
               xnwords=rs.getString("nwords").substring(0, 5);
                System.out.println("xnwords "+xnwords);
               xnchar=rs.getString("nchar").substring(0, 5);
                System.out.println("nchar "+xnchar);
               xq=rs.getString("q");
              xex=rs.getString("ex").substring(0, 5);
              xat=rs.getString("at").substring(0, 5);
              xhas=rs.getString("has").substring(0, 5);
              xurl=rs.getString("url").substring(0, 5);
              xupp=rs.getString("upp").substring(0, 3);
              xp=rs.getString("p").substring(0, 5);
              xn=rs.getString("n").substring(0, 5);
              nwords=Double.parseDouble(xnwords);
                System.out.println("nwords "+nwords);
              nchar=Double.parseDouble(xnchar);
              q=Double.parseDouble(xq);
              ex=Double.parseDouble(xex);
              at=Double.parseDouble(xat);
              has=Double.parseDouble(xhas);
              url=Double.parseDouble(xurl);
              upp=Double.parseDouble(xupp);
              p=Double.parseDouble(xp);
              n=Double.parseDouble(xn);
              if(status.equals("rumor"))
              {
                  prob=nwords*nchar*q*ex*at*has*url*upp*p*n;
                  
                  probability=prob*(rm/total);
              }
              else
              {
                  prob=nwords*nchar*q*ex*at*has*url*upp*p*n; 
                  probability=prob*(nrm/total);
              }
              PreparedStatement p4=con.prepareStatement("update probability set prob='"+probability+"' where id='"+id+"'");
              p4.executeUpdate();
              
              
            }
            PreparedStatement p6=con.prepareStatement("select * from user_rep");
            ResultSet r6=p6.executeQuery();
            String iid="";double urep=0;
            while(r6.next())
            {
                iid=r6.getString("id");
                urep=r6.getDouble("ur");
            
            PreparedStatement p7=con.prepareStatement("update probability set urep='"+urep+"' where id='"+iid+"'");
            p7.executeUpdate();
            }
            response.sendRedirect("Score");
            
        } catch (SQLException ex) {
            Logger.getLogger(Credit.class.getName()).log(Level.SEVERE, null, ex);
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
