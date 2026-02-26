# 🤖 Framework de Automatización — Java + Selenium + Cucumber

Framework de testing end-to-end construido con **Java**, **Selenium WebDriver**, **Cucumber (BDD)** y **TestNG**. Diseñado con el patrón **Page Object Model (POM)** para ser escalable y mantenible.

---

## 🧰 Stack tecnológico

| Herramienta | Versión |
|---|---|
| Java | 25 |
| Selenium WebDriver | 4.27.0 |
| Cucumber | 7.18.0 |
| TestNG | 7.8.0 |
| Maven | 3.x |

---

## 📁 Estructura del proyecto

```
src/
└── test/
    ├── java/
    │   └── ar/org/icaro/automatizacion/
    │       ├── runner/
    │       │   ├── Hooks.java          # Configuración pre/post escenario
    │       │   └── TestRunner.java     # Runner principal de Cucumber + TestNG
    │       └── steps/
    │           ├── FlujoCompraStep.java
    │           └── LoginSteps.java
    └── resources/
        └── features/
            ├── cart.feature
            ├── checkout.feature
            ├── flujo_compra.feature
            ├── login.feature
            └── login_data_driven.feature
pom.xml
testng.xml
```

---

## ⚙️ Setup

### Prerequisitos

- **Java JDK 25** instalado → [Descargar](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.x** instalado → [Descargar](https://maven.apache.org/download.cgi)
- **Google Chrome** instalado (versión actualizada)
- **IntelliJ IDEA** (recomendado)

### Instalación

```bash
# 1. Clonar el repositorio
git clone https://github.com/johatesta13/FRAMEWORK-AUTOMATION-JAVA-SELENIUM.git

# 2. Entrar al directorio
cd FRAMEWORK-AUTOMATION-JAVA-SELENIUM

# 3. Instalar dependencias
mvn clean install -DskipTests
```

### Verificar versión de Java

```bash
java -version
# Debe mostrar: openjdk version "25"
```

---

## 🚀 Ejecutar los tests

### Desde la terminal (Maven)

```bash
# Correr todos los tests
mvn test

# Correr un tag específico de Cucumber
mvn test -Dcucumber.filter.tags="@login"
```

### Desde IntelliJ IDEA

1. Abrir el proyecto en IntelliJ
2. Hacer clic en el ícono 🐘 para sincronizar Maven
3. Ir a `src/test/java/.../runner/TestRunner.java`
4. Click derecho → **Run 'TestRunner'**

### Desde `testng.xml`

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 🐛 Troubleshooting

**Error: `module not found: org.seleniumhq.selenium.*`**

Verificar que la versión de Selenium en `pom.xml` sea válida:
```xml
<!-- ✅ Usar esta versión -->
<selenium.version>4.27.0</selenium.version>
```

**ChromeDriver desactualizado**

Selenium 4.6+ incluye **Selenium Manager** que descarga el driver automáticamente. No hace falta configurar ChromeDriver manualmente.

---

## 📝 Escribir nuevos escenarios

Los escenarios se escriben en Gherkin dentro de `/resources/features/`:

```gherkin
Feature: Login de usuario

  @login @smoke
  Scenario: Login exitoso con credenciales válidas
    Given el usuario está en la página de login
    When ingresa usuario "user@test.com" y contraseña "123456"
    Then debería ver el dashboard
```

---

## 👤 Autor

**Joha Testa** — [@johatesta13](https://github.com/johatesta13)
