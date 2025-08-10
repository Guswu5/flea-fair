package com.fleafair.Service;

import com.fleafair.DTO.MessageDTO;
import com.fleafair.Entity.Message;
import com.fleafair.Entity.ChatSession;

import java.util.List;

public interface MessageService {
    
    // 发送消息
    Message sendMessage(MessageDTO messageDTO);
    
    // 获取聊天记录
    List<Message> getChatHistory(Long user1Id, Long user2Id, Integer page, Integer size);
    
    // 获取用户的聊天会话列表
    List<ChatSession> getUserChatSessions(Long userId);
    
    // 标记消息为已读
    void markMessagesAsRead(Long fromUserId, Long toUserId);
    
    // 获取未读消息数量
    int getUnreadCount(Long fromUserId, Long toUserId);
    
    // 通过WebSocket发送消息
    void sendMessageViaWebSocket(MessageDTO messageDTO);
} 