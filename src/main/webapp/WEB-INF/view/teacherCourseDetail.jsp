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

.material-box i {
    font-size: 2rem; /* Adjusted icon size */
    margin-right: 15px;
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

.material-actions .btn .fas {
    font-size: 1.2em; /* Increase the icon size */
    vertical-align: middle; /* Align icon properly */
    margin-right: 0.3em; /* Reduce space between the icon and the text */
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
   
             <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addMaterialModal">
           <i class="fas fa-plus"></i> 
     		 Create Material
        </button>
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
                                 <p class="material-meta">By ${material.createTeacher.name}</p>
                            </div>
                            <div class="material-actions">
							    <a href="/LearingSYSLMS/Teacher/materialDetail/${material.id}" class="btn btn-primary">
							        <i class="fas fa-eye"></i> View
							    </a>
							   <form action="/LearingSYSLMS/Teacher/deleteMaterial/${material.id}" method="POST" style="display:inline;">
							    <input type="hidden" name="courseId" value="${course.id}">
							    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this material?');">
							        <i class="fas fa-trash-alt"></i> Trash
							    </button>
							</form>
							</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
             <br>
             <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addcreateAssignmentModal">
             <i class="fas fa-plus"></i> 
    Create Assignment
</button>
<br><br>
        <h4><i class="fas fa-tasks"></i> Assignments</h4>
          <section id="materials">
            <div class="container">
                <div class="material-container">
                    <c:forEach var="assignments" items="${assignments}">
                        <div class="material-box">
                            <i class="fas fa-book-open"></i>
                            <div class="material-content">
                                <p class="material-title">${assignments.title}</p>
                                <p class="material-meta">By ${assignments.createTeacher.name}</p>
                            </div>
                            <div class="material-actions">
							    <a href="/LearingSYSLMS/Teacher/assignmentDetail/${assignments.id}" class="btn btn-primary">
							        <i class="fas fa-eye"></i> View
							    </a>
							    <form action="/LearingSYSLMS/Teacher/deleteAssignment/${assignments.id}" method="POST" style="display:inline;">
							    <input type="hidden" name="courseId" value="${course.id}">
							    <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this assignment?');">
							        <i class="fas fa-trash-alt"></i> Trash
							    </button>
							</form>
							</div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
         </div>
         <div class="modal fade" id="addMaterialModal" tabindex="-1" aria-labelledby="addMaterialModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addMaterialModalLabel">Add Material</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form action="/LearingSYSLMS/Teacher/createMaterial" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <div class="mb-3">
                            <label for="materialTitle" class="form-label">Material Title</label>
                            <input type="text" class="form-control" id="materialTitle" name="title" value="${MaterialDTO.title}" required>
                        </div>
                        <div class="mb-3">
                            <label for="materialDescription" class="form-label">Material Description</label>
                            <textarea class="form-control" id="materialDescription" name="description" value="${MaterialDTO.description}" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="materialFiles" class="form-label">Upload Files</label>
                            <input type="file" class="form-control" id="materialFiles" name="files" multiple required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
         
         <div class="modal fade" id="addcreateAssignmentModal" tabindex="-1" aria-labelledby="addcreateAssignmentModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addcreateAssignmentModalLabel">Create Assignment</h5>
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
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

	
