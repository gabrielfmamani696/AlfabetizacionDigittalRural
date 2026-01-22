# Alfabetización Digital Rural

Aplicación móvil educativa para tesis, enfocada en la alfabetización digital.

## 💻 Requisitos del Sistema (Para ejecutar en otra PC)

Si vas a clonar o ejecutar este proyecto en otra computadora, asegúrate de cumplir los siguientes requisitos para evitar errores de compilación.

### 1. Versión de Java (JDK) - **MUY IMPORTANTE**
Este proyecto utiliza **Android Gradle Plugin (AGP) 8.6.0**, el cual requiere que **Gradle se ejecute con JDK 17**.

*   **Gradle JDK**: Debes configurar Android Studio para usar **Java 17** (o superior, ej. 21).
    *   **Configuración**: Ve a `File` > `Settings` (o `Preferences` en Mac) > `Build, Execution, Deployment` > `Build Tools` > `Gradle`.
    *   En la opción **Gradle JDK**, selecciona `jbr-17` (JetBrains Runtime 17) o una instalación local de JDK 17.
    *   ❌ **NO uses Java 1.8 (Java 8) ni Java 11** para el Gradle Daemon.

### 2. Android Studio
*   Se recomienda usar **Android Studio Koala (2024.1.1)** o **Ladybug (2024.2.1)** o superior.
*   Si usas una versión muy antigua, el plugin de Android podría no ser compatible.

### 3. Android SDK
El proyecto compila contra la **API 34** (Android 14).
*   Asegúrate de tener instalado el **SDK Platform 34**.
*   **Min SDK**: 24 (Android 7.0 Nougat).
*   **Compile SDK**: 34.

## 🛠️ Configuración Inicial
1.  **Clonar/Descargar**: Descarga el código fuente.
2.  **Abrir**: En Android Studio, usa "Open" y selecciona la carpeta raíz del proyecto.
3.  **Sincronización**: Deja que Gradle descargue las dependencias e indexe el proyecto.
4.  **Local Properties**: El archivo `local.properties` **no se sube al repositorio** (está ignorado) porque contiene la ruta local a tu SDK. Android Studio debería crearlo automáticamente al abrir el proyecto. Si tienes problemas, crea un archivo `local.properties` en la raíz con:
    ```properties
    sdk.dir=C\:\\Users\\TU_USUARIO\\AppData\\Local\\Android\\Sdk
    ```
    *(Ajusta la ruta según tu sistema operativo)*.

## 🚀 Compilación
Ejeucta el siguiente comando en la terminal para verificar que todo esté bien:
Windows:
```powershell
./gradlew.bat assembleDebug
```
Mac/Linux:
```bash
./gradlew assembleDebug
```
