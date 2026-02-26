# ============================================================
# FLUJO_COMPRA.FEATURE - Scenarios E2E de compra completa
# ============================================================
#
# CLASE 14 - DATA-DRIVEN TESTING
#
# Este archivo demuestra cómo los Steps de diferentes clases
# se combinan para formar un flujo E2E completo.
# ============================================================

Feature: Flujo completo de compra
  Como usuario de SauceDemo
  Quiero poder realizar una compra completa
  Para obtener los productos que necesito

  # ============================================================
  # SCENARIO 1: Compra completa de un producto
  # ============================================================
  Scenario: Compra exitosa de un producto
    Given estoy en la página de login de SauceDemo
    When ingreso el usuario "standard_user"
    And ingreso la contraseña "secret_sauce"
    And hago click en el botón Login
    And agrego "Sauce Labs Backpack" al carrito
    And voy al carrito
    And procedo al checkout
    And completo el formulario con nombre "Juan" apellido "Perez" y codigo postal "5000"
    And hago click en continuar
    And confirmo la compra
    Then debería ver el mensaje de confirmación "Thank you for your order!"

  # ============================================================
  # SCENARIO 2: Compra de múltiples productos
  # ============================================================
  Scenario: Compra exitosa de múltiples productos
    Given estoy logueado como "standard_user"
    And estoy en la página de inventario
    When agrego "Sauce Labs Backpack" al carrito
    And agrego "Sauce Labs Bike Light" al carrito
    Then el contador del carrito debería mostrar "2"
    When voy al carrito
    And procedo al checkout
    And completo el formulario con nombre "Maria" apellido "Garcia" y codigo postal "1234"
    And hago click en continuar
    And confirmo la compra
    Then debería ver el mensaje de confirmación "Thank you for your order!"

  # ============================================================
  # SCENARIO 3: Verificar productos en carrito
  # ============================================================
  Scenario: Agregar productos y verificar en carrito
    Given estoy logueado como "standard_user"
    And estoy en la página de inventario
    When agrego "Sauce Labs Backpack" al carrito
    And agrego "Sauce Labs Bike Light" al carrito
    And voy al carrito
    Then debería ver "Sauce Labs Backpack" en la lista
    And debería ver "Sauce Labs Bike Light" en la lista
