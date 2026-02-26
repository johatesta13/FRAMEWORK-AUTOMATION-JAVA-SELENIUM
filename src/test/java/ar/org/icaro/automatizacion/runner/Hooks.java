package ar.org.icaro.automatizacion.runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

/**
 * ============================================================
 * HOOKS - Configuración de Cucumber @Before y @After
 * ============================================================
 * 
 * CLASE 14 - DATA-DRIVEN TESTING
 * 
 * Los Hooks se ejecutan ANTES y DESPUÉS de cada Scenario.
 * 
 * IMPORTANTE PARA SCENARIO OUTLINE:
 * Con Scenario Outline, @Before y @After se ejecutan por
 * CADA FILA de Examples. Si tenés 6 filas, se abrirá
 * y cerrará el navegador 6 veces.
 * ============================================================
 */
public class Hooks {

    // Driver estático para acceso desde Step Definitions
    public static WebDriver driver;

    /**
     * @Before - Se ejecuta ANTES de cada Scenario/Example
     */
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n========================================");
        System.out.println("INICIANDO: " + scenario.getName());
        System.out.println("========================================\n");

        // Configurar ChromeDriver automáticamente
        WebDriverManager.chromedriver().setup();

        // Configurar opciones de Chrome
        ChromeOptions options = new ChromeOptions();
        
        // Deshabilitar pantalla de selección de buscador
        options.addArguments("--disable-search-engine-choice-screen");
        
        // Deshabilitar notificaciones
        options.addArguments("--disable-notifications");
        
        // Deshabilitar barra de información
        options.addArguments("--disable-infobars");
        
        // Permitir orígenes remotos
        options.addArguments("--remote-allow-origins=*");

        // Preferencias experimentales
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Crear el driver
        driver = new ChromeDriver(options);
        
        // Maximizar ventana
        driver.manage().window().maximize();
    }

    /**
     * @After - Se ejecuta DESPUÉS de cada Scenario/Example
     */
    @After
    public void tearDown(Scenario scenario) {
        // Mostrar resultado
        if (scenario.isFailed()) {
            System.out.println("\n❌ FALLÓ: " + scenario.getName());
        } else {
            System.out.println("\n✅ PASÓ: " + scenario.getName());
        }
        System.out.println("========================================\n");

        // Cerrar navegador
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
