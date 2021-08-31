 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/talkHeader.jsp"%>

<body>
<div class="row justify-content-center">
    <div class="chat_window">

		<div class="top_menu">
			<div class="title">${roomName}</div>
		</div>
		<input type="hidden" id="sessionId" value="">
		<input type="hidden" id="roomNo" value="${roomNo}">

        <div id="chating" class="messages">



         </div>

                <div id="yourName">
                    <div class="bottom_wrapper clearfix">
                        <div class="message_input_wrapper">
                            <input type="text"class="message_input" name="userName" id="userName" placeholder="Type your ID here...">
                        </div>
                        <div class="send_message">
                            <div class="icon"></div>
                            <div class="text" onclick="chatName()" class="text">Next</div>
                        </div>
                    </div>
                </div>

            <div id="yourMsg">
			    <div class="bottom_wrapper clearfix">
				    <div class="message_input_wrapper">
                            <input id="chatting" class="message_input" placeholder="Type your message here...">
                    </div>
                    <div class="send_message">
                        <div class="icon"></div>
                        <div class="text" onclick="send()">Send</div>

                    </div>
	            </div>
	        </div>
   </div>
</div>
<script type="text/javascript">

    var ws;

    function wsOpen(){
        ws = new WebSocket("ws://" + location.host + "/chating/" +$("#roomNo").val());       //socketHandler
        wsEvt();
        console.log("name" + `${roomName}`);
    }

    function wsEvt(){

            ws.onopen = function(data){
                //소켓이 열리면 초기화 세팅하기 (이벤트 소스와의 연결이 열릴 때 이벤트 발생 )
            }
            ws.onmessage = function(data){  //  (이벤트 메세지 수신 이벤트)
                    var msg = data.data;
                       if(msg != null && msg.trim() !=''){
                            var d = JSON.parse(msg);
                            if(d.type =="firstId"){   //Id - 타입이 firstId면 초기에 설정된 값이면
                                    var si = d.sessionId != null ? d.sessionId : "";
                                    if(si !=''){
                                        $("#sessionId").val(si); //input hidden의 sessionId에 값을 세팅
                                    }
                            }else if(d.type == "message"){   //message
                                    if(d.sessionId == $("#sessionId").val()){ //초기 세팅된 값이랑 지금 메세지를 보낸 사람이 같다면 나
                                            var msgDiv = document.getElementById("chating");

                                            var output="";

                                            output += "<p>나 : "+d.msg+"</p>";

                                            var mDiv = document.createElement("div");
                                            mDiv.setAttribute("class", "sent_msg");
                                            mDiv.innerHTML=output;
                                            msgDiv.appendChild(mDiv);


                                    }else{      //초기 세팅된 값이랑 메세지를 보낸 사람이 다르면 다른 사람
                                           var msgDiv = document.getElementById("chating");

                                           var output="";

                                           output += "<p>"+ d.userName + " : " + d.msg +"</p>";

                                           var rDiv = document.createElement("div");
                                           rDiv.setAttribute("class", "received_withd_msg");
                                           rDiv.innerHTML=output;
                                           msgDiv.appendChild(rDiv);

                                    }
                            }else{
                                     console.warn("unknown type");
                            }
                       }
            }
            document.addEventListener("keypress", function(e){      // addEventListener - 특정 이벤트 발생시 특정 함수를 실행시킨다.
                    if(e.keyCode == 13){        //enter key
                        send();     //send 메소드 호출
                    }
            });
    }
    function chatName(){
        var userName = $("#userName").val();
        if(userName == null || userName.trim() == ""){
            alert("사용자 이름을 입력해 주세요.");
            $("#userName").focus();
        }else{
            wsOpen();           //socketHandler 호출
            $("#yourName").hide();
            $("#yourMsg").show();
        }
    }
    function send(){
        var option ={
            type: "message",    //메세지를 보낼때 타입 - message
            roomNo: $("#roomNo").val(),
            sessionId: $("#sessionId").val(),
            userName: $("#userName").val(),
            msg: $("#chatting").val()
        }
        ws.send(JSON.stringify(option));
        console.log(JSON.stringify(option));
        $("#chatting").val("");
    }
</script>

<script type="text/javascript" src="/resources/js/check.js"></script>
</body>

</html>