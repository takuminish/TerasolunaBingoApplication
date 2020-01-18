<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount"/>

<body>
<p>ようこそ.${f:h(userAccount.userName)}さん </p>

<ul>
<c:forEach items="${bingoRoomList}" var="bingoRoom">
<li><a href="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}">${f:h(bingoRoom.roomName)}</a></li>
</c:forEach>
</ul>
</body>

</html>