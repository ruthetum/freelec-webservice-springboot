package org.example.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들을 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 auditing 기능 포함 (자동으로 시간 매핑)
public abstract class BaseTimeEntity {
    @CreatedDate // Entity가 생성되어 저장되었을 때 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}
