package entities;


import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentRoll;

    private String studentName;

    private String mobileNumber;

    private String email;

    private boolean status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="collegeId")
    private College college;


    public Student() {
    }

    public Student(String studentName, String mobileNumber, String email, boolean status) {
        this.studentName = studentName;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.status = status;
    }


    public int getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(int studentRoll) {
        this.studentRoll = studentRoll;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentRoll=" + studentRoll +
                ", studentName='" + studentName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", college=" + college +
                '}';
    }
}
