package com.springboot.webservice.board.domain.entity;

import com.springboot.webservice.etc.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)  //  기본 생성자 자동 추가
@Getter //  클래스 내 모든 필드의 Getter 메소드 자동 생성(getID())
@Entity //  테이블과 링크될 클래스
@Table(name = "board")
public class BoardEntity extends BaseTimeEntity {

    @Id //  해당 테이블의 PK(Primary Key) 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  PK의 생성 규칙
    private Long id;

    //  @Column : 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    //  사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder    //  해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
