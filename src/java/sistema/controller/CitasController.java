
package sistema.controller;

import java.io.File;
import sistema.dao.CitaDAO;
import sistema.model.Citas;
import sistema.model.User;

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

import java.text.ParseException;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@MultipartConfig
@WebServlet(name = "CitasController", urlPatterns = {"/CitasController"})
public class CitasController extends HttpServlet {
   // Variables locales
    private CitaDAO admin;//Objeto de tipo Admin DAO
    
    public CitasController(){
        super();
        admin = new CitaDAO();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String forward = "";
        String action = request.getParameter("action");
        
        /*-----------------------INICIA LISTAR CITAS-----------------------------------------*/
        if (action.equalsIgnoreCase("allCitas")) {
            String buscar = request.getParameter("usuario");
            User x = new User();
            x = admin.getUser(buscar);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("imagen",x.getPicture());
            
            request.setAttribute("citas",admin.getAllCitas(buscar));
            forward = "/administrador/citas/index.jsp";
        }
      /*-----------------------TERMINA LISTAR CITAS-------------------------------------------*/
        
        
      /*------------------------INCIA CREAR CITAS--------------------------------------------*/ 
        else if (action.equalsIgnoreCase("formAddCita")) {
            /**Datos del usuario logeado**/
            String buscar = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            request.getSession().setAttribute("email",z.getEmail());
            
            request.setAttribute("citas",admin.getAllCitas(buscar));
            forward = "/administrador/citas/create.jsp";
        }
      /*------------------------TERMINA CREAR CITAS--------------------------------------------*/ 
      
      
      /*------------------------INICIA  ADD CITA----------------------------------------------*/ 
        else if (action.equalsIgnoreCase("addCita")) {
            /*Valores del formulario*/
            String name = request.getParameter("name");
            String doctor = request.getParameter("doctor");
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");
            String tel = request.getParameter("tel");
            String email= request.getParameter("email");
            String genero= request.getParameter("genero");
            String motivo= request.getParameter("motivo");
            String sintomas= request.getParameter("sintomas");
            String estatus="activo";
            
            String buscar = request.getParameter("usuario");
            
            Citas citas = new Citas();
            citas.setName(name);
            citas.setDoctor(doctor);
            citas.setFecha(fecha);
            citas.setHora(hora);
            citas.setTel(tel);
            citas.setCorreo(email);
            citas.setGenero(genero);
            citas.setMotivo(motivo);
            citas.setSintomas(sintomas);
            citas.setEstatus(estatus);
            
            if (admin.validarregistro(hora)) {
               request.getSession().setAttribute("mensaje", "horario ocupado");
               forward = "/administrador/citas/create.jsp";
            }else{
             admin.addCitas(citas);
             request.setAttribute("citas",admin.getAllCitas(buscar));
             forward = "/administrador/citas/index.jsp";   
            }
            
        }
      /*------------------------TERMINA ADD CITA----------------------------------------------*/
      
      /*-----------------------INCIA EDITAR CITA----------------------------------------------*/
        else if (action.equalsIgnoreCase("editCita")) {
            
            /**Datos del usuario logeado**/
            String buscar1 = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar1);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            
            /**Datos del usuario que se desea editar**/
            String id_buscar = request.getParameter("cita");
            int id= Integer.parseInt(id_buscar);
            
            Citas c = new Citas ();
            c= admin.getCita(id);
            
            request.getSession().setAttribute("id",c.getId());
            request.getSession().setAttribute("name",c.getName());
            request.getSession().setAttribute("doctor",c.getDoctor());
            request.getSession().setAttribute("fecha",c.getFecha());
            request.getSession().setAttribute("hora",c.getHora());
            request.getSession().setAttribute("tel",c.getTel());
            request.getSession().setAttribute("correo",c.getCorreo());
            request.getSession().setAttribute("genero",c.getGenero());
            request.getSession().setAttribute("motivo",c.getMotivo());
            request.getSession().setAttribute("sintomas",c.getSintomas());
   
          
            forward = "/administrador/citas/edit.jsp";
        }
      /*-----------------------TERMINA EDITAR CITA--------------------------------------*/
      
        
      /*-----------------------INCIA ACTUALIZAR CITA------------------------------------*/
        
       else if (action.equalsIgnoreCase("updaCita")) {
           String buscar = request.getParameter("usuario1");
           String hora1 = request.getParameter("hora1");
           

           String name = request.getParameter("name");
           String doctor = request.getParameter("doctor");
           String fecha = request.getParameter("fecha");
           String hora = request.getParameter("hora");
           String tel = request.getParameter("tel");
           String email= request.getParameter("email");
           String genero= request.getParameter("genero");
           String motivo= request.getParameter("motivo");
           String sintomas= request.getParameter("sintomas");
           
           String idSerch=request.getParameter("id_serch");
           int id_serach= Integer.parseInt(idSerch);
           
           
           Citas citas = new Citas();
           citas.setName(name);
           citas.setDoctor(doctor);
           citas.setFecha(fecha);
           citas.setHora(hora);
           citas.setTel(tel);
           citas.setCorreo(email);
           citas.setGenero(genero);
           citas.setMotivo(motivo);
           citas.setSintomas(sintomas);
           
           if(hora1 == null ? hora != null : hora1.equals(hora)){
               if (admin.validarregistro(hora)) {
                 request.getSession().setAttribute("mensaje", "horario ocupado");
                 forward = "/administrador/citas/edit.jsp";
               }
               else{
                  admin.updateCita(id_serach, citas);
          
                  User x = new User();
                  x = admin.getUser(buscar);
                  request.getSession().setAttribute("name", x.getName());
                  request.getSession().setAttribute("imagen",x.getPicture());
           
                  request.setAttribute("citas",admin.getAllCitas(buscar));
                  forward = "/administrador/citas/index.jsp";  
                }
            }
           else if(hora1.equals(hora)){
              admin.updateCita(id_serach, citas);
          
              User x = new User();
              x = admin.getUser(buscar);
              request.getSession().setAttribute("name", x.getName());
              request.getSession().setAttribute("imagen",x.getPicture());
           
              request.setAttribute("citas",admin.getAllCitas(buscar));
              forward = "/administrador/citas/index.jsp"; 
           }
        } 
      /*-----------------------TERMINA ACTUALIZAR CITA-------------------------------------*/ 
      
      
      /*-----------------------INCIA CANCELAR CITA-----------------------------------------*/
       else if (action.equalsIgnoreCase("cancelarCita")) {
           /**Datos del usuario logeado**/
            String buscar= request.getParameter("usuario-login");
            
           
            String idSerch=request.getParameter("cita");
            int id_serach= Integer.parseInt(idSerch);
            
            admin.cancelarCita(id_serach);
            
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            
            request.setAttribute("citas",admin.getAllCitas(buscar));
            forward = "/administrador/citas/index.jsp";  
       }
      
      /*-----------------------TERMINA CANCELAR CITA---------------------------------------*/
      
       
      /*-----------------------INCIA ElIMINAR CITA-----------------------------------------*/
       else if (action.equalsIgnoreCase("delitCita")) {
           /**Datos del usuario logeado**/
            String buscar= request.getParameter("usuario-login");
            
           
            String idSerch=request.getParameter("cita");
            int id_serach= Integer.parseInt(idSerch);
            
            admin.deleteCita(id_serach);
            
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            
            request.setAttribute("citas",admin.getAllCitas(buscar));
            forward = "/administrador/citas/index.jsp";  
       }
      
      /*-----------------------TERMINA ElIMINAR CITA-------------------------------------*/
      
      RequestDispatcher view = request.getRequestDispatcher(forward);
      view.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
