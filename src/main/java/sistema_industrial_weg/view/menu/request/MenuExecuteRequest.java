package sistema_industrial_weg.view.menu.request;

import sistema_industrial_weg.dto.request.get.RequestGetResponse;
import sistema_industrial_weg.infra.beans.MaterialBeans;
import sistema_industrial_weg.infra.beans.RequestBeans;
import sistema_industrial_weg.service.request.RequestService;
import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.Menu;
import sistema_industrial_weg.view.menu.Menus;

import java.util.List;

public class MenuExecuteRequest extends Menu {

    private final RequestService requestService;

    protected MenuExecuteRequest(Reader reader, Printer printer, RequestService requestService) {
        super(reader, printer);
        this.requestService = requestService;
    }

    @Override
    public void execute() {
        var requestList = requestService.getAllPendent();

        var request = callMenu(requestList);

        if(request == null){
            getPrinter().printText("|| Voltando");
            setNextMenu(Menus.toInstanceMainMenu(getReader(), getPrinter()));
            return;
        }

        requestService.execute(request.id());

        getPrinter().printText("| | Requisição executada com sucesso");

    }

    private RequestGetResponse callMenu(List<RequestGetResponse> requestList) {

        getPrinter().printTitle("Executar Requisição");

        getPrinter().printText(" Digite o número da requisição que deseja executar: ( 0 para voltar )");

        getPrinter().printList(requestList);
        var select = getReader().readInteger() - 1;

        if(select == -1) return null;

        if(!isValidSelect(requestList, select)) throw new RuntimeException("| Opção sem correspodencia");

        return requestList.get(select);
    }

    private boolean isValidSelect(List<?> itens, int select) {
        return select >= 0 && select < itens.size();
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuExecuteRequest(reader, printer, RequestBeans.toInstanceRequest());
    }


}
