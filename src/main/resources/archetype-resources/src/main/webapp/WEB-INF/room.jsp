<%@ taglib prefix="sm" uri="http://jsmartframework.com/v2/jsp/taglib/jsmart" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <title>JSmart Framework - Dynamic Path Archetype</title>
    </head>

    <body class="container">
        <div class="col-md-6" align="center" style="margin: 30px 25%;">

            <sm:alert id="room-link" dismissible="false" styleClass="room-link">
                <sm:header title="@{texts.path.archetype.room.link}">
                    <sm:icon name="" />
                </sm:header>

                <!-- Carry the room name -->
            </sm:alert>

            <div id="room-msgs">
                <!-- It will carry room messages -->
            </div>

            <form onSubmit="return false;">
                <sm:input id="msg-input" label="@{texts.path.archetype.room.msg}"
                        value="@{roomBean.message}" rightAddOn="msg-btn" onKeyUp="sendRoomMessage(event);">

                    <sm:button id="msg-btn" ajax="true" label="@{texts.path.archetype.send.msg}"
                            action="@{roomBean.addMessage}" onSuccess="roomMessageSent();" />
                </sm:input>
            </form>

        </div>
    </body>

     <!-- Handle callbacks and async events -->
    <script type="text/javascript" src="/js/msgs.js"></script>
</html>
