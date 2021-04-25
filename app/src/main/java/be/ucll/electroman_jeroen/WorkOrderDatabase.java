package be.ucll.electroman_jeroen;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {WorkOrderEntity.class}, version = 1)

public abstract class WorkOrderDatabase extends RoomDatabase {

    private static final String dbName = "workorder";
    private static WorkOrderDatabase workOrderDatabase;

    public static synchronized WorkOrderDatabase getWorkOrderDatabase(Context context){
        if(workOrderDatabase == null){
            workOrderDatabase = Room.databaseBuilder(context, WorkOrderDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return workOrderDatabase;
    }
    public abstract WorkOrderDao workOrderDao();
}
