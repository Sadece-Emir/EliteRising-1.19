package tr.elite.eliterising.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import tr.elite.eliterising.EliteRising;

import static tr.elite.eliterising.EliteRising.LANGUAGE;
import static tr.elite.eliterising.Teams.*;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player chatter = e.getPlayer();
        if (getTeam(chatter) == null) {
            e.setFormat(ChatColor.GOLD + "[" + ChatColor.DARK_RED + LANGUAGE.getValue("eliterising.events.chat.general") +  ChatColor.GOLD + "] " + chatter.getName() + ChatColor.YELLOW + ": " + EliteRising.capitalize(e.getMessage()));
        } else {
            String teamChatter = getTeam(chatter);
            if (e.getMessage().startsWith("!")) {
                e.setFormat(ChatColor.GOLD + "[" + ChatColor.DARK_RED + LANGUAGE.getValue("eliterising.events.chat.general") +  ChatColor.GOLD + "] " + getTeamColor(teamChatter) + chatter.getName() + ChatColor.YELLOW + ": " + EliteRising.capitalize(e.getMessage().equals("!") ? "" : e.getMessage().substring(1)));
            } else {
                e.setCancelled(true);
                if (getTeamPlayers(teamChatter).size() == 1) {
                    chatter.sendMessage(ChatColor.GOLD + "[" + getTeamColor(teamChatter) + LANGUAGE.getValue("eliterising.events.chat.team") +  ChatColor.GOLD + "] " + chatter.getName() + ChatColor.YELLOW + ": " + ChatColor.AQUA + EliteRising.capitalize(e.getMessage()));
                } else {
                    for (Player p : getTeamPlayers(teamChatter)) {
                        p.sendMessage(ChatColor.GOLD + "[" + getTeamColor(teamChatter) + LANGUAGE.getValue("eliterising.events.chat.team") +  ChatColor.GOLD + "] " + chatter.getName() + ChatColor.YELLOW + ": " + ChatColor.AQUA + EliteRising.capitalize(e.getMessage()));
                    }
                }
            }
        }
    }
}
