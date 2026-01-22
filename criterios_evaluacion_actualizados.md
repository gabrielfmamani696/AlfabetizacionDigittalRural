# Criterios de Evaluación Actualizados

## 1. Configuración del Proyecto y Optimización
1.  **Compatibilidad**: El proyecto compila correctamente con `minSdk = 24` (Android 7.0), asegurando soporte para la mayoría de dispositivos modernos sin comprometer funcionalidades clave.
2.  **Optimización**: La versión de lanzamiento (`release`) tiene habilitada la ofuscación y reducción de código (`isMinifyEnabled = true`), generando un APK optimizado para entornos de baja conectividad (meta: <15 MB).
3.  **Ejecución**: La aplicación se instala y ejecuta sin errores de tiempo de ejecución en el emulador/dispositivo objetivo.

## 2. Base de Datos Local (Room)
1.  **Estructura**: La clase `AppDatabase` y las Entidades están correctamente definidas.
2.  **Entidad Usuario**: La tabla de usuarios incluye campo para `avatarId` (referencia local) además de los datos básicos (`alias`, métricas).
3.  **Persistencia**: Los datos del usuario (nombre y avatar seleccionado) persisten tras cerrar completamente y reiniciar la aplicación.

## 3. UI: Registro de Usuario (Onboarding)
1.  **Selección de Avatar**: Se visualiza una cuadrícula (Grid) de avatares predefinidos que el usuario puede seleccionar.
2.  **Validación**: El botón "Continuar" solo se habilita o avanza si el campo de nombre no está vacío y se ha seleccionado un avatar.
3.  **Accesibilidad Auditiva (TTS)**: Al pulsar el título o un botón de ayuda, el sistema lee las instrucciones en voz alta usando el motor Text-To-Speech nativo (sin necesidad de internet).
4.  **Accesibilidad Visual**: Los elementos interactivos cumplen con el tamaño mínimo de toque (48dp).

## 4. UI: Navegación y Dashboard
1.  **Navegación**: Implementación funcional de Navigation Compose con barra inferior visible.
2.  **Flujo**: Transición fluida del Registro al Inicio; bloqueo del botón "Atrás" para no volver al registro una vez completado.
3.  **Personalización**: El Dashboard muestra el nombre del usuario registrado y el avatar que seleccionó.
