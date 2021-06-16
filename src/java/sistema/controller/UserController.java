package sistema.controller;

import java.io.File;
import sistema.dao.UserDAO;
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


@MultipartConfig
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {
    
    // Variables locales
    private UserDAO admin;//Objeto de tipo Admin DAO
    
    //Variable de subida de acrhivos
    private String pathFiles="C:\\Users\\52777\\Documents\\NetBeansProjects\\SistemaMedico\\web\\images";
    private File uploads = new File(pathFiles);
    private String[] exents ={".ico",".png",".jpg","jpeg"};
    
    
    public UserController() {
        super();
        admin = new UserDAO();
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
        String forward = "";
        String action = request.getParameter("action");
        
      /*-----------------------INCIA VER TODOS LOS USURIOS----------------------------------*/   
        if (action.equalsIgnoreCase("allUsers")) {
            String buscar = request.getParameter("usuario");
            User x = new User();
            x = admin.getUser(buscar);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("imagen",x.getPicture());
            request.getSession().setAttribute("rol",x.getId_role());
            request.setAttribute("users",admin.getAllUsers()); 
            forward = "/administrador/users/index.jsp";
        } 
      /*-----------------------TERMINA VER TODOS LOS USURIOS-------------------------------*/
      
        
      /*-----------------------------INCIA CREAR USUARIOS----------------------------------*/
        else if (action.equalsIgnoreCase("formAdd")) {
            /**Datos del usuario logeado**/
            String buscar = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            request.getSession().setAttribute("rol",z.getId_role());
            
            request.setAttribute("rol",admin.getAllRoles());
            forward = "/administrador/users/create.jsp";
        }   
     /*-----------------------------TERMINA CREAR USUARIOS--------------------------------*/
     
        
     /*------------------------INICIA  ADD USUARIOS---------------------------------------*/ 
        else if (action.equalsIgnoreCase("addUser")) {
            /*Valores del formulario*/
            String username = request.getParameter("User");
            String email = request.getParameter("email");
            String passwd = request.getParameter("pass");
            String id_role = request.getParameter("id_role");
            int role= Integer.parseInt(id_role);
            
            /*Envio a base de datos*/
            
            User user = new User();

            try{
                Part part = request.getPart("file");
                String namefoto=extractFileName(part);
                if(namefoto.equals("")){
                  user.setPicture("defaultProfile.jpg"); 
                }else{
                    user.setPicture(namefoto);
                }
                if (part == null){
                    out.print("no imagen");
                    return;
                }
                if(isExtension(part.getSubmittedFileName(),exents)){
                    String foto= saveFile(part,uploads);
                }
            }catch(Exception e){
               e.printStackTrace();
            }
  
            user.setName(username);
            user.setPassword(passwd);
            user.setEmail(email);
            user.setId_role(role);
            
            
            if (admin.validarregistro(username)) {
               request.getSession().setAttribute("mensaje", "El usuario ya está en uso");
               request.setAttribute("rol",admin.getAllRoles());
               forward = "/administrador/users/create.jsp";
            }
            else if(admin.validaemail(email)){
              request.getSession().setAttribute("mensaje", "El correo ya está en uso");
              request.setAttribute("rol",admin.getAllRoles());
              forward = "/administrador/users/create.jsp";
            }
            else{
               admin.addUser(user);
               request.setAttribute("users",admin.getAllUsers());
               forward = "/administrador/users/index.jsp";
            }   
        }
      /*------------------------TERMINA ADD USUARIOS----------------------------------------*/
      
        
      /*-----------------------INCIA EDITAR USUARIO-----------------------------------------*/
        else if (action.equalsIgnoreCase("editUser")) {
            /**Datos del usuario logeado**/
            String buscar1 = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar1);
            request.getSession().setAttribute("name1", z.getName());
            request.getSession().setAttribute("imagen1",z.getPicture());
            
            /**Datos del usuario que se desea editar**/
            String buscar2 = request.getParameter("usuario");
            User x = new User();
            x = admin.getUser(buscar2);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("email", x.getEmail());
            request.getSession().setAttribute("pass",x.getPassword());
            request.getSession().setAttribute("imagen",x.getPicture());
            
            request.getSession().setAttribute("rolId",x.getId_role());
            request.getSession().setAttribute("rolName",x.getName_rol());
            
            request.setAttribute("rol",admin.getAllRoles());
            forward = "/administrador/users/edit.jsp";
        }
      /*-----------------------TERMINA EDITAR USUARIO--------------------------------------*/
      
        
      /*-----------------------INCIA ACTUALIZAR USUARIO------------------------------------*/
       else if (action.equalsIgnoreCase("updateUser")) {
           String buscar = request.getParameter("loginUser");
           String username1 = request.getParameter("usuario1");
           String email2 = request.getParameter("emailLogin");
           String imagen = request.getParameter("imagen");
           String id_role1 = request.getParameter("idRol");
           int role1= Integer.parseInt(id_role1);
           
           
           String username = request.getParameter("usuario");
           String email = request.getParameter("email");
           String passwd = request.getParameter("password");
           String id_role = request.getParameter("id_role");
           int role= Integer.parseInt(id_role);

           User user = new User();
           
           try{
                Part part = request.getPart("file");
                String namefoto=extractFileName(part);
                if(namefoto.equals("")){
                  user.setPicture(imagen); 
                }else{
                    user.setPicture(namefoto);
                }
                if (part == null){
                    out.print("no imagen");
                    return;
                }
                if(isExtension(part.getSubmittedFileName(),exents)){
                    String foto= saveFile(part,uploads);
                }
            }catch(Exception e){
               e.printStackTrace();
            }
           
           user.setName(username);
           user.setPassword(passwd);
           user.setEmail(email);
           user.setId_role(role);

           
           
           if(username1 == null ? username != null : !username1.equals(username)){
               if (admin.validarregistro(username)) {
                 request.getSession().setAttribute("mensaje", "El usuario ya está en uso");
                 forward = "/administrador/users/edit.jsp";
               }
               else{
                  admin.updateUser(username1,user);
                  User x = new User();
                  x = admin.getUser(buscar);
                  request.getSession().setAttribute("name", x.getName());
                  request.getSession().setAttribute("imagen",x.getPicture());
                  request.getSession().setAttribute("rol",x.getId_role());
                  request.setAttribute("users",admin.getAllUsers()); 
       
                  forward = "/administrador/users/index.jsp";
               }
            }
           else if(email2== null ? email!= null : !email2.equals(email)){
              if(admin.validaemail(email)){
                  request.getSession().setAttribute("mensaje", "El correo ya está en uso");
                  forward = "/administrador/users/edit.jsp";
                }
                else{
                  admin.updateUser(username1,user);
                  User x = new User();
                  x = admin.getUser(buscar);
                  request.getSession().setAttribute("name", x.getName());
                  request.getSession().setAttribute("imagen",x.getPicture());
                  request.getSession().setAttribute("rol",x.getId_role());
                  request.setAttribute("users",admin.getAllUsers()); 
       
                  forward = "/administrador/users/index.jsp";
               }
           }
           else if(username1.equals(username)||email2.equals(email)){
               admin.updateUser(username1,user);
               User x = new User();
               x = admin.getUser(buscar);
               request.getSession().setAttribute("name", x.getName());
               request.getSession().setAttribute("imagen",x.getPicture());
               request.getSession().setAttribute("rol",x.getId_role());
               request.setAttribute("users",admin.getAllUsers()); 
       
               forward = "/administrador/users/index.jsp";
           }
           
        }
      /*-----------------------TERMINA ELIMINAR USUARIOS-----------------------------------*/
      
      /*-----------------------INCIA ELIMINAR USUARIOS--------------------------------------*/
      else if (action.equalsIgnoreCase("delitUser")) {
          String buscar = request.getParameter("usuario-login");
          String username = request.getParameter("usuario");
          String imagen = request.getParameter("img");
         
          admin.deleteUser(username);
          admin.deleteItems(imagen);
                  
           User x = new User();
           x = admin.getUser(buscar);
           request.getSession().setAttribute("name", x.getName());
           request.getSession().setAttribute("imagen",x.getPicture());
           request.getSession().setAttribute("rol",x.getId_role());
           request.setAttribute("users",admin.getAllUsers());
           
           
           forward = "/administrador/users/index.jsp";
        }
      /*-----------------------TERMINA ELIMINAR USUARIOS-----------------------------------*/
     
      RequestDispatcher view = request.getRequestDispatcher(forward);
       view.forward(request, response);
    }
  
    
    
    
    /*------------------------INICIA FUNCIONES PARA SUBIR IMAGENS---------------------------*/ 
     /*Funcion para guardar archivos*/
      private String saveFile(Part part, File pathUploads){
        String pathAbsolute="";
        try{
           Path path = Paths.get(part.getSubmittedFileName());
           String fileName =path.getFileName().toString();
           InputStream input = part.getInputStream();
           if(input!=null){
               File file =new File(pathUploads,fileName);
               pathAbsolute = file.getAbsolutePath();
               Files.copy(input, file.toPath());
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return pathAbsolute;
    }
    
     /*Funciones para verificar extension de archivos*/
     private boolean isExtension(String fileName,String[]extensions){
       for(String et:extensions){
           if(fileName.toLowerCase().endsWith(et)){
              return true;
           }
       }
       return false;
    }
   
     /*Funciones para obtener el nombre de la imagen*/
     public String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
    /*------------------------TERMINA FUNCIONES PARA SUBIR IMAGENS---------------------------*/ 
     
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
