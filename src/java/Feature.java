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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svm.SVM;

/**
 *
 * @author ADMIN
 */
public class Feature extends HttpServlet {

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
            PreparedStatement pp8=con.prepareStatement("truncate table log_dataset");
            pp8.executeUpdate();
            PreparedStatement p=con.prepareStatement("truncate table user_rep");
            p.executeUpdate();
            PreparedStatement oo=con.prepareStatement("select count(*)total from dataset");
            ResultSet sa=oo.executeQuery();
            int total=0;
        if(sa.next())
        {
                total=sa.getInt("total");
        }
        PreparedStatement p1=con.prepareStatement("select * from dataset");
        ResultSet rs=p1.executeQuery();
        int f=0,fav=0,r=0,m=0;String id="",tweet="";
        int foll=0;
        while(rs.next())
        {
            
            f=rs.getInt("followers");
            r=rs.getInt("retweet");
            fav=rs.getInt("fav");
            m=rs.getInt("mention");
        double tf1=Math.log(f);
        double tr1=Math.log(r);
        double tfav1=Math.log(fav);
        double tm1=Math.log(m);
        PreparedStatement pss=con.prepareStatement("insert into log_dataset(fo,fa,r,m)values('"+tf1+"','"+tr1+"','"+tfav1+"','"+tm1+"')");
        pss.executeUpdate();
        }
            
        PreparedStatement p9=con.prepareStatement("SELECT MAX(fo)t,MAX(fa)r,MAX(r)s,MAX(m)t FROM log_dataset");
        System.out.println("SELECT MAX(fo),MAX(fa),MAX(r),MAX(m) FROM log_dataset");
        ResultSet rs9=p9.executeQuery();
        double tf=0.0,tr=0.0,tfav=0.0,tm=0.0;
        while(rs9.next())
        {
          tf=rs9.getDouble("t");
          tr=rs9.getDouble("r");
          tfav=rs9.getDouble("s");
          tm=rs9.getDouble("t");
          
        }
            System.out.println("tf value "+tf);
            System.out.println("tf value "+tr);
        PreparedStatement p2=con.prepareStatement("select * from dataset");
        ResultSet rs2=p2.executeQuery();
        int cf=0, cr=0,cfav=0,cm=0;;int count=0; String type="";
        while(rs2.next())
        {
            id=rs2.getString("id");
            tweet=rs2.getString("tweet");
            type=rs2.getString("type");
            cf=rs2.getInt("followers");
            cr=rs2.getInt("retweet");
            cfav=rs2.getInt("fav");
            cm=rs2.getInt("mention");
            
            String c[]=tweet.split(" ");
            count=c.length;
            SVM ss = new SVM();
            String result=ss.classify(tweet, con);
            double tcf=cf;
            System.out.println("tcf value "+tcf+" "+Math.log(tcf));
            double tcr=cr;
            double tcfav=cfav;
            double tcm=cm;
            double sp=Math.log(tcf)/tf;
            double favt=Math.log(tcfav)/tfav;
            double rt=Math.log(tcr)/tr;
            double me=0.0;
            if(cm==0)
            {
               me=1;
            }
            else{
             me=Math.log(tcm)/tm;
            }
            double ee=Math.round(favt+rt+me);
            double ui=(sp+ee)/total;
            System.out.println("ui value "+ui+" "+ee);
            PreparedStatement p3=con.prepareStatement("insert into user_rep(id,tweet,type,sp,fav,rt,me,ee,ui)values('"+id+"','"+tweet+"','"+type+"','"+sp+"','"+favt+"','"+rt+"','"+me+"','"+ee+"','"+ui+"')");
            p3.executeUpdate();
            

            
        }
        out.println("<script type=\"text/javascript\">"); 			
		out.println("alert(\"Features Extracted Successfully\")");
		out.println("</script>");
		RequestDispatcher rd=request.getRequestDispatcher("Sentiment.jsp");
		rd.include(request,response);
        } catch (SQLException ex) {
            Logger.getLogger(Feature.class.getName()).log(Level.SEVERE, null, ex);
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
