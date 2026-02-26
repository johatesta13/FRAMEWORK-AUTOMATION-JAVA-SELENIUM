package ar.org.icaro.automatizacion.pages;

import ar.org.icaro.automatizacion.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * ============================================================
 * INVENTORY PAGE - Page Object para la página de productos
 * ============================================================
 * 
 * CLASE 14 - DATA-DRIVEN TESTING
 * 
 * Este Page Object representa la página de inventario/productos.
 * 
 * URL: https://www.saucedemo.com/inventory.html
 * ============================================================
 */
public class InventoryPage extends BasePage {

    // Localizadores
    private By pageTitle = By.className("title");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    
    // Botones de agregar productos
    private By addBackpackBtn = By.id("add-to-cart-sauce-labs-backpack");
    private By addBikeLightBtn = By.id("add-to-cart-sauce-labs-bike-light");
    private By addBoltTshirtBtn = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private By addFleeceJacketBtn = By.id("add-to-cart-sauce-labs-fleece-jacket");
    private By addOnesieBtn = By.id("add-to-cart-sauce-labs-onesie");
    
    // Menú lateral
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si estamos en la página de inventario.
     */
    public boolean isOnInventoryPage() {
        return waitForUrlContains("inventory");
    }

    /**
     * Obtiene el título de la página.
     */
    public String getTitle() {
        return getText(pageTitle);
    }

    /**
     * Agrega un producto al carrito según su nombre.
     * Este método es usado por los Step Definitions.
     */
    public InventoryPage addProductToCart(String productName) {
        switch (productName) {
            case "Sauce Labs Backpack":
                click(addBackpackBtn);
                break;
            case "Sauce Labs Bike Light":
                click(addBikeLightBtn);
                break;
            case "Sauce Labs Bolt T-Shirt":
                click(addBoltTshirtBtn);
                break;
            case "Sauce Labs Fleece Jacket":
                click(addFleeceJacketBtn);
                break;
            case "Sauce Labs Onesie":
                click(addOnesieBtn);
                break;
            default:
                throw new IllegalArgumentException("Producto no reconocido: " + productName);
        }
        return this;
    }

    // Métodos específicos por producto (mantienen compatibilidad)
    public InventoryPage addBackpackToCart() {
        click(addBackpackBtn);
        return this;
    }

    public InventoryPage addBikeLightToCart() {
        click(addBikeLightBtn);
        return this;
    }

    public InventoryPage addBoltTshirtToCart() {
        click(addBoltTshirtBtn);
        return this;
    }

    public InventoryPage addFleeceJacketToCart() {
        click(addFleeceJacketBtn);
        return this;
    }

    public InventoryPage addOnesieToCart() {
        click(addOnesieBtn);
        return this;
    }

    /**
     * Navega al carrito.
     */
    public CartPage goToCart() {
        click(cartIcon);
        return new CartPage(driver);
    }

    /**
     * Realiza logout.
     */
    public LoginPage logout() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        click(menuButton);
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLink));
        click(logoutLink);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("inventory")));
        return new LoginPage(driver);
    }

    /**
     * Obtiene la cantidad de items en el carrito.
     */
    public int getCartItemCount() {
        try {
            String badgeText = getText(cartBadge);
            return Integer.parseInt(badgeText);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * Verifica si el badge del carrito es visible.
     */
    public boolean isCartBadgeVisible() {
        return isElementVisible(cartBadge);
    }
}
