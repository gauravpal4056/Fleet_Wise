<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  
<%
	if(session.getAttribute("user")!=null && session.getAttribute("user").equals("driver") ){
		response.sendRedirect("indexDriver.jsp");	}
	else if(session.getAttribute("user")!=null && session.getAttribute("user").equals("admin")){
		response.sendRedirect("../DriverDashboardServlet");
	}
%>
    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Login</title>
	<script src="https://cdn.tailwindcss.com"></script>
	</head>
	
	<body>
		<div class="App h-screen flex  w-full justify-center items-center ">
	      <section class="bg-gray-50  w-full">
	        <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
	            <a href="#" class="absolute top-0 left-0 m-3 flex items-center mb-6 text-2xl font-semibold text-gray-900 ">
	                <img class="w-8 h-8 mr-2 " src="https://t4.ftcdn.net/jpg/03/69/79/91/360_F_369799193_D7bhKPUKNN3FFvrmp3PqtS6pi1NCGQmJ.jpg" alt="logo" />
	                Fleet Wise    
	            </a>
	            <div class="w-full bg-white  shadow  md:mt-0 sm:max-w-md xl:p-0">
	                <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
	                    
	                    <div class='flex flex-col items-center justify-center'>
	                      <img class="hover:scale-105 transition-all w-12 h-12 mb-2 border-gray-400 rounded-full p-2 border-2 " src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADIAAAAyCAYAAAAeP4ixAAAACXBIWXMAAAsTAAALEwEAmpwYAAADDklEQVR4nN2aSWgUQRSGv2iMCxi9mChIMAEFvag3jRcRT+ICXgU9qAQEL4IaglFEBD244ELAHU8elBxUFMUFNehBY3ABiSjJQUEDmcQFF2Ja3vAGxrJ7equZqeSHH2am61XVF2p5VR0ojhYCR4GXwBe1fD6iz5zXFOAcMAx4AZZnZ4BqHFUt0FkAwPQzoAbHVAk8igGR8xNgHA6pNQFEzi04NC8yKUAGgKk4oKYUEDlvxgG1WwC5jAN6bwHkLQ7omwUQqaPssgEiO3/Z9c4CSDcOqH20TPam0bL8VgP9KSAyrmyIol0pQJpxLGl8mADigcY6pRpNzaNCPHUxjc+fL6eBPwUA5NkpYDIjQAuAw8ALYFAtnw8B88vduVGvWmCNrlwyZG4AXbrj9+jS3K/fu3Vu3ATagJ0aW5a5MhZYCpywlPnmZ8BtwPJSrGQbgQ8WOx/kz8BWoKIYEHtLAOAZPmYbYlnIXVUxvc7m3tBTJghPF4ppNkBOlhHCU8vNZCotDtmlS+VhYElSiPHAawcgPPUbYEISkP0OdN4zvCcuhORDvx3ouGf4FzAvKkSlphGeo34MjIkC0uxAZ70QbwmDmAP8cKCjXoR7sLogiAo9enojxNeDQDZYqLwPuKTJ5SKgHpikrtffNmmZvmKkL9XAxxQVPgfWa4ofVTJhV+lbq6TtfjKvko6nqEgORmm1VlN3L4Gl71nNBYYSVHALmI49zQBuJ+jHkDJkT3pxg68BVQUuHnYDd4Be4Lu6V39rLXABUaWTONG55VXMoPsBOY8kdfdi1HMXaPSpZ2KC1VP+GSH7l4oa8NVn/ZZJezBhlizD4oDPTj0r5nsXOS9lh0nUgG1Gg7JKXUwAYPqCD8z2GPFX0VUn6mtkc0i1WIDIeYfPEBuMGLs6t6N3RCh81mioAfhpEURSIxlS+TofIa4j/7alMcLlwooSnFf2GW2sDCk/7LdgXAkJmmmU7yoCSKfRRl1Ieenzf2rQg0tQkBx98zVQBJCM0Ya0GVRWDn6z/UBEsrEEBZqyDRG3nX8u7/4Ch0H5VbQNWvkAAAAASUVORK5CYII="></img>
	                      <h1 class="text-center text-sm font-bold leading-tight tracking-tight text-gray-500 md:text-lg ">
	                          Log in
	                      </h1>
	                    </div>
	                    <div class="flex items-center justify-around gap-9">
	                      <div id='admin'  class='rounded text-center w-full  border-4 border-gray-300 py-1' >Admin</div>
	                      <div id='driver' class='rounded text-center w-full border-2 border-gray-300  py-1' >Driver</div>
	                    </div>
	                    <form class="space-y-4 md:space-y-6" action="../LoginServlet" method="post">
	                      <div>	                          
	                          <input  name="userName" id="userName" class="bg-gray-100 border-b-2  border-gray-300 text-gray-900 sm:text-sm  focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5   " placeholder="USERNAME" required="true" />
	                      </div>
	                      <input id='type' value='ADMIN'  name="type" class='hidden' />
	                      <div>	                          
	                          <input type="password" name="password" id="password" placeholder="*********" class="bg-gray-100 border-b-2  border-gray-300 text-gray-900 sm:text-sm  focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5   " required="true" />
	                      </div>
	                      <button type="submit" class="hover:shadow-2xl  transition-all text-white bg-black bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-sm text-sm px-9 py-2 text-center   ">Sign in</button>
	                    </form>
	                </div>
	            </div>
	        </div>
	      </section>
	      <div id="bg-hero" class="transition-all  hidden md:block bg-[url('https://images.unsplash.com/photo-1485575301924-6891ef935dcd?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')] bg-cover bg-center w-2/3 h-full"></div>
	    </div>
	    
	    
	    

	    <script>
	      function selectAdmin() {
	        document.getElementById('admin').style.cssText = 'border: 4px solid rgb(209 213 219) '
	        document.getElementById('type').value = 'ADMIN'
	        document.getElementById('driver').style.cssText = 'border: 2px solid rgb(209, 213, 219)'
	      }
	      function selectDriver() {
	        document.getElementById('driver').style.cssText = 'border: 4px solid rgb(209 213 219)'
	        document.getElementById('type').value = 'DRIVER'
	        document.getElementById('admin').style.cssText = 'border: 2px solid rgb(209, 213, 219)'
	      }
	      
	      document.getElementById('admin').addEventListener('click', selectAdmin);
	      document.getElementById('driver').addEventListener('click', () => selectDriver());
	      
	      
	      
	    </script>
	</body>
</html>