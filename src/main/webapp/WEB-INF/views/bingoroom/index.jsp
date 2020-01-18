<!DOCTYPE html>
<html>
<head>
<title>BingoRoom</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount"/>

<body>
<ul>
<p>${f:h(bingoRoom.roomName)}</p>
</ul>
</body>

</html>