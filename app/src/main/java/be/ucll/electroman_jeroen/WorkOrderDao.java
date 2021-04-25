package be.ucll.electroman_jeroen;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface WorkOrderDao {

    @Insert
    void registerWorkOrder(WorkOrderEntity workOrderEntity);

    @Update()
    void updateWorkOrderEntity(WorkOrderEntity workOrderEntity);

    @Query("SELECT * FROM workorders WHERE id in(:id)")
    public WorkOrderEntity loadSpecificWorkOrders(Integer id);

    @Query("delete from sqlite_sequence where name='workorders'")
    void deleteSequence();

}
