package Model;

import java.util.Iterator;

import Controller.ScoreIO;

public class ScoreProcessor {
	
	private static double sumOfWeight = 0;
	
	public static void processScore() {
		
		calWeight();
		calGPA();
		calAverage();
	
	}
//计算学分总数
private static void calWeight() {
	 

     ScoreIO.initialize();
		Iterator<String[]> scoreIte = ScoreIO.data.iterator();

     while (scoreIte.hasNext())
     {
         String[] subject = scoreIte.next();
         if (Double.valueOf(subject[9])>= 60.0) {
        	 sumOfWeight += Double.valueOf(subject[3]);
         }
        
     }

     
       
}
	//	计算GPA
	private static void calGPA(){
		
		double sumOfGPA = 0;
		ScoreIO.initialize();
		Iterator<String[]> iterator = ScoreIO.data.iterator();

	        while (iterator.hasNext())
	        {
	            String[] subject = iterator.next();
	            if (Double.valueOf(subject[9])>= 90.0)
	            {
	                sumOfGPA += 4.0 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 85.0)
	            {
	                sumOfGPA += 3.7 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 82.0)
	            {
	                sumOfGPA += 3.3 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 78.0)
	            {
	                sumOfGPA += 3.0 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 75.0)
	            {
                sumOfGPA += 2.7 * Double.valueOf(subject[3]);
	            }
            else if (Double.valueOf(subject[9])>= 72.0)
	            {
	                sumOfGPA += 2.3 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 68.0)
	            {
	                sumOfGPA += 2.0 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 64.0)
	            {
	                sumOfGPA += 1.5 * Double.valueOf(subject[3]);
	            }
	            else if (Double.valueOf(subject[9])>= 60.0)
	            {
	                sumOfGPA += 1.0 * Double.valueOf(subject[3]);
	            }
	           

	        ScoreIO.GPA = sumOfGPA/sumOfWeight;
	    }
		
	}
//	计算加权平均
	private static void calAverage() {
		 
	        double sumOfScore = 0;
	        ScoreIO.initialize();
			Iterator<String[]> scoreIte = ScoreIO.data.iterator();

	        while (scoreIte.hasNext())
	        {
        		String[] subject = scoreIte.next();
	        	if (Double.valueOf(subject[9])>= 60.0) {
		            sumOfScore += Double.valueOf(subject[3]) * Double.valueOf(subject[9]);
	            } 
	        }
	            ScoreIO.average =sumOfScore/sumOfWeight;
	}
}
