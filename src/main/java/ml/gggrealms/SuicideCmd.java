package ml.gggrealms;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.Collection;

public class SuicideCmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
          Player p = (Player) sender;
          p.addScoreboardTag("suicidal");
          p.setHealth(0);
        } else {
          sender.sendMessage("Only can be executed as a player")
        }
        return true;
    }
}
