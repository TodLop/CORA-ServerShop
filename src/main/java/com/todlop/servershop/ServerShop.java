package com.todlop.servershop;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.todlop.servershop.commands.BuyCommand;

public class ServerShop extends JavaPlugin {

    private static Economy economy = null;
    private static String revenueAccountName = "server";
    private static boolean revenueAccountEnabled = true;
    private LuckPermsIntegration luckPermsIntegration = null;
    private EssentialsIntegration essentialsIntegration = null;

    @Override
    public void onEnable() {
        // Save default config
        saveDefaultConfig();
        loadRevenueAccountConfig();

        // Setup economy
        if (!setupEconomy()) {
            getLogger().severe("Vault economy not found! Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Setup LuckPerms
        luckPermsIntegration = new LuckPermsIntegration(this);
        if (!luckPermsIntegration.setupLuckPerms()) {
            getLogger().severe("LuckPerms not found! Disabling plugin...");
            getLogger().severe("This plugin requires LuckPerms for permission management.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Setup Essentials (optional - for nickname changes)
        essentialsIntegration = new EssentialsIntegration(this);
        if (essentialsIntegration.setupEssentials()) {
            getLogger().info("EssentialsX integration enabled for nickname changes");
        } else {
            getLogger().warning("EssentialsX not found - nickname change feature disabled");
        }

        // Validate config
        try {
            new ConfigValidator(this).validate();
        } catch (ConfigValidator.ConfigException e) {
            getLogger().severe("Config validation failed: " + e.getMessage());
            getLogger().severe("Plugin will continue but sethome purchases may not work correctly!");
            getLogger().severe("Please fix your config.yml and reload the plugin.");
        }

        // Register commands
        getCommand("severshop").setExecutor(new BuyCommand(this));

        getLogger().info("ServerShop v" + getDescription().getVersion() + " enabled! Economy: " + economy.getName());
        getLogger().info("LuckPerms integration enabled for permission management");
    }

    @Override
    public void onDisable() {
        getLogger().info("ServerShop disabled.");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }

    public LuckPermsIntegration getLuckPermsIntegration() {
        return luckPermsIntegration;
    }

    public EssentialsIntegration getEssentialsIntegration() {
        return essentialsIntegration;
    }

    public void loadRevenueAccountConfig() {
        revenueAccountEnabled = getConfig().getBoolean("revenue_account.enabled", true);
        revenueAccountName = getConfig().getString("revenue_account.account", "server");
        if (revenueAccountName != null) {
            revenueAccountName = revenueAccountName.trim();
        }
        if (revenueAccountEnabled && (revenueAccountName == null || revenueAccountName.isBlank())) {
            revenueAccountEnabled = false;
            getLogger().warning("Revenue account is enabled but no account name is configured; proceeds will not be deposited.");
        }
    }

    /**
     * Deposit revenue to the configured server treasury account via Vault.
     */
    public static void depositToServerAccount(double amount, String details) {
        Economy econ = getEconomy();
        if (econ != null && revenueAccountEnabled && revenueAccountName != null && !revenueAccountName.isBlank()) {
            EconomyResponse response = econ.depositPlayer(revenueAccountName, amount);
            if (response.transactionSuccess()) {
                Bukkit.getLogger().fine("[ServerShop] Deposited " + amount + " to revenue account for: " + details);
            } else {
                Bukkit.getLogger().warning("[ServerShop] Failed to deposit " + amount + " to revenue account '"
                        + revenueAccountName + "': " + response.errorMessage);
            }
        }
    }
}
