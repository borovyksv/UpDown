<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Upload/Download/Delete Documents</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>

    <link rel="stylesheet" href="/static/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="/static/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="/static/js/bootstrap.min.js"></script>
</head>

<body>

<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of Documents (<c:out
                value="${currentFolder.description}"/>)</span></div>
        <div class="tablecontainer">

            <table class="table table-hover">
                <thead>
                <tr>
                    <th>No.</th>
                    <th>File Name</th>
                    <th>Type</th>
                    <th>Path</th>
                    <th width="100"></th>
                    <th width="100"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${documents}" var="doc" varStatus="counter">
                    <tr>
                        <td>${counter.index + 1}</td>
                        <td>${doc.name}</td>

                        <c:choose>
                            <c:when test="${doc.folder}">
                                <td><img
                                        src="https://cdn4.iconfinder.com/data/icons/small-n-flat/24/folder-blue-128.png"
                                        width="25" height="25"></td>
                            </c:when>
                            <c:otherwise>
                                <td>${doc.type}</td>
                            </c:otherwise>
                        </c:choose>

                        <td>${doc.description}</td>
                        <c:choose>
                            <c:when test="${doc.folder}">
                                <%--todo--%>
                                <td><a href="<c:url value='/open-folder-${user.id}-${doc.id}' />"
                                       class="btn btn-success custom-width">open</a></td>
                            </c:when>
                            <c:otherwise>
                                <td><a target="_blank" href="<c:url value='/download-document-${user.id}-${doc.id}' />"
                                       class="btn btn-success custom-width">download</a></td>
                            </c:otherwise>
                        </c:choose>
                        <td><a href="<c:url value='/delete-document-${user.id}-${doc.id}' />"
                               class="btn btn-danger custom-width">delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="panel panel-default">

        <div class="panel-heading"><span class="lead">Upload New Document</span></div>
        <div class="uploadcontainer">
            <form method="POST" action="/create-folder-${user.id}-${currentFolder.id}" class="form-horizontal">

                <div class="row">
                    <div class="form-group col-md-9">
                        <div class="col-md-3">Create new folder</div>
                        <div class="col-md-3">
                            <input type="text" required placeholder="Folder name" name="folderName">

                        </div>
                        <div class="col-md-3">
                            <input type="submit" value="Create" class="btn btn-primary btn-sm">
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <div class="uploadcontainer">
            <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data"
                       action="/add-document-${user.id}-${currentFolder.id}" class="form-horizontal">

                <div class="row">
                    <div class="form-group col-md-9">
                        <div class="col-md-3">Upload a document</div>
                        <div class="col-md-3">
                            <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                            <div class="has-error">
                                <form:errors path="file" class="help-inline"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <input type="submit" value="Upload" class="btn btn-primary btn-sm">
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
    <div>
	 		<span class="well pull-left">
	 			Go to <a href="<c:url value='/list' />">Users List</a>
	 		</span>
			<span class="well pull-right">
				Go to <a href="<c:url value='/add-document-${user.id}' />">ROOT folder</a>
			</span><br/><br/><br/>

    </div>
</div>

</body>
</html>