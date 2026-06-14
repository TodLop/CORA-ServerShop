# Third-Party Notices

ServerShop source code is licensed under the MIT License. The plugin is built
against third-party APIs that are not bundled in this repository or in the
published source tree.

## Provided Dependencies

These dependencies are declared with Maven `provided` scope. Server operators
install the corresponding plugins separately on their Paper server.

| Dependency | Purpose | Declared Version | License |
| --- | --- | --- | --- |
| Paper API | Minecraft server API | 1.21-R0.1-SNAPSHOT | GPL-3.0-derived upstream project licensing |
| VaultAPI | Economy abstraction | 1.7.1 | LGPL-3.0-or-later |
| LuckPerms API | Permission grants and lookups | 5.4 | MIT |
| EssentialsX API | Optional nickname integration | 2.20.1 | GPL-3.0 |

## Notes

- ServerShop does not vendor or shade these dependencies.
- Each dependency remains subject to its own license and upstream project terms.
- If you redistribute binary builds, review the licenses of the server platform
  and installed plugins used with that build.
