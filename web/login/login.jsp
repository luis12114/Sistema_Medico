<%-- 
    Document   : newjsp
    Created on : 13 abr. 2021, 1:24:04
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>User</title>
        <link href="../css/loginStyle.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <section>

            <div class="container ">

                <div class="user signinBx">

                    <div class="imgBx"><img src="../images/login-imagen.jpg" alt=""/></div>
                    

                    <div class="formBx">

                        <form action="">
                            <h2>Sign In</h2>
                            <input type="text" placeholder="Username">
                            <input type="password" placeholder="Password">
                            <input type="submit" value="Login">
                            <p class="signup">
                                don't have an account?
                                <a href="#" onclick="toggleForm();">Sign up</a>
                            </p>
                        </form>

                    </div>

                </div>

                <div class="user signupBx ">



                    <div class="formBx">

                        <form action="">
                            <h2>Create an account</h2>
                            <input type="text" placeholder="Username">
                            <input type="email" placeholder="Email Asddress">
                            <input type="password" placeholder="Create Password">
                            <input type="password" placeholder="Confirm Password">
                            <input type="submit" value="Sign Up">
                            <p class="signup">
                                Already have an account?
                                <a href="#" onclick="toggleForm();">Sign in</a>
                            </p>
                        </form>

                    </div>

                    <div class="imgBx"><img src="../images/create-imagen.jpg" alt=""/></div>
                    
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
