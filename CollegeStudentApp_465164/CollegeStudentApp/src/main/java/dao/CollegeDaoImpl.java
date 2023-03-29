package dao;

import entities.College;
import entities.Student;
import exceptions.CollegeException;
import exceptions.StudentException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import utilities.EMUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CollegeDaoImpl implements CollegeDao{
    @Override
    public College registerCollege(College college) throws CollegeException {
        EntityManager em = EMUtil.provideEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(college);
            em.getTransaction().commit();
            return college;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new CollegeException("Error in registering college!"+ e);
        } finally {
            em.close();
        }
    }

    @Override
    public College getCollegeFromCollegeId(int collegeId) throws CollegeException {

           EntityManager entityManager = EMUtil.provideEntityManager();

           try{
               College clg= entityManager.find(College.class,collegeId);
               if(clg==null)  throw new CollegeException("College not found!");
               else return clg;
           }
           catch (EntityNotFoundException e) {
               throw new CollegeException("College not found!"+ e);
           } catch (Exception e) {
               throw new CollegeException("Error in getting college!"+e);
           } finally {
               entityManager.close();
           }
    }

    @Override
    public List<College> getAllCollege() throws CollegeException {

        EntityManager entityManager = EMUtil.provideEntityManager();

        try {
            List<College> colleges=entityManager.createQuery("from College",College.class).getResultList();
            if(colleges.isEmpty())
                throw new CollegeException("No college exists in database");

            return colleges;
        }
        catch (Exception e){

            throw new CollegeException("Error in getting all colleges!"+ e);
        }
        finally {
            entityManager.close();
        }


    }

    @Override
    public Student registerStudentToCollege(int studentId, int collegeId) throws CollegeException, StudentException {
        EntityManager em = EMUtil.provideEntityManager();


        try {
            Student student = em.find(Student.class, studentId);
            College college = em.find(College.class, collegeId);

            if(student==null)
                throw new StudentException("No student exists with studentId : "+studentId);

                if(college==null) throw new CollegeException("No college exists with college id"+collegeId);

            em.getTransaction().begin();
            student.setCollege(college);
            em.getTransaction().commit();
            return student;
        }
        catch (Exception e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new StudentException("Error in student"+ e);

        }
        finally {
            em.close();
        }
    }

    @Override
    public List<Student> getAllStudentsFromCollegeId(int collegeId) throws CollegeException, StudentException {

        EntityManager em = EMUtil.provideEntityManager();

        try {

            College college = em.find(College.class, collegeId);


            if (college == null)
                throw new CollegeException("College not found");

            List<Student> students=new ArrayList<>(college.getStudents());
            if(students.isEmpty())
                throw new StudentException("No Student exists in college");

            return students;
        }
        catch (StudentException | CollegeException e) {
             throw new CollegeException("Error in getting all colleges!"+ e);

        }
        finally {

            em.close();
        }
    }
}
