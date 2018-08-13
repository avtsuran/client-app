<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Contact book</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/add-user.css" rel="stylesheet">
</head>
<body>
<nav role="navigation" class="navbar navbar-default">
    <div class="">
        <a href="#" class="navbar-brand">TASK-LIST</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/phone-book">Home</a></li>
        </ul>
    </div>
</nav>
<div class="container">
    <form:form method="post" modelAttribute="user" action="/add-user">
        <form:hidden path="id"/>
        <fieldset class="form-group">
            <form:label path="name">Name</form:label>
            <form:input path="name" type="text" class="form-control"/>
            <form:errors path="name" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="phone">Phone</form:label>
            <form:input path="phone" type="text" class="form-control"/>
            <form:errors path="phone" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="email">Email</form:label>
            <form:input path="email" type="text" class="form-control"/>
            <form:errors path="email" cssClass="text-warning"/>
        </fieldset>
        </fieldset>
        <button type="save" class="btn btn-success">Save</button>
        <a href="/" type="button" class="btn btn-default">Discard</a>
    </form:form>
</div>
</body>
</html>