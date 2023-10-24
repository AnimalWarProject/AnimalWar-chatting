var stompClient = null;
let id = null;
let nickName = null;
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
        'name' : nickName != null ? nickName : "GUEST",
        'content' : $("#content").val()
    }

    stompClient.send("/app/hello", {}, JSON.stringify(obj));
}

function parseToken() {
    const url = 'http://localhost:8080/api/v1/user/me';
    const token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJuaWNrTmFtZSI6Imdvb2Rib3kiLCJpZCI6Imp1bmtpIiwic3ViIjoianVua2kiLCJleHAiOjE2OTc3ODk1MzR9.xbClRoOdXXEeI8k2AfbpUE2jsaLX_zT6JUdJ1WD-e3k';


    fetch(url, {
        method: 'GET',
        headers: {
            'Authorization': token,
            'Content-Type': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            console.log('서버 응답:', data);
            id = data.id;
            nickName = data.nickName;
        })
        .catch(error => {
            console.error('에러 발생:', error);
        });
}

$(function () {
    parseToken(); //
    connect(); // 바로 connect.. disconnect없앴음..
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    // $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
