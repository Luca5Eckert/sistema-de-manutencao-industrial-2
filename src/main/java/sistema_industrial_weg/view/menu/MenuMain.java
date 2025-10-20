package sistema_industrial_weg.view.menu;

import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;

public class MenuMain extends Menu{
    protected MenuMain(Reader reader, Printer printer) {
        super(reader, printer);
    }

    @Override
    public void execute() {
        var entry = callMenu();

        Menu menu = switch (entry.toUpperCase().trim()){
            case "1" -> Menus.toInstanceRegisterProviderMenu(getReader(), getPrinter());
            case "2" -> Menus.toInstanceRegisterMaterialMenu(getReader(), getPrinter());
            case "3" -> Menus.toInstanceRegisterEntryNote(getReader(), getPrinter());
            case "4" -> Menus.toInstanceCreateRequest(getReader(), getPrinter());
            case "6" -> Menus.toInstanceCancelRequest(getReader(), getPrinter());
            case "0" -> endSystem();
            default -> this;
        };

        setNextMenu(menu);

    }

    private Menu endSystem() {
        var menu = Menus.toInstanceEndMenu(getReader(), getPrinter());
        menu.execute();

        return menu;
    }

    private String callMenu() {
        getPrinter().printTitle("Menu Principal");

        getPrinter().printText(" Bem-vindo ao menu principal: ");
        getPrinter().printText(" 1 - Cadastrar Fornecedor");
        getPrinter().printText(" 2 - Cadastrar Material");
        getPrinter().printText(" 3 - Cadastrar Nota de Entrada");
        getPrinter().printText(" 4 - Cadastrar Requisição");
        getPrinter().printText(" 6 - Cancelar requisição");
        getPrinter().printText(" 0 - Sair");

        return getReader().readLine();
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuMain(reader, printer);
    }
}
