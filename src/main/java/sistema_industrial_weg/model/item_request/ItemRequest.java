package sistema_industrial_weg.model.item_request;

public class ItemRequest {

    private final ItemRequestId itemRequestId;

    private double quantity;

    public ItemRequest(ItemRequestId itemRequestId, double quantity) {
        this.itemRequestId = itemRequestId;
        this.quantity = quantity;
    }

    public ItemRequestId getItemRequestId() {
        return itemRequestId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

}
