# Preguntas 
### ¿Qué has empezado implementando y por qué?
El primer paso realizado ha sido renombrar las variables para mejorar la legibilidad del código.
### ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?
Al ser `getCityWeather` un método tan grande con tantas llamadas, se dificulta enormemente hacer tests unitarios. Por lo tanto, hemos decidido modularizar al máximo la aplicación para poder realizar tests más sencillos para cada método.
### ¿Qué componentes has creado y por qué?
- `WeatherDataProcessor:` Para procesar los datos del clima y obtener descripciones legibles.
- `GeocodingService:` Para obtener coordenadas geográficas a partir de nombres de ciudades.
- `MeteoService:` Para interactuar con un servicio externo que proporciona datos meteorológicos.
- `Coordinates:` Para representar la latitud y longitud.
### Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?
Hemos utilizado Mockito para realizar mocks en nuestras pruebas unitarias, ya que es una herramienta que facilita la simulación de dependencias.
### ¿Has utilizado streams, lambdas y optionals de Java 8? ¿Qué te parece la programación funcional?
Sí, hemos utilizado streams, lambdas y optionals de Java 8. Nos parece que la programación funcional ofrece una forma más clara y concisa de manejar colecciones y evitar errores comunes, como el uso indebido de *null*. Esto mejora la legibilidad del código, simplifica su mantenimiento y evita el manejo explícito de condiciones complejas.
### ¿Qué piensas del rendimiento de la aplicación?

### ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?

### ¿Cuánto tiempo has invertido para implementar la solución?
Un día y medio.
### ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?
Sí, en un escenario real creemos que valdría la pena dedicar tiempo a realizar esta refactorización. Mantener el código limpio, modular y bien estructurado facilita la escalabilidad y la introducción de nuevas funcionalidades en el futuro. 
