package selab.mvc.models.entities;

import selab.mvc.models.Model;

/**
 * Created by kosar on 4/21/19.
 */
public class Enrollment implements Model {
    private Student student;
    private Course course;
    private String points;

    public Enrollment(Student student, Course course, String points) {
        this.student = student;
        this.course = course;
        this.points = points;
    }

    @Override
    public String getPrimaryKey() {
        return student.getPrimaryKey() + "$" + course.getPrimaryKey();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public String getPoints() {
        return points;
    }
}
