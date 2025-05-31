package com.ou.mappers;

import com.ou.dto.ExerciseAttemptAnswerDto;
import com.ou.dto.ExerciseAttemptDto;
import com.ou.pojo.Exercise;
import com.ou.pojo.ExerciseAttempt;
import com.ou.pojo.ExerciseAttemptAnswer;
import com.ou.pojo.ExerciseScoreStatus;
import com.ou.pojo.Student;
import com.ou.pojo.Question;
import com.ou.repositories.QuestionRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

import com.ou.services.ExerciseAttemptAnswerService;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExerciseAttemptMapper {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExerciseAttemptAnswerService exerciseAttemptAnswerService;

    public ExerciseAttempt toEntity(ExerciseAttemptDto exerciseAttemptDto) {
        if ( exerciseAttemptDto == null ) {
            return null;
        }

        ExerciseAttempt exerciseAttempt = new ExerciseAttempt();

        exerciseAttempt.setStudentId( exerciseAttemptDtoToStudent( exerciseAttemptDto ) );
        exerciseAttempt.setStatusId( exerciseAttemptDtoToExerciseScoreStatus( exerciseAttemptDto ) );
        exerciseAttempt.setExerciseId( exerciseAttemptDtoToExercise( exerciseAttemptDto ) );
        exerciseAttempt.setId( exerciseAttemptDto.getId() );
        exerciseAttempt.setStartedAt( exerciseAttemptDto.getStartedAt() );
        exerciseAttempt.setSubmittedAt( exerciseAttemptDto.getSubmittedAt() );
        exerciseAttempt.setTotalScore( exerciseAttemptDto.getTotalScore() );
        exerciseAttempt.setResponse( exerciseAttemptDto.getResponse() );
        exerciseAttempt.setExerciseAttemptAnswerSet( exerciseAttemptAnswerDtoSetToExerciseAttemptAnswerSet( exerciseAttemptDto.getExerciseAttemptAnswerSet() ) );

        linkExerciseAttemptAnswerSet( exerciseAttempt );

        return exerciseAttempt;
    }

    @Mapping(target = "exerciseAttemptAnswerSet", ignore = true)
    public ExerciseAttemptDto toDto(ExerciseAttempt exerciseAttempt) {
        if ( exerciseAttempt == null ) {
            return null;
        }

        ExerciseAttemptDto exerciseAttemptDto = new ExerciseAttemptDto();

        exerciseAttemptDto.setStudentIdId( exerciseAttemptStudentIdId( exerciseAttempt ) );
        exerciseAttemptDto.setStatusIdName( exerciseAttemptStatusIdName( exerciseAttempt ) );
        exerciseAttemptDto.setStatusIdId( exerciseAttemptStatusIdId( exerciseAttempt ) );
        exerciseAttemptDto.setExerciseIdId( exerciseAttemptExerciseIdId( exerciseAttempt ) );
        exerciseAttemptDto.setId( exerciseAttempt.getId() );
        exerciseAttemptDto.setStartedAt( exerciseAttempt.getStartedAt() );
        exerciseAttemptDto.setSubmittedAt( exerciseAttempt.getSubmittedAt() );
        exerciseAttemptDto.setTotalScore( exerciseAttempt.getTotalScore() );
        exerciseAttemptDto.setResponse( exerciseAttempt.getResponse() );

        return exerciseAttemptDto;
    }


    public ExerciseAttempt partialUpdate(ExerciseAttemptDto exerciseAttemptDto, ExerciseAttempt exerciseAttempt) {
        if ( exerciseAttemptDto == null ) {
            return exerciseAttempt;
        }

        if ( exerciseAttempt.getStudentId() == null ) {
            exerciseAttempt.setStudentId( new Student() );
        }
        exerciseAttemptDtoToStudent1( exerciseAttemptDto, exerciseAttempt.getStudentId() );
        if ( exerciseAttempt.getStatusId() == null ) {
            exerciseAttempt.setStatusId( new ExerciseScoreStatus() );
        }
        exerciseAttemptDtoToExerciseScoreStatus1( exerciseAttemptDto, exerciseAttempt.getStatusId() );
        if ( exerciseAttempt.getExerciseId() == null ) {
            exerciseAttempt.setExerciseId( new Exercise() );
        }
        exerciseAttemptDtoToExercise1( exerciseAttemptDto, exerciseAttempt.getExerciseId() );
        if ( exerciseAttemptDto.getId() != null ) {
            exerciseAttempt.setId( exerciseAttemptDto.getId() );
        }
        if ( exerciseAttemptDto.getStartedAt() != null ) {
            exerciseAttempt.setStartedAt( exerciseAttemptDto.getStartedAt() );
        }
        if ( exerciseAttemptDto.getSubmittedAt() != null ) {
            exerciseAttempt.setSubmittedAt( exerciseAttemptDto.getSubmittedAt() );
        }
        if ( exerciseAttemptDto.getTotalScore() != null ) {
            exerciseAttempt.setTotalScore( exerciseAttemptDto.getTotalScore() );
        }
        if ( exerciseAttemptDto.getResponse() != null ) {
            exerciseAttempt.setResponse( exerciseAttemptDto.getResponse() );
        }
        if ( exerciseAttempt.getExerciseAttemptAnswerSet() != null ) {
            Set<ExerciseAttemptAnswer> set = exerciseAttemptAnswerDtoSetToExerciseAttemptAnswerSet( exerciseAttemptDto.getExerciseAttemptAnswerSet() );
            if ( set != null ) {
                exerciseAttempt.getExerciseAttemptAnswerSet().clear();
                exerciseAttempt.getExerciseAttemptAnswerSet().addAll( set );
            }
        }
        else {
            Set<ExerciseAttemptAnswer> set = exerciseAttemptAnswerDtoSetToExerciseAttemptAnswerSet( exerciseAttemptDto.getExerciseAttemptAnswerSet() );
            if ( set != null ) {
                exerciseAttempt.setExerciseAttemptAnswerSet( set );
            }
        }

        linkExerciseAttemptAnswerSet( exerciseAttempt );

        return exerciseAttempt;
    }

    protected Student exerciseAttemptDtoToStudent(ExerciseAttemptDto exerciseAttemptDto) {
        if ( exerciseAttemptDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( exerciseAttemptDto.getStudentIdId() );

        return student;
    }

    protected ExerciseScoreStatus exerciseAttemptDtoToExerciseScoreStatus(ExerciseAttemptDto exerciseAttemptDto) {
        if ( exerciseAttemptDto == null ) {
            return null;
        }

        ExerciseScoreStatus exerciseScoreStatus = new ExerciseScoreStatus();

        exerciseScoreStatus.setName( exerciseAttemptDto.getStatusIdName() );
        exerciseScoreStatus.setId( exerciseAttemptDto.getStatusIdId() );

        return exerciseScoreStatus;
    }

    protected Exercise exerciseAttemptDtoToExercise(ExerciseAttemptDto exerciseAttemptDto) {
        if ( exerciseAttemptDto == null ) {
            return null;
        }

        Exercise exercise = new Exercise();

        exercise.setId( exerciseAttemptDto.getExerciseIdId() );

        return exercise;
    }

    // FIXED: Added questionId mapping with repository lookup
    protected ExerciseAttemptAnswer exerciseAttemptAnswerDtoToExerciseAttemptAnswer(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto) {
        if ( exerciseAttemptAnswerDto == null ) {
            return null;
        }

        ExerciseAttemptAnswer exerciseAttemptAnswer = new ExerciseAttemptAnswer();

        exerciseAttemptAnswer.setId( exerciseAttemptAnswerDto.getId() );
        exerciseAttemptAnswer.setAnswerText( exerciseAttemptAnswerDto.getAnswerText() );
        exerciseAttemptAnswer.setIsCorrect( exerciseAttemptAnswerDto.getIsCorrect() );
        exerciseAttemptAnswer.setScore( exerciseAttemptAnswerDto.getScore() );

        // FIXED: Set questionId from DTO
        if (exerciseAttemptAnswerDto.getQuestionIdId() != null) {
            Question question = questionRepository.getQuestionById(exerciseAttemptAnswerDto.getQuestionIdId());
            if (question == null) {
                throw new IllegalArgumentException("Question with ID " + exerciseAttemptAnswerDto.getQuestionIdId() + " does not exist.");
            }
            exerciseAttemptAnswer.setQuestionId(question);
        }

        return exerciseAttemptAnswer;
    }

    protected Set<ExerciseAttemptAnswer> exerciseAttemptAnswerDtoSetToExerciseAttemptAnswerSet(Set<ExerciseAttemptAnswerDto> set) {
        if ( set == null ) {
            return null;
        }

//        Set<ExerciseAttemptAnswer> set1 = new LinkedHashSet<ExerciseAttemptAnswer>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
//        for ( ExerciseAttemptAnswerDto exerciseAttemptAnswerDto : set ) {
//            set1.add( exerciseAttemptAnswerDtoToExerciseAttemptAnswer( exerciseAttemptAnswerDto ) );
//        }
//
//        return set1;

        // Không chuyển qua List nữa, tạo Set và add từng phần tử
        Set<ExerciseAttemptAnswer> resultSet = new LinkedHashSet<>();

        for(ExerciseAttemptAnswerDto exerciseAttemptAnswerDto : set) {
            ExerciseAttemptAnswer exerciseAttemptAnswer = exerciseAttemptAnswerDtoToExerciseAttemptAnswer(exerciseAttemptAnswerDto);

            // Add trực tiếp, không lo về equals/hashCode vì ta kiểm tra business logic
            boolean shouldAdd = true;

            // Kiểm tra duplicate theo questionId (business logic)
            for (ExerciseAttemptAnswer existing : resultSet) {
                if (existing.getQuestionId() != null &&
                        exerciseAttemptAnswer.getQuestionId() != null &&
                        existing.getQuestionId().getId().equals(exerciseAttemptAnswer.getQuestionId().getId())) {
                    shouldAdd = false;
                    System.out.println("Skipping duplicate answer for question: " +
                            exerciseAttemptAnswer.getQuestionId().getId());
                    break;
                }
            }

            if (shouldAdd) {
                resultSet.add(exerciseAttemptAnswer);
            }
        }

        return resultSet;
    }

    private Integer exerciseAttemptStudentIdId(ExerciseAttempt exerciseAttempt) {
        Student studentId = exerciseAttempt.getStudentId();
        if ( studentId == null ) {
            return null;
        }
        return studentId.getId();
    }

    private String exerciseAttemptStatusIdName(ExerciseAttempt exerciseAttempt) {
        ExerciseScoreStatus statusId = exerciseAttempt.getStatusId();
        if ( statusId == null ) {
            return null;
        }
        return statusId.getName();
    }

    private Integer exerciseAttemptStatusIdId(ExerciseAttempt exerciseAttempt) {
        ExerciseScoreStatus statusId = exerciseAttempt.getStatusId();
        if ( statusId == null ) {
            return null;
        }
        return statusId.getId();
    }

    private Integer exerciseAttemptExerciseIdId(ExerciseAttempt exerciseAttempt) {
        Exercise exerciseId = exerciseAttempt.getExerciseId();
        if ( exerciseId == null ) {
            return null;
        }
        return exerciseId.getId();
    }

    protected ExerciseAttemptAnswerDto exerciseAttemptAnswerToExerciseAttemptAnswerDto(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        if ( exerciseAttemptAnswer == null ) {
            return null;
        }

        ExerciseAttemptAnswerDto exerciseAttemptAnswerDto = new ExerciseAttemptAnswerDto();

        exerciseAttemptAnswerDto.setId( exerciseAttemptAnswer.getId() );
        exerciseAttemptAnswerDto.setAnswerText( exerciseAttemptAnswer.getAnswerText() );
        exerciseAttemptAnswerDto.setIsCorrect( exerciseAttemptAnswer.getIsCorrect() );
        exerciseAttemptAnswerDto.setScore( exerciseAttemptAnswer.getScore() );

        // Add questionId and attemptId mapping
        if (exerciseAttemptAnswer.getQuestionId() != null) {
            exerciseAttemptAnswerDto.setQuestionIdId(exerciseAttemptAnswer.getQuestionId().getId());
        }
        if (exerciseAttemptAnswer.getAttemptId() != null) {
            exerciseAttemptAnswerDto.setAttemptIdId(exerciseAttemptAnswer.getAttemptId().getId());
        }

        return exerciseAttemptAnswerDto;
    }

    protected Set<ExerciseAttemptAnswerDto> exerciseAttemptAnswerSetToExerciseAttemptAnswerDtoSet(Set<ExerciseAttemptAnswer> set) {
        if ( set == null ) {
            return null;
        }

        Set<ExerciseAttemptAnswerDto> set1 = new LinkedHashSet<ExerciseAttemptAnswerDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ExerciseAttemptAnswer exerciseAttemptAnswer : set ) {
            set1.add( exerciseAttemptAnswerToExerciseAttemptAnswerDto( exerciseAttemptAnswer ) );
        }

        return set1;
    }

    protected void exerciseAttemptDtoToStudent1(ExerciseAttemptDto exerciseAttemptDto, Student mappingTarget) {
        if ( exerciseAttemptDto == null ) {
            return;
        }

        if ( exerciseAttemptDto.getStudentIdId() != null ) {
            mappingTarget.setId( exerciseAttemptDto.getStudentIdId() );
        }
    }

    protected void exerciseAttemptDtoToExerciseScoreStatus1(ExerciseAttemptDto exerciseAttemptDto, ExerciseScoreStatus mappingTarget) {
        if ( exerciseAttemptDto == null ) {
            return;
        }

        if ( exerciseAttemptDto.getStatusIdName() != null ) {
            mappingTarget.setName( exerciseAttemptDto.getStatusIdName() );
        }
        if ( exerciseAttemptDto.getStatusIdId() != null ) {
            mappingTarget.setId( exerciseAttemptDto.getStatusIdId() );
        }
    }

    protected void exerciseAttemptDtoToExercise1(ExerciseAttemptDto exerciseAttemptDto, Exercise mappingTarget) {
        if ( exerciseAttemptDto == null ) {
            return;
        }

        if ( exerciseAttemptDto.getExerciseIdId() != null ) {
            mappingTarget.setId( exerciseAttemptDto.getExerciseIdId() );
        }
    }

    public void linkExerciseAttemptAnswerSet(ExerciseAttempt exerciseAttempt) {
        if (exerciseAttempt.getExerciseAttemptAnswerSet() != null) {
            exerciseAttempt.getExerciseAttemptAnswerSet().forEach(exerciseAttemptAnswer ->
                    exerciseAttemptAnswer.setAttemptId(exerciseAttempt));
        }
    }
}