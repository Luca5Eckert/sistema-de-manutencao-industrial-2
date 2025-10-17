package sistema_industrial_weg.infra.beans;

import sistema_industrial_weg.infra.dao.ItemEntryNoteDao;
import sistema_industrial_weg.repository.ItemEntryNodeRepository;

public class ItemEntryNoteBeans {
    
    private final static ItemEntryNoteDao ENTRY_NOTE_DAO = new ItemEntryNoteDao();
    private final static ItemEntryNodeRepository ENTRY_NOTE_REPOSITORY = new ItemEntryNodeRepository(ENTRY_NOTE_DAO);

    public static ItemEntryNodeRepository toInstanceRepository() {
        return ENTRY_NOTE_REPOSITORY;
    }
    
}
