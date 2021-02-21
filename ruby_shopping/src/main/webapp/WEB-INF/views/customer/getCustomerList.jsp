<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ruby Shopping Town</title>
    <style>
        table.customer_list {
            margin-left: auto; margin-right: auto;
            border: 1px solid #444444;
            border-collapse: collapse;
        }

        table.customer_list th {
            width: 100px;
            background-color: orange;
            border: 1px solid #444444;

        }
        table.customer_list td {
            text-align: center;
            border: 1px solid #444444;
        }

    </style>
</head>
<body>
<jsp:include page="../layouts/header.jsp" />
<table class="customer_list">
    <tr>
        <th>순번</th>
        <th>이름</th>
        <th>도시</th>
        <th>주소</th>
        <th>우편번호</th>
        <th>전화번호</th>
        <th>회원특징</th>
    </tr>
    <c:forEach var="customer" items="${customerList}">
    <tr>
        <td>${customer.id}</td>
        <td>${customer.name}</td>
        <td>${customer.address.city}</td>
        <td>${customer.address.roadName}</td>
        <td>${customer.address.zipCode}</td>
        <td>${customer.phone}</td>
        <td>${customer.comments}</td>
    </tr>
    </c:forEach>
</table>


<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
