# ARCN-BDD-Java-Lab

Laboratorio de Behavior-Driven Development (BDD) con Java, implementando pruebas automatizadas utilizando Cucumber, Selenium WebDriver y el patrón Page Object Model (POM) con PageFactory.

## Descripción

Este proyecto demuestra la implementación de pruebas BDD para aplicaciones web, siguiendo buenas prácticas de automatización de testing. Incluye dos funcionalidades principales:

1. **Búsqueda en Google**: Pruebas básicas de búsqueda web.
2. **Selección de Dropdown en The Internet**: Implementación avanzada utilizando PageFactory para el manejo de elementos web.

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación principal.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Cucumber**: Framework BDD para escribir y ejecutar pruebas en lenguaje natural.
- **Selenium WebDriver 4.15.0**: Automatización de navegadores web.
- **WebDriverManager 5.5.3**: Gestión automática de drivers de navegador.
- **JUnit**: Framework de testing unitario.
- **PageFactory**: Patrón para inicializar elementos web en clases Page Object.

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── org/
│           └── example/
│               └── Main.java          # Clase principal (vacía)
└── test/
    ├── java/
    │   ├── features/                  # Archivos Gherkin (.feature)
    │   │   ├── dropdown.feature       # Escenarios para dropdown
    │   │   └── google_search.feature  # Escenarios para búsqueda
    │   ├── pages/                     # Clases Page Object
    │   │   └── DropdownPage.java      # Page Object para dropdown
    │   ├── runners/                   # Configuración de ejecución
    │   │   └── TestRunner.java        # Runner principal de Cucumber
    │   └── steps/                     # Step Definitions
    │       ├── DropdownSteps.java     # Steps para dropdown
    │       └── SearchSteps.java       # Steps para búsqueda
    └── resources/                     # Recursos de test (vacío)
```

## Funcionalidades Implementadas

### 1. Búsqueda en Google
- **Archivo**: `google_search.feature`
- **Descripción**: Verifica la funcionalidad básica de búsqueda en Google.
- **Escenario**: Búsqueda de un término y verificación de resultados.
- **Implementación**: Usa configuración manual de ChromeDriver.

### 2. Selección de Dropdown en The Internet
- **Archivo**: `dropdown.feature`
- **Descripción**: Prueba la selección de opciones en un dropdown usando el sitio "The Internet" (https://the-internet.herokuapp.com/dropdown).
- **Escenarios**: Selección parametrizada de "Option 1" y "Option 2".
- **Implementación**: Utiliza PageFactory para el manejo de elementos web.
- **Patrón**: Page Object Model con anotaciones `@FindBy`.

## Configuración y Ejecución

### Prerrequisitos

- **Java 21** o superior instalado.
- **Maven 3.6+** instalado.
- Conexión a internet para descarga automática de drivers.

### Ejecución de Tests

Para ejecutar todos los tests BDD:

```bash
mvn clean test
```

Para ejecutar solo los tests del TestRunner:

```bash
mvn test -Dtest=TestRunner
```

### Configuración de Navegador

Los tests se ejecutan en **modo headless** de Chrome para compatibilidad con entornos CI/CD. La configuración incluye:

- `--headless`: Ejecución sin interfaz gráfica.
- `--disable-gpu`: Desactiva aceleración GPU.
- `--no-sandbox`: Evita restricciones de sandbox.
- `--disable-dev-shm-usage`: Soluciona problemas de memoria compartida.
- `--remote-allow-origins=*`: Permite orígenes remotos.

## Reportes de Ejecución

Después de la ejecución, se generan reportes en múltiples formatos:

- **HTML**: `target/HtmlReports/report.html` - Reporte visual interactivo.
- **JSON**: `target/JSonReports/report.json` - Datos estructurados para integración.
- **XML**: `target/JUnitReports/report.xml` - Compatible con herramientas CI/CD.

Los reportes incluyen:
- Estado de cada escenario (pasado/fallado).
- Tiempo de ejecución.
- Capturas de pantalla (si se configuran).
- Enlaces a reportes en línea de Cucumber.

## Implementación Técnica

### Patrón Page Object Model (POM)

#### DropdownPage.java
```java
@FindBy(id = "dropdown")
private WebElement dropdownElement;

public DropdownPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
}

public void selectOptionByText(String optionText) {
    Select dropdown = new Select(dropdownElement);
    dropdown.selectByVisibleText(optionText);
}
```

#### Beneficios del POM con PageFactory:
- **Separación de responsabilidades**: Lógica de UI separada de lógica de negocio.
- **Mantenibilidad**: Cambios en selectores se centralizan.
- **Reutilización**: Métodos de página pueden ser reutilizados en múltiples tests.
- **Legibilidad**: Código más claro y expresivo.

### Gestión de WebDriver

- **WebDriverManager**: Descarga automática de ChromeDriver compatible.
- **Configuración headless**: Optimizada para ejecución en servidores.
- **Timeouts**: Implicit wait de 10 segundos configurado.

### Escenarios BDD

Los escenarios usan **Gherkin** con sintaxis clara:

```gherkin
Feature: Dropdown selection on The Internet
  Scenario Outline: Selecting an option from the dropdown
    Given the user is on the dropdown page
    When the user selects option "<optionName>"
    Then the selected option should be "<optionName>"
```

## Dependencias Actualizadas

Recientemente se actualizaron las versiones para resolver incompatibilidades:

- **Selenium**: 4.0.0 → 4.15.0 (soporte para Chrome moderno)
- **WebDriverManager**: 5.3.0 → 5.5.3 (mejor detección de versiones)


## Licencia

Este proyecto es parte de un laboratorio educativo y no tiene licencia específica.


