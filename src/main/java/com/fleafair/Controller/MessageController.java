package com.fleafair.Controller;

import com.fleafair.DTO.MessageDTO;
import com.fleafair.Entity.Message;
import com.fleafair.Entity.ChatSession;
import com.fleafair.Service.MessageService;
import com.fleafair.Common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flea-fair/message")
@Slf4j
public class MessageController {

    @Autowired
    private MessageService messageService;

    // WebSocket消息处理
    @MessageMapping("/send")
    public void handleMessage(@Payload MessageDTO messageDTO, SimpMessageHeaderAccessor headerAccessor) {
        log.info("收到WebSocket消息: {}", messageDTO);
        messageService.sendMessage(messageDTO);
    }

    // 获取聊天记录
    @GetMapping("/history")
    public Result<List<Message>> getChatHistory(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer size) {
        try {
            List<Message> messages = messageService.getChatHistory(user1Id, user2Id, page, size);
            return Result.success(messages);
        } catch (Exception e) {
            log.error("获取聊天记录失败", e);
            return Result.error("获取聊天记录失败");
        }
    }

    // 获取用户的聊天会话列表
    @GetMapping("/sessions")
    public Result<List<ChatSession>> getUserChatSessions(@RequestParam Long userId) {
        try {
            List<ChatSession> sessions = messageService.getUserChatSessions(userId);
            return Result.success(sessions);
        } catch (Exception e) {
            log.error("获取聊天会话失败", e);
            return Result.error("获取聊天会话失败");
        }
    }

    // 标记消息为已读
    @PostMapping("/read")
    public Result<String> markMessagesAsRead(
            @RequestParam Long fromUserId,
            @RequestParam Long toUserId) {
        try {
            messageService.markMessagesAsRead(fromUserId, toUserId);
            return Result.success("消息已标记为已读");
        } catch (Exception e) {
            log.error("标记消息已读失败", e);
            return Result.error("标记消息已读失败");
        }
    }

    // 获取未读消息数量
    @GetMapping("/unread")
    public Result<Integer> getUnreadCount(
            @RequestParam Long fromUserId,
            @RequestParam Long toUserId) {
        try {
            int count = messageService.getUnreadCount(fromUserId, toUserId);
            return Result.success(count);
        } catch (Exception e) {
            log.error("获取未读消息数量失败", e);
            return Result.error("获取未读消息数量失败");
        }
    }

    // 发送消息（REST API）
    @PostMapping("/send")
    public Result<Message> sendMessage(@RequestBody MessageDTO messageDTO) {
        try {
            Message message = messageService.sendMessage(messageDTO);
            return Result.success(message);
        } catch (Exception e) {
            log.error("发送消息失败", e);
            return Result.error("发送消息失败");
        }
    }
} 