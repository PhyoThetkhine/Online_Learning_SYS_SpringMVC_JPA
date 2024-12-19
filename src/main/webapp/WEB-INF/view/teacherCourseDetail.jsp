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
        /* Main Content */
        .main-content {
            margin-left: 250px; /* Matches sidebar width */
            padding: 20px;
            width: calc(100% - 250px);
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
        /* Navbar Styles */
        .navbar {
            margin-top: 20px;
            background-color: #f8f9fa;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        .navbar-nav .nav-link {
            display: flex;
            align-items: center;
        }
        .navbar-nav .nav-link i {
            margin-right: 5px;
        }
        .material-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
       .material-box {
    background-color: #fff;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 15px; /* Adjust padding for smaller box height */
    display: flex;
    align-items: center;
    gap: 20px; /* Increased gap between icon and content */
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
    max-height: 180px; /* Slightly increased height to accommodate larger icons */
    overflow: hidden;
}

.material-icon {
    font-size: 3.5rem; /* Increased icon size */
    color: #2c3e50;
}

.material-content {
    flex: 1;
}

.material-title {
    font-size: 1.2em;
    font-weight: bold;
    margin: 0;
}

.material-meta {
    font-size: 0.9em;
    color: #666;
}

.material-actions button {
    margin-left: auto;
}
    </style>
</head>
<body>
    <!-- Sidebar -->
    <div class="sidebar">
        <h2><i class="fas fa-user-shield"></i> Teacher Dashboard</h2>
        <ul>
            <li>
                <a href="/Learing_management/TeacherHomeServlet">
                    <i class="fas fa-tachometer-alt"></i> Dashboard
                </a>
            </li>
            <li>
                <a href="/LearingSYSLMS/Teacher/classWork/${course.id}">
                    <i class="fas fa-chalkboard-teacher"></i> Class Work
                </a>
            </li>
            <li>
                <a href="/LearingSYSLMS/Teacher/assignments/${course.id}">
                    <i class="fas fa-book"></i> Assignment
                </a>
            </li>
            <li>
                <a href="/Learing_management/Logout">
                    <i class="fas fa-sign-out-alt"></i> Logout
                </a>
            </li>
        </ul>
    </div>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Course Card -->
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
   

        <!-- Navigation Bar -->
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
             <br>
        <h4><i class="fas fa-file-alt"></i> Materials</h4>
        <section id="materials">
            <div class="container">
                <div class="material-container">
                    <c:forEach var="material" items="${materials}">
                        <div class="material-box">
                           <i class="fas fa-file-alt"></i>
                            <div class="material-content">
                                <p class="material-title">${material.title}</p>
                                <p class="material-meta">${material.description}</p>
                            </div>
                            <div class="material-actions">
							    <a href="/LearingSYSLMS/Teacher/materialDetail/${material.id}" class="btn btn-primary">
							        <i class="fas fa-eye"></i> View
							    </a>
							</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
             <br>
        <h4><i class="fas fa-tasks"></i> Assignments</h4>
          <section id="materials">
            <div class="container">
                <div class="material-container">
                    <c:forEach var="assignments" items="${assignments}">
                        <div class="material-box">
                            <i class="fas fa-book-open"></i>
                            <div class="material-content">
                                <p class="material-title">${assignments.title}</p>
                                <p class="material-meta">${assignments.description}</p>
                            </div>
                            <div class="material-actions">
							    <a href="/LearingSYSLMS/Teacher/materialDetail/${material.id}" class="btn btn-primary">
							        <i class="fas fa-eye"></i> View
							    </a>
							</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
         </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

	
