<%-- 
    Document   : index.js
    Created on : 25 abr. 2021, 13:19:05
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
    String nom = (String) session.getAttribute("name");
    String imagen = (String) session.getAttribute("imagen");
    int rol = (int) session.getAttribute("rol");
%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Receta</title>
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">
        <link href="/SistemaMedico//css/dashboardStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    
     <style>
        table {
            width: 100%;
            border-collapse: collapse;
            border: 1px solid black;
            
        }
        
        .tab{
             position: absolute;
             top:20%;
             left: 36%;
 
        }
        
    </style>

    <body>
        <input type="checkbox" id="menu-toggle">
        <div class="sidebar">

            <div class="brand">
                <span class="las la-stethoscope"></span>
                <h2>Medical</h2>
            </div>
            <div class="sidemenu">
                <div class="side-user">
                    <div class="side-img"
                         style="
                         <%if (imagen != null) {%>
                         background-image:url(/SistemaMedico/images/<%=imagen%>
                         <%} else {%>
                         background-image:url(/SistemaMedico/images/defaultProfile.jpg
                         <%}%>
                         "
                         >
                    </div>
                    <div class="user">
                        <small><%out.println(nom);%></small>
                        <p>Panel Administrador</p>
                    </div>
                </div>
                <ul>
                    <li>
                        <form method="post" action="/SistemaMedico/controlAdmin" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-home"></span> <samp class="text">Dashboard</samp>
                            </button>
                            <input type="hidden" name="action" value="dashboard">
                            <input type="text" name="nameUser" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li> 

                    <li>
                        <form method="post" action="/SistemaMedico/DatosController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-users-cog"></span> <samp class="text">Mis Datos</samp>
                            </button>
                            <input type="hidden" name="action" value="datos">
                            <input type="text" name="usuario-login" value="<%out.println(nom);%>" style="display:none">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li> 
                    <%if (rol == 1) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/UserController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user"></span> <samp class="text">Users</samp>
                            </button>
                            <input type="hidden" name="action" value="allUsers">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>

                    <%if (rol==1){%>
                    <li>
                        <form method="post" action="/SistemaMedico/RolesController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-user-tag"></span> <samp class="text">Roles</samp>
                            </button>
                            <input type="hidden" name="action" value="allRoles">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li> 

                    <%}%>

                    <%if (rol == 1) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/MedicosController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-stethoscope"></span> <samp class="text">Medicos</samp>
                            </button>
                            <input type="hidden" name="action" value="allMedicos">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>

                    <%if (rol==1||rol==2||rol==3) {%>
                    <li>
                        <form method="post" action="/SistemaMedico/CitasController" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-calendar-day"></span> <samp class="text">Citas
                                </samp>
                            </button>
                            <input type="hidden" name="action" value="allCitas">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>
                    
                    <%if (rol == 1 || rol == 3) {%>
                    <li>
                        <form method="get" action="/SistemaMedico/ListServlet" class="opc-cont active">
                            <button type="submit" class="opc">
                                <span class="las la-file-medical-alt"></span> <samp class="text">Receta
                                </samp>
                            </button>
                            <input type="hidden" name="action" value="listarCategorias">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                    <%}%>
                    
                    <li>
                        <form method="post" action="/SistemaMedico/" class="opc-cont">
                            <button type="submit" class="opc">
                                <span class="las la-door-open"></span> <samp class="text">Salir
                                </samp>
                            </button>
                            <input type="hidden" name="action" value="allCitas">
                            <input type="text" name="usuario" value="<%out.println(nom);%>" style="display:none">
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        
         <div class="main-content">
            <header>
                <label for="menu-toggle" class="menu-toggler">
                    <span class="las la-bars"></span>
                </label>
                <div class="search">
                    <span class="las la-search"></span>
                    <input type="search" placeholder="Enter keyword">
                </div>
                <div class="header-icons">
                    <span class="las la-bell"></span>
                    <span class="las la-bookmark"></span>
                </div>
            </header>
            
            <main>
                
           
        <div align="center" class="tab">
        <h1>Tabla con datos del paciente</h1>
        <table border=1 class="center"  ALIGN="center" bottom:>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                 <th>Fecha consulta</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listCategory}" var="category">
                <tr>
                    <td><c:out value="${category.id}" /></td>
                    <td><c:out value="${category.name}" /></td>
                     
                    <td><c:out value="${category.fec}" /></td>
                    
                                       
                </tr>
            </c:forEach>
        </tbody>
        </table>
       
            <h2>ListBox con nombre del paciente y fecha de la cita</h2>
                <form action="ReportsController" method="post" >
                    <input type="hidden" name="action" value="postConParametros">
                    Selecciona:
                    <select name="categoryid">
                        <%
                            //${listCategory} esta ligado a la linea del servlet -> request.setAttribute("listCategory", listCatagory);
                        %>
                        <c:forEach  items="${listCategory}" var="category"> 
                            <% //En HTML, la opción seleccionada está representada por la presencia del atributo seleccionado en el elemento <option> %>                          
                            <option value="${category.id}" ${category.id == selectedCatId ? 'selected="selected"' : ''}>
                                ${category.name}_${category.fec}
                            </option>
                        </c:forEach>
                    </select>
                        
                     
                    <br/><br/>
                    <input type="submit" value="Submit" />
                </form>
            <!--
            <select name="categories">
                <option value="category_id">name</option>
                <option value="category_id">name</option>
                <option value="category_id">name</option>
            </select>
            -->
        </div>
                
            </main>
            
            
        </div>
        <label class="close-mobile-menu" for="menu-toggle"></label>
    </body>

</html>