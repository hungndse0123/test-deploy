package swd.inner.component;

import swd.inner.model.DeviceRS;

import java.util.List;

public interface DeviceComponent {
    DeviceRS cmpGetDevice(String id);
    List<DeviceRS> cmpGetDevices();
    void cmpAddDevice(String id, boolean type, float value);
    void cmpAdddDetail(String id, String name);
    void cmpUpdateDevice(String id, float value);
    void cmpUpdateDetail(String id, String name);
    void cmpDeleteDevice(String id);
    void cmpDeleteDetail(String id);
}
