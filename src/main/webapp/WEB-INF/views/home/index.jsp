<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<sec:authentication property="principal.userAccount" var="userAccount"/>

<body>

<p>ようこそ.${f:h(userAccount.userName)}さん </p>
</body>

</html>