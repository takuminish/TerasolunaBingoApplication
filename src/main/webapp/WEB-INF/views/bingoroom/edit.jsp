<!DOCTYPE html>
<html>
<head>
<title>BingoRoom</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount" />

<body>
  <form:form
    action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/edit"
    method="POST" modelAttribute="bingoRoomForm">
    <form:input path="roomName" value="${bingoRoom.roomName}" />
    <form:button>更新</form:button>
  </form:form>
</body>

</html>