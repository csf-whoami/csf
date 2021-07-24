package com.csf.whoami.database.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.whoami.database.dto.SearchVO;
import com.csf.whoami.database.dto.response.ChannelInfo;
import com.csf.whoami.database.models.TbChannel;

@Repository
public interface ChannelRepository extends JpaRepository<TbChannel, Long> {

    /**
     * Get channels in Group.
     * @param id
     * @return
     */
    @Query(value = "SELECT new com.csf.whoami.database.dto.response.ChannelInfo(channel.id, channel.channelName, channel.isLock, channel.createdAt)" +
            " FROM TbChannel channel" +
            " WHERE channel.groupId = :groupId")
    Page<ChannelInfo> findAllByGroupId(@Param("groupId") Long id, Pageable pageable);

    /**
     * Search channels by conditions.
     * @param id
     * @param search
     * @param pageable
     * @return
     */
    @Query(value = "SELECT new com.csf.whoami.database.dto.response.ChannelInfo(channel.id, channel.channelName, channelUrl, channel.isLock, channel.createdAt)" +
            " FROM TbChannel channel" +
            " WHERE channel.groupId = :groupId"
            + " AND (:#{#search.keyword} IS NULL)")
    Page<ChannelInfo> findAllByGroupId(@Param("groupId") Long id, @Param("search") SearchVO search, Pageable pageable);
}
