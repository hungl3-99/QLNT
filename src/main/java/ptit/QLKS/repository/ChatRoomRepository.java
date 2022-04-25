package ptit.QLKS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ptit.QLKS.entity.ChatRoom;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom , String> {
    Optional<ChatRoom> findBySenderIdAndRecipientId(@Param("senderId") String senderId,@Param("recipientId") String recipientId);
}
