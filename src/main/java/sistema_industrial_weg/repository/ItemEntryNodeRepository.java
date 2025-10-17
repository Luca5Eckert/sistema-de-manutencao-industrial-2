package sistema_industrial_weg.repository;

import sistema_industrial_weg.infra.dao.ItemEntryNoteDao;
import sistema_industrial_weg.model.item_entry_note.ItemEntryNote;

import java.util.List;

public class ItemEntryNodeRepository {

    private final ItemEntryNoteDao itemEntryNoteDao;

    public ItemEntryNodeRepository(ItemEntryNoteDao itemEntryNoteDao) {
        this.itemEntryNoteDao = itemEntryNoteDao;
    }

    public void createAll(List<ItemEntryNote> itensEntryNote, long id) {
        itemEntryNoteDao.createAll(itensEntryNote, id);
    }
}
