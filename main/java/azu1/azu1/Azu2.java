package azu1.azu1;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;


public final class Azu2 extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("azuheal")) {
            if (args.length <= 0) return true;
            if (args[0].equalsIgnoreCase("heal")) {
                if (!sender.hasPermission("Azu2.permission.Admin")) {

                    int Heal = (int) player.getHealth();
                    if ((Heal < 20)) {
                        player.sendMessage("HPを回復しました");
                        player.setHealth(20 - Heal);
                        return true;
                    }
                    if ((Heal > 20)) {
                        player.sendMessage("HPを回復しました");
                        player.setHealth(Heal - 20);
                        return true;
                    }
                }
            }

        }
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command command, @NotNull String alias, String[] args) {
        if (!command.getName().equalsIgnoreCase("azuheal")) return super.onTabComplete(sender, command, alias, args);
        if (args.length == 1) {
            if (sender.hasPermission("TheSlowHalloween.permission.Admin")) {
                if (args[0].length() == 0) {
                    return Collections.singletonList("heal");
                } else if ("heal".startsWith(args[0])) {
                    return Collections.singletonList("heal");
                }
            }
        }
        return super.onTabComplete(sender, command, alias, args);
    }
}