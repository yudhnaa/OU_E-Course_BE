package com.ou.mappers;

import com.ou.dto.TestAttemptAnswerDto;
import com.ou.dto.TestAttemptDto;
import com.ou.pojo.*;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.ou.repositories.QuestionRepository;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAttemptMapper {
    @Autowired
    private QuestionRepository questionRepository;

    public TestAttempt toEntity(TestAttemptDto testAttemptDto) {
        if ( testAttemptDto == null ) {
            return null;
        }

        TestAttempt testAttempt = new TestAttempt();

        testAttempt.setTestId( testAttemptDtoToTest( testAttemptDto ) );
        testAttempt.setUserId( testAttemptDtoToStudent( testAttemptDto ) );
        testAttempt.setId( testAttemptDto.getId() );
        testAttempt.setStartedAt( testAttemptDto.getStartedAt() );
        testAttempt.setSubmittedAt( testAttemptDto.getSubmittedAt() );
        testAttempt.setTotalScore( testAttemptDto.getTotalScore() );
        testAttempt.setTestAttemptAnswerSet( testAttemptAnswerDtoSetToTestAttemptAnswerSet( testAttemptDto.getTestAttemptAnswerSet() ) );

        linkTestAttemptAnswerSet( testAttempt );

        return testAttempt;
    }

    @Mapping(target = "testAttemptAnswerSet", ignore = true)
    public TestAttemptDto toDto(TestAttempt testAttempt) {
        if ( testAttempt == null ) {
            return null;
        }

        TestAttemptDto testAttemptDto = new TestAttemptDto();

        testAttemptDto.setTestIdId( testAttemptTestIdId( testAttempt ) );
        testAttemptDto.setUserIdId( testAttemptUserIdId( testAttempt ) );
        testAttemptDto.setId( testAttempt.getId() );
        testAttemptDto.setStartedAt( testAttempt.getStartedAt() );
        testAttemptDto.setSubmittedAt( testAttempt.getSubmittedAt() );
        testAttemptDto.setTotalScore( testAttempt.getTotalScore() );
        //testAttemptDto.setTestAttemptAnswerSet( testAttemptAnswerSetToTestAttemptAnswerDtoSet( testAttempt.getTestAttemptAnswerSet() ) );

        return testAttemptDto;
    }

    
    public TestAttempt partialUpdate(TestAttemptDto testAttemptDto, TestAttempt testAttempt) {
        if ( testAttemptDto == null ) {
            return testAttempt;
        }

        if ( testAttempt.getTestId() == null ) {
            testAttempt.setTestId( new Test() );
        }
        testAttemptDtoToTest1( testAttemptDto, testAttempt.getTestId() );
        if ( testAttempt.getUserId() == null ) {
            testAttempt.setUserId( new Student() );
        }
        testAttemptDtoToStudent1( testAttemptDto, testAttempt.getUserId() );
        if ( testAttemptDto.getId() != null ) {
            testAttempt.setId( testAttemptDto.getId() );
        }
        if ( testAttemptDto.getStartedAt() != null ) {
            testAttempt.setStartedAt( testAttemptDto.getStartedAt() );
        }
        if ( testAttemptDto.getSubmittedAt() != null ) {
            testAttempt.setSubmittedAt( testAttemptDto.getSubmittedAt() );
        }
        if ( testAttemptDto.getTotalScore() != null ) {
            testAttempt.setTotalScore( testAttemptDto.getTotalScore() );
        }
        if ( testAttempt.getTestAttemptAnswerSet() != null ) {
            Set<TestAttemptAnswer> set = testAttemptAnswerDtoSetToTestAttemptAnswerSet( testAttemptDto.getTestAttemptAnswerSet() );
            if ( set != null ) {
                testAttempt.getTestAttemptAnswerSet().clear();
                testAttempt.getTestAttemptAnswerSet().addAll( set );
            }
        }
        else {
            Set<TestAttemptAnswer> set = testAttemptAnswerDtoSetToTestAttemptAnswerSet( testAttemptDto.getTestAttemptAnswerSet() );
            if ( set != null ) {
                testAttempt.setTestAttemptAnswerSet( set );
            }
        }

        linkTestAttemptAnswerSet( testAttempt );

        return testAttempt;
    }

    protected Test testAttemptDtoToTest(TestAttemptDto testAttemptDto) {
        if ( testAttemptDto == null ) {
            return null;
        }

        Test test = new Test();

        test.setId( testAttemptDto.getTestIdId() );

        return test;
    }

    protected Student testAttemptDtoToStudent(TestAttemptDto testAttemptDto) {
        if ( testAttemptDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( testAttemptDto.getUserIdId() );

        return student;
    }

    protected TestAttemptAnswer testAttemptAnswerDtoToTestAttemptAnswer(TestAttemptAnswerDto testAttemptAnswerDto) {
        if ( testAttemptAnswerDto == null ) {
            return null;
        }

        TestAttemptAnswer testAttemptAnswer = new TestAttemptAnswer();

        testAttemptAnswer.setId( testAttemptAnswerDto.getId() );
        testAttemptAnswer.setAnswerText( testAttemptAnswerDto.getAnswerText() );
        testAttemptAnswer.setIsCorrect( testAttemptAnswerDto.getIsCorrect() );
        testAttemptAnswer.setScore( testAttemptAnswerDto.getScore() );

        if(testAttemptAnswerDto.getQuestionIdId() != null){
            Question question = questionRepository.getQuestionById(testAttemptAnswerDto.getQuestionIdId());
            if (question == null){
                throw new IllegalArgumentException("Question with ID " + testAttemptAnswerDto.getQuestionIdId() + " does not exist.");
            } else {
                testAttemptAnswer.setQuestionId(question);
            }
        }

        return testAttemptAnswer;
    }

    protected Set<TestAttemptAnswer> testAttemptAnswerDtoSetToTestAttemptAnswerSet(Set<TestAttemptAnswerDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<TestAttemptAnswer> set1 = new LinkedHashSet<TestAttemptAnswer>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TestAttemptAnswerDto testAttemptAnswerDto : set ) {
            set1.add( testAttemptAnswerDtoToTestAttemptAnswer( testAttemptAnswerDto ) );
        }

        return set1;
    }

    private Integer testAttemptTestIdId(TestAttempt testAttempt) {
        Test testId = testAttempt.getTestId();
        if ( testId == null ) {
            return null;
        }
        return testId.getId();
    }

    private Integer testAttemptUserIdId(TestAttempt testAttempt) {
        Student userId = testAttempt.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId.getId();
    }

    protected TestAttemptAnswerDto testAttemptAnswerToTestAttemptAnswerDto(TestAttemptAnswer testAttemptAnswer) {
        if ( testAttemptAnswer == null ) {
            return null;
        }

        TestAttemptAnswerDto testAttemptAnswerDto = new TestAttemptAnswerDto();

        testAttemptAnswerDto.setId( testAttemptAnswer.getId() );
        testAttemptAnswerDto.setAnswerText( testAttemptAnswer.getAnswerText() );
        testAttemptAnswerDto.setIsCorrect( testAttemptAnswer.getIsCorrect() );
        testAttemptAnswerDto.setScore( testAttemptAnswer.getScore() );

        return testAttemptAnswerDto;
    }

    protected Set<TestAttemptAnswerDto> testAttemptAnswerSetToTestAttemptAnswerDtoSet(Set<TestAttemptAnswer> set) {
        if ( set == null ) {
            return null;
        }

        Set<TestAttemptAnswerDto> set1 = new LinkedHashSet<TestAttemptAnswerDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TestAttemptAnswer testAttemptAnswer : set ) {
            set1.add( testAttemptAnswerToTestAttemptAnswerDto( testAttemptAnswer ) );
        }

        return set1;
    }

    protected void testAttemptDtoToTest1(TestAttemptDto testAttemptDto, Test mappingTarget) {
        if ( testAttemptDto == null ) {
            return;
        }

        if ( testAttemptDto.getTestIdId() != null ) {
            mappingTarget.setId( testAttemptDto.getTestIdId() );
        }
    }

    protected void testAttemptDtoToStudent1(TestAttemptDto testAttemptDto, Student mappingTarget) {
        if ( testAttemptDto == null ) {
            return;
        }

        if ( testAttemptDto.getUserIdId() != null ) {
            mappingTarget.setId( testAttemptDto.getUserIdId() );
        }
    }

    public void linkTestAttemptAnswerSet(TestAttempt testAttempt) {
        testAttempt.getTestAttemptAnswerSet().forEach(testAttemptAnswerSet -> testAttemptAnswerSet.setAttemptId(testAttempt));
    }

}
