package swd.controller;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import swd.outer.model.Log;
import swd.outer.service.LogService;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/log")
public class LogController {
    private LogService service;

    public LogController(LogService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Log> getLog(
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "size", required = false) String size)
    {
        if (id == null)
        {
            if (start == null && end == null)
            {
                if (type == null)
                {
                    if (position == null && size == null)
                    {
                        return service.svcGetLog();
                    }
                    else
                    {
                        Integer positionObject = new Integer(position);
                        Integer sizeObject = new Integer(size);

                        return service.svcGetLogPaging(positionObject - 1, sizeObject);
                    }
                }
                else
                {
                    Boolean typeObject = Boolean.valueOf(type);
                    return service.svcGetLogByType(typeObject);
                }
            }
            else
            {
                if (type == null)
                {
                    return service.svcGetLogByTimestamp(start, end);
                }
                else
                {
                    Boolean typeObject = Boolean.valueOf(type);
                    return service.svcGetLogByTypeAndTimestamp(typeObject, start, end);
                }
            }
        }
        else
        {
            if (start == null && end == null)
            {
                return service.svcGetLogById(id);
            }
            else
            {
                return service.svcGetLogByIdAndTimestamp(id, start, end);
            }
        }
    }

    @GetMapping(value = "/total", produces = MediaType.APPLICATION_JSON_VALUE)
    public int getTotalOfType(@RequestParam(value = "type") boolean type)
    {
        return service.svcGetTotalOfType(type);
    }
}
