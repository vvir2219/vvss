package biblioteca.control;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BibliotecaCtrl {

	private CartiRepoInterface cr;
	
	public BibliotecaCtrl(CartiRepoInterface cr){
		this.cr = cr;
	}
	
	public void adaugaCarte(Carte c) throws Exception {
		Validator.validateCarte(c);
		cr.adaugaCarte(c);
	}

	public List<Carte> cautaCarte(String autor) throws Exception{
		Validator.validateAutor(autor);
		List<Carte> carti = cr.getCarti();
		List<Carte> cartiGasite = new ArrayList<Carte>();
		int i=0;
		while (i<carti.size()){
			boolean flag = false;
			List<String> lref = carti.get(i).getAutori();
			int j = 0;
			while(j<lref.size()){
				if(lref.get(j).toLowerCase().contains(autor.toLowerCase())){
					flag = true;
					break;
				}
				j++;
			}
			if(flag == true){
				cartiGasite.add(carti.get(i));
			}
			i++;
		}
		return cartiGasite;
	}
	
	public List<Carte> getCarti() throws Exception{
		return cr.getCarti();
	}
	
	public List<Carte> getCartiOrdonateDinAnul(String an) throws Exception{
		if(!Validator.isYearValid(an))
			throw new Exception("Anul nu e valid!");
		List<Carte> lc = getCarti();
		List<Carte> lca = new ArrayList<Carte>();
		for(Carte c:lc){
			if(c.getAnAparitie().equals(an) == false){
				lca.add(c);
			}
		}

		Collections.sort(lca,new Comparator<Carte>(){

			@Override
			public int compare(Carte a, Carte b) {
				if(a.getAnAparitie().compareTo(b.getAnAparitie())==0){
					return a.getTitlu().compareTo(b.getTitlu());
				}

				return a.getTitlu().compareTo(b.getTitlu());
			}

		});

		return lca;
	}
}
