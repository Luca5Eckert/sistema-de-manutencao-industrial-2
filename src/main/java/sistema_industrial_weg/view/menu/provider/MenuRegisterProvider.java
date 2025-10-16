package sistema_industrial_weg.view.menu.provider;

import sistema_industrial_weg.dto.provider.ProviderRegisterRequest;
import sistema_industrial_weg.infra.beans.ProviderBeans;
import sistema_industrial_weg.service.provider.ProviderService;
import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.Menu;
import sistema_industrial_weg.view.menu.Menus;

import java.util.Objects;

import static java.util.Objects.isNull;

public class MenuRegisterProvider extends Menu {

    private final ProviderService providerService;

    protected MenuRegisterProvider(Reader reader, Printer printer, ProviderService providerService) {
        super(reader, printer);
        this.providerService = providerService;
    }

    @Override
    public void execute() {
        var request = callMenu();

        if(isNull(request)) {
            setNextMenu(Menus.toInstanceMainMenu(getReader(), getPrinter()));
            return;
        }

        providerService.register(request);

        getPrinter().printText("| Fornecedor adicionado com sucesso");

        setNextMenu(Menus.toInstanceMainMenu(getReader(), getPrinter()));
    }

    private ProviderRegisterRequest callMenu() {
        getPrinter().printTitle("Cadastrar Fornecedor");

        getPrinter().printText(" Digite as informações do fornecedor: ( 0 para cancelar )");

        getPrinter().printPhrase(" Nome: ");
        String name = getReader().readLine();

        if(name.equals("0")) return null;

        getPrinter().printPhrase(" Cnpj: ");
        String cnpj = getReader().readLine();

        return new ProviderRegisterRequest(name, cnpj);
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuRegisterProvider(reader, printer, ProviderBeans.toInstanceService());
    }


}
