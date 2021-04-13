<%-- 
    Document   : index
    Created on : 13 abr. 2021, 1:19:24
    Author     : 52777
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->
        <link rel="shortcut icon" href="./images/favicon.ico" type="image/x-icon" />

        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" />

        <!-- AOS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.css" />

        <!-- Glidejs -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.core.css" />


        <!-- Custom StyleSheet -->
        <link rel="stylesheet" href="css/styles.css">

        <title>UAEM | Medical System</title>
    </head>

    <body>
        <!-- Header -->
        <header id="home" class="header">
            <!-- Navigation -->
            <nav class="nav">
                <div class="navigation container">
                    <div class="logo">
                        <h1>Uaem<span>.</span></h1>
                    </div>

                    <div class="menu">
                        <div class="top-nav">
                            <div class="logo">
                                <h1>Uaem<span>.</span></h1>
                            </div>
                            <div class="close">
                                <i class="fas fa-times"></i>
                            </div>
                        </div>

                        <ul class="nav-list">
                            <li class="nav-item">
                                <a href="#home" class="nav-link scroll">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a href="#about" class="nav-link scroll">Nostros</a>
                            </li>
                            <li class="nav-item">
                                <a href="#services" class="nav-link scroll">Servicios</a>
                            </li>
                            <li class="nav-item">
                                <a href="login/login.jsp" class="nav-link">Login</a>
                            </li>
                        </ul>
                    </div>

                    <div class="hamburger">
                        <i class="fas fa-bars"></i>
                    </div>
                </div>
            </nav>

            <!-- Hero -->
            <div class="hero">
                <h3>Hola Bienvenidos al</h3>
                <h1>Centro Medico</h1>
                <h4>Servicios:<span id="type1"></span></h4>
                <a href="#">Saber Más</a>
            </div>

            <img class="banner" src="./images/bg.png" alt="" />

            <div class="icons">
                <span><i class="fab fa-facebook-f"></i></span>
                <span><i class="fab fa-instagram"></i></span>
                <span><i class="fab fa-twitter"></i></span>
            </div>
        </header>

        <!-- Main -->
        <section class="section about" id="about">

            <div class="title">
                <h1>Nostros</h1>
            </div>

            <div class="about-center container">
                <div class="left" data-aos="fade-right" data-aos-duration="2000">
                    <img src="./images/6.jpg" alt="" />
                </div>
                <div class="right" data-aos="fade-left" data-aos-duration="2000">
                    <h1>Services:<span id="type2"></span></h1>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Maiores placeat inventore rerum tempore cupiditate, amet adipisci aut eius facilis quisquam magni ratione maxime officiis earum blanditiis magnam illum obcaecati vero? Odit ipsa facilis officiis
                        fuga voluptate similique sunt exercitationem voluptatibus, illo et fugit eveniet provident quo atque, nesciunt explicabo tenetur!
                    </p>
                    <!-- <a href="#" class="btn">Download CV</a> -->
                </div>
            </div>
        </section>

        <!-- Services -->
        <section class="section theme" id="services">
            <div class="title">
                <h1>Services</h1>
            </div>

            <div class="services-center container">

                <div class="service" data-aos="fade-down" data-aos-duration="2000">
                    <span><i class="fas fa-file-medical-alt"></i></span>
                    <h2>Certificado</h2>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore exercitationem numquam porro asperiores neque architecto.
                    </p>
                    <a href="#" class="btn">Leer Más</a>
                </div>

                <div class="service" data-aos="fade-up" data-aos-duration="2000">
                    <span><i class="fas fa-user-md"></i></span>
                    <h2>Cita</h2>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore exercitationem numquam porro asperiores neque architecto.
                    </p>
                    <a href="#" class="btn">Leer Más</a>
                </div>

                <div class="service" data-aos="fade-down" data-aos-duration="2000">
                    <span><i class="fas fa-notes-medical"></i></span>
                    <h2>Comprobantes</h2>
                    <p>
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolore exercitationem numquam porro asperiores neque architecto.
                    </p>
                    <a href="#" class="btn">Leer Más</a>
                </div>
            </div>
        </section>
        <!-- Teams -->
        <section class="section theme" id="teams">

            <div class="title">
                <h1>Facultades</h1>
            </div>

            <div class="teams-center container" data-aos="fade-right" data-aos-duration="2000">
                <div class="glide">
                    <div class="glide__track" data-glide-el="track">
                        <ul class="glide__slides">
                            <li class="glide__slide">
                                <div class="team">
                                    <div class="img-cover">
                                        <img src="./images/6.jpg" alt="" />
                                    </div>
                                    <h3>FCAEI</h3>
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero, quo?
                                    </p>
                                </div>
                            </li>
                            <li class="glide__slide">
                                <div class="team">
                                    <div class="img-cover">
                                        <img src="./images/6.jpg" alt="" />
                                    </div>
                                    <h3>FCAEI</h3>
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero, quo?
                                    </p>
                                </div>
                            </li>
                            <li class="glide__slide">
                                <div class="team">
                                    <div class="img-cover">
                                        <img src="./images/6.jpg" alt="" />
                                    </div>
                                    <h3>FCAEI</h3>
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero, quo?
                                    </p>
                                </div>
                            </li>
                            <li class="glide__slide">
                                <div class="team">
                                    <div class="img-cover">
                                        <img src="./images/6.jpg" alt="" />
                                    </div>
                                    <h3>FCAEI</h3>
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero, quo?
                                    </p>
                                </div>
                            </li>
                            <li class="glide__slide">
                                <div class="team">
                                    <div class="img-cover">
                                        <img src="./images/6.jpg" alt="" />
                                    </div>
                                    <h3>FCAEI</h3>
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero, quo?
                                    </p>
                                </div>
                            </li>
                            <li class="glide__slide">
                                <div class="team">
                                    <div class="img-cover">
                                        <img src="./images/6.jpg" alt="" />
                                    </div>
                                    <h3>FCAEI</h3>
                                    <p>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero, quo?
                                    </p>

                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>

        <footer>
            <p>Created By <span>luis</span> | &copy; 2021 All rights reserved.</p>
        </footer>

        <!-- AOS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/aos/2.3.4/aos.js"></script>
        <!-- Typeit -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/typeit/7.0.4/typeit.min.js"></script>
        <!-- GSAP -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.5.1/gsap.min.js"></script>
        <!-- Glidejs -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/glide.min.js"></script>

        <!-- Custom Script -->
        <script src="js/scripts.js"></script>
    </body>

</html>
