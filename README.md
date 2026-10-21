# About TRC-Core
TRC-Core is the foundational library and required entry point for all TRC-mods. It centralizes essential development frameworks, optimizes low-level functions, and introduces a unified in-game configuration menu.

## Why do I need this?
Instead of managing settings for each TRC mod individually, this core library bridges them together. It allows you to tweak, enable, or disable features across all connected TRC mods from a single, organized interface, while ensuring peak performance and compatibility.

### Information
- Dependency: This mod does not add standalone content by itself, but it is strictly required for other TRC mods (such as Always Open Chests) to function.

- Side Compatibility: Works seamlessly on both client and server environments.

- Version Compatibility: Currently only Fabric and Mc-version 26+

## Opening the settings
- Main menu: the button with the TRC icon, left of "Singleplayer", opens the settings screen
  straight from the title screen. Closing it brings you back to the main menu.

- In game: press U, or whatever you bound "TRC-Settings menu" to under Controls, in the
  "TRC-Mods" category.

- Command: /trc reads and changes settings from the chat, see "The /trc command" below.

## Languages
The settings screen, its buttons and TRC-Core's own settings follow your game language. English,
German and Pirate Speak are included; any other language shows English.

Mods built on TRC-Core can translate the names of their settings the same way. A mod that
does not shows its setting names as it wrote them, and so does every name a translation is
missing for.

The server names TRC-Core's own server settings in the language your client uses, so they
match the rest of your screen even on a server set to another language. Most chat messages
and command output are in English.

## Editing server settings from the client
Server-side settings no longer have to be edited by hand on the machine the server runs on.
They appear in the same TRC settings screen as everything else, and changing one applies on
the server immediately -- for the players the server has decided may do so.

- Requirements: TRC-Core has to be installed on the server as well. If it is not, the screen
  says so next to its title and shows only your client settings.

- Who may: on a dedicated server, TRC-Core adds the setting **Required OP level to edit
  server settings** -- a slider from 0 to 5. Players at or above it see the server settings;
  everyone else does not see them at all. 0 to 4 are Minecraft's own permission levels; 5 is
  higher than any of them, so it takes the right away from every operator at once. Named
  players on the whitelist or the editors list still get in, and so does the console. It starts
  at 5: on a fresh server no operator gets in by their level until the slider is lowered.

  It only appears while there is something for it to gate: a server whose TRC mods offer no
  server settings does not show it, because there would be nothing behind it.

- Singleplayer and LAN: the host may always edit. For everyone else there is the switch
  **Allow LAN players to change server settings** (off by default), because Minecraft gives
  LAN players no meaningful permission level to grade. It appears only once the world has
  been opened to LAN -- before that the host is the only player there is.

- Note: both of these are ordinary server settings and follow their own rule. Raising the
  required level above your own, or switching LAN editing off while you are a LAN player,
  locks you out of changing it back from in game.

- Live: a change made by one admin reaches every other open settings screen at once. Losing
  the permission takes the server sections out of an open screen immediately; gaining it
  shows them the next time the screen is opened. In a singleplayer or LAN world the host's
  sliders also take effect while being dragged, not only once released; over a real
  connection the value is sent when you let go.

- Order: the server settings of each mod come first, each mod under its own heading, and
  TRC-Core's own permission setting sits at the very bottom of the list.

- Filter: while the screen shows server settings, the button top left -- opposite the search
  -- switches between showing both sides, only the server settings, or only your client
  settings. It works together with the search, and it stays as you left it until you close the
  game.

## The /trc command
`/trc <mod> <setting> [value]` reads a setting when the value is left off and sets it when it
is given. Mod, setting and -- where there is a fixed set of them -- value all tab-complete, and
only what you are actually allowed to touch is offered.

- Which settings: all of them. Every setting carries an id, given by the mod that registered
  it. TRC-Core's own two permission settings are reached the same way, but need the stronger
  right described under "Administering from the command line".

- Which mod id: "mymod" means that mod's server settings, "mymod_client" its client ones --
  the same split the settings screen shows.

- Values: true or false for a switch, a whole number within the setting's range for a
  slider or a number field, and the option's own text for a choice. A number outside the
  range is refused rather than quietly clamped.

- Hidden values: a mod can mark a text setting as hidden -- a password or a token, say. Its
  value is never shown. The settings screen offers an empty box to type a new one into, /trc
  answers (hidden), and the server never sends it to a client. It is stored in the settings
  file like any other value, and the log records accepted changes with the real value -- so
  keep the log as private as the settings file.

- Resetting: `/trc reset <mod> <setting>` puts a setting back to the value its mod shipped
  with. The same is one click in the settings screen -- every setting has a reset button on
  its right, greyed out while the value already is the default.

- Listing: `/trc list` shows the mods whose settings you can reach, with the id to type for
  each. In game it always lists your own client mods, and the server's mods as well when you
  may change server settings. From the server console it lists the server's mods.

- Files: TRC-Core reads the settings files and the access lists once, when the game or the
  server starts, and never while it runs. Changes are written to disk within a second, the
  access lists within a tick. Edit a file by hand only while the game or server is stopped --
  an edit made while it runs is overwritten. A settings file that is empty or cannot be read is
  replaced by the mod's defaults.

- Permission: client settings are always yours to change. Server settings follow exactly the
  rules above, and the server checks again when the change arrives -- the command is not a
  way around them.

- Pace: the server takes one TRC-Core request per player per tick -- a change, a settings
  screen being opened, or a /trc command. Changes that arrive faster are applied one tick
  after another, a command is refused with a message. The server console is not limited. The
  host of a singleplayer or LAN world is not limited either, except that a single setting takes
  at most one change per tick while its slider is being dragged.

- The command also exists on the server, so it works from the console and for players who do
  not have TRC-Core installed on their client. There it is hidden from anybody who may not
  use it, and appears or disappears as the permission changes rather than at the next login.
  A player who has TRC-Core on their client always has /trc, because their client settings
  are always theirs to change.

## Administering from the command line
The same /trc command carries the list administration. These branches are open to the server
console, and in game to a player on the permissionEditors list. They are hidden from everybody
else.

  /trc whitelist add <player>      /trc whitelist remove <player>      /trc whitelist list
  /trc blacklist  ...              /trc editors ...

- editors is the permissionEditors list. add takes a name; an online player is written down
  with their UUID straight away, anybody else with the name alone until they next join.
  remove suggests what is on the list rather than who is online, because the player to take
  off a blacklist is often not there.

- TRC-Core's own two settings have no branch of their own: /trc trc-core required_op_level 3
  and /trc trc-core allow_lan_edits true, exactly like any other mod's. The command asks for
  the stronger right for that one holder, so being allowed to change server settings is not
  enough to change the rule deciding who is allowed to.

- Every accepted change is written to the server log: the date and time, who made it and by
  what right -- console, host, whitelist, or their OP level -- which setting, and the value
  before and after. Refusals are logged too. Dragging a slider is not: only the value it is
  let go on. Changes to your own client settings are written to your client's log the same
  way.

- Who hears about a change: the player who made it and, for a change to somebody's rights,
  that player -- always. Beyond them, a change of who may administer TRC permissions is shown
  to the server console, the host and the other permission administrators. A change to
  granted access, to a block, or to a setting -- made with /trc or in the settings screen -- is
  shown to the server console, the host, and everybody who may change server settings, never
  to a blocked player who is not an administrator.

- Quieter chat: with TRC-Core on your client, the setting "Settings changes in chat" decides
  which notices about changed settings you see -- all, none, only changes made with a command,
  or only changes made in the settings screen (the default is command only -- a slider moved in
  the screen is often just somebody trying something). Notices about granted access, blocks and
  administrators are not affected and always shown. It is stored on your own machine, and appears in the
  settings screen under TRC-Core's heading whenever you can see the server's settings. It only
  hides what reaches you; who is sent a notice is still up to the server.

- Changes take effect at once: an open settings screen gains or loses its server sections,
  and /trc appears or disappears for the players it concerns.

- Whitelist and blacklist: config/trc-settings/trc-core-access.json holds two lists of
  players that override the rules above. A player on the blacklist cannot change the server
  settings and does not see them, whatever their OP level; a player on the whitelist can,
  without needing one, and the blacklist wins over the whitelist. The one thing neither list
  can touch is the host of a singleplayer or LAN world, who may always edit their own
  world's settings -- blacklisting yourself there is allowed but does nothing. The lists
  are edited with the commands above, or by hand in the file while the server is stopped.

- Permission editors: a third list, permissionEditors, holds the players who may change the
  rules themselves -- TRC-Core's own two settings and all three lists. It is
  the stronger of the two rights and carries the weaker one with it: a permission editor may
  change every mod's server settings as well, whatever their OP level.

  It comes only from this list, from being the host of a singleplayer or LAN world, or from
  the server console. No OP level grants it. An empty list therefore means nobody -- a fresh
  server starts closed, and its operator adds themselves from the console. The blacklist does
  not reach it: a permission editor could take themselves off that list anyway.

- Writing an entry: {"name": "Steve", "uuid": "..."}, or just one of the two. Either half is
  enough, so you can list a player you only know the name of -- writing the bare string
  "Steve" works as well. The next time that player joins, the missing half is filled in and
  a name that has changed since is corrected. Once an entry has a UUID the player is
  identified by it alone, so renaming does not hand their listing to whoever takes the name.
