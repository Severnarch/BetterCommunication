package io.github.severnarch.bettercommunication.commands;

import io.github.severnarch.bettercommunication.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission("bettercommunication.bettercommunication")) {
                sender.sendMessage("%s%sYou do not have permission to use this command.".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR));
            }
            if (args.length >= 1) {
                if (Objects.equals(args[0], "version")) {
                    sender.sendMessage("%s%s%s is running on version %s".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_INFO, Constants.PLUGIN_INFO.getName(), Constants.PLUGIN_INFO.getVersion()));
                } else if (Objects.equals(args[0], "help")) {
                    assert Constants.PLUGIN_INFO.getDescription() != null;
                    sender.sendMessage("%s%s%s is %s".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_INFO, Constants.PLUGIN_INFO.getName(), Constants.PLUGIN_INFO.getDescription().toLowerCase()));
                } else if (Objects.equals(args[0], "usage")) {
                    assert Constants.PLUGIN_INFO.getDescription() != null;
                    sender.sendMessage("%s%sCommand usage: /bettercommunication <help|version|usage>".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_INFO));
                } else {
                    sender.sendMessage("%s%sInvalid argument at Position 0: \"%s\".".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR, args[0]));
                }
            } else {
                sender.sendMessage("%s%sMissing argument at Position 0.".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Map<Number, List<String>> arguments = new HashMap<>();
        arguments.put(1, List.of("version", "help", "usage"));

        if (arguments.get(args.length) != null) {
            return arguments.get(args.length);
        } else {
            return null;
        }
    }
}
