<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ruby Shopping Town</title>
    <style>
        table.new {
            margin-left: auto; margin-right: auto;
        }
        table.new th {
            width: 100px;
            background-color: orange;
        }
        table.new td {
            text-align: left;
        }

        table.new td.btn {
            text-align: center;
        }

    </style>
</head>
<body>
<jsp:include page="../layouts/header.jsp" />
<form action="/order/new" method="post">
    <table class="new">
        <tr>
            <th>주문회원</th>
            <td>
                <select name="customerId">
                    <option>회원선택</option>
                    <c:forEach var="customer" items="${customerList}">
                    <option value="${customer.id}">${customer.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>주문상품</th>
            <td>
                <select name="productId">
                    <option>상품선택</option>
                    <c:forEach var="product" items="${productList}">
                    <option value="${product.id}">${product.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <th>수량</th>
            <td><input type="text" name="count"></td>
        </tr>

        <tr>
            <td colspan="2" class="btn"><input type="submit" value="상품 주문"></td>
        </tr>
    </table>
</form>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
