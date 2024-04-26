package controller;

import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.DAOAccount;

/**
 *
 * @author admin
 */
public class SignIn extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        request.setAttribute("check", "null");
        request.getRequestDispatcher("/JSP/signIn.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter pr = response.getWriter();
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        DAOAccount dao = new DAOAccount();
        HttpSession session = request.getSession();
        Account a = dao.checkAccount(user, pass);
        if(a != null){
            request.setAttribute("check", "success");
            session.setAttribute("user", user);
            session.setAttribute("pass", pass);
            request.getRequestDispatcher("/JSP/signIn.jsp").forward(request, response);
        } else {
            request.setAttribute("check", "fail");
            request.getRequestDispatcher("/JSP/signIn.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
