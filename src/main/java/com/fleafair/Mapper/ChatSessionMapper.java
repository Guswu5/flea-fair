package com.fleafair.Mapper;

import com.fleafair.Entity.ChatSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatSessionMapper {
    
    // 插入或更新聊天会话
    int insertOrUpdateSession(ChatSession session);
    
    // 获取用户的所有聊天会话
    List<ChatSession> getSessionsByUserId(@Param("userId") Long userId);
    
    // 获取两个用户之间的会话
    ChatSession getSessionByUsers(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
    
    // 更新会话的最后消息
    int updateLastMessage(@Param("user1Id") Long user1Id, 
                         @Param("user2Id") Long user2Id, 
                         @Param("lastMessage") String lastMessage);
    
    // 删除会话
    int deleteSession(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
} 