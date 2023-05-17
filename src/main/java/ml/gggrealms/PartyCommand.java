package ml.gggrealms;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        int argsLeng = args.length;
        if (argsLeng == 0) {
            p.sendMessage(Component.text("- Party Command -"));
            p.sendMessage(Component.text("| -=> /party join <number>"));
            p.sendMessage(Component.text("| -=> /party leave"));
            p.sendMessage(Component.text("| -=> /party info"));
        } else {
            if (args[0].equals("join")) {
                if (args.length == 2) {
                    int number = Integer.parseInt(args[1]);
                    if (number != null) {
                        if (AnarSHIT.getPartySystem().partyExists(number)) {
                            AnarSHIT.partySystem.get;
                        } else {

                        }
                    }
                } else {
                    p.sendMessage(Component.text("| -=> /party join <number>"));
                }
            } else if (args[0].equals("leave")) {

            } else if (args[0].equals("info")) {

            }
        }
    }
}
