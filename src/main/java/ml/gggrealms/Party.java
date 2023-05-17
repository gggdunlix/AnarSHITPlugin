package ml.gggrealms;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Party {
    private int pCode;
    private ArrayList<Player> players = new ArrayList<>();
    public Party(int code) {
        pCode = code;
    }
    public int getCode() {
        return pCode;
    }
    public ArrayList<Player> addPlayer(Player player) {
        players.add(player);
        return players;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
