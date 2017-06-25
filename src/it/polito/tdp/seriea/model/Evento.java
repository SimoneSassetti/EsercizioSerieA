package it.polito.tdp.seriea.model;

public class Evento implements Comparable<Evento>{
	
	private Team home;
	private Team away;
	private int giornata;
	
	public Evento(Team home, Team away, int giornata) {
		super();
		this.home = home;
		this.away = away;
		this.giornata = giornata;
	}
	public Team getHome() {
		return home;
	}
	public void setHome(Team home) {
		this.home = home;
	}
	public Team getAway() {
		return away;
	}
	public void setAway(Team away) {
		this.away = away;
	}
	public int getGiornata() {
		return giornata;
	}
	public void setGiornata(int giornata) {
		this.giornata = giornata;
	}
	@Override
	public int compareTo(Evento e) {
		return this.giornata-e.giornata;
	}
	@Override
	public String toString() {
		return "home=" + home + ", away=" + away + ", giornata=" + giornata+"\n";
	}
	
}
