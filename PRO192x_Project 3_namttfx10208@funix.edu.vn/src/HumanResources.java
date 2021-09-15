import java.util.*;
public class HumanResources {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Khai báo mảng
        ArrayList<Staff> staffArrayList = new ArrayList<>();
        Set<Department> departmentArraySet = new HashSet<>();
        Department dpment;

        ArrayList<String> managerPosition = new ArrayList<>();
        managerPosition.add("Business Leader");
        managerPosition.add("Project Leader");
        managerPosition.add("Technical Leader");

     /*
     vòng lặp điều khiển
     các lựa chọn để sử dụng
      */
        int select;
        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Show all employees");
            System.out.println("2. Show all departments");
            System.out.println("3. Show employees for each department");
            System.out.println("4. Adding new employee");
            System.out.println("5. Search employee by name or ID ");
            System.out.println("6. Arrange salary in descending order");
            System.out.println("7. Arrange salary in ascending order");
            System.out.println("8. Exit");

            System.out.print("Your choice: ");
            select = Integer.parseInt(sc.next());
            // Các lựa chọn trường hợp
            switch (select) {
                case 1:
                    System.out.println("All staff of the company");
                    showStaffList(staffArrayList);
                    System.out.println("-------------------------------------");
                    break;
                case 2:
                    System.out.println("List of departments");
                    showDepartmentList(departmentArraySet);
                    System.out.println("-------------------------------------");
                    break;
                case 3:
                    System.out.println("Show employees for each department");
                    showStaffFromDepartment(departmentArraySet);
                    System.out.println("-------------------------------------");
                    break;
                case 4:
                    System.out.print("Department Name: ");
                    String newDepartName = sc.next();
                    sc.nextLine();
                    dpment = departmentArraySet.stream().filter(n -> newDepartName
                            .equalsIgnoreCase(n.getDepartmentName())).findFirst().orElse(new Department(newDepartName));
                    departmentArraySet.add(dpment);
                    staffArrayList.add(addNew(managerPosition, dpment, sc));
                    System.out.println("-------------------------------------");
                    break;
                case 5:
                    Staff memberFound = findStaff(staffArrayList, sc);
                    if (memberFound == null) {
                        System.out.println("No employee founded");
                    } else {
                        memberFound.displayInformation();
                    }
                    System.out.println("-------------------------------------");
                    break;
                case 6:
                    System.out.println("Arrange salary in descending order");
                    showSalarySheetReverse(staffArrayList);
                    System.out.println("-------------------------------------");
                    break;
                case 7:
                    System.out.println("Arrange salary in ascending order");
                    showSalarySheet(staffArrayList);
                    System.out.println("-------------------------------------");
                    break;
                case 8:
                default:
                    break;
            }
        } while (select != 8);
        sc.close();
    }
    /*
    Thêm nhân viên hoặc thêm quản lý vào Staff
     */
    public static Staff addNew(ArrayList<String> title ,Department department,Scanner sc) {
        System.out.println("Adding staff: 1- Employee | 2- Manager ");
        int choice = 0;
        Staff employee;
        if (sc.hasNextInt()){
            choice = sc.nextInt();
        } else {
            sc.next();
        }
        while (choice != 1 && choice != 2) {
            System.out.print("Please input 1 or 2: ");
            if (sc.hasNextInt()){
                choice = sc.nextInt();
            } else {
                sc.next();
            }
        }
        if (choice == 1) {
            employee = addNewEmployee(department, sc);
        } else {
            employee = addNewManager(title ,department, sc);
        }
        return employee;
    }

    //Tạo đối tượng employee để lưu thông tin nhân viên
    public static Employee addNewEmployee(Department department,Scanner sc) {
        Employee employee = new Employee();
        employee.setDepartment(department);
        System.out.print("Staff ID: ");
        employee.setStaffID(sc.next());
        sc.nextLine();
        System.out.print("Name: ");
        employee.setName(sc.nextLine());
        System.out.print("Age: ");
        employee.setAge(sc.nextInt());
        System.out.print("Salary Coefficient: ");
        employee.setCoefficientSalary(sc.nextDouble());
        System.out.print("Day of Start: ");
        String dayStart = sc.next();
        sc.nextLine();
        employee.setDayStart(dayStart);
        System.out.print("Dayoff: ");
        employee.setDayOff(sc.nextInt());
        System.out.print("Over Time: ");
        employee.setOverTime(sc.nextInt());
        employee.setTotalSalary(employee.getCoefficientSalary(), employee.getBasicSalary(),0, employee.getOverTime());
        return employee;
    }

    //Tạo đối tượng manager để lưu thông tin quản lý
    public static Manager addNewManager(ArrayList<String> title,Department department,Scanner sc) {
        Manager manager = new Manager();
        manager.setDepartment(department);
        System.out.print("Staff ID: ");
        manager.setStaffID(sc.next());
        sc.nextLine();
        System.out.print("Name: ");
        manager.setName(sc.nextLine());
        System.out.print("Age: ");
        manager.setAge(sc.nextInt());
        System.out.print("Position: Business Leader | Project Leader | Technical Leader ");
        sc.nextLine();
        String managerPosition = sc.nextLine();
        while (!title.contains(managerPosition)){
            System.out.println("Typing corectly:");
            System.out.println("Business Leader, Project Leader, Technical Leader");
            managerPosition = sc.nextLine();
        }
        // phụ cấp lương quản lý
        int allowance;
        if (managerPosition.equalsIgnoreCase("Business Leader")){
            allowance = 8000000;
        } else if (managerPosition.equalsIgnoreCase("Project Leader")){
            allowance = 5000000;
        } else {
            allowance = 6000000;
        }
        manager.setManagerPosition(managerPosition);
        System.out.print("Salary Coefficient: ");
        manager.setCoefficientSalary(sc.nextDouble());
        System.out.print("Day of Start: ");
        manager.setDayStart(sc.next());
        System.out.print("Dayoff: ");
        manager.setDayOff(sc.nextInt());
        manager.setTotalSalary(manager.getCoefficientSalary(), manager.getBasicSalary(), allowance,0);
        return manager;
    }

    // Hiển thị thông tin nhân viên
    public static void showStaffList(ArrayList<Staff> staffArrayList) {
        for (Staff employee : staffArrayList) {
            if (employee instanceof Employee) {
                System.out.println("Position Employee: ");
            } else {
                System.out.println("Positon Manager: ");
            }
            employee.displayInformation();
            System.out.println("-------------------------------------");
        }
    }
    // Hiển thị các bộ phận
    public static void showDepartmentList(Set<Department> departmentSet) {
        for (Department department : departmentSet) {
            System.out.println(department);
            System.out.println("-------------------------------------");
        }
    }
    // Hiển thị nhân viên theo bộ phận
    public static void showStaffFromDepartment(Set<Department> departments){
        for (Department dept: departments){
            System.out.println("-----------------------------");
            System.out.println("Department: " + dept.getDepartmentName());
            for (Staff staff : dept.getStaffInDepartment()) {
                staff.displayInformation();
                System.out.println("-----------------------------");
            }
        }
    }
    /*
    Tìm kiếm thông tin nhân viên theo tên hoặc theo ID
     */
    public static Staff findStaff(ArrayList<Staff> staffArrayList, Scanner sc) {
        System.out.println("Searching Staff : 1 - By Name | 2- By ID ");
        int numb = sc.nextInt();
        while (numb != 1 && numb != 2) {
            System.out.print("Wrong number, please input 1 or 2: ");
            numb = sc.nextInt();
        }
        if (numb == 1) {
            System.out.print("Staff Name: ");
            String nameInput = sc.next();
            sc.nextLine();
            return staffArrayList.stream().filter(staff -> nameInput.equalsIgnoreCase(staff.getName())).findFirst().orElse(null);
        } else {
            System.out.println("Staff ID: ");
            String idInput = sc.next();
            sc.nextLine();
            return staffArrayList.stream().filter(staff -> idInput.equalsIgnoreCase(staff.getStaffID())).findFirst().orElse(null);
        }
    }
    // Sắp xếp theo bảng lương giảm dần
    public static void showSalarySheetReverse(ArrayList<Staff> staffArrayList){
        staffArrayList.stream().sorted(Comparator.comparingLong(Staff::getTotalSalary).reversed()).forEach(Staff::displayInformation);
    }
    // Sắp xếp theo thứ tự bảng lương tăng dần
    public static void showSalarySheet(ArrayList<Staff> staffArrayList){
        staffArrayList.stream().sorted(Comparator.comparingLong(Staff::getTotalSalary)).forEach(Staff::displayInformation);
    }
}
