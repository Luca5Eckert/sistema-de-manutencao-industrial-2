package sistema_industrial_weg.dto.request.get;

import sistema_industrial_weg.model.request.enumerator.RequestStatus;

import java.time.LocalDate;

public record RequestGetResponse(long id, String sector, LocalDate requestDate, RequestStatus status) {

    @Override
    public String toString() {
        return sector + "        " + requestDate + "          "   + status;
    }
}
