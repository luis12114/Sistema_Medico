package sistema.controller;

import sistema.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//imports adicioanles
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import sistema.dao.CategoryDAO;
import sistema.model.Category;

@WebServlet(name = "ListServlet", urlPatterns = {"/ListServlet"})
public class ListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //CategoryDAO dao = new CategoryDAO();    
    private CategoryDAO dao;
    
    public ListServlet() {

        super();
        dao = new CategoryDAO();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String forward="";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("listarCategorias")){
            listCategory(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        int categoryId = Integer.parseInt(request.getParameter("category"));
        request.setAttribute("selectedCatId", categoryId);
        listCategory(request, response);
    }
    
    //metodos definidos
    private void listCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
            try {

                //List<Category> listCatagory = dao.getAllCategories();
                //request.setAttribute("listCategory", listCatagory);
                
                String buscar = request.getParameter("usuario");
            User x = new User();
            x = dao.getUser(buscar);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("imagen",x.getPicture());
            request.getSession().setAttribute("rol",x.getId_role());
                
                request.setAttribute("listCategory", dao.getAllCategories());
 
                RequestDispatcher dispatcher = request.getRequestDispatcher("/reportePDF.jsp");
                dispatcher.forward(request, response);
 
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
    }

    
}
