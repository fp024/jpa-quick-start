<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ruby Shopping Town</title>
    <style>
        table.new_customer {
            margin-left: auto; margin-right: auto;
        }
        table.new_customer th {
            width: 100px;
            background-color: orange;
        }
        table.new_customer td {
            text-align: left;
        }

        table.new_customer td.btn {
            text-align: center;
        }

    </style>
</head>
<body>
<jsp:include page="../layouts/header.jsp" />
<form action="/product/new" method="post">
    <table class="new_customer">
        <tr>
            <th>상품명</th>
            <td><input type="text" name="name" size="20"></td>
        </tr>
        <tr>
            <th>가격</th>
            <td><input type="text" name="price"></td>
        </tr>

        <tr>
            <th>수량</th>
            <td><input type="text" name="quantity"></td>
        </tr>

        <tr>
            <td colspan="2" class="btn"><input type="submit" value="상품 등록"></td>
        </tr>
    </table>
</form>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
