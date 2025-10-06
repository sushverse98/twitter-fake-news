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
<h1 style="color: currentColor;text-shadow: 0px 2px 8px lavenderblush;" align="center">II. User Reputation</h1>
<h3 style="color: sienna;text-shadow: 0px 2px 8px lavenderblush;" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Data's Count :&nbsp;&nbsp;<%=Total%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
    <a href="Preprocess.jsp">Click here to Preprocessing</a> <br>
     </h3>
<table align="center" border="1" style="width:1000px;text-align:center;">
		<tr style="font-size:22px; color:darkred;">
		<th>User ID</th>
		<th>SP</th>
                <th>FAV</th>
		<th>RT</th>
		<th>ME</th>
                <th>EE</th>
                <th>UI</th>
                <th>UR</th>
		
        <%
        PreparedStatement query=con.prepareStatement("select * from user_rep");
        ResultSet rs=query.executeQuery();
        while(rs.next())
	{
            String id=rs.getString("id");
            String f=rs.getString("sp");
            String r=rs.getString("fav");
            String fa=rs.getString("rt");
            String m=rs.getString("me");
            String ee=rs.getString("ee");
            String ui=rs.getString("ui");
            String ur=rs.getString("ur");
			%>
				<tr>
		            <th style="color:darkblue;"><%=id%></th>
					<th style="color:darkblue;"><%=f%></th>
					<th style="color:darkblue;"><%=r%></th>
                                        <th style="color:darkblue;"><%=fa%></th>
                                        <th style="color:darkblue;"><%=m%></th>
                                        <th style="color:darkblue;"><%=ee%></th>
                                        <th style="color:darkblue;"><%=ui%></th>
                                        <th style="color:darkblue;"><%=ur%></th>
				</tr>
         <%}%>
                </table>
%>

		
        <h3 align="center"><a href="processing.jsp">Back</a></h3>
</body>
</html>