<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.model.MealWithExceed" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>

<c:forEach var="meal" items="${requestScope.get(\"mealList\")}">

    <c:choose>
        <c:when test="${meal.getExceed()}">
            <p style="color:red">${meal.getDateTime()} Тип: ${meal.getDescription()} Калорий: ${meal.getCalories()} </p>
        </c:when>
        <c:otherwise>
            <p style="color:green">${meal.getDateTime()} Тип: ${meal.getDescription()} Калорий: ${meal.getCalories()} </p>
        </c:otherwise>
    </c:choose>


</c:forEach>
</body>
</html>