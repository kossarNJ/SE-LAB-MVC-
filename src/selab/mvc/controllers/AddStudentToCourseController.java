package selab.mvc.controllers;

import org.json.JSONObject;
import selab.mvc.models.DataContext;
import selab.mvc.models.entities.Course;
import selab.mvc.models.entities.Enrollment;
import selab.mvc.models.entities.Student;
import selab.mvc.views.StaticHtmlView;
import selab.mvc.views.View;

import java.io.IOException;
import java.io.InputStream;

public class AddStudentToCourseController extends Controller {

    public AddStudentToCourseController(DataContext dataContext) {
        super(dataContext);
    }

    @Override
    public View exec(String method, InputStream body) throws IOException {
        if (!method.equals("POST"))
            throw new IOException("Method not supported");

        JSONObject input = readJson(body);
        String studentNo = input.getString("studentNo");
        String courseNo = input.getString("courseNo");
        String points = input.getString("points");

        Student student = this.dataContext.getStudents().get(studentNo);
        Course course = this.dataContext.getCourses().get(courseNo);
        Enrollment enrollment = new Enrollment(student, course, points);


        student.addCourse(enrollment);
        course.addStudent(enrollment);

        return new StaticHtmlView("static/index.html");

    }
}
