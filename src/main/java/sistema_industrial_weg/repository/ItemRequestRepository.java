package sistema_industrial_weg.repository;

import sistema_industrial_weg.infra.dao.ItemRequestDao;
import sistema_industrial_weg.model.item_request.ItemRequest;

import java.util.List;

public class ItemRequestRepository {

    private final ItemRequestDao itemRequestDao;

    public ItemRequestRepository(ItemRequestDao itemRequestDao) {
        this.itemRequestDao = itemRequestDao;
    }

    public void saveAll(long requestId, List<ItemRequest> listItemRequest) {
        itemRequestDao.saveAll(requestId, listItemRequest);
    }
}
