# **Green Shadow Crop Monitoring System**

## **Overview**
The **Green Shadow Crop Monitoring System** is an advanced, web-based platform designed to manage and monitor agricultural activities efficiently. It offers a range of features, including crop management, field tracking, equipment and vehicle allocation, staff assignment, and detailed monitoring logs, all secured with robust role-based access control.

---

## **Features**
- **User Management**: Role-based access control for MANAGER, ADMINISTRATIVE, and SCIENTIST roles.
- **Field Management**: Manage fields with geolocation data and field images.
- **Crop Management**: Add, update, and view crop details, including scientific and common names, categories, and seasonal data.
- **Staff Management**: Assign staff to specific fields, vehicles, and equipment.
- **Equipment and Vehicle Management**: Efficiently manage and allocate resources.
- **Monitoring Logs**: Record and retrieve monitoring activities for better traceability.
- **JWT Authentication**: Token-based authentication for secure access.
- **CORS Enabled**: Secure communication between front-end and back-end applications.
- **Token Blacklisting**: Enable secure logout by invalidating tokens.

---

## **Technologies Used**

### **Backend**
- **Java 17**
- **Spring Boot** (Security, Data JPA, Web)
- **MySQL**: Database management
- **JJWT**: JSON Web Token for authentication
- **Lombok**: Minimize boilerplate code
- **Maven**: Build automation

### **Frontend**
- **HTML, CSS, JavaScript** (Vanilla JS/jQuery)

### **Tools**
- **Postman**: API testing
- **Git**: Version control
- **IntelliJ IDEA**: Development environment
- **MySQL Workbench**: Database management

---

## **Setup and Installation**

### **Prerequisites**
- Java 17 or higher
- Maven
- MySQL
- Node.js (optional for building the front-end)

### **Steps**
1. Clone the repository:
   https://github.com/menukipeiris/GreenShadow_BackEnd
2. Set up the backend and database configurations.
3. Build and run the project using Maven.
4. Optionally, build the front-end using Node.js.

---

## **API Endpoints**

### **Authentication**
- **POST** `/api/v1/auth/login`: Authenticate and get a token.
- **POST** `/api/v1/auth/refresh`: Renew an existing token.
- **POST** `/api/v1/auth/logout`: Invalidate a token for secure logout.

### **Crop Management**
- **GET** `/api/v1/crop`: Retrieve all crops.
- **POST** `/api/v1/crop`: Add a new crop.
- **PUT** `/api/v1/crop/{id}`: Update crop details.
- **DELETE** `/api/v1/crop/{id}`: Remove a crop.

### **Field Management**
- **GET** `/api/v1/field`: Retrieve all fields.
- **POST** `/api/v1/field`: Add a new field.
- **PUT** `/api/v1/field/{id}`: Update field details.
- **DELETE** `/api/v1/field/{id}`: Delete a field.

### **Equipment Management**
- **GET** `/api/v1/equipment`: List all equipment.
- **POST** `/api/v1/equipment`: Add new equipment.
- **PUT** `/api/v1/equipment/{id}`: Update equipment details.
- **DELETE** `/api/v1/equipment/{id}`: Delete equipment.

### **Monitoring Logs**
- **GET** `/api/v1/monitoring-log`: View all monitoring logs.
- **POST** `/api/v1/monitoring-log`: Add a monitoring log.
- **PUT** `/api/v1/monitoring-log/{id}`: Edit a monitoring log.
- **DELETE** `/api/v1/monitoring-log/{id}`: Remove a monitoring log.

### **Vehicle Management**
- **GET** `/api/v1/vehicle`: Get all vehicles.
- **POST** `/api/v1/vehicle`: Add a new vehicle.
- **PUT** `/api/v1/vehicle/{id}`: Update vehicle details.
- **DELETE** `/api/v1/vehicle/{id}`: Delete a vehicle.

### **Staff Management**
- **GET** `/api/v1/staff`: Retrieve all staff members.
- **POST** `/api/v1/staff`: Add a new staff member.
- **PUT** `/api/v1/staff/{id}`: Update staff information.
- **DELETE** `/api/v1/staff/{id}`: Remove a staff member.

---

## **License**
This project is licensed under the MIT License. See the `License.txt` file for more details.

