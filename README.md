# Departments Microservice

A microservice demonstrating communication between a parent-child relationship between Faculties and Departments entities.
Using RestTemplate, retrieving Faculty data from another microservice and showing results for when a user wants to get all departments by searching faculty name.
Faculty endpoint is called, data retrieved and finding matches in Departments where Faculty ID's match.

Also, basic CRUD operations are implemented.
Endpoints look like:
localhost:8081/departments/