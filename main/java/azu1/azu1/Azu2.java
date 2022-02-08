package azu1.azu1;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.bukkit.Bukkit.getPlayer;


public final class Azu2 extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String label, String[] args) {
        Player player = (Player) sender;
        if (command.getLabel().equals("azuheal")) {
            if (sender.hasPermission("Azu2.permission.azuheal")) {
                if (args.length == 1) {
                    double heal = Integer.parseInt(args[0]);
                    double amount = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                    double mheal = player.getHealth();
                    if (!(heal < 0)) {
                        double balheal = ((amount - heal) - mheal);
                        player.setHealth(heal + mheal + balheal);
                        sender.sendMessage("指定した分HPを回復しました！");
                    }
                }
            }
            if (args.length == 0) {
                double amount = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
                if (player.getHealth() != 0) {
                    player.setHealth(amount);
                    sender.sendMessage("HPを回復しました！");
                }
            }
        }

        if (command.getLabel().equals("azubroadcast")) {
            if (sender.hasPermission("Azu2.permission.azubroadcast")) {
                if (args.length == 1) {
                    String string = args[0];
                    getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',"&6[&eお知らせ&6] &r" + string));



                }
                if (args.length != 1) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l使い方。 /azubroadcast [メッセージ]"));
                }
            }
        }

        if (command.getLabel().equals("azuloc")) {
            if (sender.hasPermission("Azu2.permission.azuloc")) {
                try {
                    if (args.length == 0) {
                        World world = ((Player) sender).getWorld();
                        Location loc = ((Player) sender).getLocation();
                        double yaw = ((Player) sender).getLocation().getYaw();
                        double pitch = ((Player) sender).getLocation().getPitch();
                        sender.sendMessage("現在ワールド座標向き : " + world + loc + yaw + pitch);
                        return true;
                    }
                } catch(Exception e) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c取得に失敗しました。"));
                }
                if (args.length <= 2) {
                    String playname = args[0];
                try {
                    Player player1 = getPlayer(playname);
                    assert player1 != null;
                    World world = player1.getWorld();
                    Location loc = player1.getLocation();
                    double yaw = player1.getLocation().getYaw();
                    double pitch = player1.getLocation().getPitch();
                    sender.sendMessage(player1 + "の現在ワールド座標向き : " + world + loc + yaw + pitch);
                    return true;


                } catch (Exception e) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cこの名前のプレイヤーは現在ログインしていません。"));
                }

                }
            }
        }
        return true;
    }
}