package com.ou.mappers;

import com.ou.pojo.Question;
import com.ou.pojo.TestAttempt;
import com.ou.pojo.TestAttemptAnswer;
import com.ou.dto.TestAttemptAnswerDto;
import org.springframework.stereotype.Component;

@Component
public class TestAttemptAnswerMapper {

    
    public TestAttemptAnswer toEntity(TestAttemptAnswerDto testAttemptAnswerDto) {
        if ( testAttemptAnswerDto == null ) {
            return null;
        }

        TestAttemptAnswer testAttemptAnswer = new TestAttemptAnswer();

        testAttemptAnswer.setAttemptId( testAttemptAnswerDtoToTestAttempt( testAttemptAnswerDto ) );
        testAttemptAnswer.setQuestionId( testAttemptAnswerDtoToQuestion( testAttemptAnswerDto ) );
        testAttemptAnswer.setId( testAttemptAnswerDto.getId() );
        testAttemptAnswer.setAnswerText( testAttemptAnswerDto.getAnswerText() );
        testAttemptAnswer.setIsCorrect( testAttemptAnswerDto.getIsCorrect() );
        testAttemptAnswer.setScore( testAttemptAnswerDto.getScore() );

        return testAttemptAnswer;
    }

    
    public TestAttemptAnswerDto toDto(TestAttemptAnswer testAttemptAnswer) {
        if ( testAttemptAnswer == null ) {
            return null;
        }

        TestAttemptAnswerDto testAttemptAnswerDto = new TestAttemptAnswerDto();

        testAttemptAnswerDto.setAttemptIdId( testAttemptAnswerAttemptIdId( testAttemptAnswer ) );
        testAttemptAnswerDto.setQuestionIdId( testAttemptAnswerQuestionIdId( testAttemptAnswer ) );
        testAttemptAnswerDto.setId( testAttemptAnswer.getId() );
        testAttemptAnswerDto.setAnswerText( testAttemptAnswer.getAnswerText() );
        testAttemptAnswerDto.setIsCorrect( testAttemptAnswer.getIsCorrect() );
        testAttemptAnswerDto.setScore( testAttemptAnswer.getScore() );

        return testAttemptAnswerDto;
    }

    
    public TestAttemptAnswer partialUpdate(TestAttemptAnswerDto testAttemptAnswerDto, TestAttemptAnswer testAttemptAnswer) {
        if ( testAttemptAnswerDto == null ) {
            return testAttemptAnswer;
        }

        if ( testAttemptAnswer.getAttemptId() == null ) {
            testAttemptAnswer.setAttemptId( new TestAttempt() );
        }
        testAttemptAnswerDtoToTestAttempt1( testAttemptAnswerDto, testAttemptAnswer.getAttemptId() );
        if ( testAttemptAnswer.getQuestionId() == null ) {
            testAttemptAnswer.setQuestionId( new Question() );
        }
        testAttemptAnswerDtoToQuestion1( testAttemptAnswerDto, testAttemptAnswer.getQuestionId() );
        if ( testAttemptAnswerDto.getId() != null ) {
            testAttemptAnswer.setId( testAttemptAnswerDto.getId() );
        }
        if ( testAttemptAnswerDto.getAnswerText() != null ) {
            testAttemptAnswer.setAnswerText( testAttemptAnswerDto.getAnswerText() );
        }
        if ( testAttemptAnswerDto.getIsCorrect() != null ) {
            testAttemptAnswer.setIsCorrect( testAttemptAnswerDto.getIsCorrect() );
        }
        if ( testAttemptAnswerDto.getScore() != null ) {
            testAttemptAnswer.setScore( testAttemptAnswerDto.getScore() );
        }

        return testAttemptAnswer;
    }

    protected TestAttempt testAttemptAnswerDtoToTestAttempt(TestAttemptAnswerDto testAttemptAnswerDto) {
        if ( testAttemptAnswerDto == null ) {
            return null;
        }

        TestAttempt testAttempt = new TestAttempt();

        testAttempt.setId( testAttemptAnswerDto.getAttemptIdId() );

        return testAttempt;
    }

    protected Question testAttemptAnswerDtoToQuestion(TestAttemptAnswerDto testAttemptAnswerDto) {
        if ( testAttemptAnswerDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setId( testAttemptAnswerDto.getQuestionIdId() );

        return question;
    }

    private Integer testAttemptAnswerAttemptIdId(TestAttemptAnswer testAttemptAnswer) {
        TestAttempt attemptId = testAttemptAnswer.getAttemptId();
        if ( attemptId == null ) {
            return null;
        }
        return attemptId.getId();
    }

    private Integer testAttemptAnswerQuestionIdId(TestAttemptAnswer testAttemptAnswer) {
        Question questionId = testAttemptAnswer.getQuestionId();
        if ( questionId == null ) {
            return null;
        }
        return questionId.getId();
    }

    protected void testAttemptAnswerDtoToTestAttempt1(TestAttemptAnswerDto testAttemptAnswerDto, TestAttempt mappingTarget) {
        if ( testAttemptAnswerDto == null ) {
            return;
        }

        if ( testAttemptAnswerDto.getAttemptIdId() != null ) {
            mappingTarget.setId( testAttemptAnswerDto.getAttemptIdId() );
        }
    }

    protected void testAttemptAnswerDtoToQuestion1(TestAttemptAnswerDto testAttemptAnswerDto, Question mappingTarget) {
        if ( testAttemptAnswerDto == null ) {
            return;
        }

        if ( testAttemptAnswerDto.getQuestionIdId() != null ) {
            mappingTarget.setId( testAttemptAnswerDto.getQuestionIdId() );
        }
    }
}
