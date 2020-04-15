package swd.outer.service;

import swd.outer.model.Log;

import java.util.List;

public interface LogService {
    List<Log> svcGetLog();
    List<Log> svcGetLogByTimestamp(String start, String end);
    List<Log> svcGetLogByType(boolean type);
    List<Log> svcGetLogByTypeAndTimestamp(boolean type, String start, String end);
    List<Log> svcGetLogById(String id);
    List<Log> svcGetLogByIdAndTimestamp(String id, String start, String end);
    int svcGetTotalOfType(boolean type);
}
