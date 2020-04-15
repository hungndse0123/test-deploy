package swd.inner.mapper;

import org.apache.ibatis.annotations.*;
import swd.inner.model.LogRS;

import java.util.List;

@Mapper
public interface LogMapper {

    @Select("SELECT e.e_id, e.e_recorded_device, de.d_name, d.d_type, d.d_value, e.e_recorded_value, e.e_timestamp " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "ORDER BY e.e_timestamp DESC ")
    @Results({
            @Result(column = "e_id", property = "entryId"),
            @Result(column = "e_recorded_device", property = "deviceId"),
            @Result(column = "d_name", property = "deviceName"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "defaultValue"),
            @Result(column = "e_recorded_value", property = "recordedValue"),
            @Result(column = "e_timestamp", property = "timestamp")
    })
    List<LogRS> getLog();

    @Select("SELECT e.e_id, e.e_recorded_device, de.d_name, d.d_type, d.d_value, e.e_recorded_value, e.e_timestamp " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "WHERE e.e_timestamp >= #{start} AND e.e_timestamp <= #{end} " +
            "ORDER BY e.e_timestamp DESC ")
    @Results({
            @Result(column = "e_id", property = "entryId"),
            @Result(column = "e_recorded_device", property = "deviceId"),
            @Result(column = "d_name", property = "deviceName"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "defaultValue"),
            @Result(column = "e_recorded_value", property = "recordedValue"),
            @Result(column = "e_timestamp", property = "timestamp")
    })
    List<LogRS> getLogByTimestamp(
            @Param("start") String start,
            @Param("end") String end);

    @Select("SELECT e.e_id, e.e_recorded_device, de.d_name, d.d_type, d.d_value, e.e_recorded_value, e.e_timestamp " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "WHERE d.d_type = #{type} " +
            "ORDER BY e.e_timestamp DESC ")
    @Results({
            @Result(column = "e_id", property = "entryId"),
            @Result(column = "e_recorded_device", property = "deviceId"),
            @Result(column = "d_name", property = "deviceName"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "defaultValue"),
            @Result(column = "e_recorded_value", property = "recordedValue"),
            @Result(column = "e_timestamp", property = "timestamp")
    })
    List<LogRS> getLogByType(
            @Param("type") boolean type);

    @Select("SELECT e.e_id, e.e_recorded_device, de.d_name, d.d_type, d.d_value, e.e_recorded_value, e.e_timestamp " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "WHERE d.d_type = #{type} AND e.e_timestamp >= #{start} AND e.e_timestamp <= #{end} " +
            "ORDER BY e.e_timestamp DESC ")
    @Results({
            @Result(column = "e_id", property = "entryId"),
            @Result(column = "e_recorded_device", property = "deviceId"),
            @Result(column = "d_name", property = "deviceName"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "defaultValue"),
            @Result(column = "e_recorded_value", property = "recordedValue"),
            @Result(column = "e_timestamp", property = "timestamp")
    })
    List<LogRS> getLogByTypeAndTimestamp(
            @Param("type") boolean type,
            @Param("start") String start,
            @Param("end") String end);

    @Select("SELECT e.e_id, e.e_recorded_device, de.d_name, d.d_value, e.e_recorded_value, e.e_timestamp " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "WHERE e.e_recorded_device = #{id} " +
            "ORDER BY e.e_timestamp DESC ")
    @Results({
            @Result(column = "e_id", property = "entryId"),
            @Result(column = "e_recorded_device", property = "deviceId"),
            @Result(column = "d_name", property = "deviceName"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "defaultValue"),
            @Result(column = "e_recorded_value", property = "recordedValue"),
            @Result(column = "e_timestamp", property = "timestamp")
    })
    List<LogRS> getLogById(
            @Param("id") String id);

    @Select("SELECT e.e_id, e.e_recorded_device, de.d_name, d.d_value, e.e_recorded_value, e.e_timestamp " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "WHERE e.e_timestamp >= #{start} AND e.e_timestamp <= #{end} AND e.e_recorded_device = #{id} " +
            "ORDER BY e.e_timestamp DESC ")
    @Results({
            @Result(column = "e_id", property = "entryId"),
            @Result(column = "e_recorded_device", property = "deviceId"),
            @Result(column = "d_name", property = "deviceName"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "defaultValue"),
            @Result(column = "e_recorded_value", property = "recordedValue"),
            @Result(column = "e_timestamp", property = "timestamp")
    })
    List<LogRS> getLogByIdAndTimestamp(
            @Param("id") String id,
            @Param("start") String start,
            @Param("end") String end);

    @Select("SELECT SUM(e.e_recorded_value) " +
            "FROM tbl_log e " +
            "INNER JOIN tbl_devices d ON e.e_recorded_device = d.d_id " +
            "WHERE d.d_type = #{type} ")
    int getTotalOfType(
            @Param("type") boolean type);

    @Delete("DELETE " +
            "FROM tbl_log " +
            "WHERE e_recorded_device = #{id} ")
    int deleteLog(
            @Param("id") String id);
}
