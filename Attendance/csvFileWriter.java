import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvFileWriter {

	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	public static void csvFileWriter(ArrayList<Student> allStudents) {

		FileWriter fileWriter = null;

		try {


			fileWriter = new FileWriter("Test.csv");

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