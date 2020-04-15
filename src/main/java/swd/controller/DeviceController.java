package swd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import swd.outer.model.Device;
import swd.outer.model.DeviceInput;
import swd.outer.service.DeviceService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/devices")
public class DeviceController {
    private DeviceService service;

    @Autowired
    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Device getDevice(@PathVariable("id") String id)
    {
        return service.svcGetDevice(id);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getDevices()
    {
        return service.svcGetDevices();
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addNewDevice(@RequestBody DeviceInput input)
    {
        service.svcAddDevice(input.getId(), input.getName(), input.isType(), input.getValue());
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateDevice(@PathVariable("id") String id, @RequestBody DeviceInput input)
    {
        service.svcUpdateDevice(id, input.getName(), input.getValue());
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeDevice(@PathVariable("id") String id)
    {
        service.svcDeleteDevice(id);
    }

    @GetMapping(value = "/top", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Device> getMostUsedDevice(@RequestParam("type") boolean type)
    {
        return service.svcGetDevicesTop(type);
    }
}
