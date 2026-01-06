package blake;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static final HashMap<String, Integer> STUDENT_GRADES = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        options(input);
        input.close();
    }
    public static void options(Scanner input) {
        while (true) {
            System.out.printf("1. Add Grades %n2. Check Grades%n3. Check Grade Averages%n4. Exit%n");
            if (input.hasNextInt()) {
                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1 -> storeStudentGrades(input);
                    case 2 -> checkGrades(input);
                    case 3 -> gradeAverages();
                    case 4 -> {
                        return;
                    }
                    default -> System.out.println("Invalid input. Please select 1-4.");
                }
            } else {
                input.nextLine();
                System.out.println("Invalid Input");
            }
        }
    }
    public static void storeStudentGrades(Scanner input) {
        while (true) {
            String name;

            System.out.println("What is the student's name?");
            name = input.nextLine().trim();
            if (!name.isEmpty()) {
                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
            }

            int grade = intGradeValidator(input, name);

            STUDENT_GRADES.put(name, grade); //Stores Name and Grade score to hashmap

            System.out.println("Would you like to add another grade?");
            if (!input.nextLine().trim().equalsIgnoreCase("yes")) {
                return;
            }
        }
    }
    public static int intGradeValidator(Scanner input, String name) {
        while (true) {
            System.out.printf("What is %s's grade? %n", name);

            if (!input.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                input.nextLine();
                continue;
            }

            int grade = Integer.parseInt(input.nextLine());

            if (grade < 0 || grade > 100) {
                System.out.println("Please enter a valid number 0-100.");
                continue;
            }
            return grade;
        }
    }
    public static void checkGrades(Scanner input) {
        System.out.println("Who's grade would you like to check?");
        String name = input.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("No name entered.");
            return;
        }
        if (name.length() == 1) {
            System.out.println("Please enter a name with more than 1 character.");
            return;
        }

        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        if (!name.isEmpty() && STUDENT_GRADES.containsKey(name)) {
            System.out.printf("%s has a %d%%%n", name, STUDENT_GRADES.get(name));
        } else {
            System.out.printf("Student %s not found.%n", name);
        }
    }
    public static void gradeAverages() {
        if (STUDENT_GRADES.isEmpty()) {
            System.out.println("No grades found.");
            return;
        }

        int sum = 0;
        for (int grade : STUDENT_GRADES.values()) {
            sum += grade;
        }

        double average = (double) sum / STUDENT_GRADES.size();

        System.out.printf("The class average grade is a %.2f%n", average);
    }
}
