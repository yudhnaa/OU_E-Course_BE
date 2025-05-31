package com.ou.mappers;

import com.ou.dto.QuestionDto;
import com.ou.pojo.MultipleChoiceAnswer;
import com.ou.pojo.Question;
import com.ou.pojo.WritingAnswer;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    public Question toEntity(QuestionDto questionDto) {
        if ( questionDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setId( questionDto.getId() );
        question.setContent( questionDto.getContent() );
        question.setWritingAnswerSet( writingAnswerDto1SetToWritingAnswerSet( questionDto.getWritingAnswerSet() ) );
        question.setMultipleChoiceAnswerSet( multipleChoiceAnswerDto1SetToMultipleChoiceAnswerSet( questionDto.getMultipleChoiceAnswerSet() ) );

        linkWritingAnswerSet( question );
        linkMultipleChoiceAnswerSet( question );

        return question;
    }

    
    public QuestionDto toDto(Question question) {
        if ( question == null ) {
            return null;
        }

        QuestionDto questionDto = new QuestionDto();

        questionDto.setId( question.getId() );
        questionDto.setContent( question.getContent() );
//        questionDto.setWritingAnswerSet( writingAnswerSetToWritingAnswerDto1Set( question.getWritingAnswerSet() ) );
//        questionDto.setMultipleChoiceAnswerSet( multipleChoiceAnswerSetToMultipleChoiceAnswerDto1Set( question.getMultipleChoiceAnswerSet() ) );

        return questionDto;
    }

    
    public Question partialUpdate(QuestionDto questionDto, Question question) {
        if ( questionDto == null ) {
            return question;
        }

        if ( questionDto.getId() != null ) {
            question.setId( questionDto.getId() );
        }
        if ( questionDto.getContent() != null ) {
            question.setContent( questionDto.getContent() );
        }
        if ( question.getWritingAnswerSet() != null ) {
            Set<WritingAnswer> set = writingAnswerDto1SetToWritingAnswerSet( questionDto.getWritingAnswerSet() );
            if ( set != null ) {
                question.getWritingAnswerSet().clear();
                question.getWritingAnswerSet().addAll( set );
            }
        }
        else {
            Set<WritingAnswer> set = writingAnswerDto1SetToWritingAnswerSet( questionDto.getWritingAnswerSet() );
            if ( set != null ) {
                question.setWritingAnswerSet( set );
            }
        }
        if ( question.getMultipleChoiceAnswerSet() != null ) {
            Set<MultipleChoiceAnswer> set1 = multipleChoiceAnswerDto1SetToMultipleChoiceAnswerSet( questionDto.getMultipleChoiceAnswerSet() );
            if ( set1 != null ) {
                question.getMultipleChoiceAnswerSet().clear();
                question.getMultipleChoiceAnswerSet().addAll( set1 );
            }
        }
        else {
            Set<MultipleChoiceAnswer> set1 = multipleChoiceAnswerDto1SetToMultipleChoiceAnswerSet( questionDto.getMultipleChoiceAnswerSet() );
            if ( set1 != null ) {
                question.setMultipleChoiceAnswerSet( set1 );
            }
        }

        linkWritingAnswerSet( question );
        linkMultipleChoiceAnswerSet( question );

        return question;
    }

    protected WritingAnswer writingAnswerDto1ToWritingAnswer(QuestionDto.WritingAnswerDto1 writingAnswerDto1) {
        if ( writingAnswerDto1 == null ) {
            return null;
        }

        WritingAnswer writingAnswer = new WritingAnswer();

        writingAnswer.setId( writingAnswerDto1.getId() );
        writingAnswer.setContent( writingAnswerDto1.getContent() );

        return writingAnswer;
    }

    protected Set<WritingAnswer> writingAnswerDto1SetToWritingAnswerSet(Set<QuestionDto.WritingAnswerDto1> set) {
        if ( set == null ) {
            return null;
        }

        Set<WritingAnswer> set1 = new LinkedHashSet<WritingAnswer>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( QuestionDto.WritingAnswerDto1 writingAnswerDto1 : set ) {
            set1.add( writingAnswerDto1ToWritingAnswer( writingAnswerDto1 ) );
        }

        return set1;
    }

    protected MultipleChoiceAnswer multipleChoiceAnswerDto1ToMultipleChoiceAnswer(QuestionDto.MultipleChoiceAnswerDto1 multipleChoiceAnswerDto1) {
        if ( multipleChoiceAnswerDto1 == null ) {
            return null;
        }

        MultipleChoiceAnswer multipleChoiceAnswer = new MultipleChoiceAnswer();

        multipleChoiceAnswer.setId( multipleChoiceAnswerDto1.getId() );
        multipleChoiceAnswer.setContent( multipleChoiceAnswerDto1.getContent() );
        multipleChoiceAnswer.setIsCorrect( multipleChoiceAnswerDto1.getIsCorrect() );

        return multipleChoiceAnswer;
    }

    protected Set<MultipleChoiceAnswer> multipleChoiceAnswerDto1SetToMultipleChoiceAnswerSet(Set<QuestionDto.MultipleChoiceAnswerDto1> set) {
        if ( set == null ) {
            return null;
        }

        Set<MultipleChoiceAnswer> set1 = new LinkedHashSet<MultipleChoiceAnswer>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( QuestionDto.MultipleChoiceAnswerDto1 multipleChoiceAnswerDto1 : set ) {
            set1.add( multipleChoiceAnswerDto1ToMultipleChoiceAnswer( multipleChoiceAnswerDto1 ) );
        }

        return set1;
    }

    protected QuestionDto.WritingAnswerDto1 writingAnswerToWritingAnswerDto1(WritingAnswer writingAnswer) {
        if ( writingAnswer == null ) {
            return null;
        }

        QuestionDto.WritingAnswerDto1 writingAnswerDto1 = new QuestionDto.WritingAnswerDto1();

        writingAnswerDto1.setId( writingAnswer.getId() );
        writingAnswerDto1.setContent( writingAnswer.getContent() );

        return writingAnswerDto1;
    }

    protected Set<QuestionDto.WritingAnswerDto1> writingAnswerSetToWritingAnswerDto1Set(Set<WritingAnswer> set) {
        if ( set == null ) {
            return null;
        }

        Set<QuestionDto.WritingAnswerDto1> set1 = new LinkedHashSet<QuestionDto.WritingAnswerDto1>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( WritingAnswer writingAnswer : set ) {
            set1.add( writingAnswerToWritingAnswerDto1( writingAnswer ) );
        }

        return set1;
    }

    protected QuestionDto.MultipleChoiceAnswerDto1 multipleChoiceAnswerToMultipleChoiceAnswerDto1(MultipleChoiceAnswer multipleChoiceAnswer) {
        if ( multipleChoiceAnswer == null ) {
            return null;
        }

        QuestionDto.MultipleChoiceAnswerDto1 multipleChoiceAnswerDto1 = new QuestionDto.MultipleChoiceAnswerDto1();

        multipleChoiceAnswerDto1.setId( multipleChoiceAnswer.getId() );
        multipleChoiceAnswerDto1.setContent( multipleChoiceAnswer.getContent() );
        multipleChoiceAnswerDto1.setIsCorrect( multipleChoiceAnswer.getIsCorrect() );

        return multipleChoiceAnswerDto1;
    }

    protected Set<QuestionDto.MultipleChoiceAnswerDto1> multipleChoiceAnswerSetToMultipleChoiceAnswerDto1Set(Set<MultipleChoiceAnswer> set) {
        if ( set == null ) {
            return null;
        }

        Set<QuestionDto.MultipleChoiceAnswerDto1> set1 = new LinkedHashSet<QuestionDto.MultipleChoiceAnswerDto1>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MultipleChoiceAnswer multipleChoiceAnswer : set ) {
            set1.add( multipleChoiceAnswerToMultipleChoiceAnswerDto1( multipleChoiceAnswer ) );
        }

        return set1;
    }

    private void linkWritingAnswerSet(Question question) {
        question.getWritingAnswerSet().forEach(writingAnswerSet -> writingAnswerSet.setQuestionId(question));
    }

    private void linkMultipleChoiceAnswerSet(Question question) {
        question.getMultipleChoiceAnswerSet().forEach(multipleChoiceAnswerSet -> multipleChoiceAnswerSet.setQuestionId(question));
    }

}
