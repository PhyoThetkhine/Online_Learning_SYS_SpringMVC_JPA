<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Details</title>
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
        /* Card Styles */
        .card {
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            border-radius: 10px;
        }
        .card img {
            border-radius: 10px 10px 0 0;
            object-fit: cover;
            max-height: 300px;
        }
        .card-body {
            padding: 20px;
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
    </style>
</head>
<body>
  <div class="sidebar">
    <h2><i class="fas fa-user-graduate"></i> Student Dashboard</h2>
    <ul>
        <li>
            <a href="/Learing_management/StudentHomeServlet">
                <i class="fas fa-tachometer-alt"></i> My Course
            </a>
        </li>
       <li>
            <a href="/Learing_management/StudentClassWorkServlet?courseId=${course.id}">
                <i class="fas fa-chalkboard-teacher"></i>  Class Work
            </a>
        </li>
        <li><a href="/Learing_management/StudentAssignment?courseId=${course.id}"><i class="fas fa-book"></i>Assignment</a></li>
    <li>
			<a href="/Learing_management/Logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
    </ul>
</div>

<!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Course Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/Learning_management/Stream?courseId=${course.id}">
                            <i class="fas fa-stream"></i> Stream
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/Learning_management/StudentClassWorkServlet?courseId=${course.id}">
                            <i class="fas fa-chalkboard-teacher"></i> Class Work
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/Learning_management/People?courseId=${course.id}">
                            <i class="fas fa-users"></i> People
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>


    
    <!-- Main Content -->
    <div class="main-content">
        <div class="header">
            <h1><i class="fas fa-book-open"></i> Course Details</h1>
        </div>
        
        <!-- Course Details Card -->
        <div class="card">
            <img src="${course.photoUrl}" alt="${course.title}" class="card-img-top">
            <div class="card-body">
                <h3><i class="fas fa-info-circle"></i> ${course.title}</h3>
                <p>${course.description}</p>
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
        </div>

       <h3 class="mt-4"><i class="fas fa-users"></i> Teachers</h3>
<ul class="list-group">
    <c:forEach var="assignedTeacher" items="${assignedTeachers}">
        <li class="list-group-item">
            <div style="display: flex; align-items: center; justify-content: space-between;">
                <!-- Left side: Icon and Teacher's Name -->
                <div style="display: flex; align-items: center;">
                    <i class="fas fa-user" style="margin-right: 10px;"></i>
                    <span>${assignedTeacher.name}</span>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>

		
		        <h3 class="mt-4"><i class="fas fa-users"></i> Classmates</h3>
			<ul class="list-group">
			    <c:forEach var="enrolledStudent" items="${enrolledStudents}">
			        <li class="list-group-item">
			            <div style="display: flex; align-items: center; justify-content: space-between;">
			                <!-- Left side: Icon and Student's Name -->
			                <div style="display: flex; align-items: center;">
			                    <i class="fas fa-user" style="margin-right: 10px;"></i>
			                    <span>${enrolledStudent.name}</span>
			                </div>
			            </div>
			        </li>
			    </c:forEach>
			</ul>
		    
    </div>

    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
