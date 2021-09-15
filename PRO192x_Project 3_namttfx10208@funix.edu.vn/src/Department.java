import java.util.ArrayList;
import java.util.List;

public class Department {
    private final String departmentID;
    private String departmentName;
    private final List<Staff> staffInDepartment = new ArrayList<>();
    private int employeeInDepartment;

    public Department(String departmentName){
        if (departmentName.length() > 2) {
            this.departmentID = departmentName.substring(0, 2);
        } else {
            this.departmentID = departmentName;
        }
        this.departmentName = departmentName;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentName() { return departmentName; }

    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public int getEmployeeInDepartment() { return employeeInDepartment; }

    public List<Staff> getStaffInDepartment() { return staffInDepartment; }

    public void addStafftoDepartment(Staff employee){ this.staffInDepartment.add(employee);this.employeeInDepartment++;}

    @Override
    public String toString() {
        return  "Department: " + departmentName + "\n" +
                "Department ID: " + departmentID + "\n" +
                "Employee count: " + employeeInDepartment;
    }
}
