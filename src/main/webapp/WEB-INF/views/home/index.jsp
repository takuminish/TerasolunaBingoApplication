<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount"/>

<body>
<p>ようこそ.${f:h(userAccount.userName)}さん </p>
<a href="${pageContext.request.contextPath}/host/bingoRoom/create">新規作成</a>

<ul>
<c:forEach items="${bingoRoomList}" var="bingoRoom">
<li><a href="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/edit">${f:h(bingoRoom.roomName)}</a>
<form:form action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/start" 
method="POST" modelAttribute="bingoRoomForm">
    <form:button>ゲーム開始</form:button>
</form:form>
<form:form action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/delete" 
method="POST" modelAttribute="bingoRoomForm">
    <form:button>ルーム削除</form:button>
</form:form>
</li>
</c:forEach>
</ul>
</body>

</html>