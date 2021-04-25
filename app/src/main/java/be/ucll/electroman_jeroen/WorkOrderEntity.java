package be.ucll.electroman_jeroen;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "workorders")
public class WorkOrderEntity {

    @PrimaryKey(autoGenerate = true)
    Integer id;

    Integer userId;

    @ColumnInfo(name = "city")
    String city;

    @ColumnInfo(name = "device")
    String device;

    @ColumnInfo(name = "problemcode")
    String problemCode;

    @ColumnInfo(name = "customername")
    String customerName;

    @ColumnInfo(name = "processed")
    Boolean processed;

    @ColumnInfo(name = "detailedproblemdescription")
    String detailedProblemDescription;

    @ColumnInfo(name = "repairinformation")
    String repairInformation;

    public WorkOrderEntity(int userId, String city, String device, String problemCode, String customerName, Boolean processed, String detailedProblemDescription, String repairInformation) {
        this.userId = userId;
        this.city = city;
        this.device = device;
        this.problemCode = problemCode;
        this.customerName = customerName;
        this.processed = processed;
        this.detailedProblemDescription = detailedProblemDescription;
        this.repairInformation = repairInformation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getProblemCode() {
        return problemCode;
    }

    public void setProblemCode(String problemCode) {
        this.problemCode = problemCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public String getDetailedProblemDescription() {
        return detailedProblemDescription;
    }

    public void setDetailedProblemDescription(String detailedProblemDescription) {
        this.detailedProblemDescription = detailedProblemDescription;
    }

    public String getRepairInformation() {
        return repairInformation;
    }

    public void setRepairInformation(String repairInformation) {
        this.repairInformation = repairInformation;
    }
}
