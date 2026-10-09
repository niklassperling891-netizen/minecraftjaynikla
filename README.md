# Mob Breeding Indicator (Fabric, Minecraft 1.21.11)

This is a **source project**, not a precompiled/install-ready JAR. It needs to be built against Fabric/Minecraft dependencies.

## Intended behavior
- B key toggles the indicator (change the key under Options → Controls).
- Green outline: adult animal whose breeding age is zero.
- Blue outline: animal breeding cooldown.
- Red outline: baby animal.
- Designed for client-side use.

## Build
1. Install Java 21.
2. Install Gradle 8.14+ (or use a Gradle wrapper generated for your setup).
3. From this folder run: `gradle build`
4. The output JAR should be in `build/libs/`.

## Important limitations
The project has not been compiled or tested here because this environment has no network access to download Fabric Loom, Yarn mappings, Minecraft and Fabric API. Mixin/mapping names may need adjustment for the exact 1.21.11 Yarn build. Also, generic support for third-party breeding mobs requires their implementation to expose compatible breeding state; this initial prototype does not reliably detect every modded species or every special breeding condition.
