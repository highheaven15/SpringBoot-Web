package com.example.demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


//웹소켓 구현체
@Component
public class SocketHandler extends TextWebSocketHandler {

    //HashMap<String, WebSocketSession> sessionMap = new HashMap<>(); //웹소켓 세션을 담아둘 맵
    List<HashMap<String,Object>> rls = new ArrayList<>();   // 웹소켓 세션을 담아둘 리스트 (rls - roomListSessions)

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        //메시지 발송
        String msg = message.getPayload(); //문자열
        JSONObject obj = jsonToObjectParser(msg); //jsonToObjectParser 호출 (jsonObject로 파싱처리를 해주는 함수)

        String rn = (String) obj.get("roomNo");     //obj의 roomNo를 가져옴
        HashMap<String, Object> temp = new HashMap<String, Object>();

        if(rls.size() > 0){
            for(int i=0; i < rls.size(); i++){
                String roomNo = (String) rls.get(i).get("roomNo");      //roomListSession에 있는 roomNo를 가져옴
                if(roomNo.equals(rn)){      //obj의 roomNo와 기존에 있던 list의 roomNo가 같다면 (같은 값의 방번호가 존재한다면)
                    temp = rls.get(i);  //해당 번호의 object값을 temp에 넣어줌
                    break;
                }
            }
        //해당 방의 세션만 찾아서 메세지를 발송한다
            for(String k : temp.keySet()){
                if(k.equals("roomNo")){ // 방번호는 건너뜀
                    continue;
                }

                WebSocketSession wss = (WebSocketSession) temp.get(k);
                if(wss != null) {
                    try {
                        wss.sendMessage(new TextMessage(obj.toJSONString())); //JSONObject값을 받아서 강제 문자열 형태로 보내줌
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결되면 동작
        super.afterConnectionEstablished(session);
        boolean flag = false;
        String url = session.getUri().toString();
        System.out.println(url);
        String roomNo = url.split("/chating/")[1]; //roomNo 추출
        int idx = rls.size();
        if(rls.size() > 0){     //기존에 있던 방인지 체크
            for(int i=0; i < rls.size(); i++){
                String rn= (String) rls.get(i).get("roomNo"); //i번째의 roomNo를 가져온다.
                if(rn.equals(roomNo)){  //rn과 url의 roomNo가 일치하면 true를 주고 그에 일치하는 i를 idx에 넣어준다.
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }

        if(flag){       //rn과 roomNo가 같다면 (이미 존재하는 방이라면 세션만 추가 )
            HashMap<String, Object> map = rls.get(idx);  //roomlistSession의 idx를 가져와 map에 넣어줌
            map.put(session.getId(), session);  //map에 sessionId를 넣어줌

        } else {        //처음 생성된 방이라면 (기존에 없던 방이라면 ) 세션과 roomNo를 추가 & roomListSession에 추가
            HashMap<String, Object> map = new HashMap<String,Object>();
            map.put("roomNo", roomNo);
            map.put(session.getId(), session);
            rls.add(map);
        }

        JSONObject obj = new JSONObject();
        obj.put("type", "firstId");   //생성된 세션을 저장하면 '발신 메세지'의 타입은 firstId 명시 후 (클라이언트단에서 type으로 메세지와 초기 설정값 구분)
        obj.put("sessionId", session.getId());  //생성된 세션 ID값을 클라이언트단으로 발송한다.
        session.sendMessage(new TextMessage(obj.toJSONString())); //(type과 sessionId) 클라이언트 단으로 전송한다.
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료되면 동작
        if(rls.size() > 0){
            for (int i=0; i< rls.size(); i++){
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    //json 파일이 들어오면 파싱을 해주는 함수 JsonToObjectParser 추가
    private static JSONObject jsonToObjectParser(String jsonStr) { //json 형태의 문자열을 파라미터로 받아서
        JSONParser parser = new JSONParser(); //simpleJson의 파서를 활용해서
        JSONObject obj = null;  //jsonObject로 파싱처리를 해주는 함수이다.
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
