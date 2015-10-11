package Controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Model.GradeDowner;
import Model.ScoreProcessor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

public class ScoreIO {
	
	public ScoreIO () {};

//	private static ScoreIO io = null;
	
	public static double GPA;
	public static double average;
	public static ArrayList<String[]> data = new ArrayList<>();


	public static ScoreIO initialize() {
		ScoreIO a;
		a = new ScoreIO();
		return a;
	}



	
	public void inputAndProcessScore() throws Exception {
		analysisHtml();
		ScoreProcessor.processScore();
		gradeDown(data);
	}

	public void outputXls() throws FileNotFoundException, IOException, RowsExceededException, WriteException {

		 WritableWorkbook workbook = Workbook.createWorkbook(new FileOutputStream("./outputScore.xls"));
	     WritableSheet sheet = workbook.createSheet("SHEET", 0);
//				给表格添加表头	
	            Label label1 = new Label(0, 0, "课头号");
	            sheet.addCell(label1);
	            
	            Label label2 = new Label(1, 0, "课程名称");
	            sheet.addCell(label2);
	          
	            Label label3 = new Label(2, 0, "课程类型");
	            sheet.addCell(label3);
	            Label label4 = new Label(3, 0, "学分");
	            sheet.addCell(label4);
	            Label label5 = new Label(4, 0, "教师");
	            sheet.addCell(label5);
	            Label label6 = new Label(5, 0, "授课学院");
	            sheet.addCell(label6);
	            Label label7 = new Label(6, 0, "学习类型");
	            sheet.addCell(label7);
	            Label label8 = new Label(7, 0, "学年");
	            sheet.addCell(label8);
	            Label label9 = new Label(8, 0, "学期");
	            sheet.addCell(label9);
	            Label label10 = new Label(9, 0, "成绩");
	            sheet.addCell(label10);
	         

//			把arraylist存入表格	   
	        for (int i = 0; i < data.size(); i++)
	        {
	            for (int j = 0; j < 10; j++)
	            {
	            	String[] a = data.get(i);
	            	String b = a[j];
	                Label label = new Label(j, i+1, b);
	                sheet.addCell(label);
	            }
	        }
	   

//	    输出平均分以及GPA
	        Label label11 = new Label(8, data.size()+1 , "加权平均分");
	        sheet.addCell(label11);
	        Label averageLabel = new Label(9, data.size()+1 , Double.toString(average));
	        sheet.addCell(averageLabel);

	        Label label12 = new Label(8, data.size() + 2, "GPA");
	        sheet.addCell(label12);
	        Label GPALabel = new Label(9, data.size() + 2, Double.toString(GPA));
	        sheet.addCell(GPALabel);

	        workbook.write();
	        workbook.close();
	    }
	


	
	private void analysisHtml() throws Exception {
//		加载document
		File inputHtml = new File("./inputScore.html");
		Document doc = Jsoup.parse(inputHtml,"GB2312");
//		过滤html，滤出table
		Element table = doc.select("table[class=table listTable]").first();

		Iterator<Element> selectCourse = table.select("td").iterator();

		
		while (selectCourse.hasNext()) {
			String[] course = new String[] { selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text(),selectCourse.next().text()};
			data.add(course);
			selectCourse.next();
		}
	removeUnuseableScore();
	}
	private void removeUnuseableScore () {
		 ArrayList<String[]> indirector = new ArrayList<>();

	        Iterator<String[]> iterator = data.iterator();
	        while (iterator.hasNext())
	        {
	            String[] course = iterator.next();
	            if (course[9].equals(""))
	                continue;
	            else
	                indirector.add(course);
	        }

	       data = indirector;  //update data
	       
	}
	private void gradeDown(ArrayList<String[]> data){
		data.sort(new GradeDowner());
	}

	}