package sistema.controller;

import java.io.File;
import sistema.dao.RolesDAO;
import sistema.model.User;
import sistema.model.Roles;


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
@WebServlet(name = "RolesController", urlPatterns = {"/RolesController"})
public class RolesController extends HttpServlet {

    // Variables locales
    private RolesDAO admin;//Objeto de tipo Admin DAO
    
    
    public RolesController () {
        super(); 
        admin = new RolesDAO();
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
        
      /*-----------------------INICIA LISTAR ROLES-----------------------------------------*/
      if (action.equalsIgnoreCase("allRoles")) {
            String buscar = request.getParameter("usuario");
            User x = new User();
            x = admin.getUser(buscar);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("imagen",x.getPicture());
            request.getSession().setAttribute("rol",x.getId_role());
            
            request.setAttribute("roles",admin.getAllRoles()); 
            forward = "/administrador/roles/index.jsp";
        }
      /*-----------------------TERMINA LISTAR ROLES----------------------------------------*/
     
      
      /*-----------------------------INCIA CREAR ROLE--------------------------------------*/
        else if (action.equalsIgnoreCase("formAddRole")) {
            /**Datos del usuario logeado**/
            String buscar = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            request.getSession().setAttribute("rol",z.getId_role());
            forward = "/administrador/roles/create.jsp";
        }   
      /*-----------------------------TERMINA CREAR ROLE------------------------------------*/
      
        
      /*------------------------INICIA  ADD ROLES------------------------------------------*/ 
        else if (action.equalsIgnoreCase("addRole")) {
            /*Valores del formulario*/

           String rol = request.getParameter("rol");
           String desc = request.getParameter("desc");
           String url = request.getParameter("url");
            
           Roles role = new Roles();
           role.setName_role(rol);
           role.setPermissions(desc);
           role.setUrl(url);
          
           
           if (admin.validarregistro(rol)) {
               request.getSession().setAttribute("mensaje", "El rol ya existe");
               forward = "/administrador/roles/create.jsp";
            }
           else{
               String buscar = request.getParameter("usuario-login");
               User z= new User();
               z = admin.getUser(buscar);
               request.getSession().setAttribute("name", z.getName());
               request.getSession().setAttribute("imagen",z.getPicture());
               request.getSession().setAttribute("rol",z.getId_role());
               admin.addRole(role);
               request.setAttribute("roles",admin.getAllRoles());
               forward = "/administrador/roles/index.jsp";
           }

           
        }
      /*------------------------TERMINA ADD ROLES------------------------------------------*/ 
      
        
      /*-----------------------INCIA EDITAR ROLES------------------------------------------*/
        else if (action.equalsIgnoreCase("editRole")) {
            
            /**Datos del usuario logeado**/
            String buscar1 = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar1);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            request.getSession().setAttribute("rol1",z.getId_role());
            
            /**Datos del usuario que se desea editar**/
            String id_buscar = request.getParameter("id");
            int id= Integer.parseInt(id_buscar);
            
            Roles r = new Roles();
            r= admin.getRoles(id);
            request.getSession().setAttribute("id",r.getId_rol());
            request.getSession().setAttribute("rol",r.getName_role());
            request.getSession().setAttribute("descrip",r.getPermissions());
            request.getSession().setAttribute("url",r.getUrl());
            request.getSession().setAttribute("id_permi",r.getId_permisos());
            forward = "/administrador/roles/edit.jsp";
        }
      /*-----------------------TERMINA EDITAR ROLES----------------------------------------*/
      
        
      /*-----------------------INCIA ACTUALIZAR ROLES--------------------------------------*/
        
       else if (action.equalsIgnoreCase("updateRole")) {
           String buscar = request.getParameter("usuario");
           
           String idSerch=request.getParameter("id-serch");
           int id_serach= Integer.parseInt(idSerch);
           
           String rolSearch = request.getParameter("rolSerch");
           
           
           String idUpdate = request.getParameter("id-update");
           int id_update= Integer.parseInt(idUpdate);
           String rol = request.getParameter("rol");
           String desc = request.getParameter("desc");
           String url = request.getParameter("url");
           
           Roles role = new Roles();
           role.setId_rol(id_update);
           role.setName_role(rol);
           role.setPermissions(desc);
           role.setUrl(url);
           
           if(rolSearch == null ? rol != null : !rolSearch.equals(rol)){
               if (admin.validarregistro(rol)) {
                 request.getSession().setAttribute("mensaje", "El rol ya existe");
                 forward = "/administrador/roles/edit.jsp";
               }
               else{
                  admin.updateRoles(id_serach, role);
                  User x = new User();
                  x = admin.getUser(buscar);
                  request.getSession().setAttribute("name", x.getName());
                  request.getSession().setAttribute("imagen",x.getPicture());
                  request.getSession().setAttribute("rol",x.getId_role());
                  request.setAttribute("roles",admin.getAllRoles()); 
                  forward = "/administrador/roles/index.jsp";
               }
            }
           else if (rolSearch.equals(rol)){
              admin.updateRoles(id_serach, role);
              User x = new User();
              x = admin.getUser(buscar);
              request.getSession().setAttribute("name", x.getName());
              request.getSession().setAttribute("imagen",x.getPicture());
              request.getSession().setAttribute("rol",x.getId_role());
              request.setAttribute("roles",admin.getAllRoles()); 
              forward = "/administrador/roles/index.jsp";
           }
           
          
        } 
      /*-----------------------TERMINA ACTUALIZAR ROLES------------------------------------*/  
     
       
      /*-----------------------INCIA ELIMINAR ROLES----------------------------------------*/
      else if (action.equalsIgnoreCase("delitRole")) {
          String buscar = request.getParameter("usuario-login");
          
          String id_buscar = request.getParameter("id");
          int id= Integer.parseInt(id_buscar);
          admin.deleteRoles(id);

                  
           User x = new User();
           x = admin.getUser(buscar);
           request.getSession().setAttribute("name", x.getName());
           request.getSession().setAttribute("imagen",x.getPicture());
           request.getSession().setAttribute("rol",x.getId_role());
           request.setAttribute("roles",admin.getAllRoles()); 
           
           
           forward = "/administrador/roles/index.jsp";
        }
      /*-----------------------TERMINA ELIMINAR ROLES--------------------------------------*/
      
      RequestDispatcher view = request.getRequestDispatcher(forward);
       view.forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
