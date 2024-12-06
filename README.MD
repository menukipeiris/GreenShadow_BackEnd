Green Shadow Crop Monitoring System
Overview
The Green Shadow Crop Monitoring System is a robust web application designed to streamline agricultural operations. It provides tools for crop management, field tracking, resource allocation, staff assignments, and activity monitoring, all secured through advanced role-based access control.

Key Features

User Management: Role-based access for MANAGER, ADMINISTRATIVE, and SCIENTIST users.
Field Management: Manage fields with geolocation support and field images.
Crop Management: Add, update, and view crop details, including scientific names, common names, categories, and seasonal information.
Staff Assignments: Allocate staff to specific fields, vehicles, and equipment.
Equipment and Vehicle Management: Manage and assign resources efficiently.
Monitoring Logs: Record and review detailed monitoring activities.
JWT Authentication: Secure user authentication with token-based access.
CORS Support: Enable secure communication between the front end and API.
Token Blacklisting: Secure logout by invalidating tokens.
Technologies

Backend:

Java 17
Spring Boot (Security, Data JPA, Web)
MySQL
JJWT (JSON Web Token for authentication)
Lombok (to reduce boilerplate code)
Maven (build tool)
Frontend:

HTML, CSS, JavaScript (Vanilla JS/jQuery)
Tools:

Postman (API testing)
Git (version control)
IntelliJ IDEA (IDE)
MySQL Workbench (database management)
Setup and Installation

Prerequisites

Java 17 or later
Maven
MySQL
Node.js (optional for front-end builds)
Steps

Clone the repository:
bash
Copy code
git clone https://github.com/menukipeiris/GreenShadow_BackEnd
cd green-shadow-crop-monitoring  
API Endpoints

Authentication

POST /api/v1/auth/login: Authenticate and retrieve a token.
POST /api/v1/auth/refresh: Renew an existing token.
POST /api/v1/auth/logout: Invalidate a token to securely log out.
Crop Management

GET /api/v1/crop: Retrieve all crops.
POST /api/v1/crop: Add a new crop.
PUT /api/v1/crop/{id}: Update crop details.
DELETE /api/v1/crop/{id}: Remove a crop.
Field Management

GET /api/v1/field: Retrieve all fields.
POST /api/v1/field: Add a new field.
PUT /api/v1/field/{id}: Edit field details.
DELETE /api/v1/field/{id}: Delete a field.
Equipment Management

GET /api/v1/equipment: Get a list of all equipment.
POST /api/v1/equipment: Add new equipment.
PUT /api/v1/equipment/{id}: Update equipment details.
DELETE /api/v1/equipment/{id}: Remove equipment.
Monitoring Logs

GET /api/v1/monitoring-log: Fetch all monitoring logs.
POST /api/v1/monitoring-log: Create a new monitoring log.
PUT /api/v1/monitoring-log/{id}: Edit monitoring log details.
DELETE /api/v1/monitoring-log/{id}: Delete a monitoring log.
Vehicle Management

GET /api/v1/vehicle: Retrieve all vehicles.
POST /api/v1/vehicle: Add a new vehicle.
PUT /api/v1/vehicle/{id}: Update vehicle details.
DELETE /api/v1/vehicle/{id}: Remove a vehicle.
Staff Management

GET /api/v1/staff: Fetch all staff members.
POST /api/v1/staff: Add a new staff member.
PUT /api/v1/staff/{id}: Update staff details.
DELETE /api/v1/staff/{id}: Remove a staff member.
This system is designed for efficiency, security, and scalability, making it a perfect solution for modern agricultural operations.






