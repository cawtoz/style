package com.github.cawtoz.style.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Simple utility to send messages
 * @author cawtoz
 */

@UtilityClass
public class ChatUtil {

    public void sendMsg(Player player, String message) {
        player.sendMessage(ColorUtil.formatColor(message));
    }

    public void sendMsg(Entity entity, String... message) {
        for (String s : message) sendMsg(entity, s);
    }

    public void sendMsg(CommandSender sender, String message) {
        sender.sendMessage(ColorUtil.formatColor(message));
    }

    public void sendMsg(Player player, List<String> message) {
        ColorUtil.formatColor(message).forEach(player::sendMessage);
    }

    public void sendMsg(CommandSender sender, List<String> message) {
        ColorUtil.formatColor(message).forEach(sender::sendMessage);
    }

    public void sendBroadcast(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> sendMsg(player, message));
    }

    public void sendConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(ColorUtil.formatColor(message));
    }

    public void sendConsole(String... message) {
        for (String s : message) sendConsole(s);
    }

    public void sendConsole(List<String> message) {
        ColorUtil.formatColor(message).forEach(Bukkit.getConsoleSender()::sendMessage);
    }

}
