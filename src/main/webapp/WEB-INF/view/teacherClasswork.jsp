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
        .material-container {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .material-box {
            background-color: #fff;
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            display: flex;
            align-items: center;
            gap: 20px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }
        .material-box:hover {
            transform: translateY(-5px);
            background-color: #f9fafb;
        }
        .material-icon {
            font-size: 2.5rem;
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
            <h1><i class="fas fa-book-open"></i> Materials for ${course.title} </h1>
        </div>
        <button type="button" class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addMaterialModal">
            Add Material
        </button>

        <section id="materials">
            <div class="container">
                <div class="material-container">
                    <c:forEach var="material" items="${materialList}">
                        <div class="material-box">
                            <i class="fas fa-book-open"></i>
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
    </div>

    <!-- Add Material Modal -->
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
