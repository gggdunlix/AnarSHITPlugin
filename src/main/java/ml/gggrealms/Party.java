package ml.gggrealms;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Party {
    private String pCode;
    private ArrayList<Player> players = new ArrayList<>();
    public Party(String passcode) {
        pCode = passcode;
    }
    public String getCode() {
        return pCode;
    }
    public ArrayList<Player> addPlayer(Player player) {
        players.add(player);
        return players;
    }
    public ArrayList<Player> removePlayer(Player player) {
        players.remove(player);
        return players;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
