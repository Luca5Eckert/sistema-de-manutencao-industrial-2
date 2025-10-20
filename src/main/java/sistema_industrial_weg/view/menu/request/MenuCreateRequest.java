package sistema_industrial_weg.view.menu.request;

import sistema_industrial_weg.dto.material.MaterialGetResponse;
import sistema_industrial_weg.dto.request.create.RequestCreateRequest;
import sistema_industrial_weg.dto.request.create.RequestMaterialRequest;
import sistema_industrial_weg.infra.beans.MaterialBeans;
import sistema_industrial_weg.infra.beans.RequestBeans;
import sistema_industrial_weg.service.material.MaterialService;
import sistema_industrial_weg.service.request.RequestService;
import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.Menu;
import sistema_industrial_weg.view.menu.Menus;

import java.util.ArrayList;
import java.util.List;

public class MenuCreateRequest extends Menu {

    private final RequestService requestService;
    private final MaterialService materialService;

    protected MenuCreateRequest(Reader reader, Printer printer, RequestService requestService, MaterialService materialService) {
        super(reader, printer);
        this.requestService = requestService;
        this.materialService = materialService;
    }

    @Override
    public void execute() {

        RequestCreateRequest request = callMenu();

        if(request == null){
            setNextMenu(Menus.toInstanceMainMenu(getReader(), getPrinter()));
            getPrinter().printText("| Voltando ");
            return;
        }

        requestService.create(request);

        getPrinter().printText("| |  Adicionado com sucesso");

        setNextMenu(Menus.toInstanceMainMenu(getReader(), getPrinter()));

    }

    public RequestCreateRequest callMenu(){
        getPrinter().printTitle("Criar Requisição");

        getPrinter().printText(" Digite os dados para criar um requisição: ( 0 para cancelar )");

        getPrinter().printPhrase(" Digite seu setor: ");
        String sector = getReader().readLine();

        if(sector.equals("0")) return null;

        List<RequestMaterialRequest> listMaterial = getMaterials();

        return new RequestCreateRequest(sector, listMaterial);

    }

    private List<RequestMaterialRequest> getMaterials() {
        List<MaterialGetResponse> materials = materialService.getAll();
        List<RequestMaterialRequest> associativesMaterias = new ArrayList<>();

        while(true) {
            getPrinter().printLine();

            getPrinter().printText(" Material: ");
            getPrinter().printText(" 1 - Adicionar ");
            getPrinter().printText(" 2 - Visualizar atuais");
            getPrinter().printText(" 3 - Enviar");

            getPrinter().printLine();


            var text = getReader().readLine();

            switch(text.toUpperCase().trim()){
                case "1" -> addMaterial(materials, associativesMaterias);
                case "2" -> seeMaterial(associativesMaterias, materials);
                case "3" -> {
                    return associativesMaterias;
                }
            }
        }

    }

    private void seeMaterial(List<RequestMaterialRequest> associativesMaterias, List<MaterialGetResponse> materials) {
        System.out.println("Selecione um material para remover: ( 0 para continuar ) ");

        getPrinter().printList(associativesMaterias);

        int select = getReader().readInteger() - 1;

        if(select == -1) return;

        if(!isValidSelect(associativesMaterias, select)) throw new RuntimeException("| Opção sem correspondencia");

        var material = associativesMaterias.get(select);

        materials.add(new MaterialGetResponse(material.idMaterial(), material.name(), material.unit(), material.quantity()));

        associativesMaterias.remove(select);

    }

    private void addMaterial(List<MaterialGetResponse> materias, List<RequestMaterialRequest> associativesMaterias) {
        getPrinter().printText(" Selecione um material: ");

        getPrinter().printList(materias);

        int select = getReader().readInteger() - 1;

        if(!isValidSelect(materias, select)) throw new RuntimeException("| Opção sem correspondencia");

        getPrinter().printText("Quantidade necessaria: ");
        double quantity = getReader().readDouble();

        associativesMaterias.add(new RequestMaterialRequest(materias.get(select).id(), materias.get(select).name(), quantity, materias.get(select).unit()));
        materias.remove(select);
    }

    private boolean isValidSelect(List<?> itens, int select) {
        return select >= 0 && select < itens.size();
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuCreateRequest(reader, printer, RequestBeans.toInstanceRequest(), MaterialBeans.toInstanceService());
    }


}
