package com.csf.whoami.service;

import java.util.List;

import com.csf.database.vo.TmpMemberVO;

public interface TmpMemberService {

	List<TmpMemberVO> selectAllMembers();

	void insertMember(TmpMemberVO member);

	void updateMember(int id, TmpMemberVO member);

	void deleteMember(int id);
}
