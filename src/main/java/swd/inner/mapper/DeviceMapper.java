package swd.inner.mapper;

import org.apache.ibatis.annotations.*;
import swd.inner.model.DeviceRS;

import java.util.List;

@Mapper
public interface DeviceMapper {

    @Select("SELECT d.d_id, de.d_name, d.d_type, d.d_value, de.d_status, de.d_online, de.d_duration " +
            "FROM tbl_devices d " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id " +
            "WHERE d.d_id = #{id} ")
    @Results({
        @Result(column = "d_id", property = "id"),
        @Result(column = "d_name", property = "name"),
        @Result(column = "d_type", property = "type"),
        @Result(column = "d_value", property = "value"),
        @Result(column = "d_status", property = "status"),
        @Result(column = "d_online", property = "online"),
        @Result(column = "d_duration", property = "duration")
    })
    DeviceRS getDevice(@Param("id") String id);

    @Select("SELECT d.d_id, de.d_name, d.d_type, d.d_value, de.d_status, de.d_online, de.d_duration " +
            "FROM tbl_devices d " +
            "INNER JOIN tbl_detail de ON d.d_id = de.d_id ")
    @Results({
            @Result(column = "d_id", property = "id"),
            @Result(column = "d_name", property = "name"),
            @Result(column = "d_type", property = "type"),
            @Result(column = "d_value", property = "value"),
            @Result(column = "d_status", property = "status"),
            @Result(column = "d_online", property = "online"),
            @Result(column = "d_duration", property = "duration")
    })
    List<DeviceRS> getDevices();

    @Insert("INSERT INTO tbl_devices (d_id, d_type, d_value) " +
            "VALUES (#{id}, #{type}, #{value}) ")
    int addDevice(
            @Param("id") String id,
            @Param("type") boolean type,
            @Param("value") float value);

    @Insert("INSERT INTO tbl_detail (d_id, d_name, d_status, d_online, d_duration) " +
            "VALUES (#{id}, #{name}, 0, 0, 0) ")
    int addDetail(
            @Param("id") String id,
            @Param("name") String name);

    @Update("UPDATE tbl_devices " +
            "SET d_value = #{value} " +
            "WHERE d_id = #{id} ")
    int updateDevice(
            @Param("id") String id,
            @Param("value") float value);

    @Update("UPDATE tbl_detail " +
            "SET d_name = #{name} " +
            "WHERE d_id = #{id} ")
    int updateDetail(
            @Param("id") String id,
            @Param("name") String name);

    @Delete("DELETE " +
            "FROM tbl_devices " +
            "WHERE d_id = #{id} ")
    int deleteDevice(
            @Param("id") String id);

    @Delete("DELETE " +
            "FROM tbl_detail " +
            "WHERE d_id = #{id} ")
    int deleteDetail(
            @Param("id") String id);
}
