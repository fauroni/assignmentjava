import java.util.ArrayList;
import java.util.Scanner;

// Abstract class Employee with common attributes and methods
abstract class Employee {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Add this setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Abstract method to calculate salary. Each subclass will implement this differently.
    public abstract double calculateSalary();

    // Method to display employee details
    public void displayDetails() {
        System.out.println("ID: " + getId() + ", Name: " + getName() + ", Salary: " + calculateSalary());
    }
}

// Child class FullTimeEmployee inheriting from Employee
class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }

    // Add this setter method for monthly salary
    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
}

// Child class PartTimeEmployee inheriting from Employee
class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(int id, String name, double hourlyRate, int hoursWorked) {
        super(id, name);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }

    // Add these setter methods for hourly rate and hours worked
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}

// Main class to manage CRUD operations
public class EmployeeCRUDApp {
    // List to store all employees (both full-time and part-time)
    private static ArrayList<Employee> employees = new ArrayList<>();
    private static int nextId = 1;

    // Create operation
    public static void createEmployee(Scanner scanner) {
        System.out.println("Enter employee type (1 for Full-Time, 2 for Part-Time):");
        int type = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Enter name:");
        String name = scanner.nextLine();

        if (type == 1) {
            System.out.println("Enter monthly salary:");
            double monthlySalary = scanner.nextDouble();
            Employee fullTimeEmployee = new FullTimeEmployee(nextId++, name, monthlySalary);
            employees.add(fullTimeEmployee);
            System.out.println("Full-Time employee created successfully.");
        } else if (type == 2) {
            System.out.println("Enter hourly rate:");
            double hourlyRate = scanner.nextDouble();
            System.out.println("Enter hours worked in one month:");
            int hoursWorked = scanner.nextInt();
            Employee partTimeEmployee = new PartTimeEmployee(nextId++, name, hourlyRate, hoursWorked);
            employees.add(partTimeEmployee);
            System.out.println("Part-Time employee created successfully.");
        } else {
            System.out.println("Invalid employee type.");
        }
    }

    // Read operation- print employee list
    public static void readEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the list.");
            return;
        }
        System.out.println("List of employees:");
        for (Employee employee : employees) {
            employee.displayDetails();
        }
    }

    // Update operation- update employee detail
    public static void updateEmployee(Scanner scanner) {
        System.out.println("Enter the ID of the employee to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Enter new name:");
                String newName = scanner.nextLine();
                employee.setName(newName);
                if (employee instanceof FullTimeEmployee) {
                    System.out.println("Enter new monthly salary:");
                    double newMonthlySalary = scanner.nextDouble();
                    ((FullTimeEmployee) employee).setMonthlySalary(newMonthlySalary);
                } else if (employee instanceof PartTimeEmployee) {
                    System.out.println("Enter new hourly rate:");
                    double newHourlyRate = scanner.nextDouble();
                    System.out.println("Enter new hours worked in one month:");
                    int newHoursWorked = scanner.nextInt();
                    ((PartTimeEmployee) employee).setHourlyRate(newHourlyRate);
                    ((PartTimeEmployee) employee).setHoursWorked(newHoursWorked);
                }
                System.out.println("Employee updated successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    // Delete operation- delete employee 
    public static void deleteEmployee(Scanner scanner) {
        System.out.println("Enter the ID of the employee to delete:");
        int id = scanner.nextInt();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getId() == id) {
                employees.remove(i);
                System.out.println("Employee with ID " + id + " deleted successfully.");
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Create an employee");
            System.out.println("2. Read all employees");
            System.out.println("3. Update an employee");
            System.out.println("4. Delete an employee");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    createEmployee(scanner);
                    break;
                case 2:
                    readEmployees();
                    break;
                case 3:
                    updateEmployee(scanner);
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
