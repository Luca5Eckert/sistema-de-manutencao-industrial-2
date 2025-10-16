package sistema_industrial_weg.model.item_request;

public class ItemRequestId {

    private final long requestId;
    private final long materialId;

    public ItemRequestId(long requestId, long materialId) {
        this.requestId = requestId;
        this.materialId = materialId;
    }

    public long getRequestId() {
        return requestId;
    }

    public long getMaterialId() {
        return materialId;
    }

}
