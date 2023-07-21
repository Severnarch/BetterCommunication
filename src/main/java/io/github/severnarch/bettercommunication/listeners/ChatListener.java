package io.github.severnarch.bettercommunication.listeners;

import io.github.severnarch.bettercommunication.Constants;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class ChatListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        ArrayList<String> collectedMessageContent = new ArrayList<>();
        boolean playerCanMention = event.getPlayer().hasPermission("bettercommunication.mention");
        boolean playerCanMentionPing = event.getPlayer().hasPermission("bettercommunication.mention.ping");
        boolean playerCanMentionAll = event.getPlayer().hasPermission("bettercommunication.mention.all");

        ArrayList<Player> playersToPing = new ArrayList<>();
        for (String section : message.split(" ")) {
            if (section.startsWith("@") && playerCanMention) {
                String player = section.substring(1);
                boolean continueToMention = true;
                if (player.equals("@")) {
                    if (!playerCanMentionAll) {
                        continueToMention = false;
                    }
                }
                if (continueToMention) {
                    Player target = event.getPlayer().getServer().getPlayer(player);
                    if (target != null) {
                        section = "%s%s%s".formatted(Constants.COLOUR_MENTION, section, Constants.COLOUR_RESET);
                        playersToPing.add(target);
                    }
                }
            }
            collectedMessageContent.add(section);
        }
        if (playerCanMentionPing) {
            for (Player player : playersToPing) {
                player.playNote(player.getLocation(), Instrument.CHIME, Note.natural(1, Note.Tone.C));
            }
        }
        event.setMessage(String.join(" ", collectedMessageContent));
    }
}
