package QKART_SANITY_LOGIN.Module1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
            driver.findElement(By.xpath("//button[text()='Add new address']")).click();
            driver.findElement(By.xpath("//textarea[@placeholder='Enter your complete address']")).sendKeys(addresString);
            driver.findElement(By.xpath("//button[text()='Add']")).click();
            //Thread.sleep(3000);
            WebDriverWait wait=new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Add new address']")));
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            WebDriverWait wait=new WebDriverWait(driver, 20);
            List<WebElement> searchResults=
                    driver.findElements(By.xpath("(//div[@class='MuiBox-root css-0'])[1]"));
            for(WebElement element:searchResults){
                if(element.getText().contains(addressToSelect)){

                    element.click();
                    wait.until(ExpectedConditions.elementToBeClickable(element));
                    //element.findElement(By.xpath("//button[text()='Add to cart']")).click();
                    //Thread.sleep(5000);

                    return true;
                }
            }
            System.out.println("Unable to find the given address");
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            driver.findElement(By.xpath("//button[text()='PLACE ORDER']")).click();
            WebDriverWait wait=new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.urlContains("/thanks"));
            //Thread.sleep(2000);
            return true;
            //return false;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        Boolean status=false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6
            WebElement errorElement=driver.findElement(By.xpath("//div[@id='notistack-snackbar']"));
            if(errorElement.isDisplayed()){
                if(errorElement.getText().contains("You do not have enough balance in your wallet for this purchase")){
                    status=true;
                }
            }
            return status;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
