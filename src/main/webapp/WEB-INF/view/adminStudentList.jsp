<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Management</title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
       body {
            margin: 0;
            font-family: Arial, sans-serif;
            display: flex;
        }
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
            <h1>Student Management</h1>
        </div>
        <c:if test="${not empty error}">
    <div id="errorAlert" class="alert alert-danger" role="alert">
        ${error}
    </div>
</c:if>

        
        <!-- Button to open modal -->
        <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#studentModal">Add New Student</button>
        
        <!-- Teacher List Table -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Create Admin name</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="students" items="${students}">
                    <tr>
                        <td>${students.name}</td>
                        <td>${students.email}</td>
                         <td>${students.createAdminName}</td>
                         <td>
                         
               <c:choose>
			    <c:when test="${students.status == 'ACTIVE'}">
			        <form action="updateStudentStatus" method="POST">
			            <input type="hidden" name="userId" value="${students.id}">
			            <input type="hidden" name="newStatus" value="TERMINATE">
			            <button type="submit" class="btn btn-danger">Terminate</button>
			        </form>
			    </c:when>
			    <c:when test="${students.status == 'TERMINATE'}">
			        <form action="updateStudentStatus" method="POST">
			            <input type="hidden" name="userId" value="${students.id}">
			            <input type="hidden" name="newStatus" value="ACTIVE">
			            <button type="submit" class="btn btn-success">Reemployment</button>
			        </form>
			    </c:when>
			</c:choose>
            </td>
       
            
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
     <div class="modal fade" id="studentModal" tabindex="-1" aria-labelledby="studentModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="studentModalLabel">Add New Student</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="addnewStudent" method="POST">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="name" class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" id="email" name="email" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add Student</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
<script>
    window.onload = function() {
        setTimeout(function() {
            var errorAlert = document.getElementById('errorAlert');
            if (errorAlert) {
                errorAlert.style.display = 'none';
            }
        }, 5000);  // Adjust the duration as needed
    }
</script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
