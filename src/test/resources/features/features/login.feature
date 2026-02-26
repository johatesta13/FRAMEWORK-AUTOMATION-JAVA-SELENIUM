# ============================================================
# LOGIN.FEATURE - Scenarios de autenticación
# ============================================================
#
# CLASE 14 - DATA-DRIVEN TESTING
#
# Este archivo contiene los scenarios de login ORIGINALES
# de la Clase 13. Mantenidos para compatibilidad y como
# ejemplo de Scenarios tradicionales (sin Scenario Outline).
#
# Para ver Data-Driven Testing, ver: login_data_driven.feature
# ============================================================

Feature: Login de usuarios en SauceDemo
  Como usuario de SauceDemo
  Quiero poder iniciar sesión con mis credenciales
  Para acceder a los productos de la tienda

  # ============================================================
  # SCENARIO 1: Login exitoso (camino feliz)
  # ============================================================
  Scenario: Login exitoso con credenciales válidas
    Given estoy en la página de login de SauceDemo
    When ingreso el usuario "standard_user"
    And ingreso la contraseña "secret_sauce"
    And hago click en el botón Login
    Then debería ver la página de inventario
    And el título debería ser "Products"

  # ============================================================
  # SCENARIO 2: Login fallido - contraseña incorrecta
  # ============================================================
  Scenario: Login fallido con contraseña incorrecta
    Given estoy en la página de login de SauceDemo
    When ingreso el usuario "standard_user"
    And ingreso la contraseña "password_incorrecto"
    And hago click en el botón Login
    Then debería ver un mensaje de error
    And debería permanecer en la página de login

  # ============================================================
  # SCENARIO 3: Login fallido - usuario bloqueado
  # ============================================================
  Scenario: Login fallido con usuario bloqueado
    Given estoy en la página de login de SauceDemo
    When ingreso el usuario "locked_out_user"
    And ingreso la contraseña "secret_sauce"
    And hago click en el botón Login
    Then debería ver el mensaje "Epic sadface: Sorry, this user has been locked out."

  # ============================================================
  # SCENARIO 4: Login fallido - campos vacíos
  # ============================================================
  Scenario: Login fallido sin ingresar credenciales
    Given estoy en la página de login de SauceDemo
    When hago click en el botón Login
    Then debería ver el mensaje "Epic sadface: Username is required"

  # ============================================================
  # SCENARIO 5: Login fallido - solo usuario
  # ============================================================
  Scenario: Login fallido ingresando solo usuario
    Given estoy en la página de login de SauceDemo
    When ingreso el usuario "standard_user"
    And hago click en el botón Login
    Then debería ver el mensaje "Epic sadface: Password is required"
