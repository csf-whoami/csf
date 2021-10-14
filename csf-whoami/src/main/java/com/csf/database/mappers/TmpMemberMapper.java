package com.csf.database.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.csf.database.vo.TmpMemberVO;

@Mapper
public interface TmpMemberMapper {
	List<TmpMemberVO> selectAll();

	TmpMemberVO selectById(int id);

	void insert(TmpMemberVO member);

	void update(TmpMemberVO member);

	void delete(int id);
}
