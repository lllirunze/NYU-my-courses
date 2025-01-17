package files;
import java.io.*;

public class FileUtils {

	public static String readFile(String fileName) {
//		return null;
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String currentLine;
			while ((currentLine = br.readLine()) != null) {
				stringBuilder.append(currentLine);
				stringBuilder.append("\n");
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
	
	public static void saveFile(String fileName, String data) {
		try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
			out.print(data);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
