package ${package}.async;

import ${package}.service.SpringService;

import com.google.gson.Gson;

import com.jsmartframework.web.annotation.AsyncBean;
import com.jsmartframework.web.listener.WebAsyncListener;
import com.jsmartframework.web.manager.WebContext;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;

@AsyncBean("/msgs/*")
public class RoomAsync implements WebAsyncListener {

    private static final Gson GSON = new Gson();

    @Autowired
    private SpringService springService;

    private volatile boolean finished = false;

    @Override
    public void asyncContextCreated(final AsyncContext asyncContext) {
        asyncContext.setTimeout(300000);

        asyncContext.start(new Runnable() {

            @Override
            public void run() {
                try {
                    String roomName = ((HttpServletRequest) asyncContext.getRequest()).getPathInfo();
                    roomName = roomName.substring(roomName.lastIndexOf("/") + 1);

                    while (!finished) {
                        List<String> messages = springService.getMessages(roomName);

                        WebContext.writeResponseAsEventStream(asyncContext, "msg-event", GSON.toJson(messages));

                        Thread.sleep(1000);
                    }
                } catch (IOException | InterruptedException e) {
                    asyncContext.complete();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void asyncContextDestroyed(AsyncContext asyncContext, Reason reason) {
        if (reason == Reason.TIMEOUT) {
            asyncContext.complete();
        }
        finished = true;
    }
}
