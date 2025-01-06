package com.java8;

import com.sun.jdi.Value;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class EmployeeFiltering {
    public static void main(String[] args) {
        // Sample data (replace with your actual data)
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John Doe", "New York", 50000, new Department(1, "IT")),
                new Employee(2, "Jane Smith", "London", 60000, new Department(2, "HR")),
                new Employee(3, "David Lee", "New York", 45000, new Department(1, "IT")),
                new Employee(4, "Anna Jones", "Paris", 70000, new Department(3, "Marketing")),
                new Employee(5, "Michael Brown", "New York", 55000, new Department(1, "IT"))
        );

        System.out.println("Filter employees by city and salary using Stream API:--");
        // 1 Filter employees by city and salary using Stream API
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getCity().equals("New York"))
                .filter(e -> e.getSalary() > 50000)
                .filter(e -> e.getDepartment().getName().equals("IT"))
                .collect(Collectors.toList());

        // Print the filtered employees
        filteredEmployees.forEach(e -> System.out.println("The Final Output is:  " + e.getName() + " , " + e.getCity()));

        System.out.println("Find the average salary of employees in a department:--");
        //2 Find the average salary of employees in IT department
        double averageSalary = employees.stream()
                .filter(e -> e.getDepartment().getName().equals("IT"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(00);
        System.out.println("averageSalary : " + averageSalary);

        System.out.println("Sort employees by salary in descending order:--");
        //3 Sort employees by salary in descending order.
        List<Employee> descSalary = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        descSalary.forEach(dSalary -> System.out.println("descSalary : " + dSalary.getSalary()));

        System.out.println("fetch max salary only:--");
        // fetch max salary only
        Optional<Employee> topSalary = descSalary.stream().findFirst();
        if (topSalary.isPresent()) {
            Employee emp = topSalary.get();
            System.out.println("Top empSalary: " + emp.getSalary() + " empName: " + emp.getName());
        } else {
            System.out.println("No Data Found");
        }

        System.out.println("Find the highest paid employee in each department.:--");
        //4 Find the highest paid employee in each department.
        Map<String, Optional<Employee>> highPaid = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(),
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        highPaid.forEach((dept, empl) -> {
            System.out.println("dept: " + dept + " , name: " + empl.get().getName());
        });

        System.out.println("Group employees by department:--");
        //5 Group employees by department.

        Map<String, List<Employee>> groupEmp = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName()));

        //using lambda you can avoid iteration of map
        groupEmp.forEach((department, employees1) -> {
            System.out.println("Department: " + department);
            employees1.forEach(employee -> System.out.println("\t- name: " + employee.getName()));
        });

        // print the employee
        /*for(Map.Entry<String, List<Employee>> entry : groupEmp.entrySet())
        {
            System.out.println("Department: "+entry.getKey());
            for(Employee employee : entry.getValue()){
                System.out.println("name: "+employee.getName());
            }
        }*/


        System.out.println("Calculate the total salary of all employees:--");
        //6 Calculate the total salary of all employees.
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("totalSalary:" + totalSalary);

        System.out.println("Find the number of employees in each department:--");
        //7 Find the number of employees in each department
        Map<String, Long> employeeCountByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(e -> e.getDepartment().getName(),
                                Collectors.counting()));

        employeeCountByDepartment.forEach((dep, count) -> {
            System.out.println("Department: " + dep + " having cout: " + count);
        });

        System.out.println("Remove duplicates from a list of strings:--");
        //8 Remove duplicates from a list of strings.
        List<Employee> distinctStrings = employees.stream()
                .distinct()
                .collect(Collectors.toList());

        distinctStrings.forEach(e -> System.out.println(e.getName() + " : " + e.getDepartment().getName()));

        System.out.println("Check if a list contains a specific element:--");
        //9 Check if a list contains a specific element.
        boolean containsElement = employees.stream()
                .anyMatch(s -> s.getName().equals("John Doe"));
        System.out.println("ans: " + containsElement);

        //10  Given a String, find the first repeated character in it using Stream functions?
        String input = "mahesh is best java developer";

        Character result = input
                .chars()
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first repeater character is :" + result);


        //11  Given a String, find the first non-repeated character in it using Stream functions?
        Character output = input
                .chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();

        System.out.println("the non-repeated character are: " + output);


        //12 Maximum frequency of given array element with int values
        int[] arr = {1, 3, 1, 1, 4, 1, 1, 5, 1, 2, 2};
        System.out.println("The array element is: " + Arrays.toString(arr));

        int maxFrequency = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .max(java.util.Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);

        System.out.println("The maximum frequency of element is: " + maxFrequency); //O(n)  average case

        Long maxFreValue = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);

        System.out.println("maximum frequency value of element is: " + maxFreValue); //O(n)  average case

        Map<Integer, Long> frequencyMap = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        System.out.println("Element Frequencies:");
        frequencyMap.forEach((key, value) -> System.out.println(key + ": " + value));

        //13 frequency of given array element with String mahesh
        String str = "mahesh";
        System.out.println("string is :" + str);
        long maxFrequency1 = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("Maximum Frequency: " + maxFrequency1); //O(n)

        Optional<Character> maxFreqChar = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);

        System.out.println("Character with Max Frequency: " + maxFreqChar.orElse(' ')); //O(n)

        Map<String, Long> frequencyMap1 = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Character Frequencies:");
        frequencyMap1.forEach((character, count) -> System.out.println(character + ": " + count)); //O(n)

        //14 calculate the maximum profit

        int[] prices = {1, 4, 6, 7};
        System.out.println("prices:" + Arrays.toString(prices));
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE; // Initialize minPrice to a high value
        System.out.println("minPrice:" + minPrice);
        for (int price : prices) {
            minPrice = Math.min(minPrice, price); // Update minPrice if a lower price is found
            maxProfit = Math.max(maxProfit, price - minPrice); // Calculate potential profit at each price
        }
        System.out.println("maxProfit:" + maxProfit);


        //15 find the first repeated int value from int array int[] arr = {1, 3, 1, 1, 4, 1, 1, 5, 1, 2, 2};

        Map<Integer, Long> firstRepeatedInt = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        firstRepeatedInt.forEach((key, value) -> System.out.println(key + " : " + value));
        for (int num : arr) {
            if (firstRepeatedInt.get(num) > 1L) {
                System.out.println("first repeated int value is :" + num);
                break;
            }
        }

        //16 find the first non-repeated int value from int array int[] arr = {1, 3, 1, 1, 4, 1, 1, 5, 1, 2, 2};

        Map<Integer, Long> firstNonRepeatedIntValue = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        for (int num : arr) {
            if (firstNonRepeatedIntValue.get(num) == 1L) {
                System.out.println("first non repeated int value is : " + num);
                break;
            }
        }

        System.out.println("==========================================================================================================================");


    }

}


class Employee {
    private int id;
    private String name;
    private String city;
    private double salary;
    private Department department;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee(int id, String name, String city, double salary, Department department) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.salary = salary;
        this.department = department;
    }


    // Constructor, getters, and setters
}

class Department {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor, getters, and setters
}
