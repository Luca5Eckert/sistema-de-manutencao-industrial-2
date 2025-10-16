package sistema_industrial_weg.model.item_entry_note;

import java.time.LocalDateTime;

public class ItemEntryNote {

    private final ItemEntryNoteId itemEntryNoteId;

    private double quantity;

    public ItemEntryNote(ItemEntryNoteId itemEntryNoteId) {
        this.itemEntryNoteId = itemEntryNoteId;
    }

    public ItemEntryNoteId getItemEntryNoteId() {
        return itemEntryNoteId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
