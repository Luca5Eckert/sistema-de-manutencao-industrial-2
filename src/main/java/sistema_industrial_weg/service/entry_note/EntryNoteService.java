package sistema_industrial_weg.service.entry_note;

import sistema_industrial_weg.dto.entry_note.EntryNoteRegisterRequest;
import sistema_industrial_weg.dto.entry_note_item.ItemEntryNoteRequest;
import sistema_industrial_weg.dto.material.MaterialRegisterRequest;
import sistema_industrial_weg.model.entry_note.EntryNote;
import sistema_industrial_weg.model.material.Material;
import sistema_industrial_weg.repository.EntryNoteRepository;
import sistema_industrial_weg.repository.ItemEntryNodeRepository;
import sistema_industrial_weg.repository.MaterialRepository;
import sistema_industrial_weg.repository.ProviderRepository;

import java.time.LocalDate;
import java.util.List;

public class EntryNoteService {

    private final EntryNoteMapper entryNoteMapper;

    private final EntryNoteRepository entryNoteRepository;
    private final MaterialRepository materialRepository;
    private final ProviderRepository providerRepository;
    private final ItemEntryNodeRepository itemEntryNodeRepository;

    public EntryNoteService(EntryNoteMapper entryNoteMapper, EntryNoteRepository entryNoteRepository, MaterialRepository materialRepository, ProviderRepository providerRepository, ItemEntryNodeRepository itemEntryNodeRepository) {
        this.entryNoteMapper = entryNoteMapper;
        this.entryNoteRepository = entryNoteRepository;
        this.materialRepository = materialRepository;
        this.providerRepository = providerRepository;
        this.itemEntryNodeRepository = itemEntryNodeRepository;
    }

    public void register(EntryNoteRegisterRequest request) {
        if(request == null) throw new RuntimeException("Requisição não pode ser nula");

        if(!providerRepository.existById(request.idProvider())) throw new RuntimeException("| Fornecedor não existe");
        if(request.materialsId().isEmpty()) throw new RuntimeException("| Preciso conter no minimo um material");

        var materials = materialRepository.getMaterials(request.materialsId().stream().map(ItemEntryNoteRequest::idMaterial).toList());

        if(!validStockMaterials(materials)) throw new RuntimeException("| Estoque de materials invalidos");

        EntryNote entryNote = new EntryNote(request.idProvider(), LocalDate.now());
        entryNoteRepository.create(entryNote);

        var itensEntryNote = request.materialsId().stream().map(entryNoteMapper::toEntity).toList();
        itemEntryNodeRepository.createAll(itensEntryNote, entryNote.getId());

    }

    private boolean validStockMaterials(List<Material> materials) {
        for (Material material : materials) {
            if(material.getStock() < 0 ) return false;
        }
        return true;
    }
}
