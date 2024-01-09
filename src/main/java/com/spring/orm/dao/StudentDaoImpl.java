package com.spring.orm.dao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.orm.hibernate5.HibernateTemplate;
import com.spring.orm.entities.Student;

// Data Access Object (DAO) for interacting with Student entities in the database
public class StudentDaoImpl {

    // HibernateTemplate is used to interact with Hibernate ORM
    private HibernateTemplate hibernateTemplate;

    // Transactional annotation ensures that this method participates in a transaction
    @Transactional
    // Insert a new Student entity into the database
    public int insert(Student student) {
        // Save the student entity and get the generated identifier (ID)
        Integer generatedId = (Integer) this.hibernateTemplate.save(student);
        return generatedId;
    }

    // Retrieve a Student entity from the database based on studentId
    public Student getStudent(int studentId) {
        // Use HibernateTemplate's get method to retrieve the Student entity by ID
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        return student;
    }

    // Retrieve all Student entities from the database
    public List<Student> getAllStudents() {
        // Use HibernateTemplate's loadAll method to get a list of all Student entities
        List<Student> students = this.hibernateTemplate.loadAll(Student.class);
        return students;
    }

    // Transactional annotation ensures that this method participates in a transaction
    // Delete a Student entity from the database based on studentId
    @Transactional
    public void deleteStudent(int studentId) {
        // Get the Student entity by ID
        Student student = this.hibernateTemplate.get(Student.class, studentId);
        // Delete the Student entity
        this.hibernateTemplate.delete(student);
    }

    // Transactional annotation ensures that this method participates in a transaction
    // Update an existing Student entity in the database
    @Transactional
    public void update(Student student) {
        // Use HibernateTemplate's update method to update the Student entity
        this.hibernateTemplate.update(student);
    }

    // Getter method for HibernateTemplate
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    // Setter method for HibernateTemplate
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}

