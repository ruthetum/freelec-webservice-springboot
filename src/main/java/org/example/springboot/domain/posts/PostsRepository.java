package org.example.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
// DaO 라고 생각, DB Layer 접근자, interface로 생성, JpaRepository<Entity 클래스, PK> 상속