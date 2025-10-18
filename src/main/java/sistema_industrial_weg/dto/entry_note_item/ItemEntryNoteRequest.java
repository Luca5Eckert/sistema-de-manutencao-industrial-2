package sistema_industrial_weg.dto.entry_note_item;

public record ItemEntryNoteRequest(long idMaterial, String name, double quantity, String unit) {

    @Override
    public String toString() {
        return name + " " + quantity;
    }
}
