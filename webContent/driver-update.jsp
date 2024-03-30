<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Driver" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Driver Details</title>
    <script>
        function toggleEdit() {
            var editButton = document.getElementById('editButton');
            var updateButton = document.getElementById('updateButton');
            var deleteButton = document.getElementById('deleteButton');
            var inputs = document.getElementsByTagName('input');
            var joiningDateInput = document.getElementById('joiningDate');
            
            if (editButton.value === 'Edit') {
                if (confirm('Do you want to edit this record?')) {
                    editButton.value = 'Cancel';
                    updateButton.style.display = 'inline';
                    deleteButton.style.display = 'inline';
                    for (var i = 0; i < inputs.length; i++) {
                        inputs[i].readOnly = false;
                    }
                    joiningDateInput.readOnly = true; // Prevent editing of joining date
                }
            } else {
                editButton.value = 'Edit';
                updateButton.style.display = 'none';
                deleteButton.style.display = 'none';
                for (var i = 0; i < inputs.length; i++) {
                    inputs[i].readOnly = true;
                }
            }
        }
        
    </script>
    <style>
		@charset "UTF-8";
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
  
  body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    font-size: 14px;

  }
 .form-flex{
    display: flex;
    justify-content: space-between;
    /* background-color: #0056b3; */
 }
 
 input, select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }

 
 .body-form{
    height: 100vh;
    /* display: flex; */
    padding-top: 60px;
   
    /* align-items: center; */
 }
  
 .oc1{
    width: 950px;
 }
  .lp{
    padding-bottom: 5px;
  }
  .container {
   
    max-width: 800px;
    margin: 50px auto;
    padding: 40px;

    background-color: #fff;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  
  
  h2 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 20px;
    width: 45%;
  }
  
  label {
    display: block;
    font-weight: bold;
  }
  
  input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    /* border-radius: 5px; */
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color:black;
    color: #fff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    /* background-color: #cbd0d5;
     */
    background-color: rgb(105, 106, 106);
  }
  
  #error-message {
    color: red;
    font-size: 0.9rem;
    margin-top: 5px;
  }
  
  .hp{
    padding-bottom: 30px;
    /* border-bottom: 1px solid black; */
  }
  .bbp{
    margin-top: 10px;
  }

  @media (max-width: 768px) {

    .form-flex{
      display: block;
     
    }

    .form-group {
      margin-bottom: 20px;
      width: 100%;
    }
   
    }


    /* 
    vehicle  */

    .vehicle-form{
      width: 80%;
      /* background-color: red; */
    }

    .vehicleForm{
      /* background-color: aqua; */
      width: 80%;
    }
    .vehicle-form-con{
      width: 100%;
      display: flex;
      justify-content: center;
    }    
    </style>
</head>
<body>
    <div class="body-form">
    <div class="container oc">
        <h2 class="hp">Update Driver</h2>
        <form id="addDriverForm" action="UpdateDriverServlet" method="post">
        
            <%
            // Retrieve driver object from request attribute
            Driver driver = (Driver) request.getAttribute("drivers");
            
            // Check if driver object is not null
            if (driver != null) {
            %>
        
         <div class="form-flex">
            <div class="form-group">
            	 <input type="hidden" id="driver_Id" name="driver_Id" value="<%= driver.getDriverId() %>">
                <label for="driverName" class="lp">Name</label>
                <input type="text" id="driverName" name="driverName" value="<%= driver.getName() %>"  required placeholder="Name" class="li">
              </div>
              <div class="form-group">
                <label for="driverAge" class="lp">Age</label>
                <input type="number" id="age" name="age" value="<%= driver.getAge() %>" required placeholder="Age" min="18" max="100">
              </div>
         </div>
         
         <div class="form-flex">
            <div class="form-group">
                <label for="driverEmail" class="lp">Email Address </label>
                <input  type="text" id="emailAddress" name="emailAddress"   value="<%= driver.getEmailAddress() %>" required placeholder="Email">
              </div>
              <div class="form-group">
                <label for="driverPhone" class="lp">Phone Number</label>
                <input type="text" id="phoneNumber" name="phoneNumber" value="<%= driver.getPhoneNumber() %>"  required placeholder="Phone number" pattern="[0-9]{10}">
              </div>
         </div>
         <div class="form-flex">
            <div class="form-group">
                <label for="licenceNumber" class="lp">License Number</label>
                <input type="text" id="licenceNumber" name="licenceNumber"  value="<%= driver.getLicenceNo() %>" required placeholder="License number">
              </div>
              <div class="form-group">
                <label for="driverGender" class="lp">Gender</label>
    <select id="gender" name="gender" required>
        <option value="">Select Gender</option>
        <option value="MALE" <%= driver.getGender().equals("MALE") ? "selected" : "" %>>Male</option>
        <option value="FEMALE" <%= driver.getGender().equals("FEMALE") ? "selected" : "" %>>Female</option>
        <option value="OTHER" <%= driver.getGender().equals("OTHER") ? "selected" : "" %>>Other</option>
    </select>
              </div>
         </div>
         
         <div class="form-group">
            <label for="driverAddress" class="lp">Address</label>
            <textarea id="address" name="address" required placeholder="Address" class="li"><%= driver.getAddress() %></textarea>
          </div>
              
          
          <button type="submit" id="updateButton" name="action" value="Update" >Update Details</button>
        </form>
        <div id="message"></div>
      </div>
        <% } %>
 </div>
  <script src="add-driver.js"></script>
</body>
</html>