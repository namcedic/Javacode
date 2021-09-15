
public class Manager extends Staff implements ICalculator{
    private String managerPosition;
    private int basicSalary = 5000000;

    public String getManagerPosition() { return managerPosition; }

    public void setManagerPosition(String managerPosition) { this.managerPosition = managerPosition; }

    public int getBasicSalary() { return basicSalary; }

    public void setBasicSalary(int basicSalary) { this.basicSalary = basicSalary; }

    @Override
    public void setTotalSalary(double coefficientSalary, int basicSalary, int allowance, int overTime) {
        this.totalSalary = this.calculateSalary(coefficientSalary, basicSalary, allowance, 0);
    }

    @Override
    void displayInformation() {
        System.out.println("Staff ID: " + this.getStaffID());
        System.out.println("Staff Name: " + this.getName());
        System.out.println("Age: " + this.getAge());
        System.out.println("Position: " + this.getManagerPosition());
        System.out.println("Salary Coefficient: " + this.getCoefficientSalary());
        System.out.println("Day Start: " + this.getDayStart());
        System.out.println("Department: " + this.getDepartment().getDepartmentName());
        System.out.println("Day Off: " + this.getDayOff());
//        System.out.println("Salary: ");
        System.out.format("Salary: %,d%n",this.getTotalSalary());
    }

    @Override
    public long calculateSalary(double coefficientSalary, int basicSalary, int allowance, int overTime) {
        return (long)(coefficientSalary * basicSalary + allowance + overTime * 200000L);
    }
}
