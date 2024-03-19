package com.goormpj.decimal.chat.repository;

import com.goormpj.decimal.chat.model.VideoChatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoChatRepository extends JpaRepository<VideoChatModel, String> {
    List<VideoChatModel> findByMember_Id(Long memberId);

}
