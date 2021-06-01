package sistema.controller;

import java.io.File;
import sistema.dao.MedicoDAO;
import sistema.model.User;
import sistema.model.Medico;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


@MultipartConfig
@WebServlet(name = "MedicosController", urlPatterns = {"/MedicosController"})
public class MedicosController extends HttpServlet {
   
    // Variables locales
    private MedicoDAO admin;//Objeto de tipo Admin DAO
    
    
    public MedicosController(){
        super();
        admin = new MedicoDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        
      /*-----------------------INICIA LISTAR MEDUICOS-----------------------------------------*/
      if (action.equalsIgnoreCase("allMedicos")) {
            String buscar = request.getParameter("usuario");
            User x = new User();
            x = admin.getUser(buscar);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("imagen",x.getPicture());
            
            request.setAttribute("medicos",admin.getAllMedicos()); 
            forward = "/administrador/medicos/index.jsp";
        }
      /*-----------------------TERMINA LISTAR MEDICOS----------------------------------------*/
      
      
      /*-----------------------------INCIA CREAR MEDICO--------------------------------------*/
        else if (action.equalsIgnoreCase("formAddMedico")) {
            /**Datos del usuario logeado**/
            String buscar = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            forward = "/administrador/medicos/create.jsp";
        }   
      /*-----------------------------TERMINA CREAR MEDICO------------------------------------*/
      
      /*------------------------INICIA  ADD MEDICO-------------------------------------------*/ 
        else if (action.equalsIgnoreCase("addMedico")) {
           /*Valores del formulario*/
            
           String area = request.getParameter("area");
           String nombre = request.getParameter("nombre");
           String apm = request.getParameter("apm");
           String atp = request.getParameter("atp");
           String email = request.getParameter("email");
           String tel = request.getParameter("tel");
           String hrinic = request.getParameter("hrinic");
           String hrfin = request.getParameter("hrfin");
           String direcc = request.getParameter("direcc");
           
           Medico medico = new Medico();
           medico.setArea(area);
           medico.setNombre(nombre);
           medico.setAp_mat(apm);
           medico.setAp_pat(atp);
           medico.setEmail(email);
           medico.setTel(tel);
           medico.setHora_inc(hrinic);
           medico.setHora_fin(hrfin);
           medico.setDireccion(direcc);
           
           admin.addMedico(medico);

           request.setAttribute("medicos",admin.getAllMedicos());
           forward = "/administrador/medicos/index.jsp";
        }
      /*------------------------TERMINA ADD MEDICO-----------------------------------------*/
      RequestDispatcher view = request.getRequestDispatcher(forward);
       view.forward(request, response);
    }

 
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
