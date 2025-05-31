package com.ou.mappers;

import com.ou.dto.ExerciseAttemptAnswerDto;
import com.ou.pojo.ExerciseAttempt;
import com.ou.pojo.ExerciseAttemptAnswer;
import com.ou.pojo.Question;
import javax.annotation.processing.Generated;

import com.ou.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseAttemptAnswerMapper {

    @Autowired
    private QuestionRepository questionRepository;

    
    public ExerciseAttemptAnswer toEntity(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto) {
        if ( exerciseAttemptAnswerDto == null ) {
            return null;
        }

        ExerciseAttemptAnswer exerciseAttemptAnswer = new ExerciseAttemptAnswer();

//        exerciseAttemptAnswer.setQuestionId( exerciseAttemptAnswerDtoToQuestion( exerciseAttemptAnswerDto ) );
        exerciseAttemptAnswer.setAttemptId( exerciseAttemptAnswerDtoToExerciseAttempt( exerciseAttemptAnswerDto ) );
        exerciseAttemptAnswer.setId( exerciseAttemptAnswerDto.getId() );
        exerciseAttemptAnswer.setAnswerText( exerciseAttemptAnswerDto.getAnswerText() );
        exerciseAttemptAnswer.setIsCorrect( exerciseAttemptAnswerDto.getIsCorrect() );
        exerciseAttemptAnswer.setScore( exerciseAttemptAnswerDto.getScore() );

        if (exerciseAttemptAnswerDto.getQuestionIdId() != null) {
            Question question = questionRepository.getQuestionById(exerciseAttemptAnswerDto.getQuestionIdId());
            if (question == null) {
                throw new IllegalArgumentException("Question with ID " + exerciseAttemptAnswerDto.getQuestionIdId() + " does not exist.");
            }
            exerciseAttemptAnswer.setQuestionId(question);
        }

        return exerciseAttemptAnswer;
    }

    
    public ExerciseAttemptAnswerDto toDto(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        if ( exerciseAttemptAnswer == null ) {
            return null;
        }

        ExerciseAttemptAnswerDto exerciseAttemptAnswerDto = new ExerciseAttemptAnswerDto();

        exerciseAttemptAnswerDto.setQuestionIdId( exerciseAttemptAnswerQuestionIdId( exerciseAttemptAnswer ) );
        exerciseAttemptAnswerDto.setAttemptIdId( exerciseAttemptAnswerAttemptIdId( exerciseAttemptAnswer ) );
        exerciseAttemptAnswerDto.setId( exerciseAttemptAnswer.getId() );
        exerciseAttemptAnswerDto.setAnswerText( exerciseAttemptAnswer.getAnswerText() );
        exerciseAttemptAnswerDto.setIsCorrect( exerciseAttemptAnswer.getIsCorrect() );
        exerciseAttemptAnswerDto.setScore( exerciseAttemptAnswer.getScore() );

        return exerciseAttemptAnswerDto;
    }

    
    public ExerciseAttemptAnswer partialUpdate(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto, ExerciseAttemptAnswer exerciseAttemptAnswer) {
        if ( exerciseAttemptAnswerDto == null ) {
            return exerciseAttemptAnswer;
        }

        if ( exerciseAttemptAnswer.getQuestionId() == null ) {
            exerciseAttemptAnswer.setQuestionId( new Question() );
        }
        exerciseAttemptAnswerDtoToQuestion1( exerciseAttemptAnswerDto, exerciseAttemptAnswer.getQuestionId() );
        if ( exerciseAttemptAnswer.getAttemptId() == null ) {
            exerciseAttemptAnswer.setAttemptId( new ExerciseAttempt() );
        }
        exerciseAttemptAnswerDtoToExerciseAttempt1( exerciseAttemptAnswerDto, exerciseAttemptAnswer.getAttemptId() );
        if ( exerciseAttemptAnswerDto.getId() != null ) {
            exerciseAttemptAnswer.setId( exerciseAttemptAnswerDto.getId() );
        }
        if ( exerciseAttemptAnswerDto.getAnswerText() != null ) {
            exerciseAttemptAnswer.setAnswerText( exerciseAttemptAnswerDto.getAnswerText() );
        }
        if ( exerciseAttemptAnswerDto.getIsCorrect() != null ) {
            exerciseAttemptAnswer.setIsCorrect( exerciseAttemptAnswerDto.getIsCorrect() );
        }
        if ( exerciseAttemptAnswerDto.getScore() != null ) {
            exerciseAttemptAnswer.setScore( exerciseAttemptAnswerDto.getScore() );
        }

        return exerciseAttemptAnswer;
    }

    protected Question exerciseAttemptAnswerDtoToQuestion(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto) {
        if ( exerciseAttemptAnswerDto == null ) {
            return null;
        }

        Question question = new Question();

        question.setId( exerciseAttemptAnswerDto.getQuestionIdId() );

        return question;
    }

    protected ExerciseAttempt exerciseAttemptAnswerDtoToExerciseAttempt(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto) {
        if ( exerciseAttemptAnswerDto == null ) {
            return null;
        }

        ExerciseAttempt exerciseAttempt = new ExerciseAttempt();

        exerciseAttempt.setId( exerciseAttemptAnswerDto.getAttemptIdId() );

        return exerciseAttempt;
    }

    private Integer exerciseAttemptAnswerQuestionIdId(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        Question questionId = exerciseAttemptAnswer.getQuestionId();
        if ( questionId == null ) {
            return null;
        }
        return questionId.getId();
    }

    private Integer exerciseAttemptAnswerAttemptIdId(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        ExerciseAttempt attemptId = exerciseAttemptAnswer.getAttemptId();
        if ( attemptId == null ) {
            return null;
        }
        return attemptId.getId();
    }

    protected void exerciseAttemptAnswerDtoToQuestion1(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto, Question mappingTarget) {
        if ( exerciseAttemptAnswerDto == null ) {
            return;
        }

        if ( exerciseAttemptAnswerDto.getQuestionIdId() != null ) {
            mappingTarget.setId( exerciseAttemptAnswerDto.getQuestionIdId() );
        }
    }

    protected void exerciseAttemptAnswerDtoToExerciseAttempt1(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto, ExerciseAttempt mappingTarget) {
        if ( exerciseAttemptAnswerDto == null ) {
            return;
        }

        if ( exerciseAttemptAnswerDto.getAttemptIdId() != null ) {
            mappingTarget.setId( exerciseAttemptAnswerDto.getAttemptIdId() );
        }
    }
}
