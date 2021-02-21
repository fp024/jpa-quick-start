<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time"  %>
<html>
<head>
    <title>Ruby Shopping Town</title>
    <style>
        div.search_box {
            text-align: center
        }

        table.list {
            margin-left: auto; margin-right: auto;
            border: 1px solid #444444;
            border-collapse: collapse;
        }

        table.list th {
            width: 100px;
            background-color: orange;
            border: 1px solid #444444;

        }
        table.list td {
            text-align: center;
            border: 1px solid #444444;
        }

    </style>
</head>
<body>
<jsp:include page="../layouts/header.jsp" />

<form action="/getOrderList" method="post">
    <div class="search_box">
        <span>회원명: <input type="text" name="searchCustomerName" value="${searchInfo.searchCustomerName}"></span>
        <span>주문상태:
        <select name="searchOrderStatus">
            <c:forEach var="orderStatus" items="${orderStatusList}">
            <option value="${orderStatus.name()}" <c:if test="${searchInfo.searchOrderStatus eq orderStatus}">selected="selected"</c:if>  >${orderStatus.description}</option>
            </c:forEach>
        </select>
        </span>
        <span><input type="submit" value="검색"></span>
    </div>
</form>

<table class="list">
    <tr>
        <th>순번</th>
        <th>회원명</th>
        <th>상품 이름</th>
        <th>주문수량</th>
        <th>상태</th>
        <th>일시</th>
        <th>주문 취소</th>
    </tr>
    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.id}</td>
            <td>${order.customer.name}</td>
            <td>${order.itemList[0].product.name}</td>
            <td>${order.itemList[0].count}</td>

            <td>
                <c:choose>
                    <c:when test="${order.status eq 'ORDER'}">주문</c:when>
                    <c:when test="${order.status eq 'CANCEL'}">취소</c:when>
                </c:choose>
            </td>
            <td>
                <javatime:format value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" />
            </td>
            <td>
                <c:if test="${order.status eq 'ORDER'}">
                    <a href="/order/${order.id}/orderCancel">주문취소</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>


<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
