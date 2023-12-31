package io.github.severnarch.bettercommunication;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Constants {

    public static final String CHAT_PREFIX = ChatColor.translateAlternateColorCodes('&', "&8&l[&dBetter&6Communication&8&l] &r");
    public static final String MESSAGE_PREFIX = ChatColor.translateAlternateColorCodes('&', "&8&l[&a&o%s &d-&6> &a&o%s&8&l]");

    public static final String COLOUR_INFO = ChatColor.translateAlternateColorCodes('&', "&3");
    public static final String COLOUR_WARNING = ChatColor.translateAlternateColorCodes('&', "&e");
    public static final String COLOUR_ERROR = ChatColor.translateAlternateColorCodes('&', "&c");

    public static final String COLOUR_RESET = ChatColor.translateAlternateColorCodes('&', "&r");
    public static final String COLOUR_MENTION = ChatColor.translateAlternateColorCodes('&', "&a&o");


    // Plugin Information
    public static final JavaPlugin PLUGIN = JavaPlugin.getPlugin(BetterCommunication.class);
    public static final PluginDescriptionFile PLUGIN_INFO = PLUGIN.getDescription();
}
