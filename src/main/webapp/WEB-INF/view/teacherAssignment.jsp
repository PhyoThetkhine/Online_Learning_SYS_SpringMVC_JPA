<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course ClassWork</title>
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
        

.material-container {
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.material-box {
    background-color: #f8f9fa;
    border: 1px solid #ddd;
    border-radius: 5px;
    padding: 20px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s;
}

.material-box:hover {
    transform: translateY(-5px);
    background-color: #eef2f3;
}

.material-box h3 {
    margin-bottom: 10px;
    font-size: 1.5em;
}
    </style>
</head>
<body>
   <div class="sidebar">
    <h2><i class="fas fa-user-shield"></i> Teacher Dashboard</h2>
    <ul>
        <li>
            <a href="/Learing_management/TeacherHomeServlet">
                <i class="fas fa-tachometer-alt"></i> Dashboard
            </a>
        </li>
        <li>
            <a href="/Learing_management/ClassWorkServlet?courseId=${course.id}">
                <i class="fas fa-chalkboard-teacher"></i>  Class Work
            </a>
        </li>
         <li><a href="/Learing_management/Assignment?courseId=${course.id}"><i class="fas fa-book"></i>Assignment</a></li>
         <li>
			<a href="/Learing_management/Logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
    </ul>
</div>
    
    <!-- Main Content -->
    <div class="main-content">
        
        <div class="header">
    <h1><i class="fas fa-book-open"></i>  Assignments for ${course.title} </h1>
   
    
</div>
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addMaterialModal">
    Create Assignment
</button>
<br><br>
        <section id="materials">
    <div class="container">
        <div class="material-container">
		   <c:forEach var="assingments" items="${assingments}">
		   <a href="TeacherAssignDetail?assingmentsId=${assingments.id}" class="text-decoration-none text-dark">
    <div class="material-box" >
        <p><strong>Title:</strong> ${assingments.title}</p>
        <p><strong>Description:</strong> ${assingments.description}</p>
        <p><strong>Created by:</strong> Tr ${assingments.createTeacher.name}</p>
    </div>
    </a>
</c:forEach>
        </div>
    </div>
</section>
        

    </div>
    <!-- Modal -->
<div class="modal fade" id="addMaterialModal" tabindex="-1" aria-labelledby="addMaterialModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addMaterialModalLabel">Create Assignment</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="/LearingSYSLMS/Teacher/createassignment" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="courseId" value="${course.id}">
                    <div class="mb-3">
                        <label for="title" class="form-label">Assignment Title</label>
                        <input type="text" class="form-control" id="title" name="title"  required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Assignment Description</label>
                        <textarea class="form-control" id="description" name="description" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="dueDates" class="form-label">Due Date</label>
                        <input type="datetime-local" class="form-control" id="dueDates" name="dueDates">
                    </div>
                    <div class="mb-3">
                        <label for="point" class="form-label">Point</label>
                        <select class="form-control" id="point" name="point">
                            <option value="">No Mark</option>
                            <option value="100">100</option>
                            <option value="50">50</option>
                            <option value="25">25</option>
                        </select>
                    </div>
                    <div class="mb-3">
				        <label class="form-label">Include Files?</label>
				        <div>
				            <input type="radio" id="fileYes" name="includeFiles" value="yes" onclick="toggleFileUpload(true)">
				            <label for="fileYes">Yes</label>
				            <input type="radio" id="fileNo" name="includeFiles" value="no" onclick="toggleFileUpload(false)" checked>
				            <label for="fileNo">No</label>
				        </div>
				    </div>
				
				    <!-- File Upload -->
				    <div class="mb-3" id="fileUploadContainer" style="display: none;">
				        <label for="assignmentFiles" class="form-label">Upload Files</label>
				        <input type="file" class="form-control" id="assignmentFiles" name="files" multiple>
				    </div>
				
				    <button type="submit" class="btn btn-primary">Create Assignment</button>
				</form>
            </div>
        </div>
    </div>
</div>
<script>
    function toggleFileUpload(show) {
        const fileUploadContainer = document.getElementById("fileUploadContainer");
        fileUploadContainer.style.display = show ? "block" : "none";
    }
</script>

   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
