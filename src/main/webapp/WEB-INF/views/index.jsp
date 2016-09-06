<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>Prog.kiev.ua</title>--%>
<%--</head>--%>
<%--<body>--%>
    <%--<div align="center">--%>
        <%--<h1>Your login is: ${login}, your roles are:</h1>--%>
        <%--<c:forEach var="s" items="${roles}">--%>
            <%--<c:out value="${s}" />--%>
        <%--</c:forEach>--%>

        <%--<c:url value="/logout" var="logoutUrl" />--%>
        <%--<p>Click to logout: <a href="${logoutUrl}">LOGOUT</a></p>--%>
    <%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap-filestyle.min.js"> </script>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
<div class="container">
    <h2>Bootstrap File upload demo</h2>
    <div class="col-xs-4">
        <div class="form-group">
            <label>Change icon</label>
            <input type="file" id="icondemo">
        </div>
    </div>

</div>
<script>
    $('#icondemo').filestyle({
        iconName : 'glyphicon glyphicon-file',
        buttonText : 'Select File',
        buttonName : 'btn-warning'
    });
</script>

</body>
</html>

