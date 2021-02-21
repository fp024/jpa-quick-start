<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    h1 {text-align: center;}
    table.menu {
        margin-left: auto; margin-right: auto;
    }
    table.menu th, td {
        text-align: center;
    }
    table.menu th {
        background-color: orange;
        width: 300px;
    }
</style>
<div>
    <br>
    <h1> @..@ 루비 쇼핑몰 @..@</h1>
    <table class="menu">
        <tr>
            <th>회원 관리</th>
            <th>상품 관리</th>
            <th>주문 관리</th>
        </tr>
        <tr>
            <td><a href="/customer/new">회원 가입</a></td>
            <td><a href="/product/new">상품 관리</a></td>
            <td><a href="/order/new">상품 주문</a></td>
        </tr>
        <tr>
            <td><a href="/getCustomerList">회원 목록</a></td>
            <td><a href="/getProductList">상품 목록</a></td>
            <td><a href="/getOrderList">구매 목록</a></td>
        </tr>
    </table>
</div>
<hr>
<br>