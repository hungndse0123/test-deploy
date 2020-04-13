package swd.inner.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swd.exception.CustomException;
import swd.inner.mapper.DeviceMapper;
import swd.inner.model.DeviceRS;

import java.util.List;

@Component
@Transactional(rollbackFor = Exception.class)
public class DeviceComponentImp implements DeviceComponent{
    private DeviceMapper mapper;

    @Autowired
    public DeviceComponentImp(DeviceMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public DeviceRS cmpGetDevice(String id) {
        DeviceRS device = mapper.getDevice(id);

        if (device == null)
        {
            throw new CustomException("Device does not exist");
        }

        return device;
    }

    @Override
    public List<DeviceRS> cmpGetDevices() {
        List<DeviceRS> devices = mapper.getDevices();

        if (devices.isEmpty())
        {
            throw new CustomException("Device list is empty");
        }

        return devices;
    }

    @Override
    public void cmpAddDevice(String id, boolean type, float value) {
        int i = mapper.addDevice(id, type, value);

        if (i<1)
        {
            throw new CustomException("Add new device failed");
        }
    }

    @Override
    public void cmpAdddDetail(String id, String name) {
        int i = mapper.addDetail(id, name);

        if (i<1)
        {
            cmpDeleteDevice(id);
            throw new CustomException("Add new device detail failed");
        }
    }

    @Override
    public void cmpUpdateDevice(String id, float value) {
        int i = mapper.updateDevice(id, value);

        if (i<1)
        {
            throw new CustomException("Update device failed");
        }
    }

    @Override
    public void cmpUpdateDetail(String id, String name) {
        int i = mapper.updateDetail(id, name);

        if (i<1)
        {
            throw new CustomException("Update device detail failed");
        }
    }

    @Override
    public void cmpDeleteDevice(String id) {
        int i = mapper.deleteDevice(id);

        if (i<1)
        {
            throw new CustomException("Delete device failed");
        }
    }

    @Override
    public void cmpDeleteDetail(String id) {
        int i = mapper.deleteDetail(id);

        if (i<1)
        {
            throw new CustomException("Delete device failed");
        }
    }
}
