package ar.org.icaro.automatizacion.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ============================================================
 * TEST RUNNER - Configuración de Cucumber con TestNG
 * ============================================================
 * 
 * CLASE 14 - DATA-DRIVEN TESTING
 * 
 * El TestRunner es la clase que EJECUTA los tests de Cucumber.
 * Conecta los archivos .feature con los Step Definitions.
 * 
 * NOVEDAD EN CLASE 14:
 * Los Scenario Outline en los .feature generan múltiples
 * ejecuciones automáticamente según la tabla Examples.
 * ============================================================
 */
@CucumberOptions(
    // ============================================================
    // features: Ubicación de los archivos .feature
    // ============================================================
    features = "src/test/resources/features",
    
    // ============================================================
    // glue: Ubicación de los Step Definitions y Hooks
    // ============================================================
    glue = {
        "ar.org.icaro.automatizacion.steps",
        "ar.org.icaro.automatizacion.runner"
    },
    
    // ============================================================
    // plugin: Formato de los reportes
    // ============================================================
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html"
    },
    
    // ============================================================
    // monochrome: Formato de consola
    // ============================================================
    monochrome = false,
    
    // ============================================================
    // tags: Filtrar qué Scenarios ejecutar (opcional)
    // ============================================================
    // Ejemplo para ejecutar solo los data-driven:
    // tags = "@data-driven"
    tags = ""
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    /**
     * Este metodo permite que cada fila de Examples
     * se ejecute como un test separado en TestNG.
     */
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
