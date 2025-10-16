package sistema_industrial_weg.view.menu.material;

import sistema_industrial_weg.dto.material.MaterialRegisterRequest;
import sistema_industrial_weg.infra.beans.MaterialBeans;
import sistema_industrial_weg.infra.beans.ProviderBeans;
import sistema_industrial_weg.service.material.MaterialService;
import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.Menu;
import sistema_industrial_weg.view.menu.Menus;
import sistema_industrial_weg.view.menu.provider.MenuRegisterProvider;

public class MenuRegisterMaterial extends Menu {

    private final MaterialService materialService;

    protected MenuRegisterMaterial(Reader reader, Printer printer, MaterialService materialService) {
        super(reader, printer);
        this.materialService = materialService;
    }

    @Override
    public void execute() {
        var request = callMenu();

        if(request == null){
            setNextMenu(Menus.toInstanceMainMenu(getReader(), getPrinter()));
            return;
        }

        materialService.register(request);

        getPrinter().printText("| Material adicionado com sucesso");

    }

    private MaterialRegisterRequest callMenu() {
        getPrinter().printTitle("Cadastrar material");

        getPrinter().printText("Digite as informações do material: ( 0 para cancelar )");

        getPrinter().printPhrase("Nome: ");
        String name = getReader().readLine();

        if(name.equals("0")) return null;

        getPrinter().printPhrase("Unidade: ");
        String unit = getReader().readLine();

        getPrinter().printPhrase("Quantidade inicial: ");
        double initialQuantity = getReader().readDouble();

        return new MaterialRegisterRequest(name, unit, initialQuantity);
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuRegisterMaterial(reader, printer, MaterialBeans.toInstanceService());
    }

}
