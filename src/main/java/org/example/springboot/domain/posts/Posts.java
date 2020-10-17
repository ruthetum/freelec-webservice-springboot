package org.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springboot.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드 생성
@NoArgsConstructor // 기본 생성자 자동 추가 ,public Posts(){}와 같은 효과
@Entity // 테이블과 링크될 클래스
public class Posts extends BaseTimeEntity {
    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙, 현재 Auto Increment
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼 정의
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
