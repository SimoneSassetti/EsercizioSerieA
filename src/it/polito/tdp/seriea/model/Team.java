package it.polito.tdp.seriea.model;

public class Team implements Comparable<Team>{
	
	private String team ;
	private int punti;
	
	public Team(String team) {
		super();
		this.team = team;
	}

	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti+=punti;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return team;
	}

	@Override
	public int compareTo(Team t) {
		return -(this.punti-t.punti);
	}	
}
