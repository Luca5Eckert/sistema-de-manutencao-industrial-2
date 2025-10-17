package sistema_industrial_weg.service.entry_note;

import sistema_industrial_weg.dto.entry_note_item.ItemEntryNoteRequest;
import sistema_industrial_weg.model.item_entry_note.ItemEntryNote;
import sistema_industrial_weg.model.item_entry_note.ItemEntryNoteId;

public class EntryNoteMapper {
    public ItemEntryNote toEntity(ItemEntryNoteRequest itemEntryNoteRequest) {
        ItemEntryNoteId itemEntryNoteId = new ItemEntryNoteId(itemEntryNoteRequest.idMaterial());
        return new ItemEntryNote(itemEntryNoteId, itemEntryNoteRequest.quantity());
    }
}
