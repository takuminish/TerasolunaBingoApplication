<!DOCTYPE html>
<html>
<head>
<title>ゲーム</title>
</head>
<body>

  <form:form
    action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/finish"
    method="POST" modelAttribute="bingoRoomForm">
    <form:button>ゲーム終了</form:button>
  </form:form>

  <table>
    <c:forEach items="${bingoCandiateList}" var="bingoCandidate">
      <tr>
        <c:forEach items="${bingoCandidate}" var="candidate">
          <c:if test="${candidate.resulted}">
            <td>${candidate.bingoValue}</td>
          </c:if>
          <c:if test="${!candidate.resulted}">
            <td>${candidate.bingoValue}a</td>
          </c:if>
        </c:forEach>
      </tr>
    </c:forEach>
  </table>
  <c:forEach items="${bingoResultList}" var="bingoResult">
    <p>${bingoResult.bingoValue}</p>
  </c:forEach>
</body>
</html>
