# ServerShop Commands

Primary command: `/severshop`

Aliases: `/ss`, `/buy`

## Player Commands

| Command | Description |
| --- | --- |
| `/severshop help` | Show available commands |
| `/severshop <item> [amount]` | Buy a configured item |
| `/severshop sethome` | Buy the next sethome tier |
| `/severshop sethome info` | Show current sethome tier |
| `/severshop nickname <nickname>` | Buy a nickname change |
| `/severshop nickname info` | Show nickname and cooldown info |
| `/severshop nickname reset` | Reset your nickname, respecting cooldown |
| `/severshop anvil` | Buy anvil access permission |
| `/severshop anvil info` | Show anvil access price |
| `/severshop craft` | Buy crafting-table access permission |
| `/severshop craft info` | Show crafting-table access price |
| `/severshop auction` | Buy the next auction limit tier |
| `/severshop auction info` | Show current auction limit tier |

## Admin Commands

| Command | Permission | Description |
| --- | --- | --- |
| `/severshop reload` | `servershop.admin` | Reload configuration |
| `/severshop sethome info <player>` | Operator | Show another player's sethome tier |
| `/severshop sethome top [page]` | `servershop.admin` | Show sethome leaderboard |
| `/severshop nickname set <player> <nickname>` | `servershop.nickname.admin` | Set another player's nickname |
| `/severshop nickname resetcooldown <player>` | `servershop.nickname.admin` | Reset nickname cooldown |
| `/severshop nickname reset <player>` | `servershop.nickname.admin` | Reset another player's nickname |
| `/severshop auction info <player>` | `servershop.admin` | Show another player's auction limit |
| `/severshop auction top [page]` | `servershop.admin` | Show auction limit leaderboard |

## Permissions

| Permission | Default | Description |
| --- | --- | --- |
| `servershop.buy` | true | Use the server shop |
| `servershop.admin` | op | Admin commands |
| `servershop.sethome.bypass` | op | Unlimited sethome slots |
| `servershop.nickname.admin` | op | Nickname admin commands |
| `servershop.anvil.use` | false | Anvil access permission |
| `servershop.craft.use` | false | Crafting-table access permission |
