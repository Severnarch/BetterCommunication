package io.github.severnarch.bettercommunication;

import io.github.severnarch.bettercommunication.commands.MainCommand;
import io.github.severnarch.bettercommunication.commands.MessageCommand;
import io.github.severnarch.bettercommunication.listeners.ChatListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class BetterCommunication extends JavaPlugin {

    public Map<String, PluginCommand> commands = new HashMap<>();
    public Map<String, Listener> listeners = new HashMap<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        // Add commands to commands map
        commands.put("bettercommunication", this.getCommand("bettercommunication"));
        commands.put("message", this.getCommand("message"));

        // Define command executors and tab completions
        commands.get("bettercommunication").setExecutor(new MainCommand());
        commands.get("bettercommunication").setTabCompleter(new MainCommand());
        commands.get("message").setExecutor(new MessageCommand());
        commands.get("message").setTabCompleter(new MessageCommand());

        // Add listeners to listeners map
        listeners.put("chat", new ChatListener());

        // Register listeners to server
        for (Listener listener : listeners.values()) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
