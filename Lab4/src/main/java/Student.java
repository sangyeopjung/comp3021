public class Student extends Person {
    private int id;
    private String major;

    public Student(String name, int age, int id, String major) {
        super(name, age);
        this.id = id;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", major='" + major + '\'' +
                '}';
    }
}
