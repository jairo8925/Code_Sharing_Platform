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
* Return HTML that contains a text area for users to add a new code snippet, along with inputs for time and views restriction (optional)
\
\
```GET: /code/new```

![Alt text](images/create.png?raw=true "Create code")
<br></br><br></br>

* Return HTML that contains the code snippet (and restrictions, if applicable) given its id
\
\
```GET: /code/{id}```

![Alt text](images/get.png?raw=true "Code snippet w/o restrictions")
#### Code snippet without restrictions
<br></br><br></br>


![Alt text](images/get_with_restrictions.png?raw=true "Code snippet with restrictions")
#### Code snippet with restrictions
<br></br><br></br>

* Return HTML that contains the most recently uploaded code snippets (only contains snippets w/o restrictions), ordered from newest to oldest
\
\
```GET: /code/latest```

![Alt text](images/latest.png?raw=true "Latest code snippets")
<br></br><br></br>


## TODO

* Add a home page
* Add pagination to GET: code/latest
* Improve visual of web page
