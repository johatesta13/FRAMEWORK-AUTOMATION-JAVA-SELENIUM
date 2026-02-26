package ar.org.icaro.automatizacion.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * ============================================================
 * CART PAGE - Page Object para la página del carrito
 * ============================================================
 * 
 * CLASE 14 - DATA-DRIVEN TESTING
 * 
 * Este Page Object representa la página del carrito de compras.
 * 
 * URL: https://www.saucedemo.com/cart.html
 * ============================================================
 */
public class CartPage extends BasePage {

    // Localizadores
    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    private By removeButton = By.cssSelector(".cart_button");
    private By itemNames = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Verifica si estamos en la página del carrito.
     */
    public boolean isOnCartPage() {
        return waitForUrlContains("cart");
    }

    /**
     * Obtiene la cantidad de items en el carrito.
     */
    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    /**
     * Verifica si el carrito está vacío.
     */
    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }

    /**
     * Verifica si un producto específico está en el carrito.
     */
    public boolean isProductInCart(String productName) {
        List<WebElement> names = driver.findElements(itemNames);
        for (WebElement name : names) {
            if (name.getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Procede al checkout.
     */
    public CheckoutPage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    /**
     * Vuelve a la página de productos.
     */
    public InventoryPage continueShopping() {
        click(continueShoppingButton);
        return new InventoryPage(driver);
    }

    /**
     * Elimina el primer item del carrito.
     */
    public CartPage removeFirstItem() {
        click(removeButton);
        return this;
    }
}
