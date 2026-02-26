# ============================================================
# LOGIN_DATA_DRIVEN.FEATURE - Ejemplo de Scenario Outline
# ============================================================
#
# CLASE 14 - DATA-DRIVEN TESTING
#
# Este archivo demuestra cómo usar Scenario Outline para
# ejecutar el mismo escenario con diferentes datos de prueba.
#
# BENEFICIOS:
# • Reduce duplicación de código Gherkin
# • Fácil agregar nuevos casos de prueba
# • Los Step Definitions NO necesitan cambios
# ============================================================

Feature: Login Data-Driven en SauceDemo
  Como QA Automation
  Quiero probar el login con múltiples combinaciones de datos
  Para verificar todos los casos posibles eficientemente

  # ============================================================
  # SCENARIO OUTLINE: Un template, múltiples ejecuciones
  # ============================================================
  # 
  # Los <placeholders> se reemplazan por los valores de Examples.
  # Cada fila de Examples = una ejecución del Scenario.
  #
  # Este ejemplo ejecutará 6 tests automáticamente.
  # ============================================================

  @data-driven @login
  Scenario Outline: Verificar login con credenciales "<descripcion>"
    Given estoy en la página de login de SauceDemo
    When ingreso el usuario "<usuario>"
    And ingreso la contraseña "<password>"
    And hago click en el botón Login
    Then debería ver "<resultado>"

    # ============================================================
    # EXAMPLES: Tabla de datos de prueba
    # ============================================================
    # Primera fila = nombres de columnas (headers)
    # Siguientes filas = datos para cada ejecución
    # ============================================================

    @happy-path
    Examples: Usuarios válidos (camino feliz)
      | descripcion              | usuario         | password     | resultado                  |
      | usuario estándar         | standard_user   | secret_sauce | la página de inventario    |
      | usuario con problemas    | problem_user    | secret_sauce | la página de inventario    |

    @negative
    Examples: Credenciales inválidas (casos negativos)
      | descripcion              | usuario         | password        | resultado                    |
      | contraseña incorrecta    | standard_user   | wrong_password  | credenciales inválidas       |
      | usuario bloqueado        | locked_out_user | secret_sauce    | usuario bloqueado            |

    @edge-cases
    Examples: Campos vacíos (casos borde)
      | descripcion              | usuario         | password     | resultado              |
      | sin usuario              |                 | secret_sauce | username requerido     |
      | sin contraseña           | standard_user   |              | password requerido     |


  # ============================================================
  # SCENARIO OUTLINE: Agregar productos al carrito
  # ============================================================
  # Otro ejemplo mostrando data-driven con productos
  # ============================================================

  @data-driven @carrito
  Scenario Outline: Agregar "<producto>" al carrito
    Given estoy logueado como "standard_user"
    When agrego "<producto>" al carrito
    Then el contador del carrito debería mostrar "1"

    Examples: Productos disponibles
      | producto                 |
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
