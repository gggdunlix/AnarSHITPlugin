package ml.gggrealms;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class BugreportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Component.text("Wrong usage. Use: /bug <message>", TextColor.color(210, 11, 37)));
        } else {
            Player p = (Player) sender;
            Location pLoc = p.getLocation();
            String whatWasSaid = new String();
            int x = pLoc.blockX();
            int y = pLoc.blockY();
            int z = pLoc.blockZ();
            String place = "at (" + x + ", " + y + ", " + z + ") : ";
            whatWasSaid += place;
            for (String i : args) {
                whatWasSaid += (" " + i);
            }


            DiscordWebhook hook = new DiscordWebhook("https://discord.com/api/webhooks/1108875302333063179/zI3VcEfMDlltzQa1MNZZ-s6gBT99hFKK-j5Z6fzU4HiwcLujyFUYNDEdEif7tkNTA-4l");
            hook.setContent(sender.getName() + " said " + whatWasSaid);
            hook.setUsername("/bug report from " + sender.getName());
            try {
                hook.execute();
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage(e.toString());
            }
            sender.sendMessage("report sent.");
        }
        return true;
    }
}
