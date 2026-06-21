public class Course {
    private int courseId;
    private String courseName;
    private CourseType type;
    private int credit;

    public Course(int courseId,String courseName,CourseType type,int credit){
        this.courseId=courseId;
        this.courseName=courseName;
        this.credit=credit;
        this.type= type;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getType() {
        return type;
    }

    public void setType(CourseType type) {
        this.type = type;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void displayInfo(){
        System.out.println("Course Id :"+this.courseId);
        System.out.println("Course Name :"+this.courseName);
        System.out.println("Credit :"+this.credit);
    }
}
