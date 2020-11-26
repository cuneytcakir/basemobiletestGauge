package org.example.step;

import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.example.base.BaseTest;
import org.example.helper.ElementHelper;
import org.example.helper.StoreHelper;
import org.example.model.ElementInfo;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.*;


public class StepImplementation extends BaseTest {

    public static final int DEFAULT_WAIT = 30;

    protected JavascriptExecutor getJSExecutor() {
        return (JavascriptExecutor) driver;
    }

    private MobileElement findElement(String key) {
        try {
            ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
            By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
            WebDriverWait webDriverWait = new WebDriverWait(driver, 3, 300);
            MobileElement mobileElement = (MobileElement) webDriverWait
                    .until(ExpectedConditions.presenceOfElementLocated(infoParam));
            return mobileElement;
        } catch (TimeoutException e) {
            Assert.fail("Verilen Sürede Aranan Eleman Oluşmamıştır...");
            return null;
        }
    }

    private List<MobileElement> findElements(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    public void returnList(String key){
        List<MobileElement> elementsOne = findElements(key);
        for (MobileElement a : elementsOne) {
            System.out.println(a.getText());
        }
    }

    @Step({"Native içerikten Webview içeriğe geçiş yap"})
    public void contextChange(){
        Set<String> getCont = driver.getContextHandles();
        for(String switchCont : getCont){
            System.out.println("get" +switchCont);
            if(switchCont.contains("WEBVIEW")){
                driver.context(switchCont);
            }
        }
    }

    @Step({"Back işlemi yap"})
    public void sendBack(){
        driver.navigate().back();
    }

    @Step("<key> elementini tıkla")
    public void clickAndWaitForElement(String key){
        MobileElement mobileElement = findElement(key);
        mobileElement.click();
    }

    @Step("<key> listesinin elementlerini dön")
    public void clickAndReturnElement(String key){
        returnList(key);
    }

    @Step("<key> elementini bul ve <index> li değerine tıkla")
    public void clickListItem(String key, int index) throws InterruptedException {
        List<MobileElement> elementsOne = findElements(key);
        elementsOne.get(index).click();
        Thread.sleep(2000);
    }

    @Step("<key> saniye bekle")
    public void clickListItem(int key) throws InterruptedException {
        Thread.sleep(key);
    }
}
