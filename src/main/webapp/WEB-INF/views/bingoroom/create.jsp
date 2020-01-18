<!DOCTYPE html>
<html>
<head>
<title>BingoRoom</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount"/>

<body>
<form:form action="${pageContext.request.contextPath}/host/bingoRoom/create"
    method="POST" modelAttribute="bingoRoomForm">
        <form:input path="roomName" />
        <form:button>新規作成</form:button>
    </form:form>
</body>

</html>