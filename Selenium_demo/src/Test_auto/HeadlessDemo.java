package Test_auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessDemo {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");         // Bật chế độ headless
        options.addArguments("--window-size=1920,1080"); // Đặt kích thước để tránh lỗi layout
        options.addArguments("--disable-gpu");      // (Tùy chọn) tăng ổn định

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}