package ml.gggrealms;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.*;

import java.util.ArrayList;

public class AnarSHIT extends JavaPlugin {
    public static ArrayList<Party> parties = new ArrayList<>();
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("====================================================");
        this.getCommand("party").setExecutor(new PartyCommand());
        this.getCommand("party").setTabCompleter(new PartyCommandTab());
        Bukkit.getConsoleSender().sendMessage("AnarSHIT Base Plugin Started");
        Bukkit.getConsoleSender().sendMessage("====================================================");

    }
    public static Party getPlayerParty(Player player) {
        for (Party p : parties) {
            if (p.getPlayers().contains(player)) {
                return p;
            }
        }
        return null;
    }
    public static void addPlayerParty(String passcode, Player player) {
        boolean exists = false;
        Party party = null;
        for (Party p : parties) {
            if (p.getCode().equals(passcode)) {
                party = p;
                exists = true;
            }
            if (p.getPlayers().contains(player)) {
                p.removePlayer(player);
            }
        }
        if (!exists) {
            party = new Party(passcode);
            parties.add(party);
        }
        party.addPlayer(player);
        announceToParty(player.name().append(Component.text(" has joined your party.")), party);
    }
    public static Party playerLeaveParty(Player player) {
        Party partyLeft = null;
        for (Party p : parties) {
            if (p.getPlayers().contains(player)) {
                partyLeft = p;
                p.removePlayer(player);
            }
        }
        announceToParty(player.name().append(Component.text(" has left your party.")), partyLeft);
        return partyLeft;
    }
    public static void announceToParty(Component message, Party party) {
        ArrayList<Player> players = party.getPlayers();
        for (Player p : players) {
            p.sendMessage(message);
        }
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("====================================================");
        Bukkit.getConsoleSender().sendMessage("AnarSHIT Base Plugin Shut down");
        Bukkit.getConsoleSender().sendMessage("====================================================");
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        Player attacker = (Player) event.getDamager();
        Player recipient = (Player) event.getEntity();
        String attackerPcode = getPlayerParty(attacker).getCode();
        String recipPcode = getPlayerParty(recipient).getCode();
        boolean sameParty = false;
        if (attackerPcode.equals(recipPcode)) {
            sameParty = true;
        }
        if (sameParty) {
            event.setCancelled(true);
            attacker.sendMessage("Blocked friendly fire");
        }
    }
}
