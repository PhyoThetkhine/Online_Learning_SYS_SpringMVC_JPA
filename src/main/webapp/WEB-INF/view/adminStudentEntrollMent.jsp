<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet">
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

.table-container {
	background-color: #f8f9fa;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.table {
	width: 100%;
	margin-bottom: 20px;
	background-color: #fff;
	border: 1px solid #ddd;
}

.table th, .table td {
	padding: 12px;
	text-align: left;
	border: 1px solid #ddd;
}

.table th {
	background-color: #f4f4f4;
	color: #333;
	font-weight: bold;
}

.table td {
	color: #555;
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
		
			<h2>Admin Panel - Data Table</h2>
		</div>

		<div class="table-container">
			<h3>Student Enrollment List</h3>
			<div class="export-buttons">
    <form action="/LearingSYSLMS/Admin/ExportStudentEnrollment" method="post">
        <button type="submit" class="btn btn-primary">Export PDF</button>
    </form>
</div>
			<table class="table">
				<thead>
					<tr>
						<th>No.</th>
						<th>Student Name</th>
						<th>Course</th>
						<th>Create Admin</th>
						<th>Status<th>
					</tr>
				</thead>
				<tbody>
					<!-- Dynamically populate the rows -->
					<c:forEach var="studentsEntrollList" items="${studentsEntrollList}" varStatus="status">
						<tr>
							<td>${status.index + 1}</td>
							<td>${studentsEntrollList.student.name}</td>
							<td>${studentsEntrollList.course.title}</td>
							<td>${studentsEntrollList.createAdminName}</td>
							<td>${studentsEntrollList.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
