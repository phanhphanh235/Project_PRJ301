package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOEmployee;

/**
 *
 * @author admin
 */
public class EmployeeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession ses = request.getSession(true);
        DAOEmployee dao = new DAOEmployee();
        String service = request.getParameter("service");
        if(service.equals("login")){
            String submit=request.getParameter("submit");
            if(submit==null){
                request.getRequestDispatcher("/JSP/EmployeeLogin.jsp").forward(request, response);
            }else{
                String userName=request.getParameter("userName");
                String pass=request.getParameter("password");
                boolean flag=dao.login(userName, pass);
                if(flag){
                    ses.setAttribute("user", userName);
                }
                response.sendRedirect("ProductController");
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
