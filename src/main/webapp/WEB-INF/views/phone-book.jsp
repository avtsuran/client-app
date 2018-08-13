<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Contact book</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>

    <link href="css/phone-book.css" rel="stylesheet">
</head>
<body>
<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="#" class="navbar-brand">PHONE-BOOK</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/add-user">ADD USER</a></li>
        </ul>
    </div>
</nav>


<div class="container">

    <c:if test="${not empty status}">
        <div class="alert alert-success">
            <a href="#" class="close" data-dismiss="alert">&times;</a>
            <div>Status: ${status}. All records were processed correctly!</div>
        </div>
    </c:if>

    <table id="table" class="table table-striped table-bordered">
        <thead>
        <tr>
            <td>Name</td>
            <td>Phone</td>
            <td>Email</td>
            <td class="column">Delete</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.name}
                <td>${user.phone}</td>
                <td>${user.email}</td>
                <td class="column">
                    <a type="button" class="btn btn-sm btn-danger" href="/delete/${user.id}"><span
                            class="glyphicon glyphicon-trash"></span></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    window.setTimeout(function () {
        $(".alert-success").fadeTo(500, 0).slideUp(500, function () {
            $(this).remove();
        });
    }, 2000);
</script>

</body>
</html>