package ptit.QLKS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ptit.QLKS.constrant.MessageStatus;
import ptit.QLKS.entity.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {
    long countBySenderIdAndRecipientIdAndStatus(
            @Param("senderId") String senderId,@Param("recipientId") String recipientId,@Param("status") MessageStatus status);

    List<ChatMessage> findByChatId(@Param("chatId") String chatId);

    List<ChatMessage> findBySenderIdAndRecipientId(@Param("senderId") String senderIDd ,@Param("recipientId") String RecipientId);
}
