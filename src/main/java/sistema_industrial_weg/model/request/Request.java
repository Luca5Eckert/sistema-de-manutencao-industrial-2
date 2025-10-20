package sistema_industrial_weg.model.request;

import sistema_industrial_weg.model.request.enumerator.RequestStatus;

import java.time.LocalDate;

public class Request {

    private long id;

    private String sector;
    private final LocalDate requestDate;
    private RequestStatus status;

    public Request(String sector, LocalDate requestDate, RequestStatus status) {
        this.id = -1;
        this.sector = sector;
        this.requestDate = requestDate;
        this.status = status;
    }

    public Request(long id, String sector, LocalDate requestDate, RequestStatus status) {
        this.id = id;
        this.sector = sector;
        this.requestDate = requestDate;
        this.status = status;
    }

    public void setId(long id) {
        this.id = id;
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
