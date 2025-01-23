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
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            text-decoration: none;
            color: #007bff;
        }
        a:hover {
            text-decoration: underline;
        }
        .new-post {
            display: block;
            width: 80%;
            margin: 20px auto;
            text-align: right;
        }
        .new-post a {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
        }
        .new-post a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1>게시판</h1>
<table>
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${boards}">
        <tr>
            <td>${board.id}</td>
            <td><a href="board/${board.id}">${board.title}</a></td>
            <td>${board.userId}</td>
            <td>${board.createdDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="new-post">
    <a href="board/new">새 글 작성</a>
</div>
</body>
</html>