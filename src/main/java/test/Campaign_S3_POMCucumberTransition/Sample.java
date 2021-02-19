package test.Campaign_S3_POMCucumberTransition;

import java.util.Date;

public class Sample {
	
	public static void main (String args[])
	{
		
		Date retrieveDate = new Date(new Date().getTime());
		System.out.println(retrieveDate);
		
		String formDate = retrieveDate.toString();
		String[] d=formDate.split(" ", 6);
		
		
		System.out.println(d[0]);
		System.out.println(d[1]);
		System.out.println(d[2]);
		System.out.println(d[3]);
		System.out.println(d[4]);
		System.out.println(d[5]);
		
		formDate = formDate.replaceAll("[-+:IST ]*","");
		
		String Name = "Bootcamp_Vishnu"+d[5]+d[1]+d[2]+d[3].replaceAll("[:]*","");
		System.out.println(Name);
	
		
	}

}
