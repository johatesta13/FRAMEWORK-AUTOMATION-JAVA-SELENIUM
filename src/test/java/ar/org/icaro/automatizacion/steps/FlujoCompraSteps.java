package ar.org.icaro.automatizacion.steps;

import ar.org.icaro.automatizacion.pages.*;
import ar.org.icaro.automatizacion.runner.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

/**
 * ============================================================
 * FLUJO COMPRA STEPS - Step Definitions para el flujo de compra
 * ============================================================
 *
 * CLASE 14 - DATA-DRIVEN TESTING
 *
 * Esta clase contiene los Step Definitions para:
 * - Agregar productos al carrito
 * - Navegar al carrito
 * - Proceso de checkout
 * - Confirmación de compra
 * ============================================================
 */
public class FlujoCompraSteps {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    // ============================================================
    // GIVEN STEPSa
    // ============================================================

    @Given("estoy en la página de inventario")
    public void estoyEnLaPaginaDeInventario() {
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertTrue(
                inventoryPage.isOnInventoryPage(),
                "Debería estar en la página de inventario"
        );
        System.out.println("   → Estamos en la página de inventario");
    }

    @Given("tengo {string} en el carrito")
    public void tengoProductoEnElCarrito(String producto) {
        inventoryPage = new InventoryPage(Hooks.driver);
        inventoryPage.addProductToCart(producto);
        System.out.println("   → Producto agregado al carrito: " + producto);
    }

    @Given("tengo productos en el carrito")
    public void tengoProductosEnElCarrito() {
        inventoryPage = new InventoryPage(Hooks.driver);
        inventoryPage.addBackpackToCart();
        System.out.println("   → Producto por defecto agregado al carrito");
    }

    // ============================================================
    // WHEN STEPS
    // ============================================================

    @When("agrego {string} al carrito")
    public void agregoProductoAlCarrito(String producto) {
        inventoryPage = new InventoryPage(Hooks.driver);
        inventoryPage.addProductToCart(producto);
        System.out.println("   → Agregando al carrito: " + producto);
    }

    @When("voy al carrito")
    public void voyAlCarrito() {
        inventoryPage = new InventoryPage(Hooks.driver);
        cartPage = inventoryPage.goToCart();
        System.out.println("   → Navegando al carrito");
    }

    @When("procedo al checkout")
    public void procedoAlCheckout() {
        if (cartPage == null) {
            cartPage = new CartPage(Hooks.driver);
        }
        checkoutPage = cartPage.proceedToCheckout();
        System.out.println("   → Procediendo al checkout");
    }

    @When("completo el formulario con nombre {string} apellido {string} y codigo postal {string}")
    public void completoElFormulario(String nombre, String apellido, String codigoPostal) {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage(Hooks.driver);
        }
        checkoutPage.completeCheckoutInfo(nombre, apellido, codigoPostal);
        System.out.println("   → Formulario completado: " + nombre + " " + apellido);
    }

    @And("hago click en continuar")
    public void hagoClickEnContinuar() {
        checkoutPage.clickContinue();
        System.out.println("   → Click en continuar");
    }

    @And("confirmo la compra")
    public void confirmoLaCompra() {
        checkoutPage.clickFinish();
        System.out.println("   → Compra confirmada");
    }

    @When("hago click en {string}")
    public void hagoClickEn(String boton) {
        switch (boton) {
            case "Continue Shopping":
                cartPage = new CartPage(Hooks.driver);
                inventoryPage = cartPage.continueShopping();
                break;
            case "Back Home":
                checkoutPage = new CheckoutPage(Hooks.driver);
                inventoryPage = checkoutPage.clickBackHome();
                break;
            default:
                throw new IllegalArgumentException("Botón no reconocido: " + boton);
        }
        System.out.println("   → Click en: " + boton);
    }

    @When("hago click en continuar sin completar datos")
    public void hagoClickEnContinuarSinCompletarDatos() {
        checkoutPage = new CheckoutPage(Hooks.driver);
        checkoutPage.clickContinue();
        System.out.println("   → Click en continuar sin datos");
    }

    @When("hago logout")
    public void hagoLogout() {
        inventoryPage = new InventoryPage(Hooks.driver);
        inventoryPage.logout();
        System.out.println("   → Realizando logout");
    }

    // ============================================================
    // THEN STEPS
    // ============================================================

    @Then("el contador del carrito debería mostrar {string}")
    public void elContadorDelCarritoDeberiaMostrar(String cantidadEsperada) {
        inventoryPage = new InventoryPage(Hooks.driver);
        int cantidadActual = inventoryPage.getCartItemCount();
        Assert.assertEquals(
                String.valueOf(cantidadActual),
                cantidadEsperada,
                "La cantidad en el carrito no coincide"
        );
        System.out.println("   ✓ Verificado: Carrito muestra " + cantidadActual + " items");
    }

    @Then("el contador del carrito no debería mostrarse")
    public void elContadorDelCarritoNoDeberiaMostrarse() {
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertFalse(
                inventoryPage.isCartBadgeVisible(),
                "El badge del carrito no debería ser visible"
        );
        System.out.println("   ✓ Verificado: Badge del carrito no visible");
    }

    @Then("debería ver {string} en la lista")
    public void deberiaVerProductoEnLaLista(String producto) {
        cartPage = new CartPage(Hooks.driver);
        Assert.assertTrue(
                cartPage.isProductInCart(producto),
                "El producto debería estar en el carrito: " + producto
        );
        System.out.println("   ✓ Verificado: '" + producto + "' está en el carrito");
    }

    @Then("debería ver un mensaje de error de validación")
    public void deberiaVerUnMensajeDeErrorDeValidacion() {
        checkoutPage = new CheckoutPage(Hooks.driver);
        Assert.assertTrue(
                checkoutPage.isErrorDisplayed(),
                "Debería mostrarse un error de validación"
        );
        System.out.println("   ✓ Verificado: Se muestra error de validación");
    }

    @Then("debería ver el mensaje de confirmación {string}")
    public void deberiaVerElMensajeDeConfirmacion(String mensajeEsperado) {
        checkoutPage = new CheckoutPage(Hooks.driver);
        String mensajeActual = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(
                mensajeActual,
                mensajeEsperado,
                "El mensaje de confirmación no coincide"
        );
        System.out.println("   ✓ Verificado: Mensaje de confirmación es '" + mensajeActual + "'");
    }
}
