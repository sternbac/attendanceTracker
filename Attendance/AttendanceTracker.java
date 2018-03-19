import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.PrintWriter;
/**
 * This class is the driver for attendance
 *
 * @author Shruti Manglik
 * @version 1.0
 **/

public class AttendanceTracker {

	/**
	 * The main method is designed to run the system
	 *
	 * @param args - the command line arguments
	 */
	public static void main (String[] args) throws FileNotFoundException, IOException{

		ArrayList<Student> allStudents = new ArrayList<>();
		
		File fileStudents = new File("Database.csv");
		Scanner scanStudent = new Scanner(fileStudents);

		int attendanceDays = 0;

		while (scanStudent.hasNextLine()) {
			String line = scanStudent.nextLine();
			StringTokenizer str = new StringTokenizer(line, ",");
			int count = str.countTokens();
			//System.out.println(count);
			String[] studentInfo = new String[count];
			ArrayList<Integer> attendance = new ArrayList<>();

			for (int i = 0; i < 4; i++) {
				studentInfo[i] = (String)str.nextElement();
			}
			for (int i = 4; i < count; i++) {
				String element = (String)str.nextElement();
				attendance.add(Integer.parseInt(element));
			}

			for (int i = 0; i < attendance.size(); i++) {
				System.out.print(attendance.get(i));
			}
			System.out.println();
			
			attendanceDays = count - 4;
			//System.out.println(attendanceDays);
			//System.out.println(studentInfo);
			Student student = new Student(studentInfo, attendance);
			//student.printStudent();
			allStudents.add(student);
		}


		File fileStudents1 = new File("WadeWendy.csv");
		Scanner scanStudent1 = new Scanner(fileStudents1);

		int newAttendance = 0;

		for (Student stu: allStudents) {
			stu.addAttendance(0);
		}

		while (scanStudent1.hasNextLine()) {

			ArrayList<Integer> attendance = new ArrayList<>();			
			String line = scanStudent1.nextLine();
			StringTokenizer str = new StringTokenizer(line, ",");
			int count = str.countTokens();
			//System.out.println(count);
			String[] studentInfo = new String[4];
			
			for (int i = 0; i < 4; i++) {
				studentInfo[i] = (String)str.nextElement();
			}
			//System.out.println(studentInfo);
			Student student = new Student(studentInfo,attendance);
			//student.printStudent();

			boolean studentIsThere = false;
			for (int j = 0; j < allStudents.size(); j++) {
				Student getStudent = allStudents.get(j);
				String id = getStudent.getID();
				if (id.equals(student.getID())) {
					studentIsThere = true;
					getStudent.addAttendance(1,getStudent.getAttendance().size() - 1);
					//allStudents.get(j).addAttendance(1);
					ArrayList<Integer> temp = getStudent.getAttendance();
					for (int k = 0; k < temp.size(); k++) {
						System.out.print(temp.get(k));
					}
			System.out.println();
					break;
				}
			}
			if (studentIsThere) {
		
			} else {

				for (int i = 0; i < attendanceDays; i++) {
					attendance.add(0);
				}
				attendance.add(1);
				student.setAttendance(attendance);
				allStudents.add(student);
			}

			newAttendance = student.getAttendance().size();

		}

		for (Student student: allStudents) {
			if (student.getAttendance().size() < newAttendance) {
				student.addAttendance(0);
			}
		}

		System.out.println(allStudents.size());

		//File fileOutput = new File("test.txt");
		//FileWriter fileWriter = new FileWriter(fileOutput);

		for (int i = 0; i < allStudents.size(); i++) {
			Student stu = allStudents.get(i);
			//System.out.println(stu.getAttendance().size());
			//stu.printStudent();
			//pw.write(stu.getInfo());
			//stu.printStudent();
			String info = stu.getInfo();
			System.out.println(info);
			//fileWriter.append(info + "\n");
		}

		String fileHeader = "ID,First Name,Last Name,Email,01/25/18,02/08/18,02/15/18,Total";
		csvFileWriter(allStudents,fileHeader);

		System.out.println("Total Unique Attendance: " + allStudents.size());

	}

	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public static void csvFileWriter(ArrayList<Student> allStudents, String fileHeader) {



		FileWriter fileWriter = null;

		try {

			fileWriter = new FileWriter("Test.csv");
			fileWriter.append(fileHeader);
			fileWriter.append(NEW_LINE_SEPARATOR);

			for (Student student: allStudents) {
				fileWriter.append(student.getID());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(student.getFirstName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(student.getLastName());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(student.getEmail());
				fileWriter.append(COMMA_DELIMITER);
				for (int i = 0; i < student.getAttendance().size(); i++) {
					fileWriter.append(String.valueOf(student.getAttendance(i)));
					fileWriter.append(COMMA_DELIMITER);
				}
				fileWriter.append(String.valueOf(student.totalAttendance()));
				fileWriter.append(COMMA_DELIMITER);
			fileWriter.append(NEW_LINE_SEPARATOR);
		}

		

		} catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            
            try {

                fileWriter.flush();

                fileWriter.close();

            } catch (IOException e) {

                System.out.println("Error while flushing/closing fileWriter !!!");

                e.printStackTrace();

            }

             
        }

}

}