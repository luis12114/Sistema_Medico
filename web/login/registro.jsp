<%-- 
    Document   : newjsp
    Created on : 13 abr. 2021, 1:24:04
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User</title>
        <link rel="stylesheet" type="text/css" href="/SistemaMedico/css/loginStyle.css"/>
        <link rel="stylesheet" href="https://maxst.icons8.com/vue-static/landings/line-awesome/line-awesome/1.3.0/css/line-awesome.min.css">

    </head>

    <body>
        <section>

            <div class="container ">


                <div class="user signinBx">

                    <div class="imgBx"><img src="/SistemaMedico/images/create.jpg" alt=""/></div>


                    <div class="formBx">

                        <form method="post" action="/SistemaMedico/controlAdmin" enctype="multipart/form-data">

                            <h2>Create an account</h2>

                            <input type="hidden" name="action" value="register-user">

                            <input type="text" placeholder="Username" name="usuario" required>
                            <input type="email" placeholder="Email Asddress" name="email" required>
                            <input type="password" placeholder="Create Password" name="password" required>
                            <input type="file" name="file" required>
                            <input type="text" placeholder="" name="id_role" value="2" style="display:none;">
                            <input type="submit" value="Sign Up">

                            <p class="signup">
                                Already have an account?
                                <a href="${pageContext.request.contextPath}/login/login.jsp" onclick="">Sign in</a>
                            </p>
                            
                            <%
                                String msg = (String) session.getAttribute("mensaje");
                                if (!(msg == null)) {
                            %>
                            <p style="color:white; padding-top:10px; padding-bottom: 10px; padding-left: 50px; padding-right: 50px; background-color: red; font-size:14px; margin-top:10px; "><i class="las la-times" style="color:white;margin-right:3px; font-size:14px;"></i><%out.println(msg);%></p>
                            <%
                                  session.setAttribute("mensaje", null);
                               }
                           %>
                        </form>

                    </div>
                </div>
            </div>
            <script>
                function toggleForm() {
                    section = document.querySelector('section');
                    container = document.querySelector('.container');
                    container.classList.toggle('active');
                    section.classList.toggle('active');
                }
            </script>

        </section>
    </body>

</html>
