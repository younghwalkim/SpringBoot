package com.boot.test.chatting.service;


import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChattingServiceImpl implements ChattingService{
	/*
    @Autowired
    private ChattingDao chattingDao;
    
    @Override
    public int insertMessage(Message msg) {
        return chattingDao.insertMessage(msg);
    }

	@Override
	public Message selectChatRequest(String memberID) {
		return chattingDao.selectChatRequest(memberID);
	}

	@Override
	public int deleteMessage(Message message) {
		return chattingDao.deleteMessage(message);
	}

	@Override
	public int selectReceiver(String memberID) {
		return chattingDao.selectReceiver(memberID);
	}

	@Override
	public int selectChatRequestCount(String name) {
        return chattingDao.selectChatRequestCount(name);
	}

	@Override
	public ArrayList<Message> myChatList(String name) {
		return chattingDao.myChatList(name);
	}
	*/
    
}