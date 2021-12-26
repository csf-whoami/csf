package com.csf.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.base.domain.SearchVO;
import com.csf.base.domain.response.ChannelInfo;
import com.csf.database.models.ChannelEntity;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, Long> {

    /**
     * Get channels in Group.
     * @param id
     * @return
     */
    @Query(value = "SELECT new com.csf.base.domain.response.ChannelInfo(channel.id, channel.channelName, channel.isLock, channel.createdAt)" +
            " FROM ChannelEntity channel" +
            " WHERE channel.groupId = :groupId")
    Page<ChannelInfo> findAllByGroupId(@Param("groupId") Long id, Pageable pageable);

    /**
     * Search channels by conditions.
     * @param id
     * @param search
     * @param pageable
     * @return
     */
    @Query(value = "SELECT new com.csf.base.domain.response.ChannelInfo(channel.id, channel.channelName, channelUrl, channel.isLock, channel.createdAt)" +
            " FROM ChannelEntity channel" +
            " WHERE channel.groupId = :groupId"
            + " AND (:#{#search.keyword} IS NULL)")
    Page<ChannelInfo> findAllByGroupId(@Param("groupId") Long id, @Param("search") SearchVO search, Pageable pageable);
}
