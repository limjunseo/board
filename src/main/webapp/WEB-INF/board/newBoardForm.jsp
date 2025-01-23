<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>새 글 작성</title>
    <style>
        /* Reset 스타일로 기본 여백 제거 */
        body, h1, form, input, textarea {
            margin: 0;
            padding: 0;
        }

        /* 바디 스타일 */
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            line-height: 1.6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* 카드 형식으로 폼 스타일 */
        form {
            width: 100%;
            max-width: 600px;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border: 1px solid #ddd;
        }

        /* 제목 스타일 */
        h1 {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.8em;
            color: #007bff;
        }

        /* 라벨 스타일 */
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }

        /* 입력 필드 스타일 */
        input[type="text"], textarea {
            width: 100%;
            padding: 12px;
            margin-bottom: 16px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1em;
        }

        /* 입력 필드 포커스 스타일 */
        input[type="text"]:focus, textarea:focus {
            border-color: #007bff;
            outline: none;
            box-shadow: 0 0 3px rgba(0, 123, 255, 0.3);
        }

        /* 버튼 스타일 */
        input[type="submit"] {
            width: 100%;
            padding: 12px;
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            color: #fff;
            font-size: 1.2em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        /* 버튼 호버 스타일 */
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<form action="/board" method="post">
    <h1>새 글 작성</h1>

    <label for="userId">작성자 ID:</label>
    <input type="text" id="userId" name="userId" placeholder="작성자의 ID를 입력하세요" required>

    <label for="boardCategoryCode">카테고리 코드:</label>
    <input type="text" id="boardCategoryCode" name="boardCategoryCode" placeholder="카테고리 코드를 입력하세요" required>

    <label for="boardTitle">제목:</label>
    <input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요" required>

    <label for="content">내용:</label>
    <textarea id="content" name="content" rows="8" placeholder="내용을 입력하세요" required></textarea>

    <input type="submit" value="작성">
</form>
</body>
</html>
