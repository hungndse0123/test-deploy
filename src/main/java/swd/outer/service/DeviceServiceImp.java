package swd.outer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.inner.component.DeviceComponent;
import swd.inner.model.DeviceRS;
import swd.outer.model.Device;
import swd.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {
    private DeviceComponent component;

    @Autowired
    public DeviceServiceImp(DeviceComponent component) {
        this.component = component;
    }


    @Override
    public Device svcGetDevice(String id) {
        Device device = new Device();
        DeviceRS deviceRS = component.cmpGetDevice(id);

        Utilities.copyNonNullProperties(deviceRS, device);

        return device;
    }

    @Override
    public List<Device> svcGetDevices() {
        List<Device> svcList = new ArrayList<>();
        List<DeviceRS> cmpList = component.cmpGetDevices();

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
        component.cmpAddDevice(id, type, value);
        component.cmpAdddDetail(id, name);
    }

    @Override
    public void svcUpdateDevice(String id, String name, float value) {
        component.cmpUpdateDetail(id, name);
        component.cmpUpdateDevice(id, value);
    }

    @Override
    public void svcDeleteDevice(String id) {
        component.cmpDeleteDetail(id);
        component.cmpDeleteDevice(id);
    }
}
