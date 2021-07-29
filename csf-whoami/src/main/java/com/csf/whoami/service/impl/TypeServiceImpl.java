package com.csf.whoami.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.csf.base.domain.TypeInfo;
import com.csf.whoami.database.repository.TypeRepository;
import com.csf.whoami.service.TypeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {

	private final TypeRepository typeRepository;

	@Override
	public TypeInfo typeInfo(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TypeInfo> fetchTypesByGroup(String group) {
		return typeRepository.findAllByGroup(group);
	}

	@Override
	public Page<TypeInfo> fetchTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
