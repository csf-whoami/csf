package com.csf.whoami.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csf.database.mappers.TmpMemberMapper;
import com.csf.database.vo.TmpMemberVO;
import com.csf.whoami.service.TmpMemberService;

@Service
public class TmpMemberServiceImpl implements TmpMemberService{
	
	@Autowired
	private TmpMemberMapper memberMapper;
	
	@Override
	public List<TmpMemberVO> selectAllMembers() {
		return memberMapper.selectAll();
	}

	@Override
	public void insertMember(TmpMemberVO member) {
		memberMapper.insert(member);
	}

	@Override
	public void updateMember(int id, TmpMemberVO updateMember) {
		TmpMemberVO member = memberMapper.selectById(id);
		if(member!=null){
			member.setName(updateMember.getName());
			member.setJob(updateMember.getJob());
			memberMapper.update(member);
		} else {
			throw new IllegalStateException("회원이 존재하지 않습니다.");
		}
	}

	@Override
	public void deleteMember(int id) {
		if(memberMapper.selectById(id)!=null){
			memberMapper.delete(id);
		} else {
			throw new IllegalStateException("회원이 존재하지 않습니다.");
		}
	}
}
