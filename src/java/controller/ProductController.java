
package controller;

import entity.Products;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author admin
 */
public class ProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAOProduct dao = new DAOProduct();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllProducts";
        }
        if (service.equals("updateProduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {
                ResultSet rsCate = dao.getData("Select * from Categories");
                ResultSet rsSup = dao.getData("Select * from Suppliers");

                request.setAttribute("rsCate", rsCate);
                request.setAttribute("rsSup", rsSup);

                Vector<Products> vector = dao.getProducts("Select * from Products where ProductID="
                        + Integer.parseInt(request.getParameter("pid")));

                request.setAttribute("vector", vector);
                request.getRequestDispatcher("/JSP/upateProduct.jsp")
                        .forward(request, response);
            }else{
                String id = request.getParameter("ProductID");
                int pid=Integer.parseInt(id);
                String ProductName = request.getParameter("ProductName");
                String SupplierID = request.getParameter("SupplierID"),
                        CategoryID = request.getParameter("CategoryID");
                String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                String UnitPrice = request.getParameter("UnitPrice");
                String UnitsInStock = request.getParameter("UnitsInStock"),
                        UnitsOnOrder = request.getParameter("UnitsOnOrder"),
                        ReorderLevel = request.getParameter("ReorderLevel");
                String Discontinued = request.getParameter("Discontinued");
                
                int supId = Integer.parseInt(SupplierID);
                int cateId = Integer.parseInt(CategoryID);
                double price = Double.parseDouble(UnitPrice);
                int unitStock = Integer.parseInt(UnitsInStock);
                int unitOder = Integer.parseInt(UnitsOnOrder);
                int reOder = Integer.parseInt(ReorderLevel);
                int disCon = Integer.parseInt(Discontinued);
                
                Products pro = new Products(pid, ProductName,
                        supId, cateId, QuantityPerUnit,
                        price, unitStock, unitOder,
                        reOder, (disCon == 1 ? true : false));
                dao.updateProduct(pro);
                response.sendRedirect("ProductController");
            }
        }
        if (service.equals("insertProduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {//show form insert
                //getdata
                ResultSet rsCate = dao.getData("Select * from Categories");
                ResultSet rsSup = dao.getData("Select * from Suppliers");
                // 
                request.setAttribute("rsCate", rsCate);
                request.setAttribute("rsSup", rsSup);

                request.getRequestDispatcher("/JSP/insertProduct.jsp")
                        .forward(request, response);

            } else { //insert
                String ProductName = request.getParameter("ProductName");
                String SupplierID = request.getParameter("SupplierID"),
                        CategoryID = request.getParameter("CategoryID");
                String QuantityPerUnit = request.getParameter("QuantityPerUnit");
                String UnitPrice = request.getParameter("UnitPrice");
                String UnitsInStock = request.getParameter("UnitsInStock"),
                        UnitsOnOrder = request.getParameter("UnitsOnOrder"),
                        ReorderLevel = request.getParameter("ReorderLevel");
                String Discontinued = request.getParameter("Discontinued");
                //check data: emply, duplicate, isnumber...
                // convert
                int supId = Integer.parseInt(SupplierID);
                int cateId = Integer.parseInt(CategoryID);
                double price = Double.parseDouble(UnitPrice);
                int unitStock = Integer.parseInt(UnitsInStock);
                int unitOder = Integer.parseInt(UnitsOnOrder);
                int reOder = Integer.parseInt(ReorderLevel);
                int disCon = Integer.parseInt(Discontinued);
                //create Product
                Products pro = new Products(0, ProductName,
                        supId, cateId, QuantityPerUnit,
                        price, unitStock, unitOder,
                        reOder, (disCon == 1 ? true : false));
                dao.insertProduct(pro);
                response.sendRedirect("ProductController");
            }
        }
        if (service.equals("listAllProducts")) {
            // get data from DAO
            String submit = request.getParameter("submit");
            Vector<Products> vector = null;
            if (submit == null) {//chua submit --> show all
                vector = dao.getProducts("select * from products");
            } else { // search
                String pname = request.getParameter("pname");
                vector = dao.getProducts("select * from products "
                        + " where ProductName like '%" + pname + "%'");
            }
            String titlePage = "ProductManage";
            String titleTable = "List of Products";
            //select view (JSP)
            RequestDispatcher dispath
                    = request.getRequestDispatcher("/JSP/listProduct.jsp");
            //set data for view
            request.setAttribute("data", vector);
            request.setAttribute("titlePage", titlePage);
            request.setAttribute("titleTable", titleTable);
            //run
            dispath.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
