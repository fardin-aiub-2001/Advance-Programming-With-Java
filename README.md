Problem Of Task 1
Topics: Introducing Type Inference with Local Variables, The For-Each Version of the for Loop, Nested and Inner Classes, Varargs: Variable-Length Arguments,
Dynamic Method Dispatch, Interface Overview, Enumerations

Create a console-based University Course Registration and Reporting System in Java.

1. Define an enum CourseType with values SCIENCE, ENGINEERING, BUSINESS, and ARTS. 
2. Create an interface Reportable containing a default method printHeader() that prints "===== UNIVERSITY REPORT =====", a static method printFooter() that prints "===== END OF REPORT =====", a private method decorate(String text) that returns the given text surrounded by ***, and an abstract method generateReport(). 
3. Implement a Course class with fields courseId, courseName, CourseType type, and credit, along with constructors, getters, and a displayInfo() method. 
4. Create a Student class with fields studentId, studentName, and a Course[] named enrolledCourses, including methods enroll(Course c) and showCourses(), where showCourses() uses the for-each loop to display enrolled courses. Inside Student, create an inner class Grade containing Course course and double marks, with a method getLetterGrade() that returns A for marks 80 and above, B for 70–79, C for 60–69, D for 50–59, and F otherwise. Also create a static nested class Validator with a static method isValidId(String id) that returns true if the ID starts with "S" and has at least four characters. 
5. Demonstrate dynamic method dispatch by creating a base class User with a login() method printing "User logged in" and subclasses StudentUser and TeacherUser that override login() to print "Student logged in" and "Teacher logged in" respectively.
6. In main(), declare User u, assign it objects of both subclasses one at a time, and call login(). 
7. Create a utility class Notification with a varargs method sendMessages(String... messages) that prints all messages using a for-each loop. 
8. Finally, in the main() method, use var wherever appropriate when creating objects and iterating collections, generate a report displaying course information and enrolled courses, demonstrate user login through dynamic dispatch, send multiple notifications, and produce output similar to a university report ending with "===== END OF REPORT =====".
