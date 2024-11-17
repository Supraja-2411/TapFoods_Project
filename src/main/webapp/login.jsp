<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Salsa&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="loginStyle.css">
</head>
<body>
	<div class="login-container">
     <img src="img/logintop.png" alt="Food Icon" class="food-icon">
     
        <form class="login-form" action="loginServlet" method="post">
        <h2>Sign In <span class="logo-fork"><i class="fas fa-utensils"></i></span></h2>
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" name="email" required placeholder="Email">
            </div>
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" name="password" required placeholder="Password">
            </div>
            <button type="submit" class="login-btn">Sign In</button>
        </form>
        
        <!-- Forgot Password Link -->
        <div class="forgot-password-link">
            <a href="forgotpassword.jsp">Forgot your Password?</a>
        </div>
        
        <!-- Signup Link -->
        <div class="signup-link">
            <span>Don't have an account? </span>
            <a href="register.jsp"> Sign up</a>
        </div>
        
    </div>
	


</body>
</html>