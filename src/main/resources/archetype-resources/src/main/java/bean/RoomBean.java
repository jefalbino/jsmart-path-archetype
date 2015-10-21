package ${package}.bean;

import ${package}.util.RoomUtils;
import ${package}.service.SpringService;

import com.jsmartframework.web.annotation.WebBean;
import com.jsmartframework.web.manager.WebContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import javax.inject.Inject;

@WebBean
public class RoomBean {

    @Autowired
    private SpringService springService;

    private String message;

    private String roomName;

    @PostConstruct
    public void init() {
        roomName = WebContext.getRequest().getPathInfo();

        if (StringUtils.isNotBlank(roomName)) {
            roomName = roomName.replace("/", "");
        }

        // Generate room name case not specified
        if (StringUtils.isBlank(roomName)) {
            roomName = RoomUtils.getShortName();
            WebContext.redirectTo("/room/" + roomName);
        }

        // Add alert info form room link on page
        WebContext.addInfo("room-link", getServerUrl() + "room/" + roomName);
    }

    public void addMessage() {
        if (StringUtils.isNotBlank(message)) {
            springService.putMessage(roomName, message);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServerUrl() {
        HttpServletRequest request =  WebContext.getRequest();
        return request.getScheme() + "://" + request.getServerName() + getServerPort(request) + "/";
    }

    private String getServerPort(HttpServletRequest request) {
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            return ":" + request.getServerPort();
        }
        return "";
    }
}