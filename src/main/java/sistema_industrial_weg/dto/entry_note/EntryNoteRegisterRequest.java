package sistema_industrial_weg.dto.entry_note;

import sistema_industrial_weg.dto.entry_note_item.ItemEntryNoteRequest;

import java.util.List;

public record EntryNoteRegisterRequest(long idProvider, List<ItemEntryNoteRequest> materialsId) {
}
