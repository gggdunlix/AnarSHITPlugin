package ml.gggrealms;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartySystem {
    private ArrayList<Party> parties = new ArrayList<>();
    public PartySystem() {}
    public void addParty(Party party) {
        parties.add(party);
    }
    public ArrayList<Party> getParties() {
        return parties;
    }
    public boolean partyExists(int code) {
        for (Party p : parties) {
            if (p.getCode() == code) {
                return true;
            }
        }
        return false;
    }
    public Party getPlayerParty(Player player) {
        Party p = null;
        for (Party party : parties) {
            if (party.getPlayers().contains(player)) p = party;
        }
        return p;
    }
}
