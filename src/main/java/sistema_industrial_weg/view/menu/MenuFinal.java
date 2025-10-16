package sistema_industrial_weg.view.menu;

import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;

public class MenuFinal extends Menu {

    protected MenuFinal(Reader reader, Printer printer) {
        super(reader, printer);
    }

    @Override
    public void execute() {
        getPrinter().printTitle("ENCERRANDO SISTEMA");
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuFinal(reader, printer);
    }

}
