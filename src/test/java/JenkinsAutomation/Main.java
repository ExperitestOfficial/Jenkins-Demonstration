package JenkinsAutomation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class Main {

	public static void main(String[] args) {
		
		TestNG myTestNG = new TestNG();
		XmlSuite mySuite = new XmlSuite(); 
	    mySuite.setName("MySuite"); 
	    mySuite.setParallel(XmlSuite.ParallelMode.TESTS);
	    mySuite.setVerbose(10);
	    List<XmlTest> myTests = new ArrayList<XmlTest>(); 
	    
	    Map<String, Integer> tests_map = new HashMap<String, Integer>();
	    tests_map.put("Android", Integer.parseInt(System.getenv("Android")));
	    tests_map.put("iOS", Integer.parseInt(System.getenv("iOS")));
	    tests_map.put("Chrome", Integer.parseInt(System.getenv("Chrome")));
	    tests_map.put("Firefox", Integer.parseInt(System.getenv("Firefox")));
	    tests_map.put("Internet Explorer", Integer.parseInt(System.getenv("IE")));
	    tests_map.put("Safari", Integer.parseInt(System.getenv("Safari")));
	    
	    for (Map.Entry<String, Integer> entry : tests_map.entrySet()) {
	        String test_platform = entry.getKey();
	        int parallel_executions = entry.getValue();
	        for(int i = 0; i < parallel_executions; i++) {
	        	List<XmlClass> myClasses = new ArrayList<XmlClass>();
	        	XmlTest myTest = new XmlTest(mySuite);
	        	myTest.setName("Automation - " + test_platform + " - " + i);
	        	if (test_platform.equals("Android"))
	        		myClasses.add(new XmlClass("JenkinsAutomation.AndroidTest"));
	        	else if (test_platform.equals("iOS"))
	        		myClasses.add(new XmlClass("JenkinsAutomation.IOSTest"));
	        	else {
	        		Map<String,String> desktop_web_parameters = new HashMap<String, String>();
	        		desktop_web_parameters.put("browser_name", test_platform.toLowerCase());
	        		myTest.setParameters(desktop_web_parameters);
	        		myClasses.add(new XmlClass("JenkinsAutomation.DesktopWebTest"));
	        	}
	        	myTest.setXmlClasses(myClasses);
	        	myTests.add(myTest);
	        }
	    }
	    
	    mySuite.setTests(myTests);
	    List<XmlSuite> mySuites = new ArrayList<XmlSuite>(); 
	    mySuites.add(mySuite);
	    myTestNG.setXmlSuites(mySuites);
	    mySuite.setFileName("testng.xml");
	    myTestNG.run();


	}

}
