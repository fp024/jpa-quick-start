<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ruby Shopping Town</title>
    <style>
        table.list {
            width: 800px;
            margin-left: auto;
            margin-right: auto;
            border: 1px solid #444444;
            border-collapse: collapse;
        }

        table.list th {
            background-color: orange;
            border: 1px solid #444444;
            height: 50px;

        }

        table.list td {
            text-align: center;
            border: 1px solid #444444;
        }

    </style>
</head>
<body>
<jsp:include page="../layouts/header.jsp"/>
<table class="list">
    <colgroup>
        <col style="width: 100px;"/>
        <col style="width: 400px"/>
        <col style="width: 100px"/>
        <col style="width: 100px"/>
        <col style="width: 100px"/>
    </colgroup>
    <tr>
        <th>순번</th>
        <th>상품명</th>
        <th>가격</th>
        <th>재고수량</th>
        <th>정보 수정</th>
    </tr>
    <c:forEach var="product" items="${productList}">
        <tr>
            <td>${product.id}</td>
            <td style="text-align: left">${product.name}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td><a href="/product/${product.id}/get">상품 수정</a></td>
        </tr>
    </c:forEach>
</table>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
