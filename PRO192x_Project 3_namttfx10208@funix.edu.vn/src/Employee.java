public class Employee extends Staff implements ICalculator{
    private int basicSalary = 3000000;
    private int overTime;

    @Override
    void displayInformation() {
        System.out.println("Staff ID: " + this.getStaffID());
        System.out.println("Staff Name: " + this.getName());
        System.out.println("Age: " + this.getAge());
        System.out.println("Salary Coefficient: " + this.getCoefficientSalary());
        System.out.println("Day Start: " + this.getDayStart());
        System.out.println("Department: " + this.getDepartment().getDepartmentName());
        System.out.println("Day Off: " + this.getDayOff());
        System.out.println("Over Time: " + this.getOverTime());
        System.out.format("Salary: %,d%n",this.getTotalSalary());
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public int getOverTime() { return overTime; }

    public void setOverTime(int overTime) { this.overTime = overTime; }

    @Override
    public void setTotalSalary(double coefficientSalary, int basicSalary, int allowance, int overTime) {
        this.totalSalary = this.calculateSalary(coefficientSalary, basicSalary, 0, overTime);
    }

    @Override
    public long calculateSalary(double coefficientSalary, int basicSalary, int responsibilitySalary, int overTime) {
        return (long)(coefficientSalary * basicSalary + overTime * 200000L + responsibilitySalary);
    }
}
