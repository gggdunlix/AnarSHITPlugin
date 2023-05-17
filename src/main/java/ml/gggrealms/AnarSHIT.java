package ml.gggrealms;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AnarSHIT extends JavaPlugin {
    public static PartySystem partySystem;
    public static PartySystem getPartySystem() {
        return partySystem;
    }
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("====================================================");
        this.getCommand("party").setExecutor(new PartyCommand());
        this.getCommand("party").setTabCompleter(new PartyCommandTab());
        Bukkit.getConsoleSender().sendMessage("AnarSHIT Base Plugin Started");
        Bukkit.getConsoleSender().sendMessage("====================================================");
        partySystem = new PartySystem();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("====================================================");
        Bukkit.getConsoleSender().sendMessage("AnarSHIT Base Plugin Shut down");
        Bukkit.getConsoleSender().sendMessage("====================================================");
    }
}
