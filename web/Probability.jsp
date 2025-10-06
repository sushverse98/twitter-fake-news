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
    
    <a href="Cal">Click here to Calculate Final</a> <br>
     </h3>
     <table align="center" border="1" style="width:1000px;text-align:center;">
		<tr style="font-size:22px; color:darkred;">
		<th>ID</th>
		<th>No.of Words</th>
                <th>No.of Character</th>
                <th>No.of Question Marks</th>
                <th>No.of Exlamatory</th>
                <th>No.of @</th>
                <th>No.of #</th>
                <th>No.of Url</th>
                <th>No.of Uppercase</th>
                <th>Positive</th>
                <th>Negative</th>
                
        <%
		PreparedStatement query=con.prepareStatement("select * from prob");
		ResultSet rs=query.executeQuery();
                Stopwords st=new Stopwords();
        
		while(rs.next())
		{
                String id=rs.getString("id");
                String nwords=rs.getString("nwords");
                String nchar=rs.getString("nchar");
                String q=rs.getString("q");
                String ex=rs.getString("ex");
                String at=rs.getString("at");
                String has=rs.getString("has");
                String url=rs.getString("url");
                String upp=rs.getString("upp");
                String p=rs.getString("p");
                String n=rs.getString("n");
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
                            <th style="color:darkblue;"><%=upp%></th>
                            <th style="color:darkblue;"><%=p%></th>
                            <th style="color:darkblue;"><%=n%></th>
				</tr>
         <%}%>
                </table>
		
        <h3 align="center"><a href="processing.jsp">Back</a></h3>
</body>
</html>