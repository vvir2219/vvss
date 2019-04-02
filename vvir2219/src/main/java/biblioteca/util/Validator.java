package biblioteca.util;

import biblioteca.model.Carte;

import java.awt.event.KeyEvent;

public class Validator {
	private static boolean isPrintableChar( char c ) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of( c );
		return (!Character.isISOControl(c)) &&
				c != KeyEvent.CHAR_UNDEFINED &&
				block != null &&
				block != Character.UnicodeBlock.SPECIALS;
	}

	public static void validateAutor(String autor) throws Exception {
		String []lista_nume = autor.split("[ ]+");
		if (lista_nume.length > 5)
			throw new Exception("Autorul are prea multe nume!");
		for (String nume : lista_nume) {
			if (!nume.matches("[A-Z][a-z]*\\.?"))
				throw new Exception("Numele autorului ii dubios!");
		}
	}

	private static void validateString(String string) throws Exception {
		if (string.length() == 0)
			throw new Exception("String does not contain characters!");
		for(Character c : string.toCharArray())
			if(!isPrintableChar(c))
				throw new Exception("String contains unprintable characters!");
	}
	
	public static void validateCarte(Carte c)throws Exception{
		if(c.getCuvinteCheie().size() == 0){
			throw new Exception("Lista cuvinte cheie vida!");
		}
		if(c.getAutori().size() ==  0){
			throw new Exception("Lista autori vida!");
		}

		validateString(c.getTitlu());
		validateString(c.getEditura());
		for(String autor: c.getAutori())
			validateAutor(autor);
		for(String cuv_cheie:c.getCuvinteCheie())
			validateString(cuv_cheie);

		if(!Validator.isYearValid(c.getAnAparitie()))
			throw new Exception("An aparitie invalid!");
	}
	
	public static boolean isYearValid(String s){
		try {
			Integer year = Integer.parseInt(s);
			if (year < 0 || year > 3000)
				return false;
			return true;
		} catch (NumberFormatException ignored) {
			return false;
		}
	}
}
