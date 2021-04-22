<%-- 
    Document   : error
    Created on : 15 abr. 2021, 8:56:16
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
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="/SistemaMedico/css/error-style.css"/>
    </head>

    <body>
        <div id="container">
            <div class="content">
                <h2>404</h2>
                <h4>Opps! Page not found</h4>
                <p>
                    The page you were looking for doesn't exist. You may have mistyped the address or the page may have moved
                </p>
                <a href="http://localhost:8080/SistemaMedico/">Back To Home</a>
            </div>
        </div>
        <script>
            var container = document.getElementById('container');
            window.onmousemove = function (e) {
                var x = -e.clientX / 5,
                        y = -e.clientY / 5;
                container.style.backgroundPositionX = x + 'px';
                container.style.backgroundPositionY = y + 'px';
            }
        </script>
    </body>

</html>
