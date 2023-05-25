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

public class ShutoffServer implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            int argsLeng = args.length;
            int mins = 0;
            if (argsLeng == 1) {
                mins += Integer.parseInt(args[0]);
            }
            if (mins == 0) {
                mins += 10;
            }
            Collection<Player> allPlayers = (Collection<Player>) Bukkit.getOnlinePlayers();
            for (Player p : allPlayers) {
                p.sendMessage(Component.text("-!- SERVER SHUTTING DOWN IN " + mins + " MIN -!-", TextColor.color(ff7e33)));
            }
            final int[] seconds = {mins * 60};
            final int secs = seconds[0];
            final int sec = seconds[0];
            final BossBar[] bar = {BossBar.bossBar((ComponentLike) Component.text("SHUTDOWN IN " + seconds[0] + "s"), 1.0f, BossBar.Color.RED, BossBar.Overlay.PROGRESS)};
            DiscordWebhook hook = new DiscordWebhook("https://discord.com/api/webhooks/1109221041529692172/Z0TCC2UHVj0atkP39orwCTZ6F8SKgkX5QMWPZyqsodv4DvvFPMnYO-elqtmuwbH0wgmx");
            hook.setContent("SERVER SHUTTING DOWN in " + mins + " min");
            hook.setUsername("SERVER SHUTOFF WARNING");
            try {
                hook.execute();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(e.toString());
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (seconds[0]>1) {
                        seconds[0]--;
                        Collection<Player> allPs = (Collection<Player>) Bukkit.getOnlinePlayers();

                        for (Player p : allPs) {
                            p.hideBossBar(bar[0]);
                            bar[0].name(Component.text("SHUTDOWN IN " + seconds[0] + "s"));
                            bar[0].progress((float)seconds[0] / secs);
                            p.showBossBar(bar[0]);
                        }
                    } else if (seconds[0]==1){
                         Collection<Player> allPs = (Collection<Player>) Bukkit.getOnlinePlayers();
                        for (Player p : allPs) {
                            p.kick("Server Shutting Down")
                        }
                    } else {
                        Bukkit.shutdown();

                    }
                }
            }.runTaskTimer(Bukkit.getPluginManager().getPlugin("GGG-Anarchy"), 0, 20);
        } else {
            sender.sendMessage("This command can only be executed from the server console. Nice try asshole.");
        }
        return true;
    }
}
