package entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collegeId;

    private String collegeName;

    private String collegeAddress;



    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    private Set<Student> students = new HashSet<>();



    public College(){

    }


    public College(int collegeId, String collegeName, String collegeAddress, Set<Student> students) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.collegeAddress = collegeAddress;
        this.students = students;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeAddress() {
        return collegeAddress;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "College{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", collegeAddress='" + collegeAddress + '\'' +
                ", students=" + students +
                '}';
    }
}
