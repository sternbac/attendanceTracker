import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Student {

	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private ArrayList<Integer> attendance;

	public Student(String[] studentInfo, ArrayList<Integer> attendance) {
		id = studentInfo[0];
		firstName = studentInfo[1];
		lastName = studentInfo[2];
		email = studentInfo[3];
		this.attendance = attendance;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getID() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<Integer> getAttendance() {
		return attendance;
	}

	public int getAttendance(int pos) {
		return attendance.get(pos);
	}

	public void setAttendance(ArrayList<Integer> attendance) {
		this.attendance = attendance;
	}


	public void printStudent() {
		System.out.println(getInfo());
	}

	public String getInfo() {
		String attend = "";
		//System.out.println(attendance.size());
		for (int i = 0; i < attendance.size(); i++) {
			attend = attend + "," + attendance.get(i);
		}
		//System.out.println(attend);
		String info = id + "," + firstName + "," + lastName + "," + email + attend + "," + totalAttendance();
		return info;


	}

	public void addAttendance(int i) {
		attendance.add(i);
	}

	public void addAttendance(int i, int pos) {
		attendance.set(pos,i);
	}

	public int totalAttendance() {
		int total = 0;
		for (int i: attendance) {
			total = total + i;
		}
		return total;
	}

}

