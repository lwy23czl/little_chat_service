package cn.riverwind.service;

import cn.riverwind.model.entity.ChatMsg;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket服务类
 */
@Slf4j
@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketService {


    // 这里使用静态，让 service 属于类
    private static ChatMsgService chatMsgService;
    // 注入的时候，给类的 service 注入
    @Autowired
    public void setChatService(ChatMsgService chatService) {
        WebSocketService.chatMsgService = chatService;
    }
    //记录在线人数
    private static int onlineCount=0;
    //存储每个客户端的websocket对象
    private static ConcurrentHashMap<String,WebSocketService> webSocketMap =new ConcurrentHashMap<String,WebSocketService>();
    //客户端的连接会话 session
    private Session SocketSession;
    //当前连接服务的的用户id
    private String userId;


    /**
     * 建立连接成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam("userId") String userId,Session session){
        //在线人数加一
        addOnlineCount();
        this.userId=userId;
        this.SocketSession=session;
        //判断map中是否存在此key
        if (webSocketMap.containsKey(userId)){
            //存在，将原来的删除
            webSocketMap.remove(userId);
            //重新存入
            webSocketMap.put(userId,this);
        }else {
            //不存在，直接存入
            webSocketMap.put(userId,this);
        }
        log.info("连接成功，欢迎用户：{}  当前在线用户：{}",userId,getOnlineCount());

    }

    /**
     *连接关闭调用方法
     */
    @OnClose
    public void onClose(){
        if (webSocketMap.containsKey(userId)) {
            //从map中移除当前用户的连接
            webSocketMap.remove(userId);
            //在线人数减一
            subOnlineCount();
            log.info("用户：{} 已下线，当前在线用户：{}",userId,getOnlineCount());
        }
    }

    /**
     * 客户端发送信息后调用的方法
     * @param msg
     * @param session
     */
    @OnMessage
    public void onMessage(String msg,Session session){
        //将字符串转化为json
        JSONObject jsonObject = JSONObject.parseObject(msg);
        //将json转化为实体对象
        ChatMsg chatMsg = jsonObject.toJavaObject(ChatMsg.class);
        //调用发送信息发法，向指定人发送信息
        sendToUser(chatMsg);
    }

    /**
     * 向指定用户发送信息
     * @param chatMsg
     */
    private void sendToUser(ChatMsg chatMsg) {
        //获取信息的接收者
        String reciveUserid = chatMsg.getReciveuserid();
        //获取信息内容
        String sendText = chatMsg.getSendtext();

        try {
            //插入聊天记录
            chatMsgService.insertChatRecord(chatMsg);
            //判断是否接收对象是否在线
            if (webSocketMap.containsKey(reciveUserid)){
                //在线才可以调用发送方法
                webSocketMap.get(reciveUserid).sendMessage(sendText);

            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    /**
     * 发生异常调用方法
     */
    @OnError
    public void onError(Session session, Throwable error){
        error.printStackTrace();
    }


    /**
     * 发送信息方法
     */
    public void sendMessage(String mes) throws IOException {
        this.SocketSession.getBasicRemote().sendText(mes);
        log.info("信息已发送：{}",mes);
    }

    /**
     * 获取在线人数
     */
    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    /**
     * 增加在线人数
     */
    public static synchronized void addOnlineCount(){
        WebSocketService.onlineCount++;
    }

    /**
     * 减少在线人数
     */
    public static synchronized void subOnlineCount(){
        WebSocketService.onlineCount--;
    }
}
