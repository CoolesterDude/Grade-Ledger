package blake;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static final HashMap<String, Integer> STUDENT_GRADES = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        options(input);
    }
    public static void storeStudentGrades(Scanner input) {
        String name;
        int grade;
        while (true) {
            System.out.println("What is the student's name?");
            name = input.nextLine().trim();
            if (!name.isEmpty()) {
                name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            }
            System.out.printf("What is %s's grade? %n", name);
                if (input.hasNextInt()) {
                    grade = input.nextInt();
                    input.nextLine();
                    System.out.printf("%s has a %d%% in the class.%n", name, grade);
                    STUDENT_GRADES.put(name, grade);
                } else {
                    String garbage = input.nextLine();
                    System.out.println(garbage + " is not a number. Try again.");
                    continue;
                }

            System.out.println("Would you like to add another grade?");
            if (!input.nextLine().trim().equalsIgnoreCase("yes")) {
                return;
            }
        }
    }
    public static void checkGrades(Scanner input) {
        System.out.println("Who's grade would you like to check?");
        String name = input.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("No name entered.");
            return;
        }
        name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        if (!name.isEmpty() && STUDENT_GRADES.containsKey(name)){
            System.out.printf("%s has a %d%%%n",name, STUDENT_GRADES.get(name));
        } else {
            System.out.printf("Student %s not found.%n", name);
        }
    }
    public static void gradeAverages(Scanner input) {
        if (STUDENT_GRADES.isEmpty()) {
            System.out.println("No grades found.");
            return;
        }

        int sum = 0;
        for (int grade : STUDENT_GRADES.values()) {
            sum += grade;
        }

        double average = (double) sum / STUDENT_GRADES.size();

        System.out.printf("The class average grade is a %.2f/100%n", average);
    }
    public static void options(Scanner input) {
        while (true) {
            System.out.printf("1. Add Grades %n2. Check Grades%n3. Check Grade Averages%n4. Exit%n");
            if (input.hasNextInt()) {
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> storeStudentGrades(input);
                    case 2 -> checkGrades(input);
                    case 3 -> gradeAverages(input);
                    case 4 -> {System.exit(0);}
                    default -> System.out.println("Invalid input. Please select 1-4.");
                }
            } else {
                input.nextLine();
                System.out.println("Invalid Input");
            }
        }
    }
}
