# AUTOPAUSE
This plugin will automatically pause your game if there aren't enough players online _(see usage)_

- Download for V6:
  - https://github.com/MV-Studio14/autopause/releases/download/V1.0/auto_pause_V6.jar
- Download for V7:
  - https://github.com/MV-Studio14/autopause/releases/download/V1.0/auto_pause_V7.jar

## SETUP
- Download the plugin
- Put it in (your server)/config/mods
- Start your server!

## USAGE
**!All commands must be executed from the server!**

- Use `autopause` command to enable/disable the plugin
  - _Note: the plugin is already enabled; when you re-enabled it the game is paused until the `minimum player count` is reached (default: 1)_
- Use `setminplayer <min-player>` command to set the minimum player count to unpause the game _(only integer values greater than 0)_
- Use `pause <on / off>` command to manually pause/unpause the game
  - _Note: the plugin will still check if there are enough players online, unless it is disabled_

## BUILDING
- On windows: `./gradlew.bat build`
- On linux: `./gradlew build`