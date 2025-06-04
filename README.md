# E-Course Learning Management System - Backend

A comprehensive **Learning Management System (LMS)** backend built with **Spring MVC** and **Java 11+**. This system provides a robust platform for managing online courses, handling student enrollments, tracking learning progress, conducting assessments, and issuing certificates.

<div align="center">
    <img src="https://github.com/user-attachments/assets/d0c7ea30-45be-4479-8a12-a2a024f6ef7e" alt="E-Course Platform Logo" width="400">
</div>

## 🛠️ Technology Stack & Skills

<p align="center">
    <img src="https://skillicons.dev/icons?i=java,spring,mysql,hibernate,html,css,js,maven,git&theme=light" />
</p>

<p align="center">
    <a href="https://choosealicense.com/licenses/mit/">
        <img src="https://img.shields.io/badge/License-MIT-green.svg" alt="MIT License">
    </a>
    <a href="https://github.com/your-repo/E_Course_Backend/actions">
        <img src="https://img.shields.io/badge/build-passing-brightgreen" alt="Build Status">
    </a>
    <a href="https://www.oracle.com/java/">
        <img src="https://img.shields.io/badge/Java-11%2B-orange" alt="Java Version">
    </a>
    <a href="https://spring.io/">
        <img src="https://img.shields.io/badge/Spring-6.2.5-green" alt="Spring Version">
    </a>
    <a href="https://www.mysql.com/">
        <img src="https://img.shields.io/badge/MySQL-8.0-blue" alt="MySQL">
    </a>
</p>

## ✨ Key Features

### 👥 **User Management**

- **Multi-role authentication system** (Admin, Lecturer, Student)
- Secure user registration and login with **Spring Security**
- Profile management with avatar upload via **Cloudinary**
- Password encryption and user session management

### 📚 **Course Management**

- **Complete course lifecycle management** (Create, Read, Update, Delete)
- Course categorization and search functionality
- **Multi-lecturer assignment** to courses
- Course enrollment and student management
- **Progress tracking** and completion analytics

### 🎓 **Learning Content**

- **Lesson management** with multimedia support
- **File attachments** for learning materials
- Video embedding for online lectures
- **Structured learning paths** with lesson ordering

### 📝 **Assessment System**

- **Exercise creation and management**
- **Multiple choice and writing questions**
- **Automated grading system**
- Exercise attempt tracking and scoring
- **Test management** with time limits

### 📊 **Progress Tracking**

- **Real-time learning progress calculation**
- Lesson completion tracking
- **Course completion certificates**
- Student performance analytics

### 💳 **Payment Integration**

- **Stripe payment gateway** integration
- Course purchase and enrollment workflow
- Payment receipt management
- **Secure transaction processing**

### 🏆 **Certification System**

- **Automatic certificate generation** upon course completion
- **PDF certificate downloads**
- Certificate verification system
- **Digital certificate management**

### 📱 **API & Frontend**

- **RESTful API architecture**
- **Thymeleaf templating** for server-side rendering
- **Responsive web interface**
- **JSON API endpoints** for mobile/frontend integration

## 🏗️ System Architecture

### **Core Entities**

- **User** → Admin, Lecturer, Student (Role-based hierarchy)
- **Course** → Lessons → Exercises/Tests
- **Enrollment** → Progress tracking → Certificates
- **Assessment** → Questions → Attempts → Scores

### **Technology Stack**

```
Frontend: Thymeleaf + HTML/CSS/JavaScript
Backend: Spring MVC 6.2.5 + Spring Security 6.4.4
ORM: Hibernate 6.6.13 + JPA
Database: MySQL 8.0
Build Tool: Maven
Cloud Storage: Cloudinary
Payment: Stripe API
Certificate: PDF Generation
```

## 🚀 Installation & Setup

### **Prerequisites**

- **Java 11** or higher
- **Maven 3.6+**
- **MySQL 8.0+**
- **Git**

### **Database Setup**

1. Create MySQL database:

```sql
CREATE DATABASE ecourse;
```

2. Run database schema:

```bash
mysql -u root -p ecourse < etc/db/create-db.sql
mysql -u root -p ecourse < etc/db/insert-data.sql
```

### **Environment Configuration**

1. Rename `application_example.properties` to `application.properties` and configure :

```properties
# Database Configuration
hibernate.connection.username=
hibernate.connection.password=

# JWT Configuration
jwt.secret=

# Stripe Configuration
Stripe.apiKey=
Stripe.publishableKey=
Stripe.webhookKey=

# Cloudinary Configuration
cloudinary.name=
cloudinary.api_key=
cloudinary.api_secret=
```

### **Build & Run**

```bash
# Clone the repository
git clone <repository-url>
cd E_Course/Backend

# Install dependencies
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

## 📁 Project Structure

```
src/main/java/com/ou/
├── configs/           # Spring configuration classes
├── controllers/       # REST and Web controllers
│   ├── restController/    # API endpoints
│   └── webController/     # Web page controllers
├── dto/              # Data Transfer Objects
├── exceptions/       # Custom exception classes
├── filters/          # Security and request filters
├── formatters/       # Data formatters
├── formBean/         # Form binding objects
├── helpers/          # Utility helper classes
├── mappers/          # Entity ↔ DTO mappers
├── pojo/             # Entity classes (JPA)
├── repositories/     # Data access layer
│   └── impl/             # Repository implementations
├── services/         # Business logic layer
│   └── impl/             # Service implementations
└── utils/            # Utility classes

src/main/resources/
├── static/           # CSS, JS, Images
├── templates/        # Thymeleaf templates
├── application.properties
└── META-INF/persistence.xml
```

## 🔌 API Endpoints

### **Authentication**

```
POST /api/login                    # User authentication
POST /api/user/create              # User registration
```

### **Courses**

```
GET  /api/course-list              # List all courses
GET  /api/course/{id}              # Get course details
POST /api/secure/course/{id}/review # Submit course rating
```

### **Learning**

```
GET  /api/secure/{courseId}/lessons                    # Get course lessons
POST /api/secure/courses/{courseId}/lessons/{lessonId}/mark-learned # Mark lesson complete
GET  /api/secure/enrolled-courses                     # Get enrolled courses
```

### **Certificates**

```
GET /api/secure/certificates                   # Get user certificates
GET /api/secure/certificates/course/{courseId} # Get course certificates
```

### **Payments**

```
POST /api/secure/create-checkout-session       # Create Stripe checkout
POST /api/stripe/webhook                       # Stripe webhook handler
```

## 🔐 Security Features

- **Spring Security 6.4.4** implementation
- **JWT token-based authentication**
- **Role-based access control** (RBAC)
- **Password encryption** using BCrypt
- **CSRF protection** enabled
- **SQL injection prevention** via JPA/Hibernate
- **Input validation** and sanitization

## 📊 Database Schema

### **Core Tables**

- `user` → User accounts and profiles
- `course` → Course information and metadata
- `lesson` → Course content and materials
- `course_student` → Enrollment and progress tracking
- `course_lecturer` → Instructor assignments
- `exercise` → Assessment and quiz data
- `course_certificate` → Certification records

## 📈 Performance & Scalability

- **Pagination support** for large datasets
- **Connection pooling** for database efficiency
- **Lazy loading** for entity relationships
- **Transaction management** with Spring

## 🤝 Contributing

1. **Fork** the repository
2. Create your **feature branch** (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. Open a **Pull Request**

Please ensure your code follows our coding standards and includes appropriate tests.

## 📋 Development Guidelines

- Follow **Spring Boot best practices**
- Use **proper exception handling**
- Implement **comprehensive logging**
- Write **unit and integration tests**
- Follow **RESTful API design principles**
- Maintain **clean code architecture**

## 🔄 Deployment

### **Production Configuration**

1. Update `application.properties` for production
2. Configure SSL certificates
3. Set up reverse proxy (Nginx)
4. Configure database connection pooling
5. Enable application monitoring

## 📞 Support & Contact

For questions, bug reports, or feature requests:

- **Email**:
- **GitHub Issues**: [Create an issue](../../issues)
- **Documentation**:

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Spring Framework](https://spring.io/) - Comprehensive programming framework
- [Hibernate](https://hibernate.org/) - Object-relational mapping
- [Thymeleaf](https://www.thymeleaf.org/) - Server-side Java template engine
- [Stripe](https://stripe.com/) - Payment processing platform
- [Cloudinary](https://cloudinary.com/) - Cloud-based image and video management

---
