

<%@page import="alg.ConfusionMatrix"%>
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

%>
<body style="background: palegoldenrod">
<h1 align="center" style="color: blue;text-shadow: 0px 3px 28px honeydew;">Detecting Rumour and Non-Rumour Tweets </h1>
<h1 style="color: currentColor;text-shadow: 0px 2px 8px lavenderblush;" align="center">IV. Performance Metrics</h1>
  
<!--    <a href="Cal">Click here to Calculate Final</a> <br>-->
     </h3>

<%
            ConfusionMatrix cm=new ConfusionMatrix();
            PreparedStatement pst67 = con.prepareStatement("select status, count(*)count from probability ");
            ResultSet rs67 = pst67.executeQuery();
            while(rs67.next())
            {
            cm.increaseValue("non-rumor", rs67.getString("status"), rs67.getInt("count"));
            }
            double p = cm.getPrecisionForLabel("non-rumor");
            double r = cm.getRecallForLabel("non-rumor");
            double fm = cm.getFMeasureForLabels().get("non-rumor");
            double acc = cm.getConfidence95AccuracyHigh();              
             
            System.out.println(cm);
            String resultofcm=cm.toString();            
            
            System.out.println("Precision : " + p);            
           
            System.out.println("Recall : " + r);            
            
            System.out.println("F-measure  F_tp,fp: " + fm);
            
            System.out.println("Accuracy : "+acc);
           
%>
 <div style="background-color: white;margin-left:230px;margin-right:230px;">
   <br>
   <br><br><br>
   <h3>&emsp; &emsp;Precision Value is : <span  style="color: blue;"><%=p%></span></h3>
   <br>
    <h3>&emsp; &emsp;Recall Value is : <span  style="color: blue;"><%=r%></span></h3>
   <br>
    <h3>&emsp; &emsp;F1 Measure Value is : <span  style="color: blue;"><%=fm%></span></h3>
   <br>
    <h3>&emsp; &emsp;Accuracy Value is : <span  style="color: blue;"><%=acc%></span></h3>
   <br><br>
  
   <br><br>

		
<!--        <h3 align="center"><a href="processing.jsp">Back</a></h3>-->
</body>
</html>