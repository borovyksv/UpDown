<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link rel="shortcut icon" href="/static/icon.png"
		  type="image/x-icon">
	<link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Users </span></div>
		  	<div class="tablecontainer">
				<table class="table table-hover">
		    		<thead>
			      		<tr>
					        <th>First Name</th>
					        <th>Last Name</th>
					        <th>Email</th>
					        <th>SSO ID</th>
					        <th></th>
						</tr>
			    	</thead>
		    		<tbody>
					<c:forEach items="${users}" var="user">

						<tr>
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.email}</td>
							<td>${user.ssoId}</td>
							<td>

								<div class="dropdown">
									<button class="btn btn-primary dropdown-toggle pull-right" type="button" data-toggle="dropdown">Menu
										<span class="caret"></span></button>
									<ul class="dropdown-menu">
										<li><a href="<c:url value='/add-document-${user.id}' />">Your Disc</a></li>
										<li class="divider"></li>
										<li><a href="<c:url value='/edit-user-${user.ssoId}' />" >Edit account</a></li>
										<li><a href="<c:url value='/delete-user-${user.ssoId}' />">Delete account</a></li>
									</ul>
								</div>
							</td>


							<%--<td><a href="<c:url value='/add-document-${user.id}' />" class="btn btn-info " style="width: 87px">Your Disc</a></td>--%>
							<%--<td><a href="<c:url value='/edit-user-${user.ssoId}' />" class="btn btn-success custom-width">edit</a></td>--%>
							<%--<td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">delete</a></td>--%>

						</tr>
					</c:forEach>
		    		</tbody>
		    	</table>
		    </div>
		</div>
	 	<div class="well">
	 		<a href="<c:url value='/newuser' />">Add New User</a>
	 	</div>
   	</div>
	<script src="/static/js/jquery.js"></script>
	<script src="/static/js/bootstrap.min.js"></script>

</body>
</html>