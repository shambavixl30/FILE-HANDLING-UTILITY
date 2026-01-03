package FileHandlingutility;

	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	public class FileUtility{
	    private String fileName;
	    public FileUtility(String fileName) {
	        this.fileName = fileName;
	    }

	    // Write content to file
	    public void writeToFile(String text) {
	        try (FileWriter writer = new FileWriter(fileName, false)) {
	            writer.write(text);
	            System.out.println("✔ File written successfully.");
	        } catch (IOException e) {
	            System.out.println("❌ Error writing file: " + e.getMessage());
	        }
	    }

	    // Append content
	    public void appendToFile(String text) {
	        try (FileWriter writer = new FileWriter(fileName, true)) {
	            writer.write(text);
	            System.out.println("✔ Text appended successfully.");
	        } catch (IOException e) {
	            System.out.println("❌ Error appending: " + e.getMessage());
	        }
	    }

	    // Read content
	    public void readFile() {
	        try (FileReader reader = new FileReader(fileName)) {
	            int ch;
	            System.out.println("\n📄 File Content:");
	            while ((ch = reader.read()) != -1) {
	                System.out.print((char) ch);
	            }
	            System.out.println("\n");
	        } catch (IOException e) {
	            System.out.println("❌ Error reading: " + e.getMessage());
	        }
	    }
	
//Main Method
	public static void main(String[] args) {
		FileUtility util = new FileUtility("sample.txt");
		util.writeToFile("Codtech It Solution");
		util.appendToFile("\nThis line is appended.");
		util.readFile();
	}
	
	}
		
	

