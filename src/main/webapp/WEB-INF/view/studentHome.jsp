
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }
        /* Sidebar Styles */
        .sidebar {
            width: 250px;
            background-color: #42627F;
            color: white;
            height: 100vh;
            padding: 20px 0;
            position: fixed;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .sidebar h2 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.5em;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
            width: 100%;
        }
        .sidebar ul li {
            padding: 15px 20px;
            margin: 5px 0;
            cursor: pointer;
            display: flex;
            align-items: center;
        }
        .sidebar ul li:hover {
            background-color: #34495e;
        }
        .sidebar ul li a {
            text-decoration: none;
            color: white;
            display: flex;
            align-items: center;
            width: 100%;
        }
        .sidebar ul li a i {
            margin-right: 10px;
            font-size: 1.2em;
        }
        .main-content {
            margin-left: 250px;
            padding: 20px;
            width: 100%;
        }
        .header {
            background-color: #ecf0f1;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .card {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        .list-group-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }
        .list-group-item i {
            color: #2c3e50;
        }
        .btn i {
            margin-right: 5px;
        }
         .course-container {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .course-box {
            background-color: #f8f9fa;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 300px;
            padding: 15px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .course-box h3 {
            font-size: 1.5em;
            margin-bottom: 10px;
        }
        .course-box p {
            font-size: 0.9em;
            color: #666;
            margin-bottom: 15px;
        }
        .course-box .btn {
            background-color: #007bff;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            border-radius: 5px;
        }
        .course-box .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    
       <div class="sidebar">
    <h2><i class="fas fa-user-graduate"></i> Student Dashboard</h2>
    <ul>
        <li>
            <a href="/LearingSYSLMS/Student/home">
                <i class="fas fa-tachometer-alt"></i> My Course
            </a>
        </li>
          <li>
    <a href="#" data-bs-toggle="modal" data-bs-target="#changePasswordModal">
        <i class="fas fa-key"></i> Change Password
    </a>
</li>
		<li>
			<a href="/LearingSYSLMS/logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
    </ul>
</div>
    <div class="main-content">
        <div class="header">
            <h1>Welcome to LMS Student Dashboard</h1>
        </div>
        	<c:if test="${not empty error}">
    <div id="errorAlert" class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>
<c:if test="${not empty success}">
    <div id="errorAlert" class="alert alert-success" role="alert">
        ${success}
    </div>
</c:if>
        <section id="courses">
           <div class="course-container">
            <!-- Iterate over the course list -->
            <c:forEach var="course" items="${courseList}">
    <a href="studentCourseDeatail?courseId=${course.id}" class="text-decoration-none text-dark">
    <div class="course-box">
      <img src="${course.photoUrl}" alt="${course.title}" class="img-fluid rounded" style="max-height: 200px; width: 100%; object-fit: cover;">
        <h3>${course.title}</h3>
        <p>${course.description.length() > 100 ? course.description.substring(0, 100) + "..." : course.description}</p>
        <p>
            <strong>Status:</strong> 
            <span class="badge 
                <c:choose>
                    <c:when test="${course.status == 'ACTIVE'}">bg-success text-white</c:when>
                    <c:when test="${course.status == 'COMPLETED'}">bg-secondary text-white</c:when>
                    <c:otherwise>bg-warning text-dark</c:otherwise>
                </c:choose>">
                ${course.status}
            </span>
        </p>
    </div>
</a>
</c:forEach>

        </div>
            
        </section>
        <div class="modal fade" id="changePasswordModal" tabindex="-1" aria-labelledby="changePasswordModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="changePasswordModalLabel">Change Password</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="changePassword" method="POST">
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="currentPassword" class="form-label">Current Password</label>
                        <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                    </div>
                    <div class="mb-3">
                        <label for="newPassword" class="form-label">New Password</label>
                        <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">Change Password</button>
                </div>
            </form>
        </div>
    </div>
</div>
       
    </div>
  
    <script>
    // Hide the error message after 5 seconds (5000 milliseconds)
    window.onload = function() {
        setTimeout(function() {
            var errorAlert = document.getElementById('errorAlert');
            if (errorAlert) {
                errorAlert.style.display = 'none';
            }
        }, 5000);  // Adjust the duration as needed
    }
    
    // Function to open the Edit Teacher modal and populate it with teacher data
    

</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


