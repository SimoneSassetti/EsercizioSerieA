package it.polito.tdp.seriea.model;

import java.util.*;

public class Simulatore {
	private int numSquadre;
	private PriorityQueue<Evento> coda;
	private List<Team> squadre;
	private Map<String,Team> mappa;
	
	public Simulatore(int squadre) {
		numSquadre=squadre;
		
		coda=new PriorityQueue<Evento>();
		mappa=new HashMap<String,Team>();
		this.squadre=new ArrayList<>();
	}
	
	public void inserisci(){
		String array[] = new String[numSquadre];
		for(int i=0; i<numSquadre; i++){
			array[i]="Squadra_"+i;
		}
		AlgoritmoDiBerger(array);	
	}
	public void AlgoritmoDiBerger(String[] squadre){
		 
	    int numero_squadre = squadre.length;
	    int giornate = numero_squadre - 1;
	 
	    /* crea gli array per le due liste in casa e fuori */
	    String[] casa = new String[numero_squadre /2];
	    String[] trasferta = new String[numero_squadre /2];
	 
	    for (int i = 0; i < numero_squadre /2; i++) {
	        casa [i] = squadre[i]; 
	        trasferta[i] = squadre[numero_squadre - 1 - i]; 
	    }
	 
	    for (int i = 0; i < giornate; i++) {
	        /* stampa le partite di questa giornata */
	        //System.out.printf("%d^ Giornata\n",i+1);
	 
	        /* alterna le partite in casa e fuori */
	        if (i % 2 == 0) {
	            for (int j = 0; j < numero_squadre /2 ; j++){
	                //System.out.printf("%d  %s - %s\n", j+1, trasferta[j], casa[j]);
	                Team t1 = null;
	                Team t2 = null;
					if(!mappa.containsKey(trasferta[j])){
	                	t1 = new Team(trasferta[j]);
	                	this.squadre.add(t1);
	                	mappa.put(t1.getTeam(), t1);
					}else{
						t1=mappa.get(trasferta[j]);
					}
					if(!mappa.containsKey(casa[j])){
	                	t2 = new Team(casa[j]);
	                	this.squadre.add(t2);
	                	mappa.put(t2.getTeam(), t2);
					}else{
						t2=mappa.get(casa[j]);
					}
	                Evento e=new Evento(t1,t2,i+1);
	                coda.add(e);
	            }
	        }
	        else {
	            for (int j = 0; j < numero_squadre /2 ; j++){
	                 //System.out.printf("%d  %s - %s\n", j+1, casa[j], trasferta[j]);
	                 Team t1 = null;
		                Team t2 = null;
						if(!mappa.containsKey(trasferta[j])){
		                	t1 = new Team(trasferta[j]);
		                	this.squadre.add(t1);
		                	mappa.put(t1.getTeam(), t1);
						}else{
							t1=mappa.get(trasferta[j]);
						}
						if(!mappa.containsKey(casa[j])){
		                	t2 = new Team(casa[j]);
		                	this.squadre.add(t2);
		                	mappa.put(t2.getTeam(), t2);
						}else{
							t2=mappa.get(casa[j]);
						}
		                Evento e=new Evento(t1,t2,i+1);
		                coda.add(e);
	            }
	        }
	 
	        // Ruota in gli elementi delle liste, tenendo fisso il primo elemento
	        // Salva l'elemento fisso
	        String pivot = casa [0];
	 
	        /* sposta in avanti gli elementi di "trasferta" inserendo 
	           all'inizio l'elemento casa[1] e salva l'elemento uscente in "riporto" */
			   
	 		String riporto = trasferta[trasferta.length - 1];
			trasferta = shiftRight(trasferta, casa[1]);

	 
	        /* sposta a sinistra gli elementi di "casa" inserendo all'ultimo 
	           posto l'elemento "riporto" */
			   
	        casa = shiftLeft(casa, riporto);
	 
	        // ripristina l'elemento fisso
	        casa[0] = pivot ;
	    } 
	}
	 
	private String[] shiftLeft(String[] data, String add) {
		String[] temp = new String[data.length];
		for (int i = 0; i < data.length-1; i++) {
			temp[i] = data[i + 1];
		}
		temp[data.length - 1] = add;
		return temp;
	}
		
	private String[] shiftRight(String[] data, String add) {
		String[] temp = new String[data.length];
		for (int i = 1; i < data.length; i++) {
			temp[i] = data[i - 1];
		}
		temp[0] = add;
		return temp;
	}
	
	public int run(){
		int giornate=1;
		
		while(!coda.isEmpty()){
			
			Evento e=coda.poll();
			//System.out.println(e);
			giornate=e.getGiornata();
			Team h=e.getHome();
			Team a=e.getAway();
			Random r=new Random();
			int ris=r.nextInt(3)-1;
			if(ris==0){
				h.setPunti(1);
				a.setPunti(1);
			}else if(ris==1){
				h.setPunti(3);
			}else{
				a.setPunti(3);
			}
		}
		return giornate;
	}
	
	public List<Team> getClassifica(){
		List<Team> classifica=new ArrayList<Team>(squadre);
		Collections.sort(classifica);
		return classifica;
	}
}
