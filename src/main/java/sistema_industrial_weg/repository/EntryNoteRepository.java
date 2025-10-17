package sistema_industrial_weg.repository;

import sistema_industrial_weg.infra.dao.EntryNoteDao;
import sistema_industrial_weg.model.entry_note.EntryNote;

public class EntryNoteRepository {

    private final EntryNoteDao entryNoteDao;

    public EntryNoteRepository(EntryNoteDao entryNoteDao) {
        this.entryNoteDao = entryNoteDao;
    }


    public void create(EntryNote entryNote) {
        entryNoteDao.create(entryNote);
    }
}
