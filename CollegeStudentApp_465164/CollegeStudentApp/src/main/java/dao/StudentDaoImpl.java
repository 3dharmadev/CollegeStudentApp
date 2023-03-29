package dao;

import entities.College;
import entities.Student;
import exceptions.CollegeException;
import exceptions.StudentException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import utilities.EMUtil;

import java.util.List;

public class StudentDaoImpl implements StudentDao{
    @Override
    public Student registerStudent(Student student) throws StudentException {
        EntityManager em= EMUtil.provideEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return student;
        }
        catch (Exception e){
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new StudentException("Error registering student"+ e);
        }
        finally {
            em.close();
        }

    }

    @Override
    public Student getStudentFromStudentId(int studentId) throws StudentException {
        EntityManager em = EMUtil.provideEntityManager();
        try {
           Student student= em.find(Student.class, studentId);
           if(student==null)            throw new StudentException("no student exists with id"+ studentId);
           else return student;
        } catch (Exception e) {
            throw new StudentException("Error getting student"+ e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Student> getAllActiveStudents() throws StudentException {
        EntityManager em = EMUtil.provideEntityManager();
        try {
          TypedQuery<Student> studentTypedQuery = em.createQuery("SELECT s FROM Student s WHERE s.status = true",Student.class);
          if(studentTypedQuery.getResultList().isEmpty())
              throw new StudentException("No student found in database");

            return studentTypedQuery.getResultList();
        } catch (Exception e) {
            throw new StudentException("Error getting students"+ e);
        } finally {
            em.close();
        }
    }

    @Override
    public College getCollegeFromStudentId(int studentId) throws CollegeException, StudentException {

        EntityManager em = EMUtil.provideEntityManager();
        try {

            Student student= em.find(Student.class,studentId);
            if(student==null)
                throw new StudentException("No student found with studentId : "+studentId);
            if(student.getCollege()==null)
                throw new CollegeException("Student is not studying in any college");


            return student.getCollege();
        } catch (Exception e) {
            throw new StudentException("Error getting student"+ e);
        } finally {
            em.close();
        }

    }
}
