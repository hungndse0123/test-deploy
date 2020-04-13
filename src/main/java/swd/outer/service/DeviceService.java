package swd.outer.service;

import swd.outer.model.Device;

import java.util.List;

public interface DeviceService {
    Device svcGetDevice(String id);
    List<Device> svcGetDevices();
    void svcAddDevice(String id, String name, boolean type, float value);
    void svcUpdateDevice(String id, String name, float value);
    void svcDeleteDevice(String id);
}
