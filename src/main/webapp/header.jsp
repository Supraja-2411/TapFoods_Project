<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tapfoods.model.User,java.util.ArrayList,com.tapfoods.model.Restaurant" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header Footer</title>
</head>

<style>
	html, body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  overflow-x: hidden;
}

*,
*::before,
*::after {
  box-sizing: inherit;
}
.container-fluid {
    background: black;
    padding: 5px;
    box-shadow: 0px 5px 20px rgba(255, 75, 43, 0.4);
    
}

nav.navbar.navbar-expand-lg.border-bottom.border-body {
    padding: 0px;
}
span.navbar-brand {
   font-family: "Smokum", serif;
   font-weight: bold;
   font-size: 2rem;
   padding-left: 20px;
   background: linear-gradient(45deg, #ff7e5f, #feb47b, #ff416c, #ff4b2b);
 	-webkit-background-clip: text;
  	-webkit-text-fill-color: transparent;
   
}
form.d-flex {
    padding-right: 70px;
   
}
form.d-flex {
    width: 40%;
}
.navbar li {
    margin-left: 5px;
    margin-right: 5px;
    list-style-type: none;
    padding: 5px 10px;
    position: relative;
    font-weight: bold;
}

li.nav-item {
    padding: 0px 15px;
}
div#navbarSupportedContent {
    justify-content: flex-end;
}
#active {
	 --bs-nav-link-color: #ef7a8e ;
	 padding-left: 15px;
}

#active::after {
    content: '';
    position: absolute;
    width: 50%;
    height: 2px;
    bottom: 0;
    left: 25%;
    background-color: #ef7a8e;
    visibility: visible;
    transform: scaleX(1);
}
.navbar-nav {
    --bs-nav-link-color: #ef7a8e;
    --bs-nav-link-hover-color: white;
}

.nav-link::after {
            content: '';
            position: absolute;
            width: 50%;
            height: 2px;
            bottom: 0;
            left: 25%;
            background-color: #ef7a8e;
            visibility: hidden;
            transform: scaleX(0);
            transition: all 0.3s ease-in-out;
        }
.nav-link:hover::after {
	visibility: visible;
	transform: scaleX(1);
}

.btn-outline-danger {
      border-color: #ef7a8e;
      color: #ef7a8e;
 }
        
	

</style>
<body>

<% User loggedInUser = (User)session.getAttribute("UserLoginDetails");
	%>
	

<nav class="navbar navbar-expand-lg border-bottom border-body fixed-top " data-bs-theme="dark">
			  <div class="container-fluid">
			   <span class="navbar-brand mb-0 h1">TapFoods<i class="fas fa-drumstick-bite fa-xs" style="color: #ff416c;"></i></span>
			    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			      <span class="navbar-toggler-icon"></span>
			    </button>
			    
			    <div class="collapse navbar-collapse" id="navbarSupportedContent">
			    <form class="d-flex" role="search" action="searchServlet" method="post">
		        <input class="form-control  me-2" type="text" name="menuName" placeholder="Search for menu item..." aria-label="Search">
		        <button class="btn btn-outline-danger" type="submit" value="search"><i class="fas fa-search"></i></button>
		      </form>
			      <ul class="navbar-nav  mb-2 mb-lg-0 ">
			        <li class="nav-item">
			          <a class="nav-link active" id ="active" aria-current="page" href="home.jsp">Home <i class="fas fa-utensils" ></i></a>
			        </li>
			<%	if(loggedInUser!=null) {  %>
					
		        <li class="nav-item">
		          <a class="nav-link" href="cart.jsp">Cart <i class="fas fa-store"></i></a>
		        </li>
		        
		        <li class="nav-item">
		          <a class="nav-link " href="orderHistoryServlet">View History <i class="fas fa-eye"></i></a>
		        </li>
		        <li class="nav-item">
		          <a class="nav-link logout-btn" href="#">Logout <i class="fas fa-sign-out-alt"></i></a>
		        </li>
		      
		      <%}
				else {%>
					 <li class="nav-item text-danger">
		              <a class="nav-link " href="login.jsp">SignIn <i class="fas fa-sign-in-alt"></i></a>
		            </li>
		            <li class="nav-item">
		              <a class="nav-link " href="signup.jsp">SignUp <i class="fas fa-user-plus"></i></a>
		            </li>
		            <%} %>
		      </ul>
		      
		    </div>
		  </div>
		</nav>
		
		
		
		<script type="text/javascript">
			const logoutBtn = document.querySelector(".logout-btn")
			
			logoutBtn.addEventListener("click",()=> {
				window.location.replace("home.jsp")
			})
		
		
		</script>

</body>
</html>