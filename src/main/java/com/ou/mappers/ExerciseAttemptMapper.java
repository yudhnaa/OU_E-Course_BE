package com.ou.mappers;

import com.ou.dto.ExerciseAttemptDto;
import com.ou.pojo.ExerciseAttempt;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
@Component
public class  ExerciseAttemptMapper {

    
    public ExerciseAttempt toEntity(ExerciseAttemptDto exerciseAttemptDto) {
        if ( exerciseAttemptDto == null ) {
            return null;
        }

        ExerciseAttempt exerciseAttempt = new ExerciseAttempt();

        exerciseAttempt.setId( exerciseAttemptDto.getId() );
        exerciseAttempt.setStartedAt( exerciseAttemptDto.getStartedAt() );
        exerciseAttempt.setSubmittedAt( exerciseAttemptDto.getSubmittedAt() );
        exerciseAttempt.setTotalScore( exerciseAttemptDto.getTotalScore() );
        exerciseAttempt.setResponse( exerciseAttemptDto.getResponse() );

        return exerciseAttempt;
    }

    
    public ExerciseAttemptDto toDto(ExerciseAttempt exerciseAttempt) {
        if ( exerciseAttempt == null ) {
            return null;
        }

        ExerciseAttemptDto exerciseAttemptDto = new ExerciseAttemptDto();

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

        return exerciseAttempt;
    }
}
