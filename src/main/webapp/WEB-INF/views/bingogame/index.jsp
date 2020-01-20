<!DOCTYPE html>
<html>
<head>
<title>ゲーム</title>
<link rel="stylesheet"
  href="${pageContext.request.contextPath}/resources/app/css/bingogame/style.css"
  type="text/css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
  crossorigin="anonymous"></script>
<script type="text/javascript"
  src="${pageContext.request.contextPath}/resources/app/javascript/bingogame/bingogame.js"></script>
</head>
<body>

  <form:form
    action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/finish"
    method="POST" modelAttribute="bingoRoomForm">
    <form:button>ゲーム終了</form:button>
  </form:form>

  <table id="bingoCandidateTable">
    <c:forEach items="${bingoCandiateList}" var="bingoCandidate">
      <tr id="bingoCandidateTr">
        <c:forEach items="${bingoCandidate}" var="candidate">
          <c:if test="${candidate.resulted}">
            <td class="resulted bingoCandidateTd"
              id="bingoCandidateTd${candidate.bingoValue}">${candidate.bingoValue}</td>
          </c:if>
          <c:if test="${!candidate.resulted}">
            <td class="bingoCandidateTd"
              id="bingoCandidateTd${candidate.bingoValue}">${candidate.bingoValue}</td>
          </c:if>
        </c:forEach>
      </tr>
    </c:forEach>
  </table>
  <c:forEach items="${bingoResultList}" var="bingoResult">
    <p class="bingoResult">${bingoResult.bingoValue}</p>
  </c:forEach>
  <button id="lotteyStart" onclick="lottery()">抽選開始</button>
  <form:form
    action="${pageContext.request.contextPath}/host/bingoRoom/${bingoRoom.bingoRoomId}/bingoGame/lottery"
    method="POST" modelAttribute="bingoForm">
    <form:hidden path="bingoValue" />
    <form:hidden path="bingoRoomId" value="${bingoRoom.bingoRoomId}" />
    <form:button>抽選反映</form:button>
  </form:form>
</body>
</html>
