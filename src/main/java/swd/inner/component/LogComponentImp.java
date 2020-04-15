package swd.inner.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swd.exception.CustomException;
import swd.inner.mapper.LogMapper;
import swd.inner.model.LogRS;

import java.util.List;

@Component
@Transactional(rollbackFor = Exception.class)
public class LogComponentImp implements LogComponent{
    private LogMapper mapper;

    @Autowired
    public LogComponentImp(LogMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<LogRS> cmpGetLog() {
        List<LogRS> log = mapper.getLog();

        if (log.isEmpty())
        {
            throw new CustomException("Log is empty");
        }

        return log;
    }

    @Override
    public List<LogRS> cmpGetLogByTimestamp(String start, String end) {
        List<LogRS> log = mapper.getLogByTimestamp(start, end);

        if (log.isEmpty())
        {
            throw new CustomException("Log is empty");
        }

        return log;
    }

    @Override
    public List<LogRS> cmpGetLogByType(boolean type) {
        List<LogRS> log = mapper.getLogByType(type);

        if (log.isEmpty())
        {
            throw new CustomException("Log is empty");
        }

        return log;
    }

    @Override
    public List<LogRS> cmpGetLogByTypeAndTimestamp(boolean type, String start, String end) {
        List<LogRS> log = mapper.getLogByTypeAndTimestamp(type, start, end);

        if (log.isEmpty())
        {
            throw new CustomException("Log is empty");
        }

        return log;
    }

    @Override
    public List<LogRS> cmpGetLogById(String id) {
        List<LogRS> log = mapper.getLogById(id);

        if (log.isEmpty())
        {
            throw new CustomException("Log is empty");
        }

        return log;
    }

    @Override
    public List<LogRS> cmpGetLogByIdAndTimestamp(String id, String start, String end) {
        List<LogRS> log = mapper.getLogByIdAndTimestamp(id, start, end);

        if (log.isEmpty())
        {
            throw new CustomException("Log is empty");
        }

        return log;
    }

    @Override
    public int cmpGetTotalOfType(boolean type) {
        return mapper.getTotalOfType(type);
    }

    @Override
    public void deleteLog(String id) {
        int i = mapper.deleteLog(id);

        if (i<1)
        {
            throw new CustomException("Delete device failed");
        }
    }
}
