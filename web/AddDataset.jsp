<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
</head>
<body style="background-color: #ffeb3b42;">
<h1 style="color: unset;text-shadow: 0px 5px 6px chocolate;" align="center">Detecting Rumor and Non Rumor Tweets</h1>
<br><br><br><br><br><br><br>

<p align="center" style="color: purple;font-size:30px;text-shadow: 0px 5px 6px tomato;">Upload Twitter Datasets</p>
<form action="Data" method="post" enctype="multipart/form-data">
<table align="center"  style="width: 568px;height: 120px;font-size:30px;background: green;text-align: center;border-radius: 10px;border: ridge;">
<tr><td style="color: yellow; font-family:Times New Roman">Choose Dataset</td><td><input type="file" name="file" required="" style="color: cornsilk;border-radius: 4px;height: 25px;border: violet;font-weight: bold;"></td></tr>
</table>
<p align="center"><input type="submit" value="Upload DataSet" style="width: 167px;height: 38px;font-size:20px;border-radius: 6px;border: whitesmoke;background: darkorange;"></p>
</form>
</body>
</html>