# ServerShop

ServerShop is a Paper plugin for economy-backed server purchases. It lets
players buy configured items and unlock permission-based perks through Vault,
LuckPerms, and optional EssentialsX integration.

## Features

- Item purchases through `/severshop <item> [amount]`.
- Buyable EssentialsX sethome tiers.
- Buyable nickname changes with cooldown and validation.
- Buyable anvil and crafting-table access permissions.
- Buyable PlayerAuctions-style auction limit tiers.
- Admin reload, info, and leaderboard commands.
- Configurable server revenue account for shop proceeds.

## Requirements

- Java 21
- Paper 1.21 compatible server
- Vault
- A Vault-compatible economy provider
- LuckPerms
- EssentialsX for nickname features only

## Build

```bash
mvn clean package
```

The compiled plugin artifact is written to Maven's output directory. Build
outputs are intentionally not tracked in git.

## Install

1. Build the project.
2. Copy the generated plugin artifact into your Paper server `plugins/`
   directory.
3. Install Vault, an economy provider, and LuckPerms.
4. Install EssentialsX if you want nickname purchases.
5. Start or restart the server.
6. Review and customize `plugins/ServerShop/config.yml`.

## Configuration

The default config includes Korean user-facing messages and example economy
prices. Operators should tune prices, permissions, and messages for their own
server economy.

The `revenue_account` section controls where shop proceeds go:

```yaml
revenue_account:
  enabled: true
  account: "server"
```

Set `enabled` to `false` if your economy does not use a server treasury account.

## Commands

See [docs/COMMANDS.md](docs/COMMANDS.md).

## Licensing

ServerShop is released under the MIT License. See [LICENSE](LICENSE).

Third-party APIs used by the plugin have their own licenses. See
[THIRD_PARTY_NOTICES.md](THIRD_PARTY_NOTICES.md).
