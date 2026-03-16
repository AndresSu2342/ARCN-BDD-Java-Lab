package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {

    @SuppressWarnings("unused")
    private WebDriver driver;

    // Localizador del elemento select usando PageFactory
    @FindBy(id = "dropdown")
    private WebElement dropdownElement;

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        // Inicializa los elementos FindBy en esta clase
        PageFactory.initElements(driver, this);
    }

    /**
     * Selecciona una opción del dropdown por su texto visible.
     * @param optionText El texto de la opción a seleccionar (ej. "Option 1")
     */
    public void selectOptionByText(String optionText) {
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(optionText);
    }

    /**
     * Obtiene el texto de la opción actualmente seleccionada en el dropdown.
     * @return El texto de la opción seleccionada.
     */
    public String getSelectedOptionText() {
        Select dropdown = new Select(dropdownElement);
        return dropdown.getFirstSelectedOption().getText();
    }
}
