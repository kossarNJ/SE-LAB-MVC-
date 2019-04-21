package selab.mvc.models.entities;

import selab.mvc.models.DataSet;
import selab.mvc.models.Model;

import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;

    private DataSet<Enrollment> enrollments = new DataSet<>();

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public void removeStudentFromCourses() {
        for (Enrollment e : this.enrollments.getAll()) {
            e.getCourse().removeEnrollment(e);
        }
    }

    public float getAverage() {
        float avg = 0;
        for (Enrollment e : this.enrollments.getAll()) {
            avg += Integer.parseInt(e.getPoints());
        }
        if (this.enrollments.getAll().size() > 0) {
            avg /= this.enrollments.getAll().size();
        }
        return avg;
    }

    public String getCourses() {
        String result = "";
        for(Enrollment e : this.enrollments.getAll()) {
            result += ", " + e.getCourse().getTitle();
        }
        if (result.length() > 0) {
            return result.substring(2);
        }
        return "-";
    }

    public void removeEnrollment(Enrollment e) {
        this.enrollments.remove(e);
    }

    public void addCourse(Enrollment enrollment) {
        this.enrollments.add(enrollment);
    }

    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }
}
