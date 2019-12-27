package com.springboot.webservice.member.domain.repository;

import com.springboot.webservice.member.domain.Role;
import com.springboot.webservice.member.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Role> {
    Optional<MemberEntity> findByUserid(String userUserid);
}
