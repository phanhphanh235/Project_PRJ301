package controller;

import entity.Category;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;

/**
 *
 * @author admin
 */
public class CategoriesController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DAOCategories dao = new DAOCategories();
        String service = request.getParameter("service");
        if (service == null) { // run direct controller
            //default
            service = "listAllCategories";
        }
        if (service.equals("listAllCategories")) {
            // get data from DAO
            String submit = request.getParameter("submit");
            Vector<Category> vector = null;
            if (submit == null) {//chua submit --> show all
                vector = dao.getCategories("select * from Categories");
            } else { // search
                String catname = request.getParameter("name");
                vector = dao.getCategories("select * from Categories "
                        + " where CategoryName like '%" + catname + "%'");
            }
            String titlePage = "Categories Manage";
            String titleTable = "List of Categories";
            //select view (JSP)
            RequestDispatcher dispath
                    = request.getRequestDispatcher("/JSP/listCategories.jsp");
            //set data for view
            request.setAttribute("data", vector);
            request.setAttribute("titlePage", titlePage);
            request.setAttribute("titleTable", titleTable);
            //run
            dispath.forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
