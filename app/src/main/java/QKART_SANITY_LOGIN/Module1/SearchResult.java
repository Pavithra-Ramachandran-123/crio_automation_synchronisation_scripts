package QKART_SANITY_LOGIN.Module1;

import java.net.MalformedURLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;
    //WebElement sizeCharElement= parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div[1]/div/div[1]/button"));

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() throws InterruptedException {
        String titleOfSearchResult = "";
        //WebDriver driver;
        //RemoteWebDriver driver=new RemoteWebDriver();
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        // RemoteWebDriver driver=QkartSanity.createDriver();
        // WebDriverWait wait = new WebDriverWait((WebDriver) driver, 30);
        // // Step 7: Check the contents of the cart
        // WebElement titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']")));
        Thread.sleep(3000);
        WebElement titleText=parentElement.findElement(By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-yg30e6']"));
        Thread.sleep(3000);
        //css-yg30e6p
        //*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[1]/p[1]
        //*[@id="root"]/div/div/div[3]/div[1]/div[2]/div/h4
        //*[@id="root"]/div/div/div[3]/div[1]/div[2]/div
        
        
        titleOfSearchResult = titleText.getText();
        
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            WebElement sizeCharElement= parentElement.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div[1]/div/div[1]/button"));
            sizeCharElement.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
             * Check if the size chart element exists. If it exists, check if the text of the element is "SIZE CHART". 
             * If the text "SIZE CHART" matches for the element, set status = true , else set to false
            */
            WebElement sizeCharElement= parentElement.findElement(By.xpath("//button[text()='Size chart']"));
            //*[@id='root']/div/div/div[3]/div[1]/div[2]/div[1]/div/div[1]/button
            //button[text()='Size chart']
            if(sizeCharElement.isDisplayed()){
                if(sizeCharElement.getText().equals("SIZE CHART")){
                    status=true;
                }
            }

            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            /*
             * Locate the table element when the size chart modal is open
             * 
             * Validate that the contents of expectedTableHeaders is present as the table
             * header in the same order
             * 
             * Validate that the contents of expectedTableBody are present in the table body
             * in the same order
             */
            // //WebElement table= driver.findElement(By.tagName("table"));
            List<WebElement> hrow=driver.findElements(By.xpath("//table/thead/tr/th"));
            for(int i=0;i<hrow.size();i++){
                String td= hrow.get(i).getText();
                //System.out.println(td + expectedTableHeaders.get(i));
                if(!td.trim().equals(expectedTableHeaders.get(i))){
                    status=false;
                }
            }
            // System.out.println("header verified");
            
            List<WebElement> body=driver.findElements(By.xpath("//table/tbody/tr"));
            //System.out.println(body.size());
            for(int i=1;i<=body.size();i++){
                List<String> expectedbd=expectedTableBody.get(i-1);
                for(int j=1;j<=hrow.size();j++){
                    String td= driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+j+"]")).getText();
                    //System.out.print(td+" ");
                    if(!td.trim().equals(expectedbd.get(j-1))){
                        status=false;
                    }

                }
                //System.out.println("\n");
            }
            return status;
        }
        catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = true;
        try {

            return status;
        } catch (Exception e) {
            return status;
        }
    }
}