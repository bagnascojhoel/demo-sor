# Kenect Labs API

A simple API to test out Quarkus and Micronaut for Java API development.

## Features

```
GET /api/v1/contacts
    - ?page=1

Request headers:
    Authorization: Bearer <token>

Request body:
N/A

Response headers:
    Link: <https://example.com:8080/foo?page=1>; rel="first",
    <https://example.com:8080/foo?page=2>; rel="prev",
    <https://example.com:8080/foo?page=4>; rel="next",
    <https://example.com:8080/foo?page=50>; rel="last"
    Current-Page: 3
    Page-Items: 20
    Total-Pages: 50
    Total-Count: 1000

Response body:
{
    "contacts": [
        {
            "id": 1,
            "name": "jmadsen",
            "email": "jmadsen@kenect.com",
            "created_at": "2020-06-25T02:29:23.755Z",
            "updated_at": "2020-06-25T02:29:23.755Z"
        },
        ...
    ]
}

```
> The pagination should start at 1, not 0.

> All contacts are stored in the contacts.json file.