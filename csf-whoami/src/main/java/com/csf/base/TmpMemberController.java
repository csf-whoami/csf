package com.csf.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.database.vo.TmpMemberVO;
import com.csf.whoami.service.TmpMemberService;

@RestController
public class TmpMemberController {

	@Autowired
	private TmpMemberService memberService;

	@GetMapping("/member/all")
	public List<TmpMemberVO> selectAllMembers() {
		return memberService.selectAllMembers();
	}
}
