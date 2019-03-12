package biblioteca.util;

import biblioteca.model.Carte;

public class Validator {

	// TODO dafuk is with this name, easily confused with isOKString
	public static boolean isStringOK(String s) throws Exception{
		boolean flag = s.matches("[a-zA-Z]+");
		if(flag == false)
			throw new Exception("String invalid");
		return flag;
	}
	
	public static void validateCarte(Carte c)throws Exception{
		if(c.getCuvinteCheie()==null){ // TODO vazut ca lista nu cumva chiar ii vida
			throw new Exception("Lista cuvinte cheie vida!");
		}
		if(c.getReferenti()==null){// TODO vazut ca lista nu cumva chiar ii vida
			throw new Exception("Lista autori vida!");
		}
		if(!isOKString(c.getTitlu()))
			throw new Exception("Titlu invalid!");
		for(String s:c.getReferenti()){
			if(!isOKString(s))
				throw new Exception("Autor invalid!");
		}
		for(String s:c.getCuvinteCheie()){
			if(!isOKString(s))
				throw new Exception("Cuvant cheie invalid!");
		}
		if(!Validator.isNumber(c.getAnAparitie()))
			throw new Exception("Editura invalid!"); // ps. misto an 00000000000
	}
	
	public static boolean isNumber(String s){
		return s.matches("[0-9]+");
	}

	// TODO hai cumva sa uitam de atrocitatea ce ii implementarea la functia asta
	// TODO nu de alta da vreau sa nu am cosmaruri la noapte
	public static boolean isOKString(String s){
		String []t = s.split(" ");
		if(t.length==2){
			boolean ok1 = t[0].matches("[a-zA-Z]+");
			boolean ok2 = t[1].matches("[a-zA-Z]+");
			if(ok1==ok2 && ok1==true){
				return true;
			}
			return false;
		}
		return s.matches("[a-zA-Z]+");

//		O implementare mai frumoasa
//		if (s.contains(" "))
//			return s.matches("[a-zA-Z]+ [a-zA-Z]+");
//		else
//			return s.matches("[a-zA-Z]+");
	}
	
}
