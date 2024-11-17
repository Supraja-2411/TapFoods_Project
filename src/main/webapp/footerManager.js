

class SpecialFooter extends HTMLElement {
	connectedCallback() {
		this.innerHTML = `
		   <div class="footer border-top border-dark border-opacity-50 " >
		   
		    <div class="container">
		        <!-- App Download Section -->
		        <div class="row mb-4">
		            <div class="col-12">
		                <p class="mb-3 fs-5">For better experience, download the Tapfoods app now</p>
		                <div class="app-buttons">
		                <img src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" alt="Get it on Google Play">
		                    <img src="https://developer.apple.com/app-store/marketing/guidelines/images/badge-example-preferred.png" alt="Download on the App Store">
		                    
		                </div>
		            </div>
		        </div>




		        <!-- Main Footer Content -->
		        <div class="row">
		            <!-- Company Info -->
		            <div class="col-12 col-md-3 mb-4">
		            	<h4 class="brand"> TapFoods<i class="fas fa-drumstick-bite fa-xs" style="color: #ff416c;"></i></h4>
		               
		                <p class="copyright">© 2024 TapFoods Limited</p>
		            </div>

		            <!-- Company Links -->
		            <div class="col-6 col-md-2 mb-4">
		                <h5>Company</h5>
		                <ul>
		                    <li><a href="#">About Us</a></li>
		                    <li><a href="#">Careers</a></li>
		                    <li><a href="#">TapFoods Corporate</a></li>
		                    <li><a href="#">Team</a></li>
		                </ul>
		            </div>

		            <!-- Contact Us -->
		            <div class="col-6 col-md-2 mb-4">
		                <h5>Contact Us</h5>
		                <ul>
		                    <li><a href="helpPage.html">Help & Support</a></li>
		                    <li><a href="#">Partner with us</a></li>
		                    <li><a href="#">Ride with us</a></li>
		                </ul>
		                
		                <h5 class="mt-4">Legal</h5>
		                <ul>
		                    <li><a href="#">Terms & Conditions</a></li>
		                    <li><a href="#">Cookie Policy</a></li>
		                    <li><a href="#">Privacy Policy</a></li>
		                </ul>
		            </div>

		            <!-- Available Cities -->
		            <div class="col-6 col-md-2 mb-4">
		                <h5>Available in:</h5>
		                <ul>
		                    <li><a href="#">Bangalore</a></li>
		                    <li><a href="#">Chennai</a></li>
		                    <li><a href="#">Hyderabad</a></li>
		                    <li><a href="#">Delhi</a></li>
		                    <li><a href="#">Mumbai</a></li>
		                    <li><a href="#">AndhraPradesh</a></li>
		                </ul>
		               
		            </div>

		            <!-- Life at Swiggy -->
		            <div class="col-6 col-md-2 mb-4">
		                <h5>Life at TapFoods</h5>
		                <ul>
		                    <li><a href="#">Explore with TapFoods</a></li>
		                    <li><a href="#">TapFoods News</a></li>
		                </ul>

		                <h5 class="mt-4">Social Links</h5>
		                <div class="social-links">
		                    <a href="#"><i class="fab fa-linkedin"></i></a>
		                    <a href="#"><i class="fab fa-instagram"></i></a>
		                    <a href="#"><i class="fab fa-facebook"></i></a>
		                    <a href="#"><i class="fab fa-pinterest"></i></a>
		                    <a href="#"><i class="fab fa-twitter"></i></a>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>
			    
		
		`
	}
		
}

	customElements.define('special-footer',SpecialFooter )