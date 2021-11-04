package Week8;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/** Java Priority Queue */
public class JavaPriorityQueue {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> events = new ArrayList<String>();
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            events.add(scanner.nextLine());
        }
        scanner.close();
        ArrayList<Student> arr = (ArrayList<Student>) Priorities.getStudents(events);
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getName());
        }
    }
}

class Student implements Comparable<Student> {
    private int id;
    private String name;
    private double cgpa;
    
    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }
    
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getCGPA() {
        return cgpa;
    }

    @Override
    public int compareTo(Student o) {
        if (this.cgpa == o.cgpa) {
            if (this.name.equals(o.name)) {
                return (this.id - o.id);
            } else {
                return this.name.compareTo(o.name);
            }
        } else {
            return Double.compare(o.cgpa, this.cgpa);
        }
    }
}

class Priorities {
    public static List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> pq = new PriorityQueue<Student>();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).equals("SERVED"))  {
                if (!pq.isEmpty()) {
                    pq.remove();
                }
            } else {
                String str = events.get(i).substring(6);
                String name = str.substring(0, str.indexOf(" "));
                str = str.substring(str.indexOf(" ") + 1);
                double cgpa = Double.parseDouble(str.substring(0, str.indexOf(" ")));
                int id = Integer.parseInt(str.substring(str.indexOf(" ") + 1));
                Student newStudent = new Student(id, name, cgpa);
                pq.add(newStudent);
            }
        }
        ArrayList<Student> arr = new ArrayList<>();
        while (!pq.isEmpty()) {
            arr.add(pq.remove());
        }
        return arr;
    }
}