package test.Campaign_S3_POMCucumberTransition;

import java.util.Date;

public class SampleJavaClass {
	
	public static void main (String[] args)
	{
		Date rawexpDate = new Date(new Date().getTime());


		String dt = rawexpDate.toString();
		System.out.println(dt);
		String[] ST = dt.split(" ");
		
		System.out.println(ST[0]);
		System.out.println(ST[1]);
		System.out.println(ST[2]);
		System.out.println(ST[3]);
		System.out.println(ST[4]);
		System.out.println(ST[5]);
		
		String unq = ST[5]+ST[1]+ST[2]+ST[3].replaceAll(":", "");
		System.out.println(unq);
		
	}

}


