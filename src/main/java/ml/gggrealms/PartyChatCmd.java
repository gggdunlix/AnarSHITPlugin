package ml.gggrealms;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyChatCmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        String msg = "< Party Chat | " + p.getName() + ">";
        for (String s : args) {
            msg += " " + s;
        }
        Party party = AnarSHIT.getPlayerParty(p);
        if (party == null) {
            p.sendMessage("You are not in a party");
            return true;
        }
        AnarSHIT.announceToParty(Component.text(msg, TextColor.color(0xFF9B3B)), party);
        return true;
    }
}
