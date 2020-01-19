<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount"/>

<body>

<form:form action="${pageContext.request.contextPath}/logout">
    <button type="submit">Logout</button>
</form:form>
<p>ようこそ.${f:h(userAccount.userName)}さん </p>
<a href="${pageContext.request.contextPath}/host/bingoRoom/create">新規作成</a>

<ul>
<c:forEach items="${bingoRoomList}" var="bingoRoom">
<li><a href="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/edit">${f:h(bingoRoom.roomName)}</a>
<c:if test="${!bingoRoom.started}">
<form:form action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/start" 
method="POST" modelAttribute="bingoRoomForm">
    <form:button>ゲーム開始</form:button>
</form:form>
</c:if>
<c:if test="${bingoRoom.started}">
<button onclick="location.href='${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/bingoGame'">ゲーム画面へ</button>
</c:if>
<form:form action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/delete" 
method="POST" modelAttribute="bingoRoomForm">
    <form:button>ルーム削除</form:button>
</form:form>
</li>
</c:forEach>
</ul>
</body>

</html>