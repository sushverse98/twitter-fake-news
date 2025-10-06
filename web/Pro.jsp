<%@page import="CBF.replace"%>
<%@page import="CBF.Stem"%>
<%@page import="CBF.Stopwords"%>
<%@page import="dbServices.DB"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View DataSets</title>
</head>

<%
Connection con=new DB().Con();
PreparedStatement oo=con.prepareStatement("select count(*)total from dataset");
ResultSet sa=oo.executeQuery();
int Total=0;
if(sa.next())
{
	Total=sa.getInt("total");
}
%>
<body style="background: palegoldenrod">
<h1 align="center" style="color: blue;text-shadow: 0px 3px 28px honeydew;">Detecting Rumour and Non-Rumour Tweets </h1>
<h1 style="color: currentColor;text-shadow: 0px 2px 8px lavenderblush;" align="center">III. Probability Calculation</h1>
<h3 style="color: sienna;text-shadow: 0px 2px 8px lavenderblush;" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Data's Count :&nbsp;&nbsp;<%=Total%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <a href="Credit">Click here to Calculate Probability for Rumor and Non Rumor</a> <br>
     </h3>
     <table align="center" border="1" style="width:1000px;text-align:center;">
		<tr style="font-size:22px; color:darkred;">
		<th>ID</th>
		<th>P(Words)</th>
                <th>P(Character)</th>
                <th>P(Question Marks)</th>
                <th>P(Exlamatory)</th>
                <th>P(@)</th>
                <th>P(#)</th>
                <th>P(Url)</th>
                <th>P(Uppercase)</th>
                <th>P(Positive)</th>
                <th>P(Negative)</th>
                
        <%
		PreparedStatement query=con.prepareStatement("select * from probability");
		ResultSet rs=query.executeQuery();
                Stopwords st=new Stopwords();
        
		while(rs.next())
		{
                String id=rs.getString("id");
                String nwords=rs.getString("nwords").substring(0,5);
                String nchar=rs.getString("nchar").substring(0,5);
                String q=rs.getString("q").substring(0,3);
                String ex=rs.getString("ex").substring(0,5);
                String at=rs.getString("at").substring(0,5);
                String has=rs.getString("has").substring(0,5);
                String url=rs.getString("url").substring(0,5);
                String upp=rs.getString("upp").substring(0,3);
                
                String p=rs.getString("p").substring(0,5);
                String n=rs.getString("n").substring(0,5);
                double up=Double.parseDouble(upp);
                double uup=0;
                if(up>1.0)
                {
                 uup=1.0;
                }
			%>
				<tr>
		            <th style="color:darkblue;"><%=id%></th>
			    <th style="color:darkblue;"><%=nwords%></th>
                            <th style="color:darkblue;"><%=nchar%></th>
                            <th style="color:darkblue;"><%=q%></th>
                            <th style="color:darkblue;"><%=ex%></th>
                            <th style="color:darkblue;"><%=at%></th>
                            <th style="color:darkblue;"><%=has%></th>
                            <th style="color:darkblue;"><%=url%></th>
                            <th style="color:darkblue;"><%=uup%></th>
                            <th style="color:darkblue;"><%=p%></th>
                            <th style="color:darkblue;"><%=n%></th>
				</tr>
         <%}%>
                </table>
		
        <h3 align="center"><a href="processing.jsp">Back</a></h3>
</body>
</html>