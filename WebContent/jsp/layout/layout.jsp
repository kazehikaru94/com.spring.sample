<%@ page contentType="text/html;charset=UTF-8" %>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>    
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true" /></title>
    <link rel="stylesheet" href="<c:url value="/resources/css/custom.css" />"/>
</head>
<body>
	<div><tiles:insertAttribute name="header" /></div>        
    <div>    
        <tiles:insertAttribute name="body" />
    </div>    
    <div style="clear:both"><tiles:insertAttribute name="footer" /></div>   
</body>
</html>