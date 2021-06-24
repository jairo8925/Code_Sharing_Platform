<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create</title>
    <script src="/js/scripts.js"></script>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<form autocomplete="off">
    <h6>Time Restriction (in seconds): </h6><input id="time_restriction" type="text"/>
    <h6>View Restriction: </h6><input id="views_restriction" type="text"/> <br>
    <textarea id="code_snippet">// write your code here</textarea> <br>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>
</body>
</html>