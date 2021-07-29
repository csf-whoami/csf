package com.csf.whoami.database.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.base.domain.QuestionInfo;
import com.csf.base.domain.QuestionManagementInfo;
import com.csf.whoami.database.models.TbQuestion;

@Repository
public interface QuestionRepository extends JpaRepository<TbQuestion, Long> {

    /**
     * Refer to : The best solution seems the following with WHERE ... RAND(),
     * https://stackoverflow.com/questions/18943417/how-to-quickly-select-3-random-records-from-a-30k-mysql-table-with-a-where-filte
     * 
     * @param questionNumber
     * @param groupId
     * @return
     */
//    @Query(value = "select question from QuestionEntity question "
//            + "inner join QuestionGroupEntity questionGroup on question.questionId = questionGroup.question.questionId "
//            + "where (questionGroup.group.id = :groupId AND RAND() < 16 * 3/30000) LIMIT :questNumber", nativeQuery = true)
//    List<TbQuestion> getQuestionsByGroupId(@Param("questNumber") Integer questionNumber, @Param("groupId") String groupId);

    @Query(value = "SELECT new com.csf.base.domain.QuestionManagementInfo(question.id, type.typeName, question.createdAt) "
            + " FROM TbQuestion question "
            + " INNER JOIN TbQuestionType questionType ON questionType.questionId = question.id "
            + " INNER JOIN TbType type ON type.id = questionType.typeId "
            + " INNER JOIN TbChannelQuestion channelQuestion ON channelQuestion.questionId = question.id"
            + " WHERE channelQuestion.channelId = :channel"
            + " AND type.groupName = 'QUESTION'")
    Page<QuestionManagementInfo> findAllByChannelId(@Param("channel") Long channelId, Pageable pageable);

//    private String id;
//    private String questionType;
//    private String createdAt;
    @Query(value = "SELECT new com.csf.base.domain.QuestionInfo(question.id, questionType.typeId, type.typeName,"
            + " tbgroup.id, tbgroup.groupName,"
            + " channel.id, channel.channelName,"
            + " question.content, question.createdAt) "
            + " FROM TbQuestion question "
            + " INNER JOIN TbQuestionType questionType ON questionType.questionId = question.id "
            + " INNER JOIN TbType type ON type.id = questionType.typeId "
            + " INNER JOIN TbChannelQuestion channelQuestion ON channelQuestion.questionId = question.id"
            + " INNER JOIN TbChannel channel ON channel.id = channelQuestion.channelId"
            + " INNER JOIN TbGroup tbgroup ON tbgroup.id = channel.groupId"
            + " WHERE question.id = :id"
            + " AND question.deletedAt IS NULL"
            + " AND questionType.deletedAt IS NULL"
            + " AND type.deletedAt IS NULL"
            + " AND channelQuestion.deletedAt IS NULL"
            + " AND channel.deletedAt IS NULL"
            + " AND tbgroup.deletedAt IS NULL")
    QuestionInfo findQuestionById(@Param("id")Long id);

//    List<TbQuestion> getQuestionsByGroupId(Integer questionNumber, Long groupId);
    List<TbQuestion> getQuestionsByGroupId(Long groupId);
}
