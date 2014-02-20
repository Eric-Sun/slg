package com.h13.slg.config.cache;

import com.h13.slg.config.BasicCache;
import com.h13.slg.config.ConfigParseException;
import com.h13.slg.config.XmlParser;
import com.h13.slg.config.co.GlobalCO;
import com.h13.slg.config.co.GlobalXMLCO;
import com.h13.slg.config.co.TaskCO;
import com.h13.slg.config.co.TaskXMLCO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午4:55
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TaskCache extends BasicCache<TaskCO> {

    private static final String KEY = "slg:sys:task:";

    @Resource(name = "taskTemplate")
    private RedisTemplate<String, TaskCO> taskTemplate;

    @Override
    public void doLoad(String filename) throws ConfigParseException {
        XmlParser<TaskXMLCO> parser = new XmlParser<TaskXMLCO>(TaskXMLCO.class,
                filename);
        TaskXMLCO obj = parser.parse();
        for (String id : obj.getMap().keySet()) {
            taskTemplate.opsForValue().set(KEY + id, obj.getMap().get(id));
        }
    }


    @Override
    public TaskCO get(String id) {
        return taskTemplate.opsForValue().get(KEY + id);
    }
}
