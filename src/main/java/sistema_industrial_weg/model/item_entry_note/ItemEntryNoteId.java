package sistema_industrial_weg.model.item_entry_note;

public class ItemEntryNoteId {

    private final long entryNoteId;
    private final long materialId;

    public ItemEntryNoteId(long entryNoteId, long materialId) {
        this.entryNoteId = entryNoteId;
        this.materialId = materialId;
    }

    public long getEntryNoteId() {
        return entryNoteId;
    }

    public long getMaterialId() {
        return materialId;
    }

}
