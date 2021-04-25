package be.ucll.electroman_jeroen;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class, WorkOrderEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static final String dbName = "user";
    private static AppDatabase appDatabase;

    public static synchronized AppDatabase getUserDatabase(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }
    public abstract UserDao userDao();
    public abstract WorkOrderDao workOrderDao();

    protected void populateDB(){
        this.clearAllTables();
        UserDao userDao = appDatabase.userDao();
        WorkOrderDao workOrderDao = appDatabase.workOrderDao();

        //Id's resetten van de entities
        userDao.deleteSequence();
        workOrderDao.deleteSequence();

        UserEntity userEntity1 = new UserEntity("jvdr", "ucll", "Jeroen", "Van den Rul");
        WorkOrderEntity workOrderEntity1 = new WorkOrderEntity(1,"Herselt", "Computer", "C1405", "Jef", false, "Elektronica van moederbord kapot.", "");
        WorkOrderEntity workOrderEntity2 = new WorkOrderEntity(1,"Aarschot", "Wasmachine", "W1478", "Lisa", false, "Filter van wateropvangbak verstopt.", "");
        WorkOrderEntity workOrderEntity3 = new WorkOrderEntity(1,"Rotselaar", "Grasmaaier", "G4532", "Paul", true, "Grasmaaier start niet meer.", "Nieuwe bougie + benzine");
        WorkOrderEntity workOrderEntity4 = new WorkOrderEntity(1,"Langdorp", "Strijkijzer", "S6542", "Elien", false, "Strijkijzer kan niet meer stomen.", "");
        WorkOrderEntity workOrderEntity5 = new WorkOrderEntity(1,"Gelrode", "Oven", "O3547", "Peter", true, "Licht oven kapot", "Lampje vervangen");

        userDao.registerUser(userEntity1);
        workOrderDao.registerWorkOrder(workOrderEntity1);
        workOrderDao.registerWorkOrder(workOrderEntity2);
        workOrderDao.registerWorkOrder(workOrderEntity3);
        workOrderDao.registerWorkOrder(workOrderEntity4);
        workOrderDao.registerWorkOrder(workOrderEntity5);


    }
}
