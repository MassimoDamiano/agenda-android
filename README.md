# Agenda Android

Aplicación Android nativa para gestionar contactos, desarrollada desde cero con Kotlin y vistas XML. Permite iniciar sesión, crear, consultar, buscar, editar y eliminar contactos, almacenarlos localmente e importar usuarios desde una API REST.

El proyecto aplica separación de responsabilidades mediante modelos de dominio, repositorios y fuentes de datos. Incluye persistencia con SQLite, listas eficientes con RecyclerView, consumo REST/JSON con Retrofit y Gson, operaciones asíncronas con Coroutines, estados observables mediante StateFlow y tests automatizados.

## Tecnologías

- Kotlin
- Android SDK
- XML y Material Design 3
- RecyclerView
- SQLite
- Retrofit y Gson
- Kotlin Coroutines
- StateFlow
- REST API
- JUnit
- Gradle con Kotlin DSL
- Git y GitHub

## Funcionalidades

- Inicio de sesión y persistencia de la sesión con SharedPreferences.
- Listado de contactos mediante RecyclerView.
- Creación de contactos con validación de campos obligatorios.
- Vista de detalle de cada contacto.
- Edición y eliminación de contactos.
- Búsqueda por nombre, apellido o teléfono.
- Persistencia local mediante SQLite.
- Importación de usuarios desde JSONPlaceholder.
- Estados de carga, éxito y error durante la sincronización.
- Cierre de sesión con limpieza de la pila de navegación.

## Arquitectura

```text
UI (Activities + XML + RecyclerView)
              |
              v
       Modelo Contact
          /       \
         v         v
Repositorio local  Repositorio remoto
    SQLite         Retrofit + Gson
                         |
                         v
                  REST API / JSON
```

La aplicación está dividida en las siguientes responsabilidades:

- `domain`: contiene el modelo de negocio `Contact`.
- `data`: contiene el acceso a SQLite y el repositorio local.
- `data/remote`: contiene el contrato Retrofit, los DTO, el mapper y el estado de sincronización.
- `Activities`: coordinan la interfaz, la navegación y las acciones del usuario.
- `ContactsAdapter`: adapta contactos a las filas del RecyclerView y aplica la búsqueda.

## Flujo principal

1. `LoginActivity` valida las credenciales y guarda la sesión.
2. `ContactsActivity` consulta SQLite y muestra los contactos.
3. `ContactFormActivity` permite crear o editar un contacto.
4. `ContactDetailActivity` muestra sus datos y permite editarlo o eliminarlo.
5. La acción **Sincronizar API** consulta JSONPlaceholder mediante Retrofit.
6. Los usuarios remotos se convierten al modelo local y se guardan en SQLite.

## Credenciales de demostración

```text
Usuario: profe
Contraseña: profe
```

Estas credenciales existen únicamente para demostrar el flujo de sesión. Una aplicación de producción debería autenticar contra un backend y almacenar tokens de forma segura.

## Requisitos

- Android Studio
- JDK 11 o superior compatible con Android Gradle Plugin
- Android SDK 35
- Dispositivo o emulador con Android 7.0 (API 24) o superior
- Conexión a Internet para sincronizar contactos remotos

## Ejecución

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/MassimoDamiano/agenda-android.git
   ```

2. Abrir la carpeta en Android Studio.
3. Esperar la sincronización de Gradle.
4. Seleccionar un emulador o dispositivo físico.
5. Ejecutar el módulo `app`.
6. Iniciar sesión con las credenciales de demostración.

## Tests

Ejecutar los tests unitarios:

```bash
./gradlew testDebugUnitTest
```

Generar el APK de tests instrumentados:

```bash
./gradlew assembleDebugAndroidTest
```

El proyecto incluye:

- Tests del mapper que convierte usuarios remotos en contactos de dominio.
- Un test instrumentado que comprueba la identidad de la aplicación Android.

## API externa

La sincronización utiliza el endpoint público:

```text
GET https://jsonplaceholder.typicode.com/users
```

Retrofit ejecuta la petición como una función suspendida. Gson transforma el JSON en DTO y `RemoteContactMapper` convierte esos datos al modelo `Contact` utilizado por la aplicación.

## Decisiones y mejoras futuras

Este proyecto prioriza una arquitectura sencilla y adecuada para demostrar fundamentos Android. Algunas mejoras posibles son:

- Reemplazar SQLiteOpenHelper por Room.
- Incorporar ViewModel para conservar el estado ante cambios de configuración.
- Usar ListAdapter y DiffUtil para actualizaciones más eficientes.
- Evitar contactos duplicados durante sucesivas sincronizaciones.
- Reemplazar el login de demostración por autenticación REST real.
- Incorporar inyección de dependencias.
- Agregar tests de UI con Espresso.

## Autor

Desarrollado por [Massimo Damiano](https://github.com/MassimoDamiano).
