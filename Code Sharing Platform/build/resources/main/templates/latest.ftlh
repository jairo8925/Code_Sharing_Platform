<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/atom-one-light.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <#list snippets as snippet>
        <div>
            <span id="load_date">${snippet.date}</span>
            <pre id="code_snippet"><code class="language">${snippet.code}</code></pre>
        </div>
    </#list>
</body>
</html>