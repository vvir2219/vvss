package biblioteca.control;

import biblioteca.model.Carte;
import biblioteca.repository.repoInterfaces.CartiRepoInterface;
import biblioteca.repository.repoMock.CartiRepoMock;
import biblioteca.view.IConsola;
import biblioteca.view.UIConsolaFake;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestIntegrareF1F2F3 {

    // @before se ruleaza inainte de fiecare test si @after dupa
    private static CartiRepoInterface cr;
    private static BibliotecaCtrl bc;
    private static IConsola c;
    private static byte[] oprire = ("0" + System.lineSeparator()).getBytes();
    private static byte[] adaugareCarte = adaugaCarte(
            "Ana",
            "1974",
            "Are",
            Arrays.asList("Mere"),
            Arrays.asList("ciuda")
    );
    private static byte[] cautaDupaAutor = cautaDupaAutor("Mere");
    private static byte[] cautaDupaAn = cautaDupaAn("1974");


    private static byte[] concatBytes(byte[]... bytesList){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
        try {
            for (byte[] bytes : bytesList) {
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray( );
    }

    private static byte[] adaugaCarte(String titlu, String anAparitie, String editura, List<String> autori, List<String> cuvCheie) {
        StringBuilder adaugare = new StringBuilder(
                String.format("1%s%s%s%s%s%s%s%d%s", System.lineSeparator(), titlu, System.lineSeparator(), anAparitie, System.lineSeparator(), editura, System.lineSeparator(), autori.size(), System.lineSeparator())
        );

        for (String autor :
                autori) {
            adaugare.append(autor);
            adaugare.append(System.lineSeparator());
        }
        adaugare.append(cuvCheie.size());
        adaugare.append(System.lineSeparator());
        for (String cuv :
                cuvCheie) {
            adaugare.append(cuv);
            adaugare.append(System.lineSeparator());
        }

        return adaugare.toString().getBytes();
    }
    private static byte[] cautaDupaAutor(String autor) {
        return String.format("2%s%s%s", System.lineSeparator(), autor, System.lineSeparator()).getBytes();
    }
    private static byte[] cautaDupaAn(String an) {
        return String.format("3%s%s%s", System.lineSeparator(), an, System.lineSeparator()).getBytes();
    }

    @Before
    public void initializer() {
        cr = new CartiRepoMock();
        bc = new BibliotecaCtrl(cr);
        c = new UIConsolaFake(bc);
    }

    @Test
    public void BigBangIntegration() throws Exception {
        ByteArrayInputStream in = new ByteArrayInputStream(
                concatBytes(adaugareCarte, cautaDupaAutor, cautaDupaAn, oprire)
        );

        System.setIn(in);
        c.executa();
        System.setIn(System.in);

        UIConsolaFake cf = (UIConsolaFake) c;

        assertEquals(cr.getCarti().size(), 1);
        Carte carte = cr.getCarti().get(0);
        assertEquals(bc.getCarti().size(), 1);
        assertEquals(bc.getCarti().get(0), carte);
        assertEquals(cf.lastCartiDinAnul.get(0), carte);
        assertEquals(cf.lastCartiDupaAutor.get(0), carte);
    }

    @Test
    public void TopDownIntegration() throws Exception {
        UIConsolaFake cf = (UIConsolaFake) c;

        System.setIn(new ByteArrayInputStream(concatBytes(adaugareCarte, oprire)));
        c.executa();
        assertEquals(cr.getCarti().size(), 1);
        Carte carte = cr.getCarti().get(0);
        assertEquals(bc.getCarti().size(), 1);
        assertEquals(bc.getCarti().get(0), carte);

        System.setIn(new ByteArrayInputStream(concatBytes(cautaDupaAutor, oprire)));
        c.executa();
        assertEquals(cf.lastCartiDupaAutor.get(0), carte);

        System.setIn(new ByteArrayInputStream(concatBytes(cautaDupaAn, oprire)));
        c.executa();
        assertEquals(cf.lastCartiDinAnul.get(0), carte);

        System.setIn(System.in);
    }

    @Test
    public void TestF1() throws Exception {
        (new TesttFunctionalitate1()).TC1_adaugareCorecta();
    }

    @Test
    public void TestF2() throws Exception {
        (new TesttFunctionalitate2()).TC_gasesteCarte();
    }

    @Test
    public void TestF3() throws Exception {
        (new TestFunctionalitate3()).TC1_valid();
    }
}
