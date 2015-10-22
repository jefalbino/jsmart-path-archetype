package ${package}.service;

import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class SpringService {

    private Map<String, List<String>> rooms = new ConcurrentHashMap<>();

    public void putMessage(final String roomName, final String message) {
        List<String> messages = rooms.get(roomName);

        if (messages == null) {
            messages = new ArrayList<>();
            rooms.put(roomName, messages);
        }
        messages.add(message);
    }

    public List<String> getMessages(final String roomName) {
        if (rooms.containsKey(roomName)) {
            return rooms.get(roomName);
        }
        return Collections.emptyList();
    }

}
