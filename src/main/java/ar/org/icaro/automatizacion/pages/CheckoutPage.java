package ar.org.icaro.automatizacion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ============================================================
 * CHECKOUT PAGE - Page Object para el proceso de checkout
 * ============================================================
 * 
 * CLASE 14 - DATA-DRIVEN TESTING
 * 
 * Este Page Object representa las páginas del proceso de checkout:
 * - checkout-step-one.html (información del cliente)
 * - checkout-step-two.html (resumen de la orden)
 * - checkout-complete.html (confirmación)
 * ============================================================
 */
public class CheckoutPage extends BasePage {

    // Localizadores - Formulario de información
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.cssSelector("[data-test='error']");
    
    // Localizadores - Página de confirmación
    private By finishButton = By.id("finish");
    private By confirmationMessage = By.className("complete-header");
    private By backHomeButton = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si estamos en la página de checkout.
     */
    public boolean isOnCheckoutPage() {
        return waitForUrlContains("checkout");
    }

    /**
     * Ingresa el nombre.
     */
    public CheckoutPage enterFirstName(String firstName) {
        type(firstNameField, firstName);
        return this;
    }

    /**
     * Ingresa el apellido.
     */
    public CheckoutPage enterLastName(String lastName) {
        type(lastNameField, lastName);
        return this;
    }

    /**
     * Ingresa el código postal.
     */
    public CheckoutPage enterPostalCode(String postalCode) {
        type(postalCodeField, postalCode);
        return this;
    }

    /**
     * Completa toda la información del checkout.
     */
    public CheckoutPage completeCheckoutInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
        return this;
    }

    /**
     * Hace click en el botón Continuar.
     */
    public CheckoutPage clickContinue() {
        click(continueButton);
        return this;
    }

    /**
     * Finaliza la compra.
     */
    public CheckoutPage clickFinish() {
        click(finishButton);
        return this;
    }

    /**
     * Verifica si la orden fue completada.
     */
    public boolean isOrderComplete() {
        return waitForUrlContains("checkout-complete");
    }

    /**
     * Obtiene el mensaje de confirmación.
     */
    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }

    /**
     * Vuelve a la página de inicio.
     */
    public InventoryPage clickBackHome() {
        click(backHomeButton);
        return new InventoryPage(driver);
    }

    /**
     * Verifica si se muestra un mensaje de error.
     */
    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }

    /**
     * Obtiene el mensaje de error.
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
