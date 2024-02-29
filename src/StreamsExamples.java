import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsExamples {

	static List<Employee> employeeList = new ArrayList<Employee>();

	static {
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
		employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
		employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
		employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
		employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
		employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
		employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
		employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
		employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
		employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
		employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
		employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
		employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
		employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
		employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
		employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
		employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
	}

	public static void main(String[] args) {
		// Query 1 : How many male and female employees are there in the organization?
		// method1();

		// Query 2 : Print the name of all departments in the organization?
		// method2();

		// Query 3 : What is the average age of male and female employees?
		// method3();

		// Query 4 : Get the details of highest paid employee in the organization?
		// method4();
		// System.out.println("\n");
		
		// Query 5 : Get the names of all employees who have joined after 2015?
		// method5();
		// System.out.println("\n");
		
		// Query 6 : Count the number of employees in each department?
		// method6();
		// System.out.println("\n");
		
		// Query 7 : What is the average salary of each department?
		// method7();
        // System.out.println("\n");
		
		// Query 8 : Get the details of youngest male employee in the product development department?
		// method8();
		//System.out.println("\n");
		
		// Query 9 : Who has the most working experience in the organization?
		// method9();
		// System.out.println("\n");
		
		// Query 10 : How many male and female employees are there in the sales and marketing team?
        // method10();
		// System.out.println("\n");

		// Query 11 : What is the average salary of male and female employees?
        // method11();
        // System.out.println("\n");
		
       // Query 12 : List down the names of all employees in each department?
       // method12();
       // System.out.println("\n");
		
       // Query 13 : What is the average salary and total salary of the whole organization?
       // method13();
       // System.out.println("\n");
		
       // Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
       // method14();
       // System.out.println("\n");
		
       // Query 15 : Who is the oldest employee in the organization? What is his age and which department he belongs to?
       // method15();
	   // System.out.println("\n");
		
		// Query 16: I want to double the salary of "IT" department employees without
		// changing anything to other employees and print the final list of all the emp
		// which will contain all department employees including IT, Finance , HR etc etc.
		// method16();
		// System.out.println("\n");
		
		// Query 17: Highest Paid Employee on each Department?
		//method17();
		
		// Query 18: Remove Duplicate Employee?
		method18();

	}

	private static void method18() {
		List<Employee> uniqueEmployees = employeeList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()-> new TreeSet<>(Comparator.comparingInt(Employee::getId))), ArrayList::new));
		System.out.println(uniqueEmployees);
	}

	private static void method17() {
		Map<String, Optional<Employee>> e = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,
				Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
		System.out.println(e);
	}

	private static void method16() {
//		List empWithDoubleSal = employeeList.stream().map(e -> {
//			if(e.getDepartment().equals("Product Development")) {
//				e.setSalary(e.getSalary()*2);
//			}
//			return e;
//		}
//				).collect(Collectors.toList());
//		System.out.println(empWithDoubleSal);
//		System.out.println(employeeList);
		employeeList.stream().map(e -> {
			if (e.getDepartment().equals("Product Development")) {
				e.setSalary(e.getSalary() * 2);
			}
			return e;
		});
		System.out.println(employeeList);

	}

	private static void method9() {
		Employee e = employeeList.stream().min(Comparator.comparing(Employee::getYearOfJoining)).orElse(null);
		System.out.println(e);
		Employee e1 = employeeList.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst()
				.get();
		System.out.println(e1);
	}

	private static void method8() {
		Employee emp = employeeList.stream()
				.filter(e -> e.getGender().equals("Male") && e.getDepartment().equals("Product Development"))
				.min(Comparator.comparing(Employee::getAge)).get();
		System.out.println(emp);
	}

	private static void method7() {
		Map<String, Double> m = employeeList.stream().collect(
				Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
		System.out.print(m);
	}

	private static void method6() {
		Map<String, Long> m = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
		System.out.println(m);
	}

	private static void method5() {
		List<String> names = employeeList.stream().filter(e -> e.getYearOfJoining() > 2015).map(Employee::getName)
				.collect(Collectors.toList());
		System.out.println(names);
	}

	private static void method4() {
		Employee maxSalaryEmployee = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
		System.out.println(maxSalaryEmployee);
		maxSalaryEmployee = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
		System.out.println(maxSalaryEmployee);
	}

	private static void method3() {
		Map<String, Double> m = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
		System.out.println(m);
	}

	private static void method2() {
		List<String> departments = employeeList.stream().map(Employee::getDepartment).distinct()
				.collect(Collectors.toList());
		System.out.println(departments);
	}

	private static void method1() {
		Map<String, Long> m = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(m);

	}

}
