
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% String titlePage = 
                (String)request.getAttribute("titlePage");%>
        <title><%=titlePage%></title>
        <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this product?");
        }
        </script>
    </head>
    <body>
        <p><a href="adminlist?service=insertProduct">Insert Product</a></p>
        <form action="product" method="get">
            <p><input type="text" name="pname" id="">
                <input type="submit" name="submit" value="searchName">
                <input type="reset" value="Clear">
                <input type="hidden" name="service" value="listAllProducts">
            </p>
        </form>
        <table border="1">
            <% String titleTable = (String) request.getAttribute("titleTable");%>
            <caption><%=titleTable%></caption>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Category</th>
                <th>Image</th>
                <th>Status</th>
                <th>update</th>
                <th>delete</th>
                <th>add2Cart</th>
            </tr>
            <%  Vector<Product> vector =
                    (Vector<Product> )request.getAttribute("data");
                for (Product pro : vector) {
            %>
            <tr>
                <td><%=pro.getPid()%></td>
                <td><%=pro.getName()%></td>
                <td><%=pro.getDescription()%></td>
                <td><%=pro.getPrice()%></td>
                <td><%=pro.getQuantity()%></td>
                <td><%=pro.getCatid()%></td>
                <td><%=pro.getImage()%></td>
                <td><%=pro.getStatus()%></td>
                <td><a href="adminlist?service=updateProduct&pid=<%=pro.getPid()%>">update</a></td>
                <td><a href="adminlist?service=deleteProduct&pid=<%=pro.getPid()%>" onclick="return confirmDelete()">delete</a></td>
                <td><a href="CartController?service=add2cart&pid=<%=pro.getPid()%>">add2cart</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
