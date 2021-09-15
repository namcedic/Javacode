public abstract class Staff{

//Khai báo biến
    private String staffID;
    private String name;
    private int age;
    private double coefficientSalary;
    private String dayStart;
    private Department department;
    private int DayOff;
    protected long totalSalary;

    // Hàm abstract
    abstract void displayInformation();

   // Getter and Setter
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getCoefficientSalary() {
        return coefficientSalary;
    }
    public void setCoefficientSalary(double coefficientSalary) {
        this.coefficientSalary = coefficientSalary;
    }

    public String getDayStart() { return dayStart; }
    public void setDayStart(String dayStart) { this.dayStart = dayStart; }

    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        assert department != null;
        this.department = department;
        this.department.addStafftoDepartment(this);
    }

    public int getDayOff() {
        return DayOff;
    }
    public void setDayOff(int DayOff) {
        this.DayOff = DayOff;
    }

    public long getTotalSalary() {
        return totalSalary;
    }
    public abstract void setTotalSalary(double coefficientSalary, int basicSalary, int allowance, int overTime);
}
