# UPEEats

UPEEats es una aplicación de gestión de pedidos y sugerencias para cafeterías universitarias, desarrollada en Java con Swing y una arquitectura modular basada en el patrón MVC (Modelo-Vista-Controlador). El sistema permite a los usuarios realizar pedidos, gestionar productos, categorías, sugerencias y reseñas, así como administrar notificaciones y usuarios.

## Características principales

- **Gestión de Pedidos:** Permite crear, consultar, actualizar y eliminar pedidos, así como ver el detalle de cada uno y su estado.
- **Gestión de Productos y Categorías:** Los productos pueden ser organizados en categorías, con soporte para imágenes, precios, cantidades y descripciones.
- **Sugerencias y Reseñas:** Los usuarios pueden enviar sugerencias y dejar reseñas sobre los pedidos y productos, facilitando la retroalimentación.
- **Notificaciones:** El sistema notifica a los usuarios sobre cambios importantes, como actualizaciones de pedidos o respuestas a sugerencias.
- **Gestión de Usuarios:** Soporte para diferentes roles de usuario (administrador, cliente, etc.), con autenticación y control de acceso.
- **Interfaz Gráfica Moderna:** Utiliza Swing y un sistema de temas y dimensiones personalizadas para una experiencia visual atractiva y adaptable.
- **Persistencia en Base de Datos:** Utiliza SQLite (y soporte para MariaDB) para almacenar toda la información de la aplicación.

## Estructura del proyecto

```
nbactions.xml
pom.xml
src/
  main/
    java/
      org/upemor/
        config/         # Configuración de la base de datos y utilidades
        controllers/    # Controladores para la lógica de negocio y la UI
        models/         # Modelos de datos (entidades)
        repositories/   # Repositorios para acceso a datos (CRUD)
        theme/          # Temas, colores y dimensiones de la UI
        utils/          # Utilidades generales y notificaciones
        views/          # Vistas y paneles principales de la UI
        widgets/        # Componentes visuales reutilizables
    resources/
      icons/           # Iconos usados en la interfaz
      logos/           # Logos institucionales
  test/
    java/              # Pruebas unitarias
```

## Instalación y ejecución

> [!NOTE]
> Este proyecto esta en su mayoria codificado por el editor de vscode. Duranet el desarrollo se encontro con un problema al momento de utilizar netbeans. Netbeans renderiza al momento los compenentes del editor de diseño integrado. Por esto mismo muchos de los archivos que estan relacionados a las vistas y estan usando la conexion de la base de datos son afectados y son tomandos como corruptos si No se establece una conexion con MariaDB (Solo establecer). Causa no encontrada pero si importante en tener encuenta.

1. **Requisitos previos:**

   - Java 8 o superior
   - Maven (para compilar y gestionar dependencias)
   - (Opcional) MariaDB si se desea usar en vez de SQLite

2. **Compilación:**
   Ejecuta en la raíz del proyecto:

   ```sh
   mvn clean install
   ```

3. **Ejecución:**
   Puedes ejecutar la aplicación desde el IDE o con:

   ```sh
   mvn exec:java -Dexec.mainClass="org.upemor.Main"
   ```

   (Asegúrate de que la clase Main exista y sea el punto de entrada)

4. **Base de datos:**
   - Por defecto, se utiliza un archivo SQLite ubicado en `db/cafeteria.db`.
   - Los scripts de creación y migración están en `db/sql/`.

## Principales paquetes y clases

- `models/` — Entidades como `Usuarios`, `Productos`, `Pedidos`, `DetallePedido`, `Sugerencias`, `ReseniaProducto`, etc.
- `repositories/` — Clases como `UsuariosRepositorio`, `ProductosRepositorio`, `PedidosRepositorio`, etc., que implementan la lógica de acceso a datos.
- `controllers/` — Controladores para manejar la lógica de la aplicación y la interacción con la UI.
- `widgets/` — Componentes visuales reutilizables como `ItemEntidad`, `PanelListaEntidades`, etc.
- `theme/` — Definición de colores, dimensiones y estilos visuales.
- `utils/` — Utilidades como notificaciones globales, validaciones, etc.

## Licencia

Este proyecto es de uso académico y está bajo la Licencia MIT.

## Créditos

Desarrollado por estudiantes de la Universidad Politécnica del Estado de Morelos (UPEMOR).

- ALONSO GOMEZ ANGEL MANUEL
- ORIVE CARDIEL ARIANA PAOLA
