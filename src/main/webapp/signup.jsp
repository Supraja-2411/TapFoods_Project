<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign-Up Page</title>
<link href="https://fonts.googleapis.com/css2?family=Salsa&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<link rel="stylesheet" href="signupStyle.css">   
</head>
<body>
	 <div class="register-form">
        <div class="icon">
            <i class="fas fa-user-plus"></i>
        </div>
       
        <form action="userRegister" class="form" method="post">
            <div class="input-group">
                <i class="fas fa-user"></i>
                <input type="text" required name="userName" placeholder="Name">
            </div>
            
            <div class="input-group">
                <i class="fas fa-envelope"></i>
                <input type="email" required name="email" placeholder="Email">
            </div>
            
            <div class="input-group">
                <i class="fas fa-phone"></i>
                <input type="number" required name="phonenumber" placeholder="Phone Number">
            </div>
            
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" required name="password" placeholder="Password">
            </div>
            
            <div class="input-group">
                <i class="fas fa-lock"></i>
                <input type="password" required name="cpassword" placeholder="Confirm Password">
            </div>
            
            <div class="input-group">
                <i class="fas fa-home"></i>
                <input type="text" required name="address" placeholder="Address">
            </div>

            <input type="submit" value="Register" class="button">
            <h4>Already have an Account? <a href="login.jsp">Login</a></h4>
        </form>
    </div>
			
</body>
</html>