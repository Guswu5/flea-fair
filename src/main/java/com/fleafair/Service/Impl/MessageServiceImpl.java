package com.fleafair.Service.Impl;

import com.fleafair.DTO.MessageDTO;
import com.fleafair.Entity.Message;
import com.fleafair.Entity.ChatSession;
import com.fleafair.Entity.User;
import com.fleafair.Entity.Item;
import com.fleafair.Mapper.MessageMapper;
import com.fleafair.Mapper.ChatSessionMapper;
import com.fleafair.Mapper.UserMapper;
import com.fleafair.Mapper.ItemMapper;
import com.fleafair.Service.MessageService;
import com.fleafair.Common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;
    
    @Autowired
    private ChatSessionMapper chatSessionMapper;
    
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private ItemMapper itemMapper;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public Message sendMessage(MessageDTO messageDTO) {
        try {
            // 创建消息实体
            Message message = new Message();
            message.setFromUserId(messageDTO.getFromUserId());
            message.setToUserId(messageDTO.getToUserId());
            message.setContent(messageDTO.getContent());
            message.setMessageType(messageDTO.getType());
            message.setImageUrl(messageDTO.getImageUrl());
            message.setItemId(messageDTO.getItemId());
            message.setOrderInfo(messageDTO.getOrderInfo());
            message.setIsRead(false);
            message.setCreateTime(LocalDateTime.now());
            message.setUpdateTime(LocalDateTime.now());
            
            // 保存消息到数据库
            messageMapper.insertMessage(message);
            
            // 更新或创建聊天会话
            updateChatSession(message);
            
            // 通过WebSocket发送消息
            sendMessageViaWebSocket(messageDTO);
            
            return message;
        } catch (Exception e) {
            log.error("发送消息失败", e);
            throw new RuntimeException("发送消息失败");
        }
    }

    @Override
    public List<Message> getChatHistory(Long user1Id, Long user2Id, Integer page, Integer size) {
        int offset = (page - 1) * size;
        List<Message> messages = messageMapper.getMessagesBySession(user1Id, user2Id, size, offset);
        
        // 标记消息为已读
        markMessagesAsRead(user1Id, user2Id);
        
        return messages;
    }

    @Override
    public List<ChatSession> getUserChatSessions(Long userId) {
        return chatSessionMapper.getSessionsByUserId(userId);
    }

    @Override
    public void markMessagesAsRead(Long fromUserId, Long toUserId) {
        messageMapper.markMessagesAsRead(fromUserId, toUserId);
    }

    @Override
    public int getUnreadCount(Long fromUserId, Long toUserId) {
        return messageMapper.getUnreadCount(fromUserId, toUserId);
    }

    @Override
    public void sendMessageViaWebSocket(MessageDTO messageDTO) {
        try {
            // 设置时间戳
            messageDTO.setTimestamp(LocalDateTime.now());
            
            // 如果是订单请求，添加商品信息
            if ("order_request".equals(messageDTO.getType()) && messageDTO.getItemId() != null) {
                Item item = itemMapper.getById(messageDTO.getItemId());
                if (item != null) {
                    messageDTO.setItemTitle(item.getTitle());
                    messageDTO.setItemImage(item.getCoverImage());
                    messageDTO.setItemPrice(item.getPrice().doubleValue());
                }
            }
            
            // 发送给接收者
            messagingTemplate.convertAndSendToUser(
                messageDTO.getToUserId().toString(),
                "/queue/messages",
                messageDTO
            );
            
            // 发送给发送者（确认消息已发送）
            messagingTemplate.convertAndSendToUser(
                messageDTO.getFromUserId().toString(),
                "/queue/messages",
                messageDTO
            );
            
        } catch (Exception e) {
            log.error("WebSocket发送消息失败", e);
        }
    }

    private void updateChatSession(Message message) {
        ChatSession session = new ChatSession();
        session.setUser1Id(Math.min(message.getFromUserId(), message.getToUserId()));
        session.setUser2Id(Math.max(message.getFromUserId(), message.getToUserId()));
        session.setItemId(message.getItemId());
        session.setLastMessage(message.getContent());
        session.setLastMessageTime(message.getCreateTime());
        session.setUpdateTime(LocalDateTime.now());
        
        chatSessionMapper.insertOrUpdateSession(session);
    }
} 