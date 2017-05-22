<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <link rel="stylesheet" href="<c:url value="/resources/css/main.css" />" />

    <title>Заявка</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
<c:url var="saveUrl" value="/user/profile" />
<c:url var="start" value="/" />
<div class="row">
    <div class="col-md-9">
        <a href="${start}">
            <h1>
                <c:out value="${user.surname}" />
                <c:out value="${user.firstName}" />
                <c:out value="${user.secondName}" />
            </h1>
        </a>
    </div>
    <div class="col-md-3" style="padding-top: 20px;">
        <div class="btn-group" role="group" style="float: right">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Профиль <span class="caret"></span>
            </button>
            <ul class="dropdown-menu pull-right">
                <li><a href="#myModal" data-toggle="modal">Настройки</a></li>
                <li><a href="#" onclick="document.forms['logoutForm'].submit()">Выйти</a></li>
            </ul>
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            <!-- Modal -->
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Настройки профиля</h4>
                        </div>
                        <div class="modal-body">
                            <form:form commandName="user" action="${saveUrl}" method="post" id="profile">
                                <form:label path="id" cssStyle="display: none" />
                                <form:input path="id" cssStyle="display: none" />
                                <div class="form-group">
                                    <form:label path="email">E-mail</form:label>
                                    <form:input path="email" cssClass="form-control" type="email"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="phoneNumber">Номер телефона</form:label>
                                    <form:input path="phoneNumber" cssClass="form-control"/>
                                </div>
                                <c:if test="${perm_add_fio_eng==true}">
                                <form:label path="">ФИО на английском языке</form:label>
                                    <div class="form-group">
                                        <form:label path="firstNameEng"/>
                                        <form:input path="firstNameEng" cssClass="form-control" placeholder="Фамилия"/>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="surnameEng"/>
                                        <form:input path="surnameEng" cssClass="form-control" placeholder="Имя"/>
                                    </div>
                                    <div class="form-group">
                                        <form:label path="secondNameEng"/>
                                        <form:input path="secondNameEng" cssClass="form-control" placeholder="Отчество"/>
                                    </div>
                                </c:if>
                            </form:form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                            <button type="button" class="btn btn-primary" onclick="document.forms['profile'].submit()">Сохранить изменения</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
