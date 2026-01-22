# Contexto del Proyecto: Alfabetización Digital Rural

Este documento resume el estado actual, la arquitectura y los requisitos del proyecto para continuar el desarrollo en una nueva sesión o chat.

## 🎯 Objetivo General
Desarrollar una aplicación móvil Android para una tesis sobre **Alfabetización Digital Rural**. La app debe ser educativa, con diseño inclusivo (botones grandes, alto contraste, TTS) y funcionar offline (base de datos local Room).

## 🛠️ Stack Tecnológico
*   **Lenguaje**: Kotlin.
*   **UI**: Jetpack Compose.
*   **Arquitectura**: MVVM con Repositorios.
*   **Base de Datos**: Room Database (Local).
*   **Inyección de Dependencias**: Manual (`AppContainer` en `AlfabetizacionApplication`).
*   **Navegación**: Navigation Compose (`NavHost` en `MainScreen` y `AlfabetizacionApp`).
*   **Imágenes**: Coil (`io.coil-kt:coil-compose`).
*   **SDK**: Min 24, Target 34.

## 📊 Estado del Proyecto

### Sprint 1: Configuración y Perfil (Completado)
*   **Base de Datos**: `UserEntity`, `LessonEntity`, `UserDao`, `EducationDao`.
*   **Registro (Onboarding)**:
    *   `ProfileSetupScreen`: Solicita nombre y avatar.
    *   **Features**: TTS Offline ("Escribe tu nombre..."), Grid de selección de Avatares (Iconos placeholder).
    *   **Persistencia**: Guarda el usuario y `avatarId` en Room.
*   **Navegación Principal**:
    *   Bottom Navigation Bar instalada (Inicio, Lecciones, Perfil).

### Sprint 2: Lecciones (En Progreso / Parcialmente Completado)
*   **Visualización**: `LessonsScreen` muestra lista de lecciones.
*   **Entidad Lección**: Actualizada con campo `imagenRuta` (String?).
*   **UI de Lección**:
    *   Tarjeta con Título, Tema, Autor e Imagen (usando `AsyncImage` de Coil).
    *   **Acciones**: 
        *   **Compartir**: Funcional (`Intent.ACTION_SEND`).
        *   **Eliminar**: Funcional (Borra de BD).
        *   **Editar**: Botón presente visualmente, pero SIN lógca de navegación aún.
*   **Datos de Prueba**: Botón "Crear Lección de Prueba" genera datos dummy para verificar.

## 📝 Requisitos y Reglas Estrictas
1.  **CERO Comentarios**: No agregar comentarios (`//` o `/**/`) en el código. El código debe ser autoexplicativo.
2.  **JDK 17**: El proyecto requiere JDK 17 para compilar con AGP 8.6.0.
3.  **Local Properties**: No subir `local.properties` a control de versiones.
4.  **Diseño Inclusivo**: Mantener textos grandes y buen contraste.

## 🔜 Pasos Siguientes (Pendientes)
1.  **Navegación a Edición**: Crear pantalla de edición de lección y conectar el botón de "Lápiz".
2.  **Creación de Lecciones Real**: Implementar formulario para añadir lecciones (actualmente solo hay dummy data).
3.  **Persistencia de Imágenes**: Implementar lógica para guardar imágenes reales (copiar a almacenamiento interno) en lugar de solo URLs/Rutas.
4.  **Dashboard Real**: Conectar `DashboardScreen` con métricas reales de lecciones completadas (actualmente hardcoded o básicas).

## 📂 Estructura Clave de Archivos
*   `data/repository/EducationRepository.kt`: Maneja Lecciones y lógica de borrado.
*   `ui/lessons/LessonsScreen.kt`: UI de la lista.
*   `ui/lessons/LessonsViewModel.kt`: Lógica de presentación y acciones (Share/Delete).
*   `MainActivity.kt`: Punto de entrada e instancia de `AppContainer`.
