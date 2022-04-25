//package ptit.QLKS.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//import ptit.QLKS.constrant.MessageStatus;
//import ptit.QLKS.entity.ChatMessage;
//import ptit.QLKS.exception.ResourceNotFoundException;
//import ptit.QLKS.repository.ChatMessageRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ChatMessageService {
//
//    @Autowired
//    private ChatMessageRepository repository;
//
//    @Autowired
//    private ChatRoomService chatRoomService;
//
//    public ChatMessage save(ChatMessage chatMessage) {
//        chatMessage.setStatus(MessageStatus.RECEIVED);
//        repository.save(chatMessage);
//        return chatMessage;
//    }
//
//    public long countNewMessages(String senderId, String recipientId) {
//        return repository.countBySenderIdAndRecipientIdAndStatus(
//                senderId, recipientId, MessageStatus.RECEIVED);
//    }
//
//    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
//        String chatId = chatRoomService.getChatId(senderId, recipientId, false);
//
//        var messages =
//                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());
//
//        if(messages.size() > 0) {
//            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
//        }
//
//        return messages;
//    }
//
//    public ChatMessage findById(String id) {
//        return repository
//                .findById(id)
//                .map(chatMessage -> {
//                    chatMessage.setStatus(MessageStatus.DELIVERED);
//                    return repository.save(chatMessage);
//                })
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("can't find message (" + id + ")"));
//    }
//
//    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
//        List<ChatMessage> list = repository.findBySenderIdAndRecipientId(senderId , recipientId);
//        if(!ObjectUtils.isEmpty(list) && list.size() > 0){
//            list.forEach(x -> {
//                x.setStatus(status);
//            });
//            repository.saveAll(list);
//        }
//    }
//}
