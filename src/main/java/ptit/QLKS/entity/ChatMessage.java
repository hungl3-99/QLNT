package ptit.QLKS.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ptit.QLKS.constrant.MessageStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ChatMessage implements Serializable {
    @Id
    private String id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "sender_id")
    private String senderId;

    @Column(name = "recipient_id")
    private String recipientId;

    @Column(name = "sender_name")
    private String senderName;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Date timestamp;

    @Column(name = "status")
    private MessageStatus status;
}
