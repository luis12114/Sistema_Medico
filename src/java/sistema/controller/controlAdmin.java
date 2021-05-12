package sistema.controller;

import java.io.File;
import sistema.dao.AdminDAO;
import sistema.model.User;
import sistema.util.Database;

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
@WebServlet(name = "controlAdmin", urlPatterns = {"/controlAdmin"})
public class controlAdmin extends HttpServlet {
    
    // Variables locales
    private AdminDAO admin;//Objeto de tipo Admin DAO
    
    //Variable de subida de acrhivos
    private String pathFiles="C:\\Users\\52777\\Documents\\NetBeansProjects\\SistemaMedico\\web\\images";
    private File uploads = new File(pathFiles);
    private String[] exents ={".ico",".png",".jpg","jpeg"};
    
    
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
              //forward = "/administrador/index.jsp";
                forward =  admin.getURLMenu(user);
            } else {
                forward = "/login/login.jsp";
            }
            User u = new User();
            u= admin.getUser(user);
            request.getSession().setAttribute("name", u.getName());
            request.getSession().setAttribute("imagen", u.getPicture());
        } 
      /*--------------------------------TERMINA LOGIN----------------------------------------*/ 
        
        
      /*------------------------INICIA REGISTRO DE USUARIOS---------------------------------*/ 
        else if (action.equalsIgnoreCase("register-user")) {
            /*Valores del formulario*/
            String username = request.getParameter("usuario");
            String email = request.getParameter("email");
            String passwd = request.getParameter("password");
            String passwd_confirm = request.getParameter("password-confirm");
            String id_role = request.getParameter("id_role");
            int role= Integer.parseInt(id_role);
            
            /*Envio a base de datos*/
            
            User user = new User();

            try{
                Part part = request.getPart("file");
                String namefoto=extractFileName(part);
                user.setPicture(namefoto);
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
            user.setId_role(role);
            
            admin.addUser(user);
            forward = "/login/login.jsp";
        }
      /*------------------------TERMINA REGISTRO DE USUARIOS-------------------------------*/ 
        
        
      /*-----------------------INCIA VOLVER A HOME DEL PANEL ADMIN-------------------------*/ 
      else if (action.equalsIgnoreCase("dashboard")) {
           String buscar = request.getParameter("nameUser");
           
           User x = new User();
           x = admin.getUser(buscar);
           
           request.getSession().setAttribute("name", x.getName());
           request.getSession().setAttribute("imagen",x.getPicture());
           
           forward = "/administrador/index.jsp";
       }
      /*-----------------------TERMINA VOLVER A HOME DEL PANEL ADMIN-----------------------*/ 
        
        
      /*-----------------------INCIA VER TODOS LOS USURIOS----------------------------------*/   
        else if (action.equalsIgnoreCase("allUsers")) {
            String buscar = request.getParameter("usuario");
            User x = new User();
            x = admin.getUser(buscar);
            request.getSession().setAttribute("name", x.getName());
            request.getSession().setAttribute("imagen",x.getPicture());
            request.setAttribute("users",admin.getAllUsers()); 
            forward = "/administrador/users/index.jsp";
        }
        
      /*-----------------------TERMINA VER TODOS LOS USURIOS-------------------------------*/  
       
        
     /*-----------------------------INCIA CREAR USUARIOS-----------------------------------*/
        else if (action.equalsIgnoreCase("formAdd")) {
            /**Datos del usuario logeado**/
            String buscar = request.getParameter("usuario-login");
            User z= new User();
            z = admin.getUser(buscar);
            request.getSession().setAttribute("name", z.getName());
            request.getSession().setAttribute("imagen",z.getPicture());
            forward = "/administrador/users/create.jsp";
        }   
     /*-----------------------------TERMINA CREAR USUARIOS---------------------------------*/
        
        
      
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
            request.getSession().setAttribute("imagen",x.getPicture());
            request.getSession().setAttribute("pass",x.getPassword());
            forward = "/administrador/users/edit.jsp";
        }
        
      /*-----------------------TERMINA EDITAR USUARIO---------------------------------------*/
        
        
      /*-----------------------INCIA ACTUALIZAR USUARIO-----------------------------------------*/
        
       else if (action.equalsIgnoreCase("updateUser")) {
           String buscar = request.getParameter("loginUser");
           
           String username1 = request.getParameter("usuario1");
           String username = request.getParameter("usuario");
           String passwd = request.getParameter("password");

           User user = new User();
           user.setName(username);
           user.setPassword(passwd);
           admin.updateUser(username1,user);
           
           
           User x = new User();
           x = admin.getUser(buscar);
           request.getSession().setAttribute("name", x.getName());
           request.getSession().setAttribute("imagen",x.getPicture());
           
           request.setAttribute("users",admin.getAllUsers()); 
       
           forward = "/administrador/users/index.jsp";
        } 
      
      /*-----------------------TERMINA ACTUALIZAR USUARIO---------------------------------------*/
       
       
      /*-----------------------INCIA ELIMINAR USUARIOS------------------------------------------*/
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
           
           request.setAttribute("users",admin.getAllUsers());
           
           
           forward = "/administrador/users/index.jsp";
        }
      /*-----------------------TERMINA ELIMINAR USUARIOS---------------------------------------*/
        
        
        else {
           forward = "/errors/error.jsp";
        }
        
        
       RequestDispatcher view = request.getRequestDispatcher(forward);
       view.forward(request, response);
    }

   
    /*------------------------INICIA FUNCIONES PARA SUBIR IMAGENS-------------------------------*/ 
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
    /*------------------------TERMINA FUNCIONES PARA SUBIR IMAGENS-------------------------------*/ 
     
     
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
