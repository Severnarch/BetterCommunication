package io.github.severnarch.bettercommunication.commands;

import io.github.severnarch.bettercommunication.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

public class MessageCommand implements CommandExecutor, TabCompleter {

    private final Map<Player, Player> lastMessagedMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> onlinePlayers = new ArrayList<>();
        for (Player player : sender.getServer().getOnlinePlayers()) {
            onlinePlayers.add(player.getName());
        }
        if (sender instanceof Player) {
            if (!sender.hasPermission("bettercommunication.message")) {
                sender.sendMessage("%s%sYou do not have permission to use this command.".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR));
            }
            if (args.length >= 1) {
                Player lastMessaged = lastMessagedMap.get(sender);
                Player target = null;
                String message = null;
                if (List.of("r", "reply").contains(label)) {
                    if (lastMessaged != null) {
                        target = lastMessaged;
                        message = String.join(" ", args);
                    } else {
                        sender.sendMessage("%s%sYou have nobody to reply to!".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR));
                    }
                } else if (List.of("message", "msg", "whisper", "w").contains(label)) {
                    if (sender.getServer().getPlayer(args[0]) != null) {
                        target = sender.getServer().getPlayer(args[0]);
                        if (args.length >= 2) {
                            ArrayList<String> messageArgs = new ArrayList<>();
                            Boolean doTrimCheck = true;
                            for (String arg : args) {
                                if (doTrimCheck) {
                                    if (!Objects.equals(arg, args[0])) {
                                        doTrimCheck = false;
                                        messageArgs.add(arg);
                                    }
                                } else {
                                    messageArgs.add(arg);
                                }
                            }
                            message = String.join(" ", messageArgs);
                        } else {
                            sender.sendMessage("%s%sPlease provide a message.".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR));
                        }
                    } else {
                        sender.sendMessage("%s%s%s is not a valid player or is not online.".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR, args[0]));
                    }
                } else {
                    sender.sendMessage("%s%sThis command alias has been registered but not linked to any code. Please contact plugin author(s) to fix this.".formatted(Constants.CHAT_PREFIX, Constants.COLOUR_ERROR));
                }
                if (target != null) {
                    lastMessagedMap.put((Player) sender, target);
                    lastMessagedMap.put(target, (Player) sender);
                    sender.sendMessage("%s%s".formatted(Constants.MESSAGE_PREFIX.formatted("You", target.getName()), message));
                    target.sendMessage("%s%s".formatted(Constants.MESSAGE_PREFIX.formatted(sender.getName(), "You"), message));
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Map<Number, List<String>> arguments = new HashMap<>();
        if (List.of("message", "msg", "whisper", "w").contains(label)) {
            ArrayList<String> onlinePlayers = new ArrayList<>();
            for (Player player : sender.getServer().getOnlinePlayers()) {
                onlinePlayers.add(player.getName());
            }
            arguments.put(1, onlinePlayers);
        }

        if (arguments.get(args.length) != null) {
            return arguments.get(args.length);
        } else {
            return null;
        }
    }
}
