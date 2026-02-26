package ar.org.icaro.automatizacion.steps;

import ar.org.icaro.automatizacion.pages.InventoryPage;
import ar.org.icaro.automatizacion.pages.LoginPage;
import ar.org.icaro.automatizacion.runner.Hooks;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;


public class LoginSteps {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    //GIVEN

    @Given("estoy en la página de login de SauceDemo")
    public void estoyenlaoaginadelogin(){

        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        System.out.println("   → Navegando a la página de login");

    }

    @Given("estoy logueado como {string}")
    public void estoyLogueadoComo(String usuario){
        loginPage = new LoginPage(Hooks.driver);
        loginPage.goTo();
        inventoryPage = loginPage.loginAs(usuario, "secret_sauce");
    }

    // ============================================================
    // WHEN STEPS
    // ============================================================

    @When("ingreso el usuario {string}")
    public void ingresoElUsuario(String usuario) {
        loginPage.enterUsername(usuario);
        System.out.println("   → Ingresando usuario: " + usuario);
    }

    @And("ingreso la contraseña {string}")
    public void ingresoLaContrasena(String password) {
        loginPage.enterPassword(password);
        System.out.println("   → Ingresando contraseña: ****");
    }

    @And("hago click en el botón Login")
    public void hagoClickEnElBotonLogin() {
        loginPage.clickLoginButton();
        System.out.println("   → Click en botón Login");
    }

    // ============================================================
    // THEN STEPS
    // ============================================================

    @Then("debería ver la página de inventario")
    public void deberiaVerLaPaginaDeInventario() {
        inventoryPage = new InventoryPage(Hooks.driver);
        Assert.assertTrue(
                inventoryPage.isOnInventoryPage(),
                "Debería estar en la página de inventario"
        );
        System.out.println("   ✓ Verificado: Estamos en la página de inventario");
    }

    @And("el título debería ser {string}")
    public void elTituloDeberiaSer(String tituloEsperado) {
        inventoryPage = new InventoryPage(Hooks.driver);
        String tituloActual = inventoryPage.getTitle();
        Assert.assertEquals(
                tituloActual,
                tituloEsperado,
                "El título no coincide"
        );
        System.out.println("   ✓ Verificado: Título es '" + tituloActual + "'");
    }

    @Then("debería ver un mensaje de error")
    public void deberiaVerUnMensajeDeError() {
        Assert.assertTrue(
                loginPage.isErrorDisplayed(),
                "Debería mostrarse un mensaje de error"
        );
        System.out.println("   ✓ Verificado: Se muestra mensaje de error");
    }

    @Then("debería ver el mensaje {string}")
    public void deberiaVerElMensaje(String mensajeEsperado) {
        String mensajeActual = loginPage.getErrorMessage();
        Assert.assertEquals(
                mensajeActual,
                mensajeEsperado,
                "El mensaje de error no coincide"
        );
        System.out.println("   ✓ Verificado: Mensaje es '" + mensajeEsperado + "'");
    }

    @And("debería permanecer en la página de login")
    public void deberiaPermanecerEnLaPaginaDeLogin() {
        Assert.assertTrue(
                loginPage.isOnLoginPage(),
                "Debería permanecer en la página de login"
        );
        System.out.println("   ✓ Verificado: Permanecemos en la página de login");
    }

    @Then("debería ver la página de login")
    public void deberiaVerLaPaginaDeLogin() {
        loginPage = new LoginPage(Hooks.driver);
        Assert.assertTrue(
                loginPage.isLoginButtonDisplayed(),
                "Debería estar en la página de login"
        );
        System.out.println("   ✓ Verificado: Estamos en la página de login");
    }



}
