<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>DNS LookUp</title>
<style>
div.a {
  text-align: center;
}

div.b {
  text-align: left;
}

div.c {
  text-align: right;
} 

div.d {
  text-align: justify;
} 
</style>
</head>
<body>
<div class="a">
<h1><font color ="Blue" >Nameservers Lookup </font></h1></h1>
<p>(Nameservers define your domain's current DNS provider)</p>
<!-- 
<font color ="Blue" >Hemapriya</font> -->
<!-- 
<h1>Hello JSP and Servlet!</h1> -->
<form action="dns" method="post">
    Enter any domain name to find the name servers (example:hemapriya.com)</br>
    </br>
    <input type="text" name="dnsDomainName" size="35" >
    </br>
    </br>
    <input type="submit" value="Submit" size="10" />   <input name='reset' type="reset" value='Reset'/>

</form>
</div>
<div class="c">

</div>
</body>
</html>