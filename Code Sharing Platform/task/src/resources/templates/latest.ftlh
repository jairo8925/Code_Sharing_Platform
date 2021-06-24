<html lang="en">
<head>
    <title>Latest</title>
    <style>
        pre {
            margin-top: 2px;
            font-family: "Lucida Console", monospace;
            width: 45%;
            padding: 10px 15px 10px 15px;
            background-color: rgb(240, 240, 240);
            border: 2px solid gray;
            line-height: 1.8em;
            font-size: 16px;
        }

        span {
            font-family: "Lucida Console", monospace;
            font-size: 18px;
            color: green;
        }

        div {
            margin: 15px 5px 30px 5px;
        }
    </style>
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