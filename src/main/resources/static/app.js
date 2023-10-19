var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/stomp-endpoint'); // #1 socket열기(server와 연결)
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) { // #2'/topic/greetings'을 구독
            showGreeting(JSON.parse(greeting.body)); // #2-2 '/topic/greetings'에서 받은 메세지를 분해해서 보여주기
        });
    });
}

// # 2-2
function showGreeting(message) {

    $("#greetings").append("<tr><td>" + message.name + " : " + message.message + "</td></tr>");
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// #4 메시지 보내기
function sendName() {

    const obj = {
        'name' : $("#name").val(),
        'content' : $("#content").val()
    }

    stompClient.send("/app/hello", {}, JSON.stringify(obj));
}



$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
