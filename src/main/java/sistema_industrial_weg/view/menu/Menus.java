package sistema_industrial_weg.view.menu;

import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.entry_note.MenuRegisterEntryNote;
import sistema_industrial_weg.view.menu.material.MenuRegisterMaterial;
import sistema_industrial_weg.view.menu.provider.MenuRegisterProvider;

public class Menus {

    public static Menu toInstanceMainMenu(Reader reader, Printer printer){
        return MenuMain.toInstance(reader, printer);
    }

    public static Menu toInstanceRegisterProviderMenu(Reader reader, Printer printer) {
        return MenuRegisterProvider.toInstance(reader, printer);
    }

    public static Menu toInstanceRegisterMaterialMenu(Reader reader, Printer printer) {
        return MenuRegisterMaterial.toInstance(reader, printer);
    }

    public static Menu toInstanceEndMenu(Reader reader, Printer printer) {
        return MenuFinal.toInstance(reader, printer);
    }

    public static Menu toInstanceRegisterEntryNote(Reader reader, Printer printer) {
        return MenuRegisterEntryNote.toInstance(reader, printer);
    }
}
