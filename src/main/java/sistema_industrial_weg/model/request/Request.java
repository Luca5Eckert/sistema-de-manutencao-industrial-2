package sistema_industrial_weg.model.request;

import java.time.LocalDate;

public class Request {

    private final long id;

    private String sector;
    private final LocalDate requestDate;

    public Request(String sector, LocalDate requestDate) {
        this.id = -1;
        this.sector = sector;
        this.requestDate = requestDate;
    }

    public Request(long id, String sector, LocalDate requestDate) {
        this.id = id;
        this.sector = sector;
        this.requestDate = requestDate;
    }

    public long getId() {
        return id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

}
