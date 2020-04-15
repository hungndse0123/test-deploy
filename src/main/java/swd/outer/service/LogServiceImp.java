package swd.outer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swd.inner.component.LogComponent;
import swd.inner.model.LogRS;
import swd.outer.model.Log;
import swd.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImp implements LogService{
    private LogComponent component;

    @Autowired
    public LogServiceImp(LogComponent component) {
        this.component = component;
    }


    @Override
    public List<Log> svcGetLog() {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLog();

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public List<Log> svcGetLogByTimestamp(String start, String end) {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLogByTimestamp(start, end);

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public List<Log> svcGetLogByType(boolean type) {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLogByType(type);

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public List<Log> svcGetLogByTypeAndTimestamp(boolean type, String start, String end) {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLogByTypeAndTimestamp(type, start, end);

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public List<Log> svcGetLogById(String id) {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLogById(id);

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public List<Log> svcGetLogByIdAndTimestamp(String id, String start, String end) {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLogByIdAndTimestamp(id, start, end);

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public List<Log> svcGetLogPaging(int position, int size) {
        List<Log> svcList = new ArrayList<>();
        List<LogRS> cmpList = component.cmpGetLogPaging(position, size);

        for (LogRS logRS: cmpList)
        {
            Log log = new Log();
            Utilities.copyNonNullProperties(logRS, log);
            svcList.add(log);
        }

        return svcList;
    }

    @Override
    public int svcGetTotalOfType(boolean type) {
        return component.cmpGetTotalOfType(type);
    }
}
