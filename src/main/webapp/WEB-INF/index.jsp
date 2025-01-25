<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>게시판 메인화면</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        li {
            background-color: #fff;
            margin: 10px auto;
            padding: 10px;
            border: 1px solid #ddd;
            width: 80%;
            text-align: center;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>게시판 분류</h1>
<ul>
    <c:forEach var="category" items="${categories}">
        <li><a href="board/category/${category.code}">${category.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>