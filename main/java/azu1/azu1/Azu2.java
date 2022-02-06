package azu1.azu1;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public final class Azu2 extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getLabel().equals("azuheal")) {
            if (args.length == 1) {
                double heal = Integer.parseInt(args[0]);
                double amount = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double mheal = player.getHealth();
                if (!(heal < 0)) {
                    double balheal = ((amount - heal) - mheal);
                    player.setHealth(heal + mheal + balheal);
                    sender.sendMessage("指定した分HPを回復しました！");
                }
            }
            if (args.length == 0) {
                if (sender.hasPermission("Azu2.permission.Admin")) {
                    double amount = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                    if (player.getHealth() != 0) {
                        player.setHealth(amount);
                        sender.sendMessage("HPを回復しました！");
                        }
                    }
                }
        }
        return true;
    }
}