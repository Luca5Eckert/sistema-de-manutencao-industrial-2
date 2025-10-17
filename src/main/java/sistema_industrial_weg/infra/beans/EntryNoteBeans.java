package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.infra.dao.EntryNoteDao;
import sistema_industrial_weg.repository.EntryNoteRepository;
import sistema_industrial_weg.service.entry_note.EntryNoteMapper;
import sistema_industrial_weg.service.entry_note.EntryNoteService;

public class EntryNoteBeans {

    private final static EntryNoteDao ENTRY_NOTE_DAO = new EntryNoteDao();
    private final static EntryNoteMapper ENTRY_NOTE_MAPPER = new EntryNoteMapper();
    private final static EntryNoteRepository ENTRY_NOTE_REPOSITORY = new EntryNoteRepository(ENTRY_NOTE_DAO);
    private final static EntryNoteService ENTRY_NOTE_SERVICE = new EntryNoteService(ENTRY_NOTE_MAPPER, ENTRY_NOTE_REPOSITORY, MaterialBeans.toInstanceRepository(), ProviderBeans.toInstanceRepository(), ItemEntryNoteBeans.toInstanceRepository());

    public static EntryNoteService toInstanceService() {
        return ENTRY_NOTE_SERVICE;
    }

}
