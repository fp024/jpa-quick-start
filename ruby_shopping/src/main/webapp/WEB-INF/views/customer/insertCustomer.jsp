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

    </style>
</head>
<body>
<jsp:include page="../layouts/header.jsp" />
<form action="/customer/new" method="post">
    <table class="new_customer">
        <tr>
            <th>이름</th>
            <td><input type="text" name="name" size="20"></td>
        </tr>
        <tr>
            <th>도시</th>
            <td><input type="text" name="address.city" size="10"></td>
        </tr>

        <tr>
            <th>거리</th>
            <td><input type="text" name="address.roadName" size="50"></td>
        </tr>

        <tr>
            <th>우편번호</th>
            <td><input type="text" name="address.zipCode" size="20"></td>
        </tr>

        <tr>
            <th>전화번호</th>
            <td><input type="text" name="phone" size="20"></td>
        </tr>

        <tr>
            <th>회원 특징</th>
            <td><input type="text" name="comments" size="50"></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="회원 가입"></td>
        </tr>
    </table>
</form>
<jsp:include page="../layouts/footer.jsp"/>
</body>
</html>
