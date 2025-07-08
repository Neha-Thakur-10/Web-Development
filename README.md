# 🎓 Student Registration and Reporting System

A full-stack web application for managing student data with robust validation, dynamic form rendering, database integration, and professional PDF report generation. Built using Spring Boot, Thymeleaf, MySQL, and DynamicReports.

---

## 📌 Features

- 📝 Student registration form with field-level validation
- 🛡 Secure backend validation using Jakarta Bean Validation
- 🧩 CRUD operations (Create, Read, Update, Delete)
- 📄 PDF report generation using DynamicReports and JasperReports
- 🌐 API support for JSON-based data submission
- 🖥 Responsive UI built with Bootstrap 5

---

## 🛠 Tech Stack

| Layer         | Technology             |
|--------------|------------------------|
| Frontend     | Thymeleaf, HTML, CSS, Bootstrap 5 |
| Backend      | Spring Boot, Java      |
| Validation   | Jakarta Validation API |
| Database     | MySQL                  |
| ORM          | Spring Data JPA        |
| Reporting    | DynamicReports, JasperReports |
| Testing      | Postman                |

---

## 🚀 How It Works

### 1. Registration
- User visits `/` endpoint → `register.html`
- Validates input using HTML attributes + Jakarta annotations
- On success → stores student data in MySQL DB

### 2. Listing
- `/UserData` endpoint displays all students in a Bootstrap-styled table

### 3. Editing
- `/edit/{id}` loads editable form → validates on update → redirects to `/UserData`

### 4. Deletion
- Deletes student via `/delete/{id}` → success flash message

### 5. Reporting
- `/report` generates a tabular PDF listing student details

---

## ⚙️ Validation Strategy

**Client Side**:  
- HTML5 patterns and attributes  
- Bootstrap feedback elements

**Server Side**:  
- Jakarta annotations like `@Size`, `@Pattern`, `@NotBlank`  
- Controlled via `@Valid` and `BindingResult` in the controller

**Global Error Handling**:
- Catches `ConstraintViolationException` for invalid API calls

---

## 📄 Report Fields

- Sr. No  
- Full Name  
- Date of Birth  
- Gender  
- Education  
- Contact Number  
- Address

---

## ✅ Future Enhancements

- Authentication (Admin login)
- File uploads (profile pictures, certificates)
- Excel/CSV export
- Charts and dashboards
- Pagination and advanced filtering

---

---

## 📷 Screenshots 
![Screenshot (223)](https://github.com/user-attachments/assets/fe121d8e-b8a7-49f7-b2df-3bd93d61d9c0)

This was an Front end of Student Registration form 

 ![Screenshot (224)](https://github.com/user-attachments/assets/25a31799-72e9-46e3-bc3e-7456a65eee74)
After Submiting the register form user shift to this context where it can saw all data of students also here user are perform any curd options 

![Screenshot (225)](https://github.com/user-attachments/assets/8f7e9469-8292-4aeb-b572-814b770dfd2e)
 if user click on Download format button then they see a another format to create this data into pdf form like shown below 
 
![Screenshot (228)](https://github.com/user-attachments/assets/6fcedfcf-6be8-454b-ba3c-422d5f46e1ef)




