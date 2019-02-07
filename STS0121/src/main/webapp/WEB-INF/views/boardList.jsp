<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>[boardList.jsp]</title>
 	<style type="text/css">
		*{font-size:16pt; font-weight:bold; }
		a{text-decoration:none ;font-size:20pt; font-weight:bold;}
		a:hover{text-decoration:underline ;font-size:22pt; font-weight:bold;}
	</style>
</head>
<body>
   <table  width=700 border=1 cellpadding="0">
    <tr bgcolor=yellow height=50>
    	<td>번호</td> <td>이름</td> <td>제목</td> <td>내용</td> <td>취미</td>
    </tr>
    
     <c:forEach var="dto" items="${naver}"> 
  <tr>
 	 <td> ${dto.rn} </td>
 	 <td> ${dto.name} </td> 	 
 	 <td> <a href="detail.do?idx=${dto.hobby_idx}">${dto.title}</a><c:if test="${dto.rcnt>0}"> <font style="color:red;font-size:10pt"> [${dto.rcnt}] </font> </c:if> </td>
     <td> ${dto.content} </td>     
     <td> ${dto.hobby}</td>    
  </tr>   
  </c:forEach>
   </table>
	
<p>
  <a href="index.jsp">[index.jsp]</a>
  <a href="write.do">[신규등록]</a>
</body>
</html>



