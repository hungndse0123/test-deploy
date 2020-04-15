package swd.inner.component;

import swd.inner.model.LogRS;

import java.util.List;

public interface LogComponent {
    List<LogRS> cmpGetLog();
    List<LogRS> cmpGetLogByTimestamp(String start, String end);
    List<LogRS> cmpGetLogByType(boolean type);
    List<LogRS> cmpGetLogByTypeAndTimestamp(boolean type, String start, String end);
    List<LogRS> cmpGetLogById(String id);
    List<LogRS> cmpGetLogByIdAndTimestamp(String id, String start, String end);
    List<LogRS> cmpGetLogPaging(int position, int size);
    int cmpGetTotalOfType(boolean type);
    void deleteLog(String id);
}
