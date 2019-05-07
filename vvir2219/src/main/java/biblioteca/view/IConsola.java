package biblioteca.view;

import java.io.IOException;

public interface IConsola {
    void executa() throws IOException;

    void printMenu();

    void adauga();

    void afiseazaToateCartile();

    void cautaCartiDupaAutor();

    void afiseazaCartiOrdonateDinAnul();
}
