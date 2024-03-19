package com.goormpj.decimal.chat.service;

import com.goormpj.decimal.chat.dto.VideoChatDto;
import com.goormpj.decimal.chat.mapper.VideoChatMapper;
import com.goormpj.decimal.chat.model.VideoChatModel;
import com.goormpj.decimal.chat.repository.VideoChatRepository;
import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoChatService {

    @Value("${OPENVIDU_URL}")
    private String openviduUrl;

    @Value("${OPENVIDU_SECRET}")
    private String openviduSecret;

    private OpenVidu openVidu;

    private final VideoChatRepository videoChatRepository;

    @PostConstruct
    private void init() {
        this.openVidu = new OpenVidu(openviduUrl, openviduSecret);
    }

    @Autowired
    public VideoChatService(VideoChatRepository videoChatRepository) {
        this.videoChatRepository = videoChatRepository;
    }

    public String initializeSession(VideoChatDto videoChatDto) throws OpenViduJavaClientException, OpenViduHttpException {
        SessionProperties properties = SessionProperties.fromJson(videoChatDto.getProperties()).build();
        Session session = this.openVidu.createSession(properties);
        return session.getSessionId();
    }

    public String createConnection(String sessionId, VideoChatDto videoChatDto) throws Exception {
        try {
            Session session = this.openVidu.getActiveSession(sessionId);
             if (session == null) {
                throw new Exception("Session not found");
            }
            ConnectionProperties properties = ConnectionProperties.fromJson(videoChatDto.getProperties()).build();
            Connection connection = session.createConnection(properties);
            return connection.getToken();
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new Exception("Failed to create connection: " + e.getMessage(), e);
        }

    }

    public List<VideoChatDto> getUserSessions(Long memberId) {
        List<VideoChatModel> sessions = videoChatRepository.findByMember_Id(memberId);
        return sessions.stream()
                .map(VideoChatMapper::modelToDto)
                .collect(Collectors.toList());
    }
}
