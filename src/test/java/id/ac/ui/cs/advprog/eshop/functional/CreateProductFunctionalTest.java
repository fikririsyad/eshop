package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @AfterEach
    void tearDown(ChromeDriver driver) {
        driver.quit();
    }

    @Test
    void simulateCreateProduct(ChromeDriver driver) throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.linkText("See Product")).click();
        driver.findElement(By.linkText("Create Product")).click();

        WebElement nameField = driver.findElement(By.id("nameInput"));
        nameField.clear();
        nameField.sendKeys("Sampo Cap Bambang");

        WebElement quantityField = driver.findElement(By.id("quantityInput"));
        quantityField.clear();
        quantityField.sendKeys("100");
        driver.findElement(By.id("submitButton")).click();

        WebElement productList = driver.findElement(By.cssSelector("table.table-striped"));
        List<WebElement> rows = productList.findElements(By.xpath(".//tbody/tr[position() > 0]"));

        assertTrue(rows.size() > 0);

        for (WebElement row: rows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td"));
            String productName = cells.get(0).getText();
            String productQuantity = cells.get(1).getText();
            assertEquals("Sampo Cap Bambang", productName);
            assertEquals("100", productQuantity);
        }
    }
}
