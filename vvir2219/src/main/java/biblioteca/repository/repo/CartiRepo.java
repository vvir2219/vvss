package biblioteca.repository.repo;


import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CartiRepo implements CartiRepoInterface {
	
	private String file = "cartiBD.dat";
	
	public CartiRepo(){
		URL location = CartiRepo.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(location.getFile());
	}
	
	@Override
	public void adaugaCarte(Carte c) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file,true));
			bw.write(c.toString());
			bw.newLine();
			
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Carte> getCarti() {
		List<Carte> lc = new ArrayList<Carte>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line = null;
			while((line=br.readLine())!=null){
				lc.add(Carte.getCarteFromString(line));
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lc;
	}
}
