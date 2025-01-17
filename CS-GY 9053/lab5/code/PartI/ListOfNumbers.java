import java.io.*;
import java.util.List;
import java.util.ArrayList;
 
public class ListOfNumbers {
	
//    private ArrayList rdfTripleList;
	private ArrayList<RDFTriple<Integer, Integer, Integer>> rdfTripleList;
    private String fileName;
 
    public ListOfNumbers () {
        // create an ArrayList of Pairs of Integers
    	this.rdfTripleList = new ArrayList<RDFTriple<Integer,Integer,Integer>>();
    }
    
    public ArrayList getRdfTripleList() {
    	return this.rdfTripleList;
    }
    
    public void createList() {
    	for (int i = 0 ; i< 100 ; i++) {
    		Integer number1 = (int) (Math.random()*10000);
    		Integer number2 = (int) (Math.random()*10000);
    		Integer number3 = (int) (Math.random()*10000);
    		// fill the existing list with RDFTriple objects
    		// of three numbers.
    		RDFTriple<Integer, Integer, Integer> rdfTriple = new RDFTriple<Integer, Integer, Integer>(number1, number2, number3);
    		this.rdfTripleList.add(rdfTriple);
    	}
    }
    

    public ListOfNumbers (String fileName) {
    	this();
    	this.fileName = fileName;	
    }
    
    public void readList() {
    	
    	this.rdfTripleList = new ArrayList<RDFTriple<Integer,Integer,Integer>>();
    	
    	try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName))) {
    		String line;
    		while ((line = bufferedReader.readLine()) != null) {
    			String[] nums = line.split(" ");
    			Integer num1 = Integer.parseInt(nums[0]);
    			Integer num2 = Integer.parseInt(nums[1]);
    			Integer num3 = Integer.parseInt(nums[2]);
    			RDFTriple<Integer, Integer, Integer> rdfTriple = new RDFTriple<Integer, Integer, Integer>(num1, num2, num3);
    			this.rdfTripleList.add(rdfTriple);
    		}
    	} catch (FileNotFoundException e) {
			// TODO: handle exception
    		e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }
    
    public void writeList() {
        PrintWriter out = null;
        try {
            System.out.println("Entering try statement");
            out = new PrintWriter(new FileWriter("outFile.txt"));
            for (int i = 0; i < rdfTripleList.size(); i++)
                out.println(rdfTripleList.get(i).getSubj() + " " + rdfTripleList.get(i).getPred()+ " " + rdfTripleList.get(i).getObj());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IndexOutOfBoundsException: " +
                                 e.getMessage());
        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        } finally {
            if (out != null) {
                System.out.println("Closing PrintWriter");
                out.close();
            } else {
                System.out.println("PrintWriter not open");
            }
        }
    }
    
    public static void cat(String fileName) {
        RandomAccessFile input = null;
        String line = null;
        File file = new File(fileName);
        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (FileNotFoundException e) {
			// TODO: handle exception
    		e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (input != null) {
	                input.close();
	            }
			} catch (IOException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
        }
    }
    
    public static void main(String[] args) {
    	ListOfNumbers listOfNumbers = new ListOfNumbers("numberfile.txt");
    	ListOfNumbers.cat("numberfile.txt");
    	listOfNumbers.readList();
    	listOfNumbers.writeList();
    }

}
