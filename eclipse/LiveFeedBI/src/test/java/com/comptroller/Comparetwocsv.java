package com.comptroller;
import java.io.*;
import java.util.ArrayList;

/* file1 - file2 = file3*/

public class Comparetwocsv {
	
	public static void main(String args[]) throws FileNotFoundException, IOException
	{
	   
	    String file1="C:\\Users\\Likhitha.batthula\\Desktop\\Comptroller Files\\clm013_Contractual_Schedule_P00340_2018_08_22_16_30_09.csv";
	    String file2="C:\\Users\\Likhitha.batthula\\Desktop\\Comptroller Voucher documentation\\Vouchers\\Contractual Voucher Samples\\Normal Claim\\clm013_Contractual_Schedule_P00340_2018_01_11_08_59_23.csv";
	    ArrayList al1=new ArrayList();
	    ArrayList al2=new ArrayList();
	    //ArrayList al3=new ArrayList();

	    BufferedReader CSVFile1 = new BufferedReader(new FileReader(file1));
	    String dataRow1 = CSVFile1.readLine();
	    while (dataRow1 != null)
	    {
	        String[] dataArray1 = dataRow1.split(",");
	        for (String item1:dataArray1)
	        { 
	           al1.add(item1);
	        }

	        dataRow1 = CSVFile1.readLine(); // Read next line of data.
	    }

	     CSVFile1.close();

	    BufferedReader CSVFile2 = new BufferedReader(new FileReader(file2));
	    String dataRow2 = CSVFile2.readLine();
	    while (dataRow2 != null)
	    {
	        String[] dataArray2 = dataRow2.split(",");
	        for (String item2:dataArray2)
	        { 
	           al2.add(item2);

	        }
	        dataRow2 = CSVFile2.readLine(); // Read next line of data.
	    }
	     CSVFile2.close();

	     

	     int size=al1.size();
	     System.out.println(size);

	    /* try
	        {
	            FileWriter writer=new FileWriter(path+file3);
	            while(size!=0)
	            {
	                size--;
	                writer.append(""+al1.get(size));
	                writer.append('\n');
	            }
	            writer.flush();
	            writer.close();
	        }
	        catch(IOException e)
	        {
	            e.printStackTrace();
	        }*/
	}
	
}