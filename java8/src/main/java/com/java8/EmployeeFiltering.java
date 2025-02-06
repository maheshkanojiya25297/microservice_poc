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
                new Employee(1, "John Doe", "New York", 34, 50000, new Department(1, "IT")),
                new Employee(2, "Jane Smith", "London", 24, 60000, new Department(2, "HR")),
                new Employee(3, "David Lee", "New York", 21, 45000, new Department(1, "IT")),
                new Employee(4, "Anna Jones", "Paris", 17, 70000, new Department(3, "Marketing")),
                new Employee(5, "Michael Brown", "New York", 30, 55000, new Department(1, "IT"))
        );

        System.out.println("[1] Filter employees by city and salary using Stream API:--");
        // 1 Filter employees by city and salary using Stream API
        List<Employee> filteredEmployees = employees.stream()
                .filter(e -> e.getCity().equals("New York"))
                .filter(e -> e.getSalary() > 50000)
                .filter(e -> e.getDepartment().getName().equals("IT"))
                .collect(Collectors.toList());

        // Print the filtered employees
        filteredEmployees.forEach(e -> System.out.println("The Final Output is:  " + e.getName() + " , " + e.getCity()));

        System.out.println("[2] Find the average salary of employees in a department:--");
        //2 Find the average salary of employees in IT department
        double averageSalary = employees.stream()
                .filter(e -> e.getDepartment().getName().equals("IT"))
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(00);
        System.out.println("averageSalary : " + averageSalary);

        System.out.println("[3] Sort employees by salary in descending order:--");
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

        System.out.println("[4] Find the highest paid employee in each department.:--");
        //4 Find the highest paid employee in each department.
        Map<String, Optional<Employee>> highPaid = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(),
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        highPaid.forEach((dept, empl) -> {
            System.out.println("dept: " + dept + " , name: " + empl.get().getName());
        });

        System.out.println("[5] Find the youngest  employee in each department.:--");
        //5 Find the youngest  employee in each department.
        Map<String, Optional<Employee>> yongestEmployeeEachDeartment = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName(),
                        Collectors.minBy(Comparator.comparingInt(Employee::getAge))));

        yongestEmployeeEachDeartment.forEach((dep, Emp) -> {
            System.out.println("department: " + dep + ", name: " + Emp.get().getName());
        });

        System.out.println("[6] Find the youngest male employee:--");
        //6 find the youngest male employee.

        Employee yongestMaleEmployee = employees.stream().min(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println("youngest male employee found:--" + yongestMaleEmployee.getName() + " : " + yongestMaleEmployee.getAge());

        System.out.println("[7] find the minimum salary employee:--");
        //7 find the minimum salary employee.

        Employee minimumSalary = employees.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println("minimum salary employee found:--" + minimumSalary.getName() + " : " + minimumSalary.getSalary());

        System.out.println("[8] Group employees by department:--");
        //8 Group employees by department.

        Map<String, List<Employee>> groupEmp = employees.stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment().getName()));

        //using lambda you can avoid iteration of map
        groupEmp.forEach((department, employees1) -> {
            System.out.println("Department: " + department);
            employees1.forEach(employee -> System.out.println("\t- name: " + employee.getName()));
        });

        //other alternative way to group employee
        Map<Department, List<Employee>> groupEmployeee = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        System.out.println("groupEmployeee------------------>:");

        for(Map.Entry<Department,List<Employee>> entry: groupEmployeee.entrySet()){
            Department dep = entry.getKey();
            List<Employee> emp = entry.getValue();
            emp.forEach(e->System.out.println("{"+dep.getName()+":"+e.getName()+"}"));
        }


        // print the employee
        /*for(Map.Entry<String, List<Employee>> entry : groupEmp.entrySet())
        {
            System.out.println("Department: "+entry.getKey());
            for(Employee employee : entry.getValue()){
                System.out.println("name: "+employee.getName());
            }
        }*/


        System.out.println("[9] Calculate the total salary of all employees:--");
        //9 Calculate the total salary of all employees.
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("totalSalary:" + totalSalary);

        System.out.println("[10] Find the number of employees in each department:--");
        //10 Find the number of employees in each department
        Map<String, Long> employeeCountByDepartment =
                employees.stream()
                        .collect(Collectors.groupingBy(e -> e.getDepartment().getName(),
                                Collectors.counting()));

        employeeCountByDepartment.forEach((dep, count) -> {
            System.out.println("Department: " + dep + " having cout: " + count);
        });

        System.out.println("[11] Remove duplicates from a list of strings:--");
        //11 Remove duplicates from a list of strings.
        List<Employee> distinctStrings = employees.stream()
                .distinct()
                .collect(Collectors.toList());

        distinctStrings.forEach(e -> System.out.println(e.getName() + " : " + e.getDepartment().getName()));

        System.out.println("[12] Check if a list contains a specific element:--");
        //12 Check if a list contains a specific element.
        boolean containsElement = employees.stream()
                .anyMatch(s -> s.getName().equals("John Doe"));
        System.out.println("ans: " + containsElement);

        System.out.println("[13] calculate the maximum profit:--");
        //13 calculate the maximum profit

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

        System.out.println("[14] write a function functional interface to get second last digit from str = abcd123nhcj345ab6m7");
        //14 write a function functional interface to get second last digit from str = abcd123nhcj345ab6m7

        String funStr = "abcd123nhcj345ab6m7";
        Function<String,Integer> extract = s->{
          StringBuilder sb = new StringBuilder();
          for(char c : s.toCharArray()){
              if(Character.isDigit(c)){
                  sb.append(c);
              }
          }
          return Character.getNumericValue(sb.charAt(sb.length()-2));
        };

        int secondlastdigitwithFunction = extract.apply(funStr);
        System.out.println("secondlastdigitwithFunction : " +secondlastdigitwithFunction);

        System.out.println("[15]using java 8 to get second last digit from str = abcd123nhcj345ab6m7");
        //15 to get second last digit from str = abcd123nhcj345ab6m7
        int secondInt = funStr.chars()
                .filter(Character::isDigit)
                .mapToObj(Character::getNumericValue)
                .mapToInt(Integer::intValue)
                .skip(Math.max(0, funStr.chars()
                        .filter(Character::isDigit).count()-2)).findFirst().orElse(0);

        System.out.println("secondInt : " +secondInt);


        System.out.println("***********************************************************************************************lets start with some Java 8 features codding Practise !!");
        System.out.println("[1] find the first non-repeated character in it using Stream functions with int valaues = {1,2,2,4,1,5,5}");

        int[] intValues = {1, 2, 2, 2, 2, 4, 1, 5, 5};
        Map<Integer, Long> firstNonRepeatedIntFromArray = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        firstNonRepeatedIntFromArray.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        for (int val : intValues) {
            if (firstNonRepeatedIntFromArray.get(val) == 1L) {
                System.out.println("first non-repeated character found :-" + val);
                break;
            }
        }

        System.out.println("[2] Given a String, find the first non-repeated character in it using Stream functions? String str= mahesh ");
        String strValues = "mahesh";
        Character firstNonRepeatedCharFromString = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first non-repeated character found: " + firstNonRepeatedCharFromString);

        System.out.println("[3] find the first repeated character in given string t using Stream functions with intvalaues = {1,2,2,4,1,5,5}");
        Map<Integer, Long> firstRepeatedIntFromArray = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        firstRepeatedIntFromArray.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        for (int val : intValues) {
            if (firstRepeatedIntFromArray.get(val) > 1L) {
                System.out.println("first repeated character found: " + val);
                break;
            }
        }

        System.out.println("[4] Given a String, find the first repeated character in it using Stream functions? String str= mahesh ");
        Character firstRepeatedCharFromString = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println("first repeated character found: " + firstRepeatedCharFromString);

        System.out.println("[5] find the frequency of given array element with int [] intvalaues = {1,2,2,4,1,5,5}");
        Map<Integer, Long> frequencyOfeachElementOfIntValus = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        System.out.println("frequency of each element are :");
        frequencyOfeachElementOfIntValus.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        System.out.println("[6] find the frequency of character of given String = mahesh");
        Map<String, Long> frequencyOfeachElementOfStrValus = Arrays.stream(strValues.split(""))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("frequency of each element are :");
        frequencyOfeachElementOfStrValus.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

        System.out.println("[7] Find the element who having Maximum frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");
        Integer elehavingMaxFreq = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();

        System.out.println("elehavingMaxFreq : " + elehavingMaxFreq);

        System.out.println("[8] Find the element who having Maximum frequency of given String strValues = Mahesh");
        Character charelehavingMaxFreq = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .get();
        System.out.println("charelehavingMaxFreq : " + charelehavingMaxFreq);

        System.out.println("[9] Find the element of Maximum frequency of given array element with intvalaues = {1,2,2,2,2,4,1,5,5}");
        Long maxFreq = Arrays.stream(intValues)
                .boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("maxFreq : " + maxFreq);

        System.out.println("[10] Find the element of Maximum frequency of given String strValues= Mahesh");
        Long maxFreqChar = strValues.chars()
                .mapToObj(c -> Character.toLowerCase(Character.valueOf((char) c)))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .mapToLong(Map.Entry::getValue)
                .max()
                .orElse(0);
        System.out.println("maxFreqChar : " + maxFreqChar);
    }
}


class Employee {


    private int id;
    private String name;
    private String city;
    private int age;
    private double salary;
    private Department department;


    public Employee(int id, String name, String city, int age, double salary, Department department) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.age = age;
        this.salary = salary;
        this.department = department;
    }


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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
