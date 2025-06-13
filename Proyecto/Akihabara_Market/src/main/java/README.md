# Akihabara Market

**Autor**: Jacobo Álvarez Delgado
**Version**: 1.0

## Contenido del Proyecto:

- Proyecto Maven de Java (Akihabara Market)
- Archivo SQL (Akihabra.SQL)
- Manual de Uso

## Funcionalidades Principales

- Agregar Productos / Eliminar Productos.
- Actualizar producto existente.
- Solicitar ayuda a la I.A.
- Mostrar productos.
- Mostrar productos por id especifica.

## Tecnologías Utilizadas

- Java 23 [JavaSE-1.8]
- Estructura DAO
- MYSQL WORKBENCH
- Uso de API's
- POO

### Config.properties

-Si lo hemos clonado de git, no tendra el archivo "confi.properties", en caso de que no este lo tendremos que crear nosotros a la altura del proyecto [src/main/java]
Tiene que tener la siguiente estructura:

db.user=Akihabara_JAD
db.pass=(Tu contraseña de MYSQL Workbench)
db.URL=jdbc:mysql://localhost:3306/AkihabaraDB
api.key=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

--
### MYSQL

**IMPORTANTE**: HAY QUE CAMBIAR LA CONTRASEÑA DE SQL, YA QUE CADA UNO EN SU EQUIPO LOCAL TENEMOS UNA DISTINTA PARA ELLO:

- Primero: Iremos al archivo "config.properties", ahí veremos el campo "db.pass", borraremos el texto en **AMARILLO** y pondremos nuestra contraseña ahí.

**USO**:
- Para el uso de la base de datos tendremos que primero abrir el archivo "Akihabara.sql", podremos ejecutar el script entero.

 - **[¿QUE HACE?]**
- Primero crea un Usuario y le da los permisos correspondientes para que ese usuario pueda ejecutar las sentencias.
- Segundo crea la tabla correspondiente al proyecto y algunos datos de ejemplo para poder ejecutar directamente en busca de resultados.

### API - IA

(Opcional): En caso de querer cambiar la API-key solamente tendremos que ir a "config.properties" y cambiar la parte **AMARILLA** de "api.key" por la nueva API-key que vayamos a usar.

# Como Ejecutar

[ HACER LOS PASOS INDICADOS ARRIBA PARA EL USO CORRECTO Y PERSONAL DE LA APLICACION ]

1. Descargar archivo "Akihabara_Market.rar" y tendremos que descomprimirlo en la carpeta correspondiente de eclipse.
2. Ahora lo importaremos a nuestro Eclipse
3. Tendremos que ir a la carpeta "Akihabara_Market", y tendremos que ejecutar el archivo .bat para ejecutarlo directamente .
4. Y ya podremos estar usar de forma constante la aplicación.

### Estructuras de Carpetas

Akihabara_Market <br>
├── src <br>
│   ├── main <br>
│   │   ├── java <br>
│   │   │   ├── config <br>
│   │   │   │   └── ConfigLoader.java <br>
│   │   │   ├── dao <br>
│   │   │   │   ├── BDconnection.java <br>
│   │   │   │   ├── LlmService.java <br>
│   │   │   │   └── ProductoDAO.java <br>
│   │   │   ├── interfaz <br>
│   │   │   │   └── Interfaz_Consola.java <br>
│   │   │   ├── model<br> 
│   │   │   │   └── ProductoOtaku.java<br>
│   │   │   ├── principal <br>
│   │   │   │   └── Main.java <br>
│   │   │   └── vista <br>
│   │   │       └── Vista_Funciones.java <br>
│   │   └── resources <br>
│   │       └── config.properties <br>
│   └── test <br>
│       └── java <br>
│           └── dao<br>
│               └── ProductoDAO_Test.java <br>
└── pom.xml <br>
      
  - pom.xml 
