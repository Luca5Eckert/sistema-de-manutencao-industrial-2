package sistema_industrial_weg.view.menu;

import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;


import java.util.Objects;

public abstract class Menu {

    protected Menu(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    private Menu nextMenu;
    private final Reader reader;
    private final Printer printer;

    public abstract void execute();

    public Menu getNextMenu() {
        if(Objects.isNull(nextMenu)) return this;

        return nextMenu;
    }

    public Reader getReader() {
        return reader;
    }

    public Printer getPrinter() {
        return printer;
    }

    public void setNextMenu(Menu nextMenu) {
        this.nextMenu = nextMenu;
    }
}
