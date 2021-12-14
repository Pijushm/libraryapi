# libraryapi
A library management system rest api

Endpoints
GET   api/v1/books
GET   api/v1/books/{bookid}
GET   api/v1/books/isbn/{bookisbn}
GET   api/v1/users
GET   api/v1/users/{userid}
GET   api/v1/issuecards
GET   api/v1/issuecards/{issuecard}
GET   api/v1/issuecards/{issuecard}/books
GET   api/v1/users/{userid}/issuecards/
GET   api/v1/users/{userid}/books
GET   api/v1/books/{bookisbn}/users
POST  api/v1/books
POST  api/v1/users
POST  api/v1/users/{userid}/issuecards
PUT   api/v1/books/{bookid}
PUT   api/v1/users/{userid}
PUT   api/v1/{userid}/submission/issuecards/{issuecard}
DELETE api/v1/book/{bookid}    
DELETE api/v1/users/{userid}

