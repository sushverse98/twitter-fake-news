/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dbServices.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Cal1 extends HttpServlet {

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
            PreparedStatement pst=con.prepareStatement("truncate table final1");
            pst.executeUpdate();
            double rcount=0,ncount=0;
            int w=0,tot=0;
            String s="";
            int w1=0,c=0,c1=0,q=0,q1=0,ex=0,ex1=0,at=0,at1=0,h=0,h1=0,ur=0,ur1=0,upp=0,upp1=0,p=0,po=0,n=0,n1=0;
            PreparedStatement pr=con.prepareStatement("SELECT STATUS,COUNT(*)tot FROM prob WHERE STATUS='rumor'");
            ResultSet rr=pr.executeQuery();
            while(rr.next())
            {
                rcount=rr.getDouble("tot"); 
            }
            System.out.println("rcount "+rcount);
            PreparedStatement pn=con.prepareStatement("SELECT STATUS,COUNT(*)tot FROM prob WHERE STATUS='non-rumor'");
            ResultSet rn=pn.executeQuery();
            while(rn.next())
            {
                ncount=rn.getDouble("tot"); 
            }
            System.out.println("rcount "+ncount);
            //number of words *************************************
            PreparedStatement p1=con.prepareStatement("SELECT STATUS,nwords,COUNT(*)tot FROM prob WHERE nwords='0' AND STATUS='non-rumor'");
            ResultSet r1=p1.executeQuery();
            if(r1.next())
            {
                w=r1.getInt("tot");
            }
            //step 0 and Non rumor
            double zor=w/rcount;
            PreparedStatement p2=con.prepareStatement("SELECT STATUS,nwords,COUNT(*)tot FROM prob WHERE nwords='1' AND STATUS='non-rumor'");
            ResultSet r2=p2.executeQuery();
            if(r2.next())
            {
                w1=r2.getInt("tot");
            }
            //step 1 and Non rumor
            double z1=w1/ncount;
            //number of Characters ******************************
            PreparedStatement p3=con.prepareStatement("SELECT STATUS,nchar,COUNT(*)tot FROM prob WHERE nchar='0' AND STATUS='non-rumor'");
            ResultSet r3=p3.executeQuery();
            if(r3.next())
            {
                c=r3.getInt("tot");
            }
            //step 0 and rumor
            double cor=c/rcount;
            PreparedStatement p4=con.prepareStatement("SELECT STATUS,nchar,COUNT(*)tot FROM prob WHERE nchar='1' AND STATUS='non-rumor'");
            ResultSet r4=p4.executeQuery();
            if(r4.next())
            {
                c1=r4.getInt("tot");
            }
            //step 1 and rumor
            double zc=c1/ncount;
            //number of Question Marks ******************************
            PreparedStatement p5=con.prepareStatement("SELECT STATUS,q,COUNT(*)tot FROM prob WHERE q='0' AND STATUS='non-rumor'");
            ResultSet r5=p5.executeQuery();
            if(r5.next())
            {
                q=r5.getInt("tot");
            }
            //step 0 and rumor
            double qor=q/rcount;
            PreparedStatement p6=con.prepareStatement("SELECT STATUS,q,COUNT(*)tot FROM prob WHERE q='1' AND STATUS='non-rumor'");
            ResultSet r6=p6.executeQuery();
            if(r6.next())
            {
                q1=r6.getInt("tot");
            }
            //step 1 and rumor
            double qc=q1/ncount;
            //number of Exlamatory ******************************
            PreparedStatement p7=con.prepareStatement("SELECT STATUS,ex,COUNT(*)tot FROM prob WHERE ex='0' AND STATUS='non-rumor'");
            ResultSet r7=p7.executeQuery();
            if(r7.next())
            {
                ex=r7.getInt("tot");
            }
            //step 0 and rumor
            double exr=ex/rcount;
            PreparedStatement p8=con.prepareStatement("SELECT STATUS,ex,COUNT(*)tot FROM prob WHERE ex='1' AND STATUS='non-rumor'");
            ResultSet r8=p8.executeQuery();
            if(r8.next())
            {
                ex1=r8.getInt("tot");
            }
            //step 1 and rumor
            double exr1=ex1/ncount;
            //number of @ ******************************
            PreparedStatement p9=con.prepareStatement("SELECT STATUS,at,COUNT(*)tot FROM prob WHERE at='0' AND STATUS='non-rumor'");
            ResultSet r9=p9.executeQuery();
            if(r9.next())
            {
                at=r9.getInt("tot");
            }
            //step 0 and rumor
            double atr=at/rcount;
            PreparedStatement p10=con.prepareStatement("SELECT STATUS,at,COUNT(*)tot FROM prob WHERE at='1' AND STATUS='non-rumor'");
            ResultSet r10=p10.executeQuery();
            if(r10.next())
            {
                at1=r10.getInt("tot");
            }
            //step 1 and rumor
            double atr1=at1/ncount;
            //number of hashh ******************************
            PreparedStatement p11=con.prepareStatement("SELECT STATUS,has,COUNT(*)tot FROM prob WHERE has='0' AND STATUS='non-rumor'");
            ResultSet r11=p11.executeQuery();
            if(r11.next())
            {
                h=r11.getInt("tot");
            }
            //step 0 and rumor
            double hr=h/rcount;
            PreparedStatement p12=con.prepareStatement("SELECT STATUS,has,COUNT(*)tot FROM prob WHERE has='1' AND STATUS='non-rumor'");
            ResultSet r12=p12.executeQuery();
            if(r12.next())
            {
                h1=r12.getInt("tot");
            }
            //step 1 and rumor
            double hr1=h1/ncount;
            //number of Url ******************************
            PreparedStatement p13=con.prepareStatement("SELECT STATUS,url,COUNT(*)tot FROM prob WHERE url='0' AND STATUS='non-rumor'");
            ResultSet r13=p13.executeQuery();
            if(r13.next())
            {
                ur=r13.getInt("tot");
            }
            //step 0 and rumor
            double url=ur/rcount;
            PreparedStatement p14=con.prepareStatement("SELECT STATUS,url,COUNT(*)tot FROM prob WHERE url='1' AND STATUS='non-rumor'");
            ResultSet r14=p14.executeQuery();
            if(r14.next())
            {
                ur1=r14.getInt("tot");
            }
            //step 1 and rumor
            double url1=ur1/ncount;
            //number of Uppercase ******************************
            PreparedStatement p15=con.prepareStatement("SELECT STATUS,upp,COUNT(*)tot FROM prob WHERE upp='0' AND STATUS='non-rumor'");
            ResultSet r15=p15.executeQuery();
            if(r15.next())
            {
                upp=r15.getInt("tot");
            }
            //step 0 and rumor
            double upr=upp/rcount;
            PreparedStatement p16=con.prepareStatement("SELECT STATUS,upp,COUNT(*)tot FROM prob WHERE upp='1' AND STATUS='non-rumor'");
            ResultSet r16=p16.executeQuery();
            if(r16.next())
            {
                upp1=r16.getInt("tot");
            }
            //step 1 and rumor
            double upr1=upp1/ncount;
            //number of Positive ******************************
            PreparedStatement p17=con.prepareStatement("SELECT STATUS,p,COUNT(*)tot FROM prob WHERE p='0' AND STATUS='non-rumor'");
            ResultSet r17=p17.executeQuery();
            if(r17.next())
            {
                p=r17.getInt("tot");
            }
            //step 0 and rumor
            double prr=p/rcount;
            PreparedStatement p18=con.prepareStatement("SELECT STATUS,p,COUNT(*)tot FROM prob WHERE p='1' AND STATUS='non-rumor'");
            ResultSet r18=p18.executeQuery();
            if(r18.next())
            {
                po=r18.getInt("tot");
            }
            //step 1 and rumor
            double prr1=po/ncount;
            //number of Negative ******************************
            PreparedStatement p19=con.prepareStatement("SELECT STATUS,n,COUNT(*)tot FROM prob WHERE n='0' AND STATUS='non-rumor'");
            ResultSet r19=p19.executeQuery();
            if(r19.next())
            {
                n=r19.getInt("tot");
            }
            //step 0 and rumor
            double nr=n/rcount;
            PreparedStatement p20=con.prepareStatement("SELECT STATUS,n,COUNT(*)tot FROM prob WHERE n='1' AND STATUS='non-rumor'");
            ResultSet r20=p20.executeQuery();
            if(r20.next())
            {
                n1=r20.getInt("tot");
            }
            //step 1 and rumor
            double nr1=n1/ncount;
            PreparedStatement p21=con.prepareStatement("insert into final1(zor,z1,cor,zc,qor,qc,exr,exr1,atr,atr1,hr,hr1,url,url1,upr,upr1,prr,prr1,nr,nr1)values('"+zor+"','"+z1+"','"+cor+"','"+zc+"','"+qor+"','"+qc+"','"+exr+"','"+exr1+"','"+atr+"','"+atr1+"','"+hr+"','"+hr1+"','"+url+"','"+url1+"','"+upr+"','"+upr1+"','"+prr+"','"+prr1+"','"+nr+"','"+nr1+"')");
            p21.executeUpdate();
            response.sendRedirect("Final");
        } catch (SQLException ex2) {
            Logger.getLogger(Cal1.class.getName()).log(Level.SEVERE, null, ex2);
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
