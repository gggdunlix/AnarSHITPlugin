package ml.gggrealms;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        int argsLeng = args.length;
        if (argsLeng == 0) {
            p.sendMessage(Component.text("- Party Command -"));
            p.sendMessage(Component.text("| -=> /party join <password>"));
            p.sendMessage(Component.text("| -=> /party leave"));
            p.sendMessage(Component.text("| -=> /party info"));
        } else {
            if (args[0].equals("join")) {
                if (args.length == 2) {
                    String code = args[1];
                    AnarSHIT.addPlayerParty(code, p);
                    p.sendMessage("You have joined party \"" + code + "\".");
                } else {
                    p.sendMessage(Component.text("| -=> /party join <password>"));
                }
            } else if (args[0].equals("leave")) {
                AnarSHIT.playerLeaveParty(p);
            } else if (args[0].equals("info")) {
                Party party = AnarSHIT.getPlayerParty(p);
                if (party == null) {
                    p.sendMessage(Component.text("You're not in a party"));
                    return true;
                }
                p.sendMessage(Component.text("- Party Info -"));
                p.sendMessage(Component.text("| -=> code: " + party.getCode()));
                p.sendMessage(Component.text("| -=> players: "));
                ArrayList<Player> pPlayers = party.getPlayers();
                for (Player player : pPlayers) {
                    p.sendMessage(Component.text("| -=> -=- "+ player.getName() +": "));
                    String location = "(" + player.getLocation().getBlockX() + ", " +player.getLocation().getBlockY()+ ", " +player.getLocation().getBlockZ()+ "), " + player.getWorld().getName();
                    p.sendMessage(Component.text("| -=> -=- > > Location: " + location));
                    p.sendMessage(Component.text("| -=> -=- > > Health: " + (int) player.getHealth() + "/" + player.getMaxHealth()));
                }
            }
        }
        return true;
    }
}
