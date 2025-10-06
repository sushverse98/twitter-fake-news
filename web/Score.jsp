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

<body style="background: palegoldenrod">
<h1 align="center" style="color: blue;text-shadow: 0px 3px 28px honeydew;">Detecting Rumour and Non-Rumour Tweets </h1>
<h1 style="color: currentColor;text-shadow: 0px 2px 8px lavenderblush;" align="center">III. Probability Calculation</h1>

<h3 style="color: sienna;text-shadow: 0px 2px 8px lavenderblush;" align="center"><a href="Performance.jsp">Performance Metrics</a> <br>
     </h3>

     <table align="center" border="1" style="width:1000px;text-align:center;">
		<tr style="font-size:22px; color:darkred;">
		<th>ID</th>
		<th>OverAll Probability</th>
                <th>User Reputation</th>
                <th>Credibility Score(P+UR)</th>
                
        <%  
                Connection con=new DB().Con();
		PreparedStatement query=con.prepareStatement("select * from probability");
		ResultSet rs=query.executeQuery();
                Stopwords st=new Stopwords();
        
		while(rs.next())
		{
                String id=rs.getString("id");
                String prb=rs.getString("prob");
                String urep=rs.getString("urep").substring(0,6);
                String credit=rs.getString("credit").substring(0,6);
			%>
				<tr>
		            <th style="color:darkblue;"><%=id%></th>
			    <th style="color:darkblue;"><%=prb%></th>
                            <th style="color:darkblue;"><%=urep%></th>
                            <th style="color:darkblue;"><%=credit%></th>
				</tr>
         <%}%>
                </table>
		
        <h3 align="center"><a href="processing.jsp">Back</a></h3>
</body>
</html>