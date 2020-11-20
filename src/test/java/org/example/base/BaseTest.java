package org.example.base;


import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class BaseTest {

    public static String APPPACKAGE = "trendyol.com";
    public static String APPACTIVITY = "com.trendyol.ui.splash.SplashActivity";
    public static AppiumDriver<MobileElement> driver;

    @BeforeScenario
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        capabilities.setCapability("appPackage", APPPACKAGE);
        capabilities.setCapability("appActivity", APPACTIVITY);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AOSP on IA Emulator");
        //capabilities.setCapability(MobileCapabilityType.APP, "/Users/cuneytcakir/Desktop/NTV.ipa");
        capabilities.setCapability("udid", "emulator-5554");
        URL hubURL = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(hubURL, capabilities);
    }

    @AfterScenario
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
