package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.Assert.assertEquals;

import pages.DropdownPage;
import java.time.Duration;

public class DropdownSteps {
    private WebDriver driver;
    private DropdownPage dropdownPage;

    @Before("@DropdownTest") // Opcional: Para independizar del otro Before si corren juntos
    public void setUp() {
        // Si se ejecuta junto con SearchSteps, Cucumber podría quejarse de múltiples
        // @Before y @After.
        // Lo ideal en un framework real es usar un archivo Hooks.java compartido.
        // Para este reto usaremos la inicialización en el Given si driver es null o
        // dejaremos que un solo bloque @Before maneje todo.
    }

    @Given("the user is on the dropdown page")
    public void the_user_is_on_the_dropdown_page() {
        // Inicializamos WebDriver aquí para este Feature específicamente a modo
        // educativo
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navegamos a The Internet Herokuapp, página de desplegables
        driver.get("https://the-internet.herokuapp.com/dropdown");

        // Inicializamos nuestro PageObject
        dropdownPage = new DropdownPage(driver);
    }

    @When("the user selects option {string}")
    public void the_user_selects_option(String optionName) {
        // Usamos el PageObject para interactuar - sin WebDriver crudo aquí!
        dropdownPage.selectOptionByText(optionName);
    }

    @Then("the selected option should be {string}")
    public void the_selected_option_should_be(String expectedOption) {
        // Obtenemos el valor desde el PageObject y comparamos
        String actualOption = dropdownPage.getSelectedOptionText();
        assertEquals("The selected option does not match the expected value.", expectedOption, actualOption);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}