package com.fleafair.Mapper;

import com.fleafair.Entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {
    
    // 插入消息
    int insertMessage(Message message);
    
    // 根据会话ID获取消息列表
    List<Message> getMessagesBySession(@Param("user1Id") Long user1Id, 
                                      @Param("user2Id") Long user2Id, 
                                      @Param("limit") Integer limit, 
                                      @Param("offset") Integer offset);
    
    // 获取两个用户之间的最新消息
    Message getLastMessage(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id);
    
    // 标记消息为已读
    int markMessagesAsRead(@Param("fromUserId") Long fromUserId, 
                          @Param("toUserId") Long toUserId);
    
    // 获取未读消息数量
    int getUnreadCount(@Param("fromUserId") Long fromUserId, @Param("toUserId") Long toUserId);
    
    // 根据消息ID获取消息
    Message getMessageById(@Param("messageId") Long messageId);
} 