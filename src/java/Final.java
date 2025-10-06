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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class Final extends HttpServlet {

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
            PreparedStatement pst=con.prepareStatement("truncate table Probability");
                pst.executeUpdate();
            PreparedStatement pr=con.prepareStatement("SELECT * from final");
            ResultSet rr=pr.executeQuery();
            double zor=0, z1=0,cor=0,zc=0,qor=0,qc=0,exr=0,exr1=0,atr=0,atr1=0,hr=0,hr1=0,url=0,url1=0,upr=0,upr1=0,prr=0,prr1=0,nr=0,nr1=0;
            double nzor=0,nz1=0,ncor=0,nzc=0,nqor=0,nqc=0,nexr=0,nexr1=0,natr=0,natr1=0,nhr=0,nhr1=0,nurl=0,nurl1=0,nupr=0,nupr1=0,nprr=0,nprr1=0,nnr=0,nnr1=0;
            double nwords=0,nchar=0,q=0,ex=0,at=0,has=0,ur=0,upp=0,p=0,n=0;String type="",id="",tweet="";
            double o1=0,o2=0,o3=0,o4=0,o5=0,o6=0,o7=0,o8=0,o9=0,o10=0;
            double no1=0,no2=0,no3=0,no4=0,no5=0,no6=0,no7=0,no8=0,no9=0,no10=0;
            while(rr.next())
            {
               zor=rr.getDouble("zor");
               System.out.println("zor "+zor);
               z1=rr.getDouble("z1");
               cor=rr.getDouble("cor");
               zc=rr.getDouble("zc");
               qor=rr.getDouble("qor");
               qc=rr.getDouble("qc");
               exr=rr.getDouble("exr");
               exr1=rr.getDouble("exr1");
               atr=rr.getDouble("atr");
               atr1=rr.getDouble("atr1");
               hr=rr.getDouble("hr");
               hr1=rr.getDouble("hr1");
               url=rr.getDouble("url");
               url1=rr.getDouble("url1");
               upr=rr.getDouble("upr");
               upr1=rr.getDouble("upr1");
               prr=rr.getDouble("prr");
               prr1=rr.getDouble("prr1");
               nr=rr.getDouble("nr");
               nr1=rr.getDouble("nr1");
               
            }
            PreparedStatement p1=con.prepareStatement("select * from final1");
            System.out.println("select * from final1");
            ResultSet r1=p1.executeQuery();
            while(r1.next())
            {
               nzor=r1.getDouble("zor");
               System.out.println("nzor "+nzor);
               nz1=r1.getDouble("z1");
               ncor=r1.getDouble("cor");
               nzc=r1.getDouble("zc");
               nqor=r1.getDouble("qor");
               nqc=r1.getDouble("qc");
               nexr=r1.getDouble("exr");
               nexr1=r1.getDouble("exr1");
               natr=r1.getDouble("atr");
               natr1=r1.getDouble("atr1");
               nhr=r1.getDouble("hr");
               nhr1=r1.getDouble("hr1");
               nurl=r1.getDouble("url");
               nurl1=r1.getDouble("url1");
               nupr=r1.getDouble("upr");
               nupr1=r1.getDouble("upr1");
               nprr=r1.getDouble("prr");
               nprr1=r1.getDouble("prr1");
               nnr=r1.getDouble("nr");
               nnr1=r1.getDouble("nr1");
               
            }
            PreparedStatement p2=con.prepareStatement("SELECT * from prob");
            ResultSet r2=p2.executeQuery();
            
            while(r2.next())
            {
                id=r2.getString("id");
                tweet=r2.getString("tweet");
                type=r2.getString("status");
                nwords=r2.getDouble("nwords");
                nchar=r2.getDouble("nchar");
                q=r2.getDouble("q");
                ex=r2.getDouble("ex");
                at=r2.getDouble("at");
                has=r2.getDouble("has");
                ur=r2.getDouble("url");
                upp=r2.getDouble("upp");
                p=r2.getDouble("p");
                n=r2.getDouble("n");
                if(type.equals("rumor"))
                {
                    if(nwords==0)
                    {
                        o1=zor;
                        //System.out.println("o1 "+o1);
                    }
                    else
                    {
                        o1=z1;
                    }
                    if(nchar==0)
                    {
                        o2=cor;
                    }
                    else
                    {
                        o2=zc;
                    }
                    if(q==0)
                    {
                        o3=qor;
                    }
                    else
                    {
                        o3=qc;
                    }
                    if(ex==0)
                    {
                        o4=exr;
                    }
                    else
                    {
                        o4=exr1;
                    }
                    if(at==0)
                    {
                        o5=atr;
                    }
                    else
                    {
                        o5=atr1;
                    }
                    if(has==0)
                    {
                        o6=hr;
                    }
                    else
                    {
                        o6=hr1;
                    }
                    if(ur==0)
                    {
                        o7=url;
                    }
                    else
                    {
                        o7=url1;
                    }
                    if(upp==0)
                    {
                       o8=upr; 
                    }
                    else
                    {
                       o8=upr1; 
                    }
                    if(p==0)
                    {
                       o9=prr; 
                    }
                    else
                    {
                        o9=prr1;
                    }
                    if(n==0)
                    {
                       o10=nr; 
                    }
                    else
                    {
                        o10=nr1;
                    }
//                    PreparedStatement p3=con.prepareStatement("insert into probability(id,tweet,status,nwords,nchar,q,ex,at,has,url,upp,p,n)values('"+id+"','"+tweet+"','"+type+"','"+o1+"','"+o2+"','"+o3+"','"+o4+"','"+o5+"','"+o6+"','"+o7+"','"+o8+"','"+o9+"','"+o10+"')");
//                    p3.executeUpdate();
                    //System.out.println("insert into probability(id,tweet,status,nwords,nchar,q,ex,at,has,url,upp,p,n)values('"+id+"','"+tweet+"','"+type+"','"+o1+"','"+o2+"','"+o3+"','"+o4+"','"+o5+"','"+o6+"','"+o7+"','"+o8+"','"+o9+"','"+o10+"'");
                }
                else
                {
                   if(nwords==0)
                    {
                        o1=nzor;
                    }
                    else
                    {
                        o1=z1;
                    }
                    if(nchar==0)
                    {
                        o2=ncor;
                        System.out.println("o2 "+ncor);
                    }
                    else
                    {
                        o2=nzc;
                    }
                    if(q==0)
                    {
                        o3=nqor;
                        System.out.println("o3 "+nqor);
                    }
                    else
                    {
                        o3=nqc;
                    }
                    if(ex==0)
                    {
                        o4=nexr;
                    }
                    else
                    {
                        o4=nexr1;
                    }
                    if(at==0)
                    {
                        o5=natr;
                    }
                    else
                    {
                        o5=natr1;
                    }
                    if(has==0)
                    {
                        o6=nhr;
                    }
                    else
                    {
                        o6=nhr1;
                    }
                    if(ur==0)
                    {
                        o7=nurl;
                    }
                    else
                    {
                        o7=nurl1;
                    }
                    if(upp==0)
                    {
                       o8=nupr; 
                    }
                    else
                    {
                       o8=nupr1; 
                    }
                    if(p==0)
                    {
                       o9=nprr; 
                    }
                    else
                    {
                        o9=nprr1;
                    }
                    if(n==0)
                    {
                       o10=nnr; 
                    }
                    else
                    {
                        o10=nnr1;
                    } 
                    
                }
                PreparedStatement p4=con.prepareStatement("insert into probability(id,tweet,status,nwords,nchar,q,ex,at,has,url,upp,p,n)values('"+id+"','"+tweet+"','"+type+"','"+o1+"','"+o2+"','"+o3+"','"+o4+"','"+o5+"','"+o6+"','"+o7+"','"+o8+"','"+o9+"','"+o10+"')");
                p4.executeUpdate();
                
                
                
            }
            out.println("<script type=\"text/javascript\">"); 			
		out.println("alert(\"Probability Calculated\")");
		out.println("</script>");
		RequestDispatcher rd=request.getRequestDispatcher("Pro.jsp");
		rd.include(request,response); 
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Final.class.getName()).log(Level.SEVERE, null, ex);
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
