public class Student implements Reportable{
    private String studentId;
    private String studentName;
    private Course[] enrolledCourses;
    private int courseCount;

    public Student(String studentId,String studentName,int maxCourses) {
        this.studentName=studentName;
        this.studentId=studentId;
        this.enrolledCourses = new Course[maxCourses];
        this.courseCount = 0;
    }

    public void enroll(Course c) {
        if (courseCount < enrolledCourses.length) {
            enrolledCourses[courseCount] = c;
            courseCount++;
            System.out.println(studentName + " enrolled in " + c.getCourseName());
        } else {
            System.out.println("Course limit reached!");
        }
    }
    public void showCourses( ){
        System.out.println("Course enrolled by "+this.studentName+" : ");
        for(Course course : enrolledCourses){
            if(course!=null){
                System.out.println(course.getCourseName());
            }
        }
    }

    //Inner Class
    public class Grade{
        private Course course;
        private double marks;
        public Grade(Course course, double marks){
            this.course=course;
            this.marks=marks;
        }
        public String getLetterGrade(){
            if(this.marks>=80){
                return "A";
            }else if(this.marks>=70){
                return "B";
            }else if(this.marks>=60){
                return "C";
            }
            else if(this.marks>=50){
                return "D";
            }else{
                return "F";
            }
        }

        static class Validator{
            static boolean isValidId(String id){
                return id != null && id.startsWith("S") && id.length() >= 4;
            }
        }

    }
    public void generateReport(){
        printHeader();
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + studentName);
        showCourses();
        Reportable.printFooter();
    }
}
