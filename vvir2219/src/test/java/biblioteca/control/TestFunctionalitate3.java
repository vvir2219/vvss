package biblioteca.control;

import biblioteca.model.Carte;
import biblioteca.repository.repo.CartiRepo;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestFunctionalitate3 {

    // @before se ruleaza inainte de fiecare test si @after dupa
    private static CartiRepoInterface cr;
    private static BibliotecaCtrl bc;

    @BeforeClass
    public static void initializer() {
        cr = new CartiRepoMock();
        bc = new BibliotecaCtrl(cr);

        cr.adaugaCarte(Carte.getCarteFromString("Povesti;Mihai Eminescu,Ion Caragiale,Ion Creanga;1973;Corint;povesti,povestiri"));
        cr.adaugaCarte(Carte.getCarteFromString("Poezii;Sadoveanu;1973;Corint;poezii"));
        cr.adaugaCarte(Carte.getCarteFromString("Enigma Otiliei;George Calinescu;1948;Litera;enigma,otilia"));
        cr.adaugaCarte(Carte.getCarteFromString("Dale carnavalului;Caragiale Ion;1948;Litera;caragiale,carnaval"));
        cr.adaugaCarte(Carte.getCarteFromString("Intampinarea crailor;Mateiu Caragiale;1948;Litera;mateiu,crailor"));
        cr.adaugaCarte(Carte.getCarteFromString("Test;Calinescu,Tetica;1992;Pipa;am,casa"));
    }

    @Test
    public void TC1_valid() throws Exception {
        List<Carte> cartiOrdonateDinAnul = bc.getCartiOrdonateDinAnul("1948");
        assertEquals(cartiOrdonateDinAnul.size(), 3);
        assertEquals(cartiOrdonateDinAnul.get(0).getTitlu(), "Dale carnavalului");
        assertEquals(cartiOrdonateDinAnul.get(1).getTitlu(), "Enigma Otiliei");
        assertEquals(cartiOrdonateDinAnul.get(2).getTitlu(), "Intampinarea crailor");
    }

    @Test(expected = Exception.class)
    public void TC2_invalid() throws Exception {
        bc.getCartiOrdonateDinAnul("3001");
    }
}