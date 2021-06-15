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
           
           if (admin.validar1(nombre)) {
               request.getSession().setAttribute("mensaje", "El nombre ya existe");
               forward = "/administrador/medicos/create.jsp";
            }
           else if (admin.validar2(atp)) {
               request.getSession().setAttribute("mensaje", "El apellido ya existe");
               forward = "/administrador/medicos/create.jsp";
            }
           else if (admin.validar3(apm)) {
               request.getSession().setAttribute("mensaje", "El apellido ya existe");
               forward = "/administrador/medicos/create.jsp";
            }
           else if (admin.validar4(email)) {
               request.getSession().setAttribute("mensaje", "El correo ya existe");
               forward = "/administrador/medicos/create.jsp";
            }
           else if (admin.validar5(tel)) {
               request.getSession().setAttribute("mensaje", "El telefono ya existe");
               forward = "/administrador/medicos/create.jsp";
            }
           else{
             admin.addMedico(medico);
             request.setAttribute("medicos",admin.getAllMedicos());
             forward = "/administrador/medicos/index.jsp";
           }
           
        }
      /*------------------------TERMINA ADD MEDICO-----------------------------------------*/
      
      /*-----------------------INCIA EDITAR MEDICO-----------------------------------------*/
        else if (action.equalsIgnoreCase("editMedico")) {
            
            /**Datos del usuario logeado**/
            String buscar1 = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar1);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            
            /**Datos del usuario que se desea editar**/
            String id_buscar = request.getParameter("medico");
            int id= Integer.parseInt(id_buscar);
            
            Medico m = new Medico();
            m= admin.getMedico(id);
            
            request.getSession().setAttribute("id",m.getId());
            request.getSession().setAttribute("area",m.getArea());
            request.getSession().setAttribute("nombre",m.getNombre());
            request.getSession().setAttribute("apell_p",m.getAp_pat());
            request.getSession().setAttribute("apell_m",m.getAp_mat());
            request.getSession().setAttribute("direccion",m.getDireccion());
            request.getSession().setAttribute("email",m.getEmail());
            request.getSession().setAttribute("tel",m.getTel());
            request.getSession().setAttribute("hora_inic",m.getHora_inc());
            request.getSession().setAttribute("hora_fin",m.getHora_fin());
            
            forward = "/administrador/medicos/edit.jsp";
        }
      /*-----------------------TERMINA EDITAR MEDICO-------------------------------------*/
      
      /*-----------------------INCIA ACTUALIZAR MEDICO-----------------------------------*/
        
       else if (action.equalsIgnoreCase("updateMedico")) {
           String buscar = request.getParameter("usuario1");
           
           String idSerch=request.getParameter("id_serch");
           int id_serach= Integer.parseInt(idSerch);
           
           String nombre1 = request.getParameter("nombre1");
           String apm1 = request.getParameter("apm1");
           String atp1 = request.getParameter("atp1");
           String email1 = request.getParameter("email1");
           String tel1 = request.getParameter("tel1");
           
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
           
           if(nombre1 == null ? nombre != null : !nombre1.equals(nombre)){
               if (admin.validar1(nombre)) {
                 request.getSession().setAttribute("mensaje", "El nombre ya existe");
                 forward = "/administrador/medicos/edit.jsp";
               }
               else{
                 admin.updateMedico(id_serach,medico);
                 User x = new User();
                 x = admin.getUser(buscar);
                 request.getSession().setAttribute("name", x.getName());
                 request.getSession().setAttribute("imagen",x.getPicture());
           
                 request.setAttribute("medicos",admin.getAllMedicos()); 
                 forward = "/administrador/medicos/index.jsp";
               }
            }
           
           else if(atp1 == null ? atp != null : !atp1.equals(atp)){
               if (admin.validar2(atp)) {
                 request.getSession().setAttribute("mensaje", "El apellido ya existe");
                 forward = "/administrador/medicos/edit.jsp";
               }
               else{
                 admin.updateMedico(id_serach,medico);
                 User x = new User();
                 x = admin.getUser(buscar);
                 request.getSession().setAttribute("name", x.getName());
                 request.getSession().setAttribute("imagen",x.getPicture());
           
                 request.setAttribute("medicos",admin.getAllMedicos()); 
                 forward = "/administrador/medicos/index.jsp";
               }
            }
           
           else if(apm1 == null ? apm != null : !apm1.equals(apm)){
               if (admin.validar3(apm)) {
                 request.getSession().setAttribute("mensaje", "El apellido ya existe");
                 forward = "/administrador/medicos/edit.jsp";
               }
               else{
                 admin.updateMedico(id_serach,medico);
                 User x = new User();
                 x = admin.getUser(buscar);
                 request.getSession().setAttribute("name", x.getName());
                 request.getSession().setAttribute("imagen",x.getPicture());
           
                 request.setAttribute("medicos",admin.getAllMedicos()); 
                 forward = "/administrador/medicos/index.jsp";
               }
            }
           
            else if(email1 == null ? email != null : !email1.equals(email)){
               if (admin.validar4(apm)) {
                 request.getSession().setAttribute("mensaje", "El email ya existe");
                 forward = "/administrador/medicos/edit.jsp";
               }
               else{
                 admin.updateMedico(id_serach,medico);
                 User x = new User();
                 x = admin.getUser(buscar);
                 request.getSession().setAttribute("name", x.getName());
                 request.getSession().setAttribute("imagen",x.getPicture());
           
                 request.setAttribute("medicos",admin.getAllMedicos()); 
                 forward = "/administrador/medicos/index.jsp";
               }
            }
           
           else if(tel1== null ? tel != null : !tel1.equals(tel)){
               if (admin.validar5(tel)) {
                 request.getSession().setAttribute("mensaje", "El tel ya existe");
                 forward = "/administrador/medicos/edit.jsp";
               }
               else{
                 admin.updateMedico(id_serach,medico);
                 User x = new User();
                 x = admin.getUser(buscar);
                 request.getSession().setAttribute("name", x.getName());
                 request.getSession().setAttribute("imagen",x.getPicture());
           
                 request.setAttribute("medicos",admin.getAllMedicos()); 
                 forward = "/administrador/medicos/index.jsp";
               }
            }
            else if(nombre1.equals(nombre)||atp1.equals(atp)|| apm1.equals(apm)||email1.equals(email)||tel1.equals(tel) ){
              admin.updateMedico(id_serach,medico);
              User x = new User();
              x = admin.getUser(buscar);
              request.getSession().setAttribute("name", x.getName());
              request.getSession().setAttribute("imagen",x.getPicture());
           
              request.setAttribute("medicos",admin.getAllMedicos()); 
              forward = "/administrador/medicos/index.jsp";
            }
           
        } 
      /*-----------------------TERMINA ACTUALIZAR MEDICO-----------------------------------*/ 
      
      
      /*-----------------------INCIA ELIMINAR MEDICO---------------------------------------*/
      else if (action.equalsIgnoreCase("delitMedico")) {
          String buscar = request.getParameter("usuario-login");
          
          String id_buscar = request.getParameter("medico");
          int id= Integer.parseInt(id_buscar);
          
          admin.deleteMedico(id);
                  
           User x = new User();
           x = admin.getUser(buscar);
           request.getSession().setAttribute("name", x.getName());
           request.getSession().setAttribute("imagen",x.getPicture());
           
           request.setAttribute("medicos",admin.getAllMedicos()); ; 
           
           
           forward = "/administrador/medicos/index.jsp";
        }
      /*-----------------------TERMINA ELIMINAR MEDICO----------------------------------*/
      
      RequestDispatcher view = request.getRequestDispatcher(forward);
       view.forward(request, response);
    }

 
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
