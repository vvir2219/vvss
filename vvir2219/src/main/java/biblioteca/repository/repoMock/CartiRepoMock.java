package biblioteca.repository.repoMock;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartiRepoMock implements CartiRepoInterface {

	private List<Carte> carti;
	
	public CartiRepoMock(){
		carti = new ArrayList<Carte>();
		
//		carti.add(Carte.getCarteFromString("Povesti;Mihai Eminescu,Ion Caragiale,Ion Creanga;1973;Corint;povesti,povestiri"));
//		carti.add(Carte.getCarteFromString("Poezii;Sadoveanu;1973;Corint;poezii"));
//		carti.add(Carte.getCarteFromString("Enigma Otiliei;George Calinescu;1948;Litera;enigma,otilia"));
//		carti.add(Carte.getCarteFromString("Dale carnavalului;Caragiale Ion;1948;Litera;caragiale,carnaval"));
//		carti.add(Carte.getCarteFromString("Intampinarea crailor;Mateiu Caragiale;1948;Litera;mateiu,crailor"));
//		carti.add(Carte.getCarteFromString("Test;Calinescu,Tetica;1992;Pipa;am,casa"));
	}
	
	@Override
	public void adaugaCarte(Carte c) {
		carti.add(c);
	}

	@Override
	public List<Carte> getCarti() {
		return carti;
	}
}
