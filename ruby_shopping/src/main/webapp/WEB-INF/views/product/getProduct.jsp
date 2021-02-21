<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<form action="/product/new" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <table class="new">
        <tr>
            <th>상품명</th>
            <td><input type="text" name="name" size="20" value="${product.name}"></td>
        </tr>
        <tr>
            <th>가격</th>
            <td><input type="text" name="price" value="${product.price}"></td>
        </tr>

        <tr>
            <th>수량</th>
            <td><input type="text" name="quantity" value="${product.quantity}"></td>
        </tr>

        <tr>
            <td colspan="2" class="btn"><input type="submit" value="상품 수정"></td>
        </tr>
    </table>
</form>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
