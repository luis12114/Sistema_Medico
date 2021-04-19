package sistema.controller;

import sistema.dao.AdminDAO;
import sistema.model.User;
import sistema.util.Database;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controlAdmin", urlPatterns = {"/controlAdmin"})
public class controlAdmin extends HttpServlet {

    // Variables locales
    private AdminDAO admin;//Objeto de tipo Admin DAO

    public controlAdmin() {
        super();
        admin = new AdminDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String forward = "";
        String action = request.getParameter("action");

        /*-------------------------------INICIA LOGIN------------------------------------------*/
        if (action.equalsIgnoreCase("login")) {
            /*Valores del formulario*/
            String user = request.getParameter("usuario");
            String passwd = request.getParameter("password");
            if (admin.login(user, passwd)) {
                forward = "index.jsp";
            } else {
                forward = "errors/error.jsp";
            }
        } /*--------------------------------TERMINA LOGIN----------------------------------------*/ 
        
        /*------------------------INICIA REGISTRO DE USUARIOS---------------------------------*/ 
        else if (action.equalsIgnoreCase("register-user")) {
            /*Valores del formulario*/
            String username = request.getParameter("usuario");
            String email = request.getParameter("email");
            String passwd = request.getParameter("password");
            String passwd_confirm = request.getParameter("password-confirm");
           
            /*Envio a base de datos*/
            User user = new User();
            user.setName(username);
            user.setPassword(passwd);

            admin.addUser(user);
            forward = "login/login.jsp";
        } /*------------------------TERMINA REGISTRO DE USUARIOS-------------------------------*/ 
        
        else {
            forward = "errors/error.jsp";
        }
       RequestDispatcher view = request.getRequestDispatcher(forward);
       view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
