<%-- 
    Document   : register
    Created on : Jan 25, 2021, 10:02:44 PM
    Author     : thant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/1acc75252a.js" crossorigin="anonymous"></script>

    <style>
        html {
            font-family: "Cairo", sans-serif !important;
            -webkit-font-smoothing: antialiased;
        }

/*        :root {
            --input-padding-x: 1.5rem;
            --input-padding-y: 0.75rem;
        }*/


        .image {
            min-height: 100vh;
        }

        .bg-image {
          
            background-size: cover;
            background-position: center;
            padding: 20px;
        }

        .login-heading {
				font-weight: 300;
			}

			.btn-login {
				font-size: 0.9rem;
				letter-spacing: 0.05rem;
				padding: 0.75rem 1rem;
				border-radius: 2rem;
			}

			.form-label-group {
				position: relative;
				margin-bottom: 1rem;
			}

			.form-label-group>input,
			.form-label-group>label {
				/*				padding: var(--input-padding-y) var(--input-padding-x);*/
				border-radius: 2rem;
				height: 47px;
				padding-top: 12px;
				padding-bottom: 12px;

			}

			.form-label-group>label {
				position: absolute;
				top: 0;
				left: 0;
				display: block;
				width: 100%;
				margin-bottom: 0;
				/* Override default `<label>` margin */
				line-height: 1.5;
				color: #495057;
				cursor: text;
				/* Match the input under the label */
				border: 1px solid transparent;
				border-radius: .25rem;
				transition: all .1s ease-in-out;
			}

        .or-container {
            align-items: center;
            color: #ccc;
            display: flex;
            margin: 25px 0
        }

        .line-separator {
            background-color: #ccc;
            flex-grow: 5;
            height: 1px
        }

        .or-label {
            flex-grow: 1;
            margin: 0 15px;
            text-align: center
        }

        #back,
        .back-text {
            font-family: Verdana, Geneva, Tahoma, sans-serif;
            font-size: 30px;
            color: #7dc129;
            text-decoration: none;
        }

        #back,
        .back-text:hover {
            font-family: Verdana, Geneva, Tahoma, sans-serif;
            text-decoration: none;
            color: #7dc129;
        }
    </style>
</head>

<body>

    
    <div class="container-fluid shadow-lg" style="max-width: 60%;margin-top: 160px;border-radius: 10px;">
        <div class="row no-gutter" style="height: 600px;">
            <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image">
                <img src="./undraw_welcome_cats_thqn.png" style="max-width: 700px;max-height: 300px;margin-top: 120px;" alt="">
            </div>
            <div class="col-md-8 col-lg-6">
                <div class="login d-flex align-items-center py-5">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-9 col-lg-8 mx-auto" style="padding-top: 0px;">
                                <h3 class="login-heading mb-4">Hi!<br>Register new Account here!</h3>
                              
								<form action="RegisterController" method="POST">
                                    <div class="form-label-group">
										<c:if test="${sessionScope.userAccount_re ==null}">
											<input name="userId" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>                         
										</c:if> 
											<c:if test="${sessionScope.userAccount_re !=null}">
												<input name="userId" value="${sessionScope.userAccount_re}" type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>                         
										</c:if>
										<p style="color: red">${sessionScope.userId_ERROR_re}</p>
									</div>
                                    <div class="form-label-group">
                                        <input name="name" type="text" id="inputEmail" class="form-control" placeholder="Full Name" required autofocus>                                  
										<p style="color: red">${sessionScope.name_ERROR_re}</p>	
									</div>
                                    <div class="form-label-group">
                                        <input name="password" type="password" id="inputPassword" class="form-control"
                                            placeholder="Password" required>                                      
                                    <p style="color: red">${sessionScope.password_ERROR_re}</p>	
									</div>
                                    <div class="form-label-group">
                                        <input name="repassword" type="password" id="inputPassword" class="form-control"
                                            placeholder="Re Password" required>  
										 <p style="color: red">${sessionScope.repassword_ERROR_re}</p>	
                                    </div>
                                    <input
                                        class="btn btn-lg btn-success btn-block btn-login text-uppercase font-weight-bold mb-2"
                                        type="submit">
                                    <div class="or-container">
                                        <div class="line-separator"></div>
                                        <div class="or-label"><span style="color: #7dc129;">or</span></div>
                                        <div class="line-separator"></div>
                                    </div>
                                    <div style="display: flex; justify-content: center;">
                                        <span style="margin-right: 3px;">You have a Account?</span>
                                       <a style="color: #7dc129;" href="Login_Page_Controller">Sign In</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>



    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
        integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
        crossorigin="anonymous"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script>
        function bigImg() {
            document.getElementById('back').innerHTML = 'Back';
        }
        function normalImg() {
            document.getElementById('back').innerHTML = '';

        }
    </script>
</body>

</html>