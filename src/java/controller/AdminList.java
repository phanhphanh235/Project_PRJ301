package controller;

import entity.Product;
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
public class AdminList extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAOProduct dao = new DAOProduct();
        String service = request.getParameter("service");
        if (service == null) {
            service = "listAllProducts";
        }
        if (service.equals("deleteProduct")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            int n = dao.deleteProduct(pid);
            System.out.println(n);
            response.sendRedirect("product");
        }

        if (service.equals("listAllProducts")) {
            String submit = request.getParameter("submit");
            Vector<Product> vector = null;
            if (submit == null) {
                vector = dao.getProduct("select * from Products");
            } else { // search
                String pname = request.getParameter("pname");
                vector = dao.getProduct("select * from products "
                        + " where name like '%" + pname + "%'");
            }
            String titlePage = "Product Manage";
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

        if (service.equals("insertProduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {//show form insert
                //getdata
                ResultSet rsCate = dao.getData("Select * from Categories");
                //ResultSet rsStatus = dao.getData("Select * from status");
                // 
                request.setAttribute("rsCate", rsCate);
                //request.setAttribute("rsStatus", rsStatus);

                request.getRequestDispatcher("/JSP/insertProduct.jsp")
                        .forward(request, response);
            } else {
                String ProductName = request.getParameter("name");
                String Description = request.getParameter("description");
                String Price = request.getParameter("price");
                String Quantity = request.getParameter("quantity");
                String Category = request.getParameter("catid");
                String image = request.getParameter("image");
                String status = request.getParameter("status");

                float price = Float.parseFloat(Price);
                int quantity = Integer.parseInt(Quantity);
                int catid = Integer.parseInt(Category);

                Product pro = new Product(catid, image, Description, price, quantity, catid, image, status);
                dao.addProduct(pro);
                response.sendRedirect("product");
            }
        }

        if (service.equals("updateProduct")) {
            String submit = request.getParameter("submit");
            if (submit == null) {//show form update
                //getdata
                ResultSet rsCate = dao.getData("Select * from Categories");
                //ResultSet rsStatus = dao.getData("Select * from status");
                // 
                request.setAttribute("rsCate", rsCate);
                //request.setAttribute("rsStatus", rsStatus);
                Vector<Product> vector
                        = dao.getProduct("select * from Products where pid="
                                + Integer.parseInt(request.getParameter("pid")));
                request.setAttribute("vector", vector);

                request.getRequestDispatcher("/JSP/updateProduct.jsp")
                        .forward(request, response);
            }else{
                String id = request.getParameter("pid");
                String ProductName = request.getParameter("name");
                String Description = request.getParameter("description");
                String Price = request.getParameter("price");
                String Quantity = request.getParameter("quantity");
                String Category = request.getParameter("catid");
                String image = request.getParameter("image");
                String status = request.getParameter("status");

                int pid = Integer.parseInt(id);
                float price = Float.parseFloat(Price);
                int quantity = Integer.parseInt(Quantity);
                int catid = Integer.parseInt(Category);
                
                Product pro = new Product(pid, image, Description, price, quantity, catid, image, status);
                dao.updateProduct(pro);
                response.sendRedirect("product");
            }
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
