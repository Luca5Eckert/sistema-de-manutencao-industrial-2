package sistema_industrial_weg.view.menu.entry_note;

import sistema_industrial_weg.dto.entry_note.EntryNoteRegisterRequest;
import sistema_industrial_weg.dto.entry_note_item.ItemEntryNoteRequest;
import sistema_industrial_weg.dto.material.MaterialGetResponse;
import sistema_industrial_weg.infra.beans.EntryNoteBeans;
import sistema_industrial_weg.infra.beans.MaterialBeans;
import sistema_industrial_weg.infra.beans.ProviderBeans;
import sistema_industrial_weg.service.entry_note.EntryNoteService;
import sistema_industrial_weg.service.material.MaterialService;
import sistema_industrial_weg.service.provider.ProviderService;
import sistema_industrial_weg.view.Printer;
import sistema_industrial_weg.view.Reader;
import sistema_industrial_weg.view.menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRegisterEntryNote extends Menu {

    private final EntryNoteService entryNoteService;
    private final ProviderService providerService;
    private final MaterialService materialService;

    protected MenuRegisterEntryNote(Reader reader, Printer printer, EntryNoteService entryNoteService, ProviderService providerService, MaterialService materialService) {
        super(reader, printer);
        this.entryNoteService = entryNoteService;
        this.providerService = providerService;
        this.materialService = materialService;
    }


    @Override
    public void execute() {
        var request = callMenu();

        entryNoteService.register(request);

        getPrinter().printText("| Material adicionado com sucesso");

    }

    private EntryNoteRegisterRequest callMenu() {
        getPrinter().printTitle("Cadastrar nota de entrada");

        getPrinter().printText("Digite as informações do nota de entrada: ( 0 para cancelar )");

        getPrinter().printText("Fornecedor: ");
        var provider = getProvider();

        var materiais = getMaterias();

        return new EntryNoteRegisterRequest(provider, materiais);
    }

    private long getProvider() {
        var providers = providerService.getAll();

        getPrinter().printList(providers);

        int select = getReader().readInteger() - 1;

        if(!isValidSelect(providers, select)) throw new RuntimeException("| Opção sem correspondencia");

        return providers.get(select).id();
    }

    private List<ItemEntryNoteRequest> getMaterias() {
        var materias = materialService.getAll();
        var associativesMaterias = new ArrayList<ItemEntryNoteRequest>();

        while(true) {
            getPrinter().printText(" Material: ");
            getPrinter().printText(" 1 - Adicionar ");
            getPrinter().printText(" 2 - Visualizar atuais");
            getPrinter().printText(" 3 - Enviar");

            var text = getReader().readLine();

            switch(text.toUpperCase().trim()){
                case "1" -> addMaterial(materias, associativesMaterias);
                case "2" -> seeMaterial(associativesMaterias);
                case "3" -> {
                    return associativesMaterias;
                }
            }
        }

    }

    private void seeMaterial(ArrayList<ItemEntryNoteRequest> associativesMaterias) {
        System.out.println("Selecione um material para remover: ( 0 para continuar ) ");

        getPrinter().printList(associativesMaterias);

        int select = getReader().readInteger() - 1;

        if(select == -1) return;

        if(!isValidSelect(associativesMaterias, select)) throw new RuntimeException("| Opção sem correspondencia");

        associativesMaterias.remove(select);

    }

    private void addMaterial(List<MaterialGetResponse> materias, ArrayList<ItemEntryNoteRequest> associativesMaterias) {
        getPrinter().printText(" Selecione um material: ");

        getPrinter().printList(materias);

        int select = getReader().readInteger() - 1;

        if(!isValidSelect(materias, select)) throw new RuntimeException("| Opção sem correspondencia");

        getPrinter().printText("Quantidade necessaria: ");
        double quantity = getReader().readDouble();

        associativesMaterias.add(new ItemEntryNoteRequest(materias.get(select).id(), quantity));
        materias.remove(select);
    }

    private boolean isValidSelect(List<?> itens, int select) {
        return select >= 0 && select < itens.size();
    }

    public static Menu toInstance(Reader reader, Printer printer) {
        return new MenuRegisterEntryNote(reader, printer, EntryNoteBeans.toInstanceService(), ProviderBeans.toInstanceService(), MaterialBeans.toInstanceService());
    }
}
