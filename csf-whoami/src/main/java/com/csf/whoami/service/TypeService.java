package com.csf.whoami.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.csf.base.domain.TypeInfo;

public interface TypeService {

    /**
     * Get type information.
     * @param id
     * @return
     */
    TypeInfo typeInfo(Long id);

    /**
     * Get types by Group. EX: Question, Channel....
     * @param group
     * @return
     */
    List<TypeInfo> fetchTypesByGroup(String group);

    /**
     * Get all types, using for Type management.
     * @return
     */
    Page<TypeInfo> fetchTypes();
}
