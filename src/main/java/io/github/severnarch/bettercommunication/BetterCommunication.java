package io.github.severnarch.bettercommunication;

import io.github.severnarch.bettercommunication.commands.MainCommand;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class BetterCommunication extends JavaPlugin {

    public Map<String, PluginCommand> commands = new HashMap<>();

    @Override
    public void onEnable() {
        // Add commands to commands map
        commands.put("bettercommunication", this.getCommand("bettercommunication"));

        // Define command executors and tab completions
        commands.get("bettercommunication").setExecutor(new MainCommand());
        commands.get("bettercommunication").setTabCompleter(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
