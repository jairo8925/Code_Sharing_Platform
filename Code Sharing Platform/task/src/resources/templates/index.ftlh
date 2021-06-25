<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${date}</span>
    <p><span id="views_restriction">${views}</span> more views allowed </p>
    <p>The code will be available for <span id="time_restriction">${time}</span> seconds</p>
    <pre id="code_snippet"><code class="language-java">${code}</code></pre>
</body>
</html>