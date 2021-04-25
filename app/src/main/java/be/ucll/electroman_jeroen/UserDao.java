package be.ucll.electroman_jeroen;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE username IN(:username) AND password IN(:password)")
    List<UserEntity> checkLoginAttempt(String username, String password);

    @Query("SELECT * FROM users WHERE id in(:id)")
    public UsersWithWorkOrders loadSpecificUserWithWorkOrders(Integer id);

    @Query("SELECT * FROM users")
    public List<UsersWithWorkOrders> loadUsersWithWorkOrders();

    //Id resetten
    @Query("delete from sqlite_sequence where name='users'")
    void deleteSequence();

}
