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
<h1 style="color: currentColor;text-shadow: 0px 2px 8px lavenderblush;" align="center">II. Data Pre Processing</h1>
<h3 style="color: sienna;text-shadow: 0px 2px 8px lavenderblush;" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total Data's Count :&nbsp;&nbsp;<%=Total%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    
<a href="Feature.jsp">Click here to Extract Feature</a> </h3>

		<table align="center" border="1" style="width:1000px;text-align:center;">
		<tr style="font-size:22px; color:darkred;">
		<th>ID</th>
		<th>User_name</th>
		
		
		<th>Tweets</th>
        <%
		PreparedStatement query=con.prepareStatement("select * from dataset");
		ResultSet rs=query.executeQuery();
        Stopwords st=new Stopwords();
        Stem stem=new Stem();
        replace rep=new replace();
		while(rs.next())
		{
            String id=rs.getString("id");
            String twitter_id=rs.getString("uname");
            twitter_id=rep.remove(twitter_id);
            String tweets=rs.getString("tweet");
            tweets=rep.remove(tweets);
            tweets=st.words(tweets);
            tweets=stem.stem(tweets); 
            
			%>
				<tr>
		            <th style="color:darkblue;"><%=id%></th>
					<th style="color:darkblue;"><%=twitter_id%></th>
					<th style="color:darkblue;"><%=tweets%></th>
				</tr>
         <%}%>
                </table>
        <h3 align="center"><a href="AddDataset.jsp">Click to Back</a></h3>
</body>
</html>