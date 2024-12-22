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
            background-color: #8b5e3c;
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
        /* Navbar Styles */
        .navbar {
            position: fixed;
             background-color: #ecf0f1;
            top: 0;
            left: 250px; /* Matches sidebar width */
            width: calc(100% - 250px);
            z-index: 1000;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        /* Main Content */
        .main-content {
            margin-left: 250px; /* Matches sidebar width */
            margin-top: 70px; /* Space for navbar */
            padding: 20px;
            width: calc(100% - 250px);
        }
        .header {
            background-color: #ecf0f1;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        /* List Styling */
        .list-group-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            border-radius: 5px;
        }
        .list-group-item:hover {
            background-color: #f8f9fa;
        }
        .list-group-item i {
            color: #2c3e50;
        }
        /* Button Styling */
        .btn i {
            margin-right: 5px;
        }
    </style>
</head>
<body>
   <div class="sidebar">
    <h2><i class="fas fa-user-shield"></i> Teacher Dashboard</h2>
    <ul>
        <li>
            <a href="/LearingSYSLMS/Teacher/home">
                <i class="fas fa-tachometer-alt"></i> Dashboard
            </a>
        </li>
		<li>
			<a href="/LearingSYSLMS/logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
    </ul>
</div>
      <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/LearingSYSLMS/Teacher/teacherCourseDetails?courseId=${course.id}">
                                <i class="fas fa-stream"></i> Stream
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/LearingSYSLMS/Teacher/teacherCoursePeople?courseId=${course.id}">
                                <i class="fas fa-users"></i> People
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    
    <!-- Main Content -->
    <div class="main-content">
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
   
        <!-- Assigned Teachers -->
        <h3 class="mt-4"><i class="fas fa-users"></i>Teachers</h3>
        <ul class="list-group">
            <c:forEach var="assignedTeacher" items="${assignedTeachers}">
                <li class="list-group-item">
                    <div style="display: flex; align-items: center;">
                        <i class="fas fa-user"></i>
                        <span style="margin-left: 10px;">${assignedTeacher.teacher.name}
                        <c:if test="${assignedTeacher.teacher.id == loginuser.id}">
                        <span style="color: green;">(You)</span>
                    </c:if>
                        </span>
                    </div>
                </li>
            </c:forEach>
        </ul>

        <!-- Enrolled Students -->
        <h3 class="mt-4"><i class="fas fa-user-graduate"></i>Students</h3>
        <ul class="list-group">
            <c:forEach var="enrolledStudent" items="${enrolledStudents}">
                <li class="list-group-item">
                    <div style="display: flex; align-items: center;">
                        <i class="fas fa-user"></i>
                        <span style="margin-left: 10px;">${enrolledStudent.student.name}</span>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    	        <!-- Button to Trigger Modal -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>