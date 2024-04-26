<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector,entity.ProductCart" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <table border="1">
            <caption>Cart Details</caption>
            <tr>
                <th>ProductID</th>
                <th>ProductName</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>subTotal</th>
                <th>remove</th>

            </tr>
            <%  Vector<ProductCart> vector =
                    (Vector<ProductCart> )request.getAttribute("vectorCart");
                float grandTotal = 0;
                if (vector != null) {
                for (ProductCart pro : vector) { 
                    float subTotal = pro.getPrice() * pro.getQuantity();
                    grandTotal += subTotal;
            %>
            <tr>
                <td><%=pro.getPid()%></td>
                <td><%=pro.getName()%></td>
                <td><%=pro.getPrice()%></td>
                <td><%=pro.getQuantity()%></td>
                <td><%= subTotal %></td>
                <td><a href="cart?service=remove&pid=<%=pro.getPid()%>">Remove</a></td>
            </tr>
            <% }
            } else{
            %>
            <tr>
                <td colspan="5">No data available</td>
            </tr>
            <% } %>

        </table>
        <p> Grand Total: <%= grandTotal %></p>
        <p><a href="cart?service=removeAll">Remove all</a></p>
        <p><a href="checkout.jsp" >check out</a></p>
    </body>
</html>
