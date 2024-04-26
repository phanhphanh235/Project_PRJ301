package controller;

import entity.Product;
import entity.ProductCart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;
import model.DAOProduct;

/**
 *
 * @author admin
 */
public class CartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        DAOProduct dao = new DAOProduct();
        String service = request.getParameter("service");

        if (service.equals("add2cart")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            String key = pid + "";
            ProductCart proCart = (ProductCart) session.getAttribute(key);
            if (proCart == null) {
                String sql = "Select * from Products where pid=" + pid;
                Vector<Product> vector = dao.getProduct(sql);
                Product pro = vector.get(0);
                proCart = new ProductCart(pid,
                        pro.getName(),
                        pro.getPrice(), 1);
            } else {
                proCart.setQuantity(proCart.getQuantity() + 1);
            }
            session.setAttribute(key, proCart);
            response.sendRedirect("cart?service=showCart");

        }
        if (service.equals("showCart")) {
            Vector<ProductCart> vectorcart = new Vector<ProductCart>();
            double grandTotal = 0;
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("user")) {
                    ProductCart procart = (ProductCart) session.getAttribute(key);
                    vectorcart.add(procart);
                    grandTotal += procart.getPrice() * procart.getQuantity();
                }
            }
            request.setAttribute("vectorcart", vectorcart);
            request.setAttribute("grandTotal", grandTotal);
            request.getRequestDispatcher("/JSP/showCart.jsp").forward(request, response);
        }
        if (service.equals("remove")) {
            int pid = Integer.parseInt(request.getParameter("pid"));
            String key = pid + "";
            session.removeAttribute(key);
            response.sendRedirect("cart?service=showCart");
        }
        if (service.equals("removeAll")) {
            Enumeration enu = session.getAttributeNames();
            while (enu.hasMoreElements()) {
                String key = (String) enu.nextElement();
                if (!key.equals("user")) {
                    session.removeAttribute(key);
                }
            }
            response.sendRedirect("cart?service=showCart");
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
