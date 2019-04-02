package biblioteca.control;

import biblioteca.model.Carte;
import biblioteca.repository.repo.CartiRepo;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.repository.repoMock.CartiRepoMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BibliotecaCtrlTest {

    // @before se ruleaza inainte de fiecare test si @after dupa
    private static CartiRepoInterface cr;
    private static BibliotecaCtrl bc;
    private static Carte carte;

    @BeforeClass
    public static void initializer() {
        cr = new CartiRepoMock();
        bc = new BibliotecaCtrl(cr);
    }

    @Before
    public void before(){
        carte = Carte.getCarteFromString("a;Mihai Eminescu,Ion Caragiale,Ion Creanga;1984;Corint;povesti,povestiri");
    }

    @Test
    public void TC1_adaugareCorecta() throws Exception {
        bc.adaugaCarte(carte);
        assertTrue(bc.getCarti().contains(carte));
    }

    @Test(expected = Exception.class)
    public void TC2_titluContineCaractereNeprintabile() throws Exception {
        carte.setTitlu("\u0003");
        bc.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void TC3_titluEVid() throws Exception {
        carte.setTitlu("");
        bc.adaugaCarte(carte);
    }

    @Test
    public void TC4_titluAreLungimeNegativa(){
        // imposibil de testat
    }

    @Test(expected = Exception.class)
    public void TC5_anAparitieNegativ() throws Exception {
        carte.setAnAparitie("-1");
        bc.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void TC5_anAparitieMaiMareCa3000() throws Exception {
        carte.setAnAparitie("3001");
        bc.adaugaCarte(carte);
    }

    @Test(expected = Exception.class)
    public void TC5_anAparitieNuENumar() throws Exception {
        carte.setAnAparitie("ana");
        bc.adaugaCarte(carte);
    }
}