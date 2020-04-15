package swd.outer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.inner.component.DeviceComponent;
import swd.inner.component.LogComponent;
import swd.inner.model.DeviceRS;
import swd.outer.model.Device;
import swd.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {
    private DeviceComponent deviceComponent;
    private LogComponent logComponent;

    @Autowired
    public DeviceServiceImp(DeviceComponent deviceComponent, LogComponent logComponent) {
        this.deviceComponent = deviceComponent;
        this.logComponent = logComponent;
    }

    @Override
    public Device svcGetDevice(String id) {
        Device device = new Device();
        DeviceRS deviceRS = deviceComponent.cmpGetDevice(id);

        Utilities.copyNonNullProperties(deviceRS, device);

        return device;
    }

    @Override
    public List<Device> svcGetDevices() {
        List<Device> svcList = new ArrayList<>();
        List<DeviceRS> cmpList = deviceComponent.cmpGetDevices();

        for (DeviceRS deviceRS: cmpList)
        {
            Device device = new Device();
            Utilities.copyNonNullProperties(deviceRS, device);
            svcList.add(device);
        }

        return svcList;
    }

    @Override
    public List<Device> svcGetDevicesTop(boolean type) {
        List<Device> svcList = new ArrayList<>();
        List<DeviceRS> cmpList = deviceComponent.cmpGetDevicesTop(type);

        for (DeviceRS deviceRS: cmpList)
        {
            Device device = new Device();
            Utilities.copyNonNullProperties(deviceRS, device);
            svcList.add(device);
        }

        return svcList;
    }

    @Override
    public void svcAddDevice(String id, String name, boolean type, float value) {
        deviceComponent.cmpAddDevice(id, type, value);
        deviceComponent.cmpAdddDetail(id, name);
    }

    @Override
    public void svcUpdateDevice(String id, String name, float value) {
        deviceComponent.cmpUpdateDetail(id, name);
        deviceComponent.cmpUpdateDevice(id, value);
    }

    @Override
    public void svcDeleteDevice(String id) {
        logComponent.deleteLog(id);
        deviceComponent.cmpDeleteDetail(id);
        deviceComponent.cmpDeleteDevice(id);
    }
}
