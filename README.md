# Code Sharing Platform

An application that allows users to create, share, and access code on the web. Code snippets are stored in the database and deleted if they have a time and/or views restriction that is met.

## API Endpoints
* Get JSON of the code snippet given its id
\
\
```GET: /api/code/{id}```

* Get the JSON array of the 10 most recently uploaded code snippets, ordered from newest to oldest
\
\
```GET: /api/code/latest```

* Upload a new code snippet given a JSON object request body that contains the code snippet, time restriction (optional), and views restriction (optional).
Returns the generated unique id of the code snippet
\
\
```POST: /api/code/new```


## Web Endpoints
* Return HTML that contains the code snippet (and restrictions, if applicable) given its id
\
\
```GET: /code/{id}```


* Return HTML that contains the 10 most recently uploaded code snippets, ordered from newest to oldest
\
\
```GET: /code/latest```


* Return HTML that contains a text area for users to add a new code snippet, along with inputs for time and views restriction (optional)
\
\
```GET: /code/new```



## TODO

* Add pictures of the application
* Add a home page
* Improve visual of web page
