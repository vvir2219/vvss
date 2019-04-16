package biblioteca.control;

import biblioteca.model.Carte;
import biblioteca.repository.repo.CartiRepo;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TesttFunctionalitate2 {

    // @before se ruleaza inainte de fi
    // ecare test si @after dupa
    private static CartiRepoInterface cr;
    private static BibliotecaCtrl bc;

    @Before
    public void initializer() {
        cr = new CartiRepoMock();
        bc = new BibliotecaCtrl(cr);
    }

    @Test
    public void TC1_repoGol() throws Exception {
        assertTrue(bc.cautaCarte("Ion").size() == 0);
    }

    @Test
    public void TC2_carteFaraAutori() throws Exception {
        Carte carte = Carte.getCarteFromString("a;;1984;Corint;povesti,povestiri");
        cr.adaugaCarte(carte);
        assertEquals(bc.cautaCarte("Ana").size(), 0);
    }

    @Test
    public void TC3_nuGasesteAutor() throws Exception {
        Carte carte = Carte.getCarteFromString("a;Mihai Eminescu,Ion Caragiale,Ion Creanga;1984;Corint;povesti,povestiri");
        cr.adaugaCarte(carte);
        assertEquals(bc.cautaCarte("Ana").size(), 0);
    }

    @Test
    public void TC_gasesteCarte() throws Exception {
        Carte carte = Carte.getCarteFromString("a;Ana;1984;Corint;povesti,povestiri");
        cr.adaugaCarte(carte);
        assertEquals(bc.cautaCarte("Ana").size(), 1);
    }
}