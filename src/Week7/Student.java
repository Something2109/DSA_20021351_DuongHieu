package Week7;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/** Java Sort. */
class Student implements Comparable<Student> {
	private int id;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
	}
    
    public int compareTo(Student other) {
        if (this.cgpa == other.cgpa) {
            if (this.fname.equals(other.fname)) {
                return (this.id - other.id);
            } else {
                return this.fname.compareTo(other.fname);
            }
        } else {
            if (this.cgpa > other.cgpa) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		
		List<Student> studentList = new ArrayList<Student>();
		while(testCases>0){
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();
			
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			testCases--;
		}
        in.close();
        Collections.sort(studentList);
      	for(Student st: studentList){
			System.out.println(st.getFname());
		}
	}
}