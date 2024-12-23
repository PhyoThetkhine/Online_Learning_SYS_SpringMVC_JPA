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
	background-color: #2c3e50;
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
            margin-left: 250px;
            padding: 20px;
            width: 100%;
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
        .edit-photo-button {
    position: absolute;
    top: 10px;
    right: 10px;
    z-index: 10;
    background-color: rgba(0, 0, 0, 0.7);
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    font-size: 0.9em;
    transition: background-color 0.3s ease;
}
.edit-photo-button:hover {
    background-color: rgba(0, 0, 0, 0.9);
}
        
    </style>
</head>
<body>
    
	<div class="sidebar">
		<h2>
			<i class="fas fa-user-shield"></i> Admin Dashboard
		</h2>
		<ul>
			<li><a href="/LearingSYSLMS/Admin/home"> <i
					class="fas fa-tachometer-alt"></i> Dashboard
			</a></li>
			<li>
            <a href="/LearingSYSLMS/Admin/TeacherList">
                <i class="fas fa-chalkboard-teacher"></i>  Teacher Management
            </a>
        </li>
        <li>
            <a href="/LearingSYSLMS/Admin/StudentList">
                <i class="fas fa-user-graduate"></i>  Student Management
            </a>
        </li>
          <li>
            <a href="/LearingSYSLMS/Admin/StudentEntrollment">
                <i class="fas fa-users"></i>  Student Enrollment List
            </a>
        </li>
        <li>
            <a href="/LearingSYSLMS/Admin/TeacherEntrollment">
                <i class="fas fa-users"></i>  Teacher Enrollment List
            </a>
        </li>
		<li>
			<a href="/LearingSYSLMS/logout">
				<i class="fas fa-sign-out-alt"></i> Logout
			</a>
		</li>
		</ul>
	</div>
    
    <!-- Main Content -->
    <div class="main-content">
        <div class="header">
            <h1><i class="fas fa-book-open"></i> Course Details</h1>
        </div>
        
        <div class="card position-relative">
    <img src="${course.photoUrl}" alt="${course.title}" class="card-img-top">
    <!-- Edit Photo Button -->
    <button class="edit-photo-button" data-bs-toggle="modal" data-bs-target="#editPhotoModal">
        <i class="fas fa-camera"></i> Edit Photo
    </button>
</div>
        <!-- Course Details Card -->
        <div class="card">
           <%--  <img src="${course.photoURL}" alt="${course.title}" class="card-img-top"> --%>
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
                <button class="btn btn-warning mt-4" data-bs-toggle="modal" data-bs-target="#editCourseModal">
                    <i class="fas fa-edit"></i> Edit Course
                </button>
            </div>
        </div>


 <button class="btn btn-primary mt-4" data-bs-toggle="modal" data-bs-target="#assignTeacherModal" 
        <c:if test="${course.status eq 'COMPLETED'}">disabled</c:if>>
        <i class="fas fa-plus"></i> Assign Teacher
    </button>
        <button class="btn btn-secondary mt-4" data-bs-toggle="modal" data-bs-target="#droppedTeachersModal"
        <c:if test="${course.status eq 'COMPLETED'}">disabled</c:if>>
        <i class="fas fa-users"></i> View Dropped Teachers
    </button>
        <!-- Assigned Teachers -->
        <h3 class="mt-4"><i class="fas fa-users"></i> Assigned Teachers</h3>
        <ul class="list-group">
            <c:forEach var="assignedTeacher" items="${assignedTeachers}">
                <li class="list-group-item">
                    <div style="display: flex; align-items: center;">
                        <i class="fas fa-user"></i>
                        <span style="margin-left: 10px;">${assignedTeacher.teacher.name}</span>
                    </div>
                    <form method="post" action="DeleteTeacherEntroll">
                        <input type="hidden" name="teacherId" value="${assignedTeacher.teacher.id}">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button type="submit" class="btn btn-danger btn-sm"
                        <c:if test="${course.status eq 'COMPLETED'}">disabled</c:if>>
                    <i class="fas fa-trash"></i> Terminate
                </button>
                    </form>
                </li>
            </c:forEach>
        </ul> 

 <button class="btn btn-success mt-4" data-bs-toggle="modal" data-bs-target="#assignStudentModal"
        <c:if test="${course.status eq 'COMPLETED'}">disabled</c:if>>
        <i class="fas fa-user-plus"></i> Assign Student
    </button>
		     <button class="btn btn-secondary mt-4" data-bs-toggle="modal" data-bs-target="#droppedStudentsModal"
        <c:if test="${course.status eq 'COMPLETED'}">disabled</c:if>>
        <i class="fas fa-users"></i> View Dropped Students
    </button>
        <!-- Enrolled Students -->
        <h3 class="mt-4"><i class="fas fa-user-graduate"></i> Enrolled Students</h3>
        <ul class="list-group">
            <c:forEach var="enrolledStudent" items="${enrolledStudents}">
                <li class="list-group-item">
                    <div style="display: flex; align-items: center;">
                        <i class="fas fa-user"></i>
                        <span style="margin-left: 10px;">${enrolledStudent.student.name}</span>
                    </div>
                    <form method="post" action="DeleteEntrollStudent">
                        <input type="hidden" name="studentId" value="${enrolledStudent.student.id}">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <button type="submit" class="btn btn-danger btn-sm"
                        <c:if test="${course.status eq 'COMPLETED'}">disabled</c:if>>
                    <i class="fas fa-trash"></i> Terminate
                </button>
                    </form>
                </li>
            </c:forEach>
        </ul>
    </div>
    	        <!-- Button to Trigger Modal -->
        
		
<!-- Bootstrap Modal for Assigning Teacher -->
    <div class="modal fade" id="assignTeacherModal" tabindex="-1" aria-labelledby="assignTeacherModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="assignTeacherModalLabel"><i class="fas fa-user-plus"></i> Assign Teacher to Course</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="assignTeacher" method="POST">
                    <div class="modal-body">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <div class="mb-3">
                            <label for="teacher" class="form-label">Select Teacher</label>
                            <select class="form-control" id="teacher" name="teacherId" required>
                                <c:forEach var="teacher" items="${teacherList}">
                                    <option value="${teacher.id}">${teacher.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Close</button>
                        <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i> Assign Teacher</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap Modal for Assigning Student -->
    <div class="modal fade" id="assignStudentModal" tabindex="-1" aria-labelledby="assignStudentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="assignStudentModalLabel"><i class="fas fa-user-plus"></i> Assign Student to Course</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="assignStudent" method="POST">
                    <div class="modal-body">
                        <input type="hidden" name="courseId" value="${course.id}">
                        <div class="mb-3">
                            <label for="student" class="form-label">Select Student</label>
                            <select class="form-control" id="student" name="studentId" required>
                                <c:forEach var="student" items="${studentList}">
                                    <option value="${student.id}">${student.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Close</button>
                        <button type="submit" class="btn btn-success"><i class="fas fa-check"></i> Assign Student</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Bootstrap Modal for Editing Course -->
<div class="modal fade" id="editCourseModal" tabindex="-1" aria-labelledby="editCourseModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editCourseModalLabel"><i class="fas fa-edit"></i> Edit Course</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="courseEdit" method="POST">
                <div class="modal-body">
                    <!-- Hidden field for Course ID -->
                    <input type="hidden" name="courseId" value="${course.id}">
                    
                    <!-- Course Title -->
                    <div class="mb-3">
                        <label for="courseTitle" class="form-label">Course Title</label>
                        <input type="text" class="form-control" id="courseTitle" name="title" value="${course.title}" required>
                    </div>
                    
                    <!-- Course Description -->
                    <div class="mb-3">
                        <label for="courseDescription" class="form-label">Course Description</label>
                        <textarea class="form-control" id="courseDescription" name="description" rows="3" required>${course.description}</textarea>
                    </div>
                    
                    <!-- Course Status -->
                    <div class="mb-3">
                        <label for="courseStatus" class="form-label">Course Status</label>
                        <select class="form-control" id="courseStatus" name="status" required>
                            <option value="ACTIVE" ${course.status == 'ACTIVE' ? 'selected' : ''}>Active</option>
                            <option value="COMPLETED" ${course.status == 'COMPLETED' ? 'selected' : ''}>Completed</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cancel</button>
                    <button type="submit" class="btn btn-success"><i class="fas fa-save"></i> Save Changes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="editPhotoModal" tabindex="-1" aria-labelledby="editPhotoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editPhotoModalLabel"><i class="fas fa-camera"></i> Edit Course Photo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="editCoursePhoto" method="POST" enctype="multipart/form-data">
                <div class="modal-body">
                    <!-- Hidden field for Course ID -->
                    <input type="hidden" name="courseId" value="${course.id}">
                    
                    <!-- File Input -->
                    <div class="mb-3">
                        <label for="coursePhoto" class="form-label">Upload New Photo</label>
                        <input type="file" class="form-control" id="coursePhoto" name="coursePhoto" accept="image/*" required>
                    </div>
                    
                    <!-- Preview -->
                    <div class="text-center mt-3">
                        <img id="photoPreview" src="#" alt="Photo Preview" style="max-width: 100%; display: none;" />
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cancel</button>
                    <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Save Photo</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- Modal for Dropped Students -->
<div class="modal fade" id="droppedStudentsModal" tabindex="-1" aria-labelledby="droppedStudentsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="droppedStudentsModalLabel">
                    <i class="fas fa-user-graduate"></i> Dropped Students
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <ul class="list-group">
                    <c:forEach var="droppedStudent" items="${DropStudents}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <span>
                                <i class="fas fa-user"></i> ${droppedStudent.student.name}
                            </span>
                            <form action="reenrolledStudent" method="POST" style="margin: 0;">
                                <input type="hidden" name="studentId" value="${droppedStudent.student.id}">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button type="submit" class="btn btn-success btn-sm">
                                    <i class="fas fa-check"></i> Re-enroll
                                </button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    <i class="fas fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="droppedTeachersModal" tabindex="-1" aria-labelledby="droppedTeachersModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="droppedTeachersModalLabel">
                    <i class="fas fa-users"></i> Dropped Teachers
                </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <ul class="list-group">
                    <c:forEach var="droppedTeacher" items="${DropTeachers}">
                        <li class="list-group-item d-flex justify-content-between align-items-center">
                            <span>
                                <i class="fas fa-user"></i> ${droppedTeacher.teacher.name}
                            </span>
                            <form action="reenrolledTeacher" method="POST" style="margin: 0;">
                                <input type="hidden" name="teacherId" value="${droppedTeacher.teacher.id}">
                                <input type="hidden" name="courseId" value="${course.id}">
                                <button type="submit" class="btn btn-success btn-sm">
                                    <i class="fas fa-check"></i> Re-enroll
                                </button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    <i class="fas fa-times"></i> Close
                </button>
            </div>
        </div>
    </div>
</div>
    
<script>
    document.getElementById('coursePhoto').addEventListener('change', function(event) {
        const file = event.target.files[0];
        const preview = document.getElementById('photoPreview');
        
        if (file) {
            const reader = new FileReader();
            reader.onload = function(e) {
                preview.src = e.target.result;
                preview.style.display = 'block';
            };
            reader.readAsDataURL(file);
        } else {
            preview.style.display = 'none';
        }
    });
</script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


 
		        
	