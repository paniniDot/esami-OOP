package e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.transform.stream.StreamSource;

public class TurnamentImpl implements Turnament {

	private final List<Player> registerdPlayers = new ArrayList<>();
	private final List<Match> pendingMatches = new ArrayList<>();
	private final List<Player> lastRoundWinners = new ArrayList<>();
	private final Map<Player, Set<Player>> beatedFrom = new HashMap<>();
	
	@Override
	public Player makePlayer(int id, String name) {
		return new PlayerImpl(id, name);
	}

	@Override
	public Match makeMatch(Player p1, Player p2) {
		return new MatchImpl(p1, p2);
	}

	@Override
	public void registerPlayer(Player player) {
		this.registerdPlayers.add(player);
		this.beatedFrom.put(player, new HashSet<>());
	}
	
	private void buildPendingMatchesFrom(List<? extends Player> list) {
		for(int i=0; i<list.size(); i+=2) {
			this.pendingMatches.add(new MatchImpl(list.get(i), list.get(i+1)));
		}
	}
	
	@Override
	public void startTurnament() {
		this.buildPendingMatchesFrom(registerdPlayers);
	}

	@Override
	public List<Player> getPlayers() {
		return this.registerdPlayers;
	}

	@Override
	public List<Match> getPendingGames() {
		return this.pendingMatches;
	}
	
	private Player getLoser(Match match, Player winner) {
		return match.getFirstPlayer().equals(winner) ? match.getSecondPlayer() : match.getFirstPlayer();
	}
	
	@Override
	public void playMatch(Match match, Player winner) {
		this.lastRoundWinners.add(winner);
		this.beatedFrom.get(winner).add(this.getLoser(match, winner));
		this.beatedFrom.remove(this.getLoser(match, winner));
		this.pendingMatches.remove(match);
		if(this.pendingMatches.size() == 0 && this.lastRoundWinners.size() > 1) {
			this.lastRoundWinners.sort((p1, p2) -> this.registerdPlayers.indexOf(p1) - this.registerdPlayers.indexOf(p2));
			this.buildPendingMatchesFrom(lastRoundWinners);
			this.lastRoundWinners.removeAll(this.lastRoundWinners);
		}
	}

	@Override
	public boolean isTurnamentOver() {
		return this.pendingMatches.size() == 0;
	}

	@Override
	public Player winner() {
		return this.lastRoundWinners.get(0);
	}

	@Override
	public Set<Player> opponents(Player player) {
		return this.beatedFrom.get(player);
	}

}
