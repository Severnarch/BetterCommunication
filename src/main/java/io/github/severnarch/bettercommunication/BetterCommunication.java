package io.github.severnarch.bettercommunication;

import io.github.severnarch.bettercommunication.commands.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterCommunication extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("bettercommunication").setExecutor(new MainCommand());
        this.getCommand("bettercommunication").setTabCompleter(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
