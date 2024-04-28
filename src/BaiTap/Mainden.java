package BaiTap;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;

public class Mainden{
//<T extends Number>{

	  public static  String id;
	 public static  String name;
	 public static  String address;
	 public static  Date dateOfBirth;
	 public static int Sum;

	  
	    public Mainden(String id,String name, String address,Date dateOfBirth) {
	        this.id = id;
	        this.name = name;
	        this.address = address;
	        this.dateOfBirth = dateOfBirth;
	    }
	    

		public static void main(String[] args) {
	    	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	    	SimpleDateFormat df1 = new SimpleDateFormat("yyyy");
	    	SimpleDateFormat df2 = new SimpleDateFormat("MM");
	    	SimpleDateFormat df3 = new SimpleDateFormat("dd");

	    	Scanner sc = new Scanner(System.in);
	    	Mainden m1 = new Mainden( id, name, address, dateOfBirth);
	    	System.out.print("nhap id:");
	    	id = sc.nextLine();
	    	System.out.print("nhap name:");
	    	name = sc.nextLine();
	    	System.out.print("nhap address:");
	    	address = sc.nextLine();
	    	System.out.print("nhap ngaythangnamsinh(cu_phap: dd-MM-yyyy):");
	    	try {
				dateOfBirth =df.parse(sc.nextLine());
			} catch (ParseException e) {
				System.out.println("Ban nhap sai cu phap!");
				e.printStackTrace();
			}
	    	
	    	Thread DocFile = new Thread(new Runnable() {
	            @Override
	            public void run() {
	            	File file = new File("src/BaiTap/file.xml");
	    	        // Dùng FileReader để đọc
	    	        try (FileReader fileReader = new FileReader(file)) {
	    	        	int character;
	    	            // Đọc từng ký tự
	    	            while ((character = fileReader.read()) != -1) {
	    	                System.out.print((char) character);
	    	            }
	    	        } catch (IOException e) {
	    	            e.printStackTrace();
	    	        }
	    	        System.out.println();
	            }
	        });
	    	        
	    	

	        //  Tính tuổi
	        Thread Age = new Thread(new Runnable() {
	            @Override
	            public void run() {
	            	String year = df1.format(dateOfBirth);
	                int age = 2024 - Integer.parseInt(year) ;
	                System.out.println("Tuổi: " + age);
	            }
	        });

	        //  Ktr so nguyen to
//	        int Sum;
	        Thread isDigit = new Thread(new Runnable() {
	            @Override
	            public void run() {
	            	int sumy=0, sumM=0,sumd=0;
	                String year = df1.format(dateOfBirth);
	                int y =Integer.parseInt(year);
	                while(y>0){
	                	sumy +=(y%10);
	                	y/=10;
	                }
//                	System.out.println(sumy);
	                String month = df2.format(dateOfBirth);
	                int m =Integer.parseInt(month);
	                while(m>0){
	                	sumM +=(m%10);
	                	m/=10;
	                }
//	                System.out.println(sumM);
	                String date = df3.format(dateOfBirth);
	                int d =Integer.parseInt(date);
	                while(d>0){
	                	sumd +=(d%10);
	                	d/=10;
	                }
//	                System.out.println(sumd);
	                Sum = sumy+sumM+sumd;
	                System.out.println("Tổng các chữ số dateofbirth: "+Sum);
	                
	                int dem = 0;
	                for(int i=2;i<= Math.sqrt(Sum);i++) {
	                	if(Sum%i==0) {
	                		dem++;
	                	}	
	                }
	                if(Sum<2 && dem>0) {
	                	System.out.println(Sum+" không phải là số nguyên tố ");
	                }
	                else {
	                	System.out.println(Sum+" là số nguyên tố ");
	                }
	                
	            }
	        });

	        //  thực hiện các luồng
	        DocFile.start();
	        Age.start();
	        isDigit.start();
	        try {
	        	DocFile.join();
	            Age.join();
	            isDigit.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        System.out.println("");
	        
	        
	       
	        File file2 = new File("src/BaiTap/test.xml");
	        String string = "Student("+Age+", "+Sum+", "+isDigit+")";
//	        String currentPath = new File("").getAbsolutePath();
//	        System.out.println("Đường dẫn hiện tại: " + currentPath);
//	        Dùng try () {} thế này sẽ tự gọi .close()
	        try (FileWriter fileWriter = new FileWriter(file2)) {
	            fileWriter.write(string);
	        } catch (IOException e) {
	        	System.out.println("Thông tin trống!");
	            e.printStackTrace();
	    }
	}	
		
		void DocFileOther() {
		File file3 = new File("src/BaiTap/test.xml");
        // Dùng FileReader để đọc
        try (FileReader fileReader = new FileReader(file3)) {
        	int character;
            // Đọc từng ký tự
            while ((character = fileReader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
