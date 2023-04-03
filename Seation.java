package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Seation {
	private static BufferedReader bReader;
	
	public static void setSeation(int iD, String fName, String lName, String email, String password) {
		try {
			FileWriter seation=new FileWriter("seation.txt");
			BufferedWriter bw=new BufferedWriter(seation);
			bw.write(iD+"");
			bw.newLine();
			bw.write(fName);
			bw.newLine();
			bw.write(lName);
			bw.newLine();
			bw.write(email);
			bw.newLine();
			bw.write(password);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
		}
	}
	public static String getSeationId() {
    	try {
    		FileReader seation=new FileReader("seation.txt");
    		bReader = new BufferedReader(seation);
    		
    		return bReader.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "";
	}
	public static String getSeationFname() {
    	try {
    		FileReader seation=new FileReader("seation.txt");
    		bReader = new BufferedReader(seation);
    		bReader.readLine();
    		return bReader.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "";
	}
	public static String getSeationLname() {
    	try {
    		FileReader seation=new FileReader("seation.txt");
    		bReader = new BufferedReader(seation);
    		bReader.readLine();
    		bReader.readLine();
    		return bReader.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "";
	}
	public static String getSeationEmail() {
    	try {
    		FileReader seation=new FileReader("seation.txt");
    		bReader = new BufferedReader(seation);
    		bReader.readLine();
    		bReader.readLine();
    		bReader.readLine();
    		return bReader.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "";
	}
	public static String getSeationPassword() {
    	try {
    		FileReader seation=new FileReader("seation.txt");
    		bReader = new BufferedReader(seation);
    		bReader.readLine();
    		bReader.readLine();
    		bReader.readLine();
    		bReader.readLine();
    		return bReader.readLine();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	return "";
	}
	public static void removeSeation() {
		try {
			FileWriter seation=new FileWriter("seation.txt");
			BufferedWriter bw=new BufferedWriter(seation);
			bw.write("");
			bw.close();
		} catch (Exception e) {
		}
	}
	
}
