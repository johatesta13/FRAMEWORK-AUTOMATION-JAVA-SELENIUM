package ar.org.icaro.automatizacion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * ============================================================
 * LOGIN PAGE - Page Object para la página de login
 * ============================================================
 * 
 * CLASE 14 - DATA-DRIVEN TESTING
 * 
 * Este Page Object será utilizado por los Step Definitions
 * para ejecutar las acciones de login definidas en Gherkin.
 * 
 * URL: https://www.saucedemo.com
 * ============================================================
 */
public class LoginPage extends BasePage {

    // Localizadores
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    private static final String URL = "https://www.saucedemo.com";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navega a la página de login.
     */
    public LoginPage goTo() {
        driver.get(URL);
        return this;
    }

    /**
     * Ingresa el nombre de usuario.
     */
    public LoginPage enterUsername(String username) {
        type(usernameField, username);
        return this;
    }

    /**
     * Ingresa la contraseña.
     */
    public LoginPage enterPassword(String password) {
        type(passwordField, password);
        return this;
    }

    /**
     * Hace click en el botón Login.
     * Retorna InventoryPage porque esperamos un login exitoso.
     */
    public InventoryPage clickLogin() {
        click(loginButton);
        return new InventoryPage(driver);
    }

    /**
     * Hace click en Login sin esperar navegación.
     * Usado para tests donde esperamos error.
     */
    public void clickLoginButton() {
        click(loginButton);
    }

    /**
     * Realiza login completo en una sola llamada.
     */
    public InventoryPage loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    /**
     * Verifica si se muestra mensaje de error.
     */
    public boolean isErrorDisplayed() {
        return isElementVisible(errorMessage);
    }

    /**
     * Obtiene el texto del mensaje de error.
     */
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    /**
     * Verifica si estamos en la página de login.
     */
    public boolean isOnLoginPage() {
        return isElementVisible(loginButton);
    }

    /**
     * Verifica si el botón de login es visible.
     */
    public boolean isLoginButtonDisplayed() {
        return isElementVisible(loginButton);
    }
}
