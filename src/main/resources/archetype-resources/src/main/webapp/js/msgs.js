var source;

$(document).ready(function() {
    source = new EventSource(window.location.pathname.replace('room', 'msgs'));
    source.addEventListener('msg-event', updateRoomMessage, false);
});

$(window).on("beforeunload", function() {
    if (source) {
        source.close();
    }
});

function sendRoomMessage(event) {
    if (event.keyCode == 13) {
        $('#msg-btn').click();
    }
}

function roomMessageSent() {
    $('#msg-input').val('');
}

function updateRoomMessage(event) {
    var room = $('#room-msgs');
    var msgs = jQuery.parseJSON(event.data);

    for (var i = 0; i < msgs.length; i++) {
        var span = room.find('div[id="msg-' + i + '"]');

        if (!span || span.length == 0) {
            room.append($('<div id="msg-' + i + '"></div>').text(msgs[i]));

            // Scroll div to bottom
            $('#room-msgs').animate({scrollTop: $('#room-msgs')[0].scrollHeight}, 1000);
        }
    }
}
