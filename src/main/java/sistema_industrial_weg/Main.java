package sistema_industrial_weg;

import sistema_industrial_weg.view.MenuManager;
import sistema_industrial_weg.view.MenuProvider;
import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.Menus;

import static java.lang.IO.println;


public class Main {
    public static void main(String[] args) {

        Reader reader = new Reader();
        Printer printer = new Printer();
        MenuProvider menuProvider = new MenuProvider(Menus.toInstanceMainMenu(reader, printer));

        new MenuManager(menuProvider).init();

    }
}