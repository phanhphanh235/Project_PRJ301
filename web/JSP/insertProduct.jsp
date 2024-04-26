<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Product</title>
    </head>
    <body>
        <%
            ResultSet rsCate=(ResultSet)request.getAttribute("rsCate");
        %>

        <form action="product" method="post">
            <table>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="name" id=""></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description" id=""></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="price" id=""></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" id=""></td>
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
                    <td><input type="text" name="image" id=""></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="text" name="status" id=""></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" value="insert Product"></td>
                    <td><input type="reset" value="reset">
                        <input type="hidden" name="service" value="insertProduct">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
