package be.ucll.electroman_jeroen;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UsersWithWorkOrders {

    @Embedded public UserEntity userEntity;

    @Relation(parentColumn = "id", entityColumn = "userId", entity = WorkOrderEntity.class)
    List<WorkOrderEntity> workorders;

}
