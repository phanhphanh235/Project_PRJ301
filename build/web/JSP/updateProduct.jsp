
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet, java.util.Vector, entity.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ResultSet rsCate = (ResultSet) request.getAttribute("rsCate");
            Vector<Product> vector = (Vector<Product>) request.getAttribute("vector");
            Product pro = vector.get(0);
        %>
        <form action="product" method="post">
            <table>
                <tr>
                    <td>Product ID</td>
                    <td><input type="text" name="pid" id="" readonly value="<%=pro.getPid()%>"></td>
                </tr>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="name" id="" value="<%=pro.getName()%>"></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description" id="" value="<%=pro.getDescription()%>"></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="price" id="" value="<%=pro.getPrice()%>"></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" id="" value="<%=pro.getQuantity()%>"></td>
                </tr>
                <tr>
                    <td>Category</td>
                    <td>
                        <select name="catid" id="">
                            <%while (rsCate.next()){%>
                            <option value="<%=rsCate.getInt(1)%>"><%=rsCate.getString(2)%></option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Image</td>
                    <td><input type="text" name="image" id="" value="<%=pro.getImage()%>"></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="text" name="status" id="" value="<%=pro.getStatus()%>"></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" value="update Product"></td>
                    <td><input type="reset" value="reset">
                        <input type="hidden" name="service" value="updateProduct">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
