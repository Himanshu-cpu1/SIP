package SIP;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getExtentReports();
    private static ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        // This method can remain empty as extent is initialized in the manager
    	
    }

    @Override
    public void onTestStart(ITestResult result) {
      //  test = extent.createTest(result.getMethod().getMethodName());
     // Get the module name from the class name
        String moduleName = result.getTestClass().getName();
        test = extent.createTest(result.getMethod().getMethodName())
                     .assignCategory(moduleName); // Set module name as category
    
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass(result.getMethod().getMethodName() + "  TEST PASSED ");
    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//        String moduleName = result.getTestClass().getName();    
//        String failureReason = result.getThrowable() != null ? result.getThrowable().getMessage() : "No specific error message";
//
//        // Print to console
//        System.out.println("Test Failed: " + result.getName());
//        if (result.getThrowable() != null) {
//            System.out.println("Reason: " + failureReason);
//        }
//
//        // Log the failure to the report
//        test.fail(result.getMethod().getMethodName() + " - TEST FAILED: " + failureReason + " | Test Failed in Module: " + moduleName);
//    }

    @Override
    public void onTestFailure(ITestResult result) {
    	String moduleName = result.getTestClass().getName();   	
    	String failureReason = result.getThrowable() != null ? result.getThrowable().getMessage() : "No specific error message";
        test.fail(result.getMethod().getMethodName() +"-   TEST FAILED:   " + failureReason + "    Test Failed in Module:   " + moduleName);
       // test.fail("Test Failed in Module:   " + moduleName);
    }
    
    
    @Override
    public void onTestSkipped(ITestResult result) {
    	String moduleName = result.getTestClass().getName();
        test.skip(result.getMethod().getMethodName() +"  TEST SKIPPED  " + "  Test Skipped in Module:  " + moduleName);
        //test.skip("Test Skipped in Module: " + moduleName);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}