public class Main {
    static void main(String[] args){
        User u= new User();
        User u1=new StudentUser();
        User u2=new TeacherUser();
        u.Login();
        u1.Login();
        u2.Login();

        var c1=new Course(1,"OOP1",CourseType.SCIENCE,3);
        var c2=new Course(2,"OOP2",CourseType.SCIENCE,3);

        var s1 = new Student("S1234", "Fardin", 5);
        s1.enroll(c1);
        s1.enroll(c2);

        var g1 = s1.new Grade(c1, 85);
        var g2 = s1.new Grade(c2, 72);
        g1.getLetterGrade();
        g2.getLetterGrade();

        s1.generateReport();
        Notification.sendMessages(
                "Course registration successful",
                "Grades have been published",
                "University report generated"
        );
    }
}
