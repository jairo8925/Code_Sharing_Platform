<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet" href="/css/styles.css">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/atom-one-light.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${code.date}</span>
    <#if code.viewsRestricted == true>
        <p><b><span id="views_restriction">${code.views}</span></b> more views allowed</p>
    </#if>
    <#if code.timeRestricted == true>
        <p>The code will be available for <b><span id="time_restriction">${code.time}</span></b> seconds</p>
    </#if>
    <pre id="code_snippet"><code class="language">${code.code}</code></pre>
</body>
</html>