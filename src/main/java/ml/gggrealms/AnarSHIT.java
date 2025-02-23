package ml.gggrealms;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.event.HoverEventSource;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class AnarSHIT extends JavaPlugin implements Listener {
    public static ArrayList<Party> parties = new ArrayList<>();
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("===================================================");
        this.getCommand("party").setExecutor(new PartyCommand());
        this.getCommand("party").setTabCompleter(new PartyCommandTab());
        this.getCommand("partychat").setExecutor(new PartyChatCmd());
        this.getCommand("shutoff").setExecutor(new ShutoffServer());
        this.getCommand("bugreport").setExecutor(new BugreportCommand());
        this.getCommand("suicide").setExecutor(new SuicideCmd());

        getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage("AnarSHIT Base Plugin Started");
        Bukkit.getConsoleSender().sendMessage("===================================================");

    }
    @EventHandler
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        /*  - Permissions Change for Geyser Players, Disabled since I took out the anticheat from the server! -
        Bukkit.getConsoleSender().sendMessage(Component.text(event.getPlayer().getName() + " joined, running perms..."));
        GeyserConnection connection = GeyserApi.api().connectionByUuid(event.getPlayer().getUniqueId());
        HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();

        PermissionAttachment attachment = player.addAttachment(this);
        perms.put(player.getUniqueId(), attachment);
        PermissionAttachment pperms = perms.get(player.getUniqueId());
        if (connection == null) {
            Bukkit.getConsoleSender().sendMessage(Component.text(event.getPlayer().getName() + " joined on java, removing perms..."));
            perms.get(player.getUniqueId()).unsetPermission("nocheatplus.shortcut.bypass");
        } else {
            Bukkit.getConsoleSender().sendMessage(Component.text(event.getPlayer().getName() + " joined on bedrock, adding perms..."));
            pperms.setPermission("nocheatplus.shortcut.bypass", true);
        }
        perms.put(player.getUniqueId(), attachment);
        */
        event.getPlayer().sendMessage(Component.text("Welcome to the AnarSHIT anarchy server."));
        event.getPlayer().sendMessage(Component.text("Here, you can do whatever the hell you want."));
        Component discordLink = Component.text("https://discord.gg/Yr3sCDgVvR", TextColor.color(0x2752AC));
        discordLink.hoverEvent(HoverEvent.showText(Component.text("Click to open")));
        try {
            discordLink.clickEvent(ClickEvent.openUrl(new URL("https://discord.gg/Yr3sCDgVvR")));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        event.getPlayer().sendMessage(discordLink);
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player dead = event.getPlayer();
        if (dead.getScoreboardTags().contains("suicidal")) {
            Random rand = new Random();
            int randomNum = rand.nextInt(1,9);
            if (randomNum ==1) {
                event.deathMessage(Component.text(dead.getName() + " took the easy way out"));
            } else if (randomNum==2) {
                event.deathMessage(Component.text(dead.getName() + " killed themself"));
            } else if (randomNum==3) {
                event.deathMessage(Component.text(dead.getName() + " committed suicide"));
            } else if (randomNum==4){
                event.deathMessage(Component.text(dead.getName() + "'s plead for death was answered"));
            } else if (randomNum==5) {
                event.deathMessage(Component.text(dead.getName() + "'s flailing about was finally stopped"));
            } else if (randomNum==6) {
                event.deathMessage(Component.text(dead.getName() + "'s fate was the same as Hitler's"));
            } else if (randomNum==7) {
                event.deathMessage(Component.text(dead.getName() + " ended it all themself."));
            } else if (randomNum==8) {
                event.deathMessage(Component.text(dead.getName() + " took matters into their own hands."));
            }
        }
        dead.removeScoreboardTag("suicidal");
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
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player attacker = (Player) event.getDamager();
        Player recipient = (Player) event.getEntity();
        if (getPlayerParty(attacker) != null && getPlayerParty(recipient) != null) {
            String attackerPcode = getPlayerParty(attacker).getCode();
            String recipPcode = getPlayerParty(recipient).getCode();
        } else {
            return;
        }
         if (attackerPcode == null || recipPcode == null) {
            return;
        }
        if (attackerPcode.equals(recipPcode)) {
            event.setCancelled(true);
            attacker.sendMessage("Blocked friendly fire");
        }

    }
}
