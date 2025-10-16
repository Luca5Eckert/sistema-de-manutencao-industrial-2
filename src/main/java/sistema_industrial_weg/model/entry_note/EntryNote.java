package sistema_industrial_weg.model.entry_note;

import java.time.LocalDate;

public class EntryNote {

    private final long id;

    private final long providerId;
    private final LocalDate entryDate;

    public EntryNote(long providerId, LocalDate entryDate) {
        this.id = -1;
        this.providerId = providerId;
        this.entryDate = entryDate;
    }

    public EntryNote(long id, long providerId, LocalDate entryDate) {
        this.id = id;
        this.providerId = providerId;
        this.entryDate = entryDate;
    }

    public long getId() {
        return id;
    }

    public long getProviderId() {
        return providerId;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

}
