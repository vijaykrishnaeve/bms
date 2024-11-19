
/**
 * 
 */

/**
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Interview3 {

	public static void main (String[] args) {

		
		//int a = Integer.max(1, 2);
		//System.out.println(Stream.of("Russia", "India", "China", "Japan","China","india", "", "Ghana").collect(Collectors.toMap(s -> s.co, null)));
		
		
		List<Employee> employees = EmployeeData.get();
		//concat the firstnames of all the employees
		// static Collector<CharSequence, ?, String> joining(CharSequence delimiter)
		String firstNameJoin = employees.stream().map(e-> e.getFirstName()).collect(Collectors.joining(", "));
		System.out.println(firstNameJoin);
		System.out.println("-----");
		// Split the list based on salary > 25000	--> partitioning
		Map<Boolean, List<Employee>> empSalaryPartition = employees.stream().collect(Collectors.partitioningBy(e-> e.getSalary() > 25000.00));
		System.out.println(empSalaryPartition);
		System.out.println("---------");
		Map<Boolean, List<Employee>> empSalaryPartition2 = employees.stream().filter(e -> e.getSalary() >25000.00).collect(Collectors.partitioningBy(e-> e.getSalary() > 25000.00));
		System.out.println(empSalaryPartition2);
		System.out.println("---------");
		Map<Boolean, Set<Employee>> empSalaryPartitionInSet = employees.stream().filter(e -> e.getSalary() >25000.00).collect(
				Collectors.partitioningBy(e-> e.getSalary() > 25000.00,Collectors.toSet()));
		
		// Split the list based on salary> 25000.0 and count them
		Map<Boolean, Long> countTheEmpCollector = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary()>25000.0, Collectors.counting()));
		System.out.println("countTheEmpCollector "+countTheEmpCollector);
		System.out.println("-------------");
		
		// Grouping the employees by their department
		Map<Boolean, List<Employee>> devGroupEmps = employees.stream().collect(Collectors.groupingBy(e-> e.getDepartment().equals("Dev")));
		Map<String, List<Employee>> devGroupEmps2 = employees.stream().collect(Collectors.groupingBy(e-> e.getDepartment()));
		//in above 2 grouping by functions one is accepting boolean but there returns boolean type of map key, where as 2nd is String return type.
		// try --> Map<String, Long> devGroupEmps2 = employees.stream().collect(Collectors.groupingBy(e-> e.getDepartment(),Collectors.counting()));
		System.out.println("devGroupEmps " +devGroupEmps);
		System.out.println("devGroupEmps2 " +devGroupEmps2);
		
		// Group the employees by their department & List the employee names by each department
		Map<String, List<String>> firstNamesListOfDepartments = employees.stream().collect(Collectors.groupingBy(
				e -> e.getDepartment(), Collectors.mapping(e-> e.getFirstName(), Collectors.toList())));
		System.out.println("firstNamesListOfDepartments "+ firstNamesListOfDepartments);
		System.out.println("-----------");
		
		
		//Group the employees by their department & SUM the salaries of employees by each department
		Map<String, Optional<Double>> empsSalaryByReducing = employees.stream().collect(Collectors.groupingBy(
				e -> e.getDepartment(), Collectors.mapping(e-> e.getSalary(), Collectors.reducing((a,b) -> a+b))) );
		System.out.println("empsSalaryByReducing "+empsSalaryByReducing);
		System.out.println("-----------");
		
		// Instead of we calculate manually the SUM, default mappers available as below
		
		Map<String, Double> empsSalaryByDefaultSumming = employees.stream().collect(Collectors.groupingBy(
				e -> e.getDepartment(), Collectors.summingDouble(e-> e.getSalary()))) ;
		System.out.println("empsSalaryByDefaultSumming "+empsSalaryByDefaultSumming);
		
		// Summary statistics of the salaries of employees by each department
		Map<String, DoubleSummaryStatistics> staticsSummary = employees.stream().collect(Collectors.groupingBy(
				e -> e.getDepartment(), Collectors.summarizingDouble(e -> e.getSalary())));
		System.out.println("staticsSummary "+staticsSummary);
		System.out.println("-----------");
		
		// Summary statistics of the salaries of employees in DEV department only
		DoubleSummaryStatistics devSummaryStats = employees.stream().collect(Collectors.filtering(
				e -> e.getDepartment().equals("Dev"), Collectors.summarizingDouble(e -> e.getSalary())));
		System.out.println("devSummaryStats "+devSummaryStats);
		
		// Employee with highest age
		Optional<Employee> eldestEmployee = employees.stream().collect(Collectors.maxBy((e1, e2) -> e1.getAge() - e2.getAge()));
		System.out.println("eldestEmployee "+eldestEmployee);
		System.out.println("---------");
		// --> Try -->  Optional<Employee> eldestEmployee1 = employees.stream().collect(Collectors.maxBy(Comparator.comparing(e-> e.getAge())));
		
		// Name of the employee with highest salary
		Optional<String> collectAndThenOptional = employees.stream().collect(Collectors.collectingAndThen(
				Collectors.maxBy(Comparator.comparing(e-> e.getSalary())), empOptional-> empOptional.map(e -> e.getFirstName())));
		System.out.println("collectAndThenOptional "+ collectAndThenOptional.get());
		
		// try --> String collectAndThenOptional = employees.stream().collect(Collectors.collectingAndThen(
					//Collectors.maxBy(Comparator.comparing(e-> e.getSalary())), empOptional-> empOptional.map(e -> e.getFirstName()).orElse("Name Not Found")));
		
	}
}

class EmployeeData {

	public static ArrayList<Employee> get() {
		return new ArrayList<Employee>(Arrays.asList(
				new Employee(1, "Madan", "Neelapu", "Dev", "SE", 24000.00, 30),
				new Employee(1, "Raju", "M", "QA", "SE",		 29000.00, 40),
				new Employee(1, "Bharath", "S", "Testing", "SE",	 58000.00, 30),
				new Employee(1, "Vijay", "K", "FullStak Dev", "SE",		 99000.00, 35),
				new Employee(1, "Anand", "Mudunuri", "Dev", "SE", 56000.00, 50),
				new Employee(1, "Murthy", "GSN", "Devops", "SE",	 23000.00, 40),
				new Employee(1, "Swathi", "Metta", "QA", "SE",	 85000.00, 45),
				new Employee(1, "Sruthi", "NP", "Dev", "SE",	 92000.00, 36)));

	}

}

class Employee {

	private Number empId;
	private String firstName;
	private String lastName;
	private String department;
	private String empRole;
	private Double salary;
	private int age;

	public Employee(Number empId, String firstName, String lastName, String department, String empRole, Double salary,
			int age) {
		super();

		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.empRole = empRole;
		this.salary = salary;
		this.age = age;
	}

	@Override
	public String toString() {
		return firstName;
	}

	public Number getEmpId() {
		return empId;
	}

	public void setEmpId(Number empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}