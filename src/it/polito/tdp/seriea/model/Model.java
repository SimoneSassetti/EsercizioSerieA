package it.polito.tdp.seriea.model;

import java.util.*;

public class Model {
	private static int giornate=0;
	
	public Model(){
			
	}
	
	public static void main(String[] args) {
		
		Model model=new Model();
		List<Team> temp=new ArrayList<>();
		int best=0;
		for(int i=0; i<100; i++){
			temp=model.simula(20);
			if(best<temp.get(0).getPunti())
				best=(temp.get(0).getPunti());
		}
		System.out.println("Disputate "+giornate+" giornate\nPunteggio migliore: "+best+"\nClassifica:\n");
		for(Team t: temp){
			System.out.println(t.getTeam()+" -> "+t.getPunti());
		}
	}
	
	private List<Team> simula(int squadre) {
		
		Simulatore sim=new Simulatore(squadre);
		sim.inserisci();
		giornate=sim.run();
		return sim.getClassifica();
	}

}
