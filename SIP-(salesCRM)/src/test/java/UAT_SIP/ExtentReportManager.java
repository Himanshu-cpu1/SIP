package UAT_SIP;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("UAT_SIP.html");
           // sparkReporter.config().setTheme(Theme.DARK);  // Set theme to DARK for black background
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Automation Test Report-Of-SIP");
            sparkReporter.config().setReportName("Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Tester", "Himanshu Ranjan"); // Update with your name if needed
            extent.setSystemInfo("Java Version", System.getProperty("java.version")); // Add Java version
            extent.setSystemInfo("Browser", "Chrome"); // Update with the browser you're using
            extent.setSystemInfo("Browser Version", "130.0.6723.70"); // Update with the browser version
            try {
                extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName()); // Get the host name
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        		
        }
        return extent;
    }
}

