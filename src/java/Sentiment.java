/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dbServices.DB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svm.Stem;
import svm.Stopwords;

/**
 *
 * @author ADMIN
 */
public class Sentiment extends HttpServlet {

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
            String result="";
            PreparedStatement query=con.prepareStatement("select * from dataset");
            ResultSet rs=query.executeQuery();
            while(rs.next())
            {
                String id=rs.getString("id");
                String twitter_id=rs.getString("uname");
                String Reviews=rs.getString("tweet");
                String ReviewsTemp=""; 
            
            //Preprocessing work//
            //Remove Stop_words
            Stopwords Stop_remove = new Stopwords();
            ReviewsTemp=Stop_remove.words(Reviews);
            //Remove Stemming
            Stem Stemp_Remove = new Stem();
            ReviewsTemp=Stemp_Remove.stem(ReviewsTemp);
            String word[]=ReviewsTemp.split(" ");
            int len=word.length;
            //Storing All Dataset in Collection Objects.
            LinkedList Positive=new LinkedList();
            LinkedList Negative=new LinkedList();

            for(String find:word)
            {
                PreparedStatement a=con.prepareStatement("select * from positive where gud='"+find+"' ");
                ResultSet b=a.executeQuery();
                if(b.next())
                {
                    Positive.add(b.getString("gud"));
                }
                PreparedStatement c=con.prepareStatement("select * from negative where neg='"+find+"' ");
                ResultSet d=c.executeQuery();
                if(d.next())
                {
                    Negative.add(d.getString("neg"));
                }
            }

            //Checking word level filtering method in review Sentences
            int poss=0,neg=0;int positive=0,negative=0;
            for(String Filter:word)
            {
                //Positive words checking
                if(Positive.contains(Filter))
                {
                    poss=1;
                    positive+=1;
                }
                //Negative words checking
                if(Negative.contains(Filter))
                {
                    neg=1;
                    negative+=1;
                }
            }
            System.out.println(poss+" "+neg);
            boolean P=false,N=false,Nt=false,gn=false;
            //Assign boolean value of Positive Reviews
            if(poss==1&&neg==0)
            {
                P=true;
            }
            //Assign boolean value of Negative Reviews
            else if(neg==1&&poss==0)
            {
                N=true;
            }
            //Assign boolean value of Nuetral Reviews
            else if(poss==1&&neg==1)
            {
                Nt=true;
            }
            //Inserting Positive Reviews
            else
            {
                gn=true;
            }
            if(P)
            {
                result="Positive";
            }
            //Inserting Negative Reviews
            else if(N)
            {
                result="Negative";
            }
            //Inserting Nuetral Reviews
            else if(Nt)
            {
               result="Neutral";
            }
            else if(gn)
            {
                result="General";
            }
            positive=len-negative;
            PreparedStatement ps1=con.prepareStatement("update user_rep set pos='"+positive+"',neg='"+negative+"',status='"+result+"',count='"+len+"' where id='"+id+"'");
            ps1.executeUpdate();
        }
            
                out.println("<script type=\"text/javascript\">"); 			
		out.println("alert(\"Analyzed Successfully\")");
		out.println("</script>");
		RequestDispatcher rd=request.getRequestDispatcher("View_Sent.jsp");
		rd.include(request,response);   
            } catch (SQLException ex) {
            Logger.getLogger(Sentiment.class.getName()).log(Level.SEVERE, null, ex);
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
