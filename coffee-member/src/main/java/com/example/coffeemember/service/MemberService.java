package com.example.coffeemember.service;

import com.example.coffeemember.mapper.MemberMapper;
import com.example.coffeemember.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;

    /*회원인지 아닌지 체크*/
    public MemberVO findMember(MemberVO vo) {
        return memberMapper.exitsByMemberName(vo);
    }

    /*회원 테이블 생성*/
    public int createMemberTable() {
        return memberMapper.createMemberTable();
    }

    /*회원 데이터 생성*/
    public int insertMember() {
        return memberMapper.insertMemberData();
    }
}
