package e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChampionshipImpl implements Championship {

	private final List<String> registeredTeams = new ArrayList<>();
	private final List<Match> pendingMatchesOfTheDay = new ArrayList<>();
	private final List<Match> allMatches = new ArrayList<>();
	private final Map<String, Integer> classification = new HashMap<>();
	
	@Override
	public void registerTeam(String name) {
		this.registeredTeams.add(name);
		this.classification.put(name, 0);
	}

	@Override
	public void startChampionship() {
		Combinations.combine(this.registeredTeams).stream().forEach(l -> this.allMatches.add(new MatchImpl(l.get(0), l.get(1))));
	}

	@Override
	public void newDay() {
		IntStream.range(0, 2).forEach(i -> this.pendingMatchesOfTheDay.add(this.allMatches.remove(0)));
	}

	@Override
	public Set<Match> pendingMatches() {
		return this.pendingMatchesOfTheDay.stream().collect(Collectors.toSet());
	}
	
	private int getPointsOfHome(int homeGoals, int awayGoals) {
		return homeGoals > awayGoals ? 3 : homeGoals == awayGoals ? 1 : 0;
	}
	
	private int getPointsOfAway(int homeGoals, int awayGoals) {
		return awayGoals > homeGoals ? 3 : awayGoals == homeGoals ? 1 : 0;
	}

	@Override
	public void matchPlay(Match match, int homeGoals, int awayGoals) {
		this.pendingMatchesOfTheDay.remove(match);
		this.classification.merge(match.getHomeTeam(), this.getPointsOfHome(homeGoals, awayGoals), Integer::sum);
		this.classification.merge(match.getAwayTeam(), this.getPointsOfAway(homeGoals, awayGoals), Integer::sum);
	}

	@Override
	public Map<String, Integer> getClassification() {
		return this.classification;
	}

	@Override
	public boolean championshipOver() {
		return this.allMatches.size() == 0 && this.pendingMatchesOfTheDay.size() == 0;
	}

}
