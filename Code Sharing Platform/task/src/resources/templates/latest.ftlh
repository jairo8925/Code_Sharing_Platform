<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <#list snippets as snippet>
        <div>
            <span id="load_date">${snippet.date}</span>
            <pre id="code_snippet">${snippet.code}</pre>
        </div>
    </#list>
</body>
</html>