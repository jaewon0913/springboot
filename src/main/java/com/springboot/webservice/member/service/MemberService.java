package com.springboot.webservice.member.service;

import com.springboot.webservice.member.domain.Role;
import com.springboot.webservice.member.domain.entity.MemberEntity;
import com.springboot.webservice.member.domain.repository.MemberRepository;
import com.springboot.webservice.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(memberDto.toString());
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userUserId) throws UsernameNotFoundException {
        Optional<MemberEntity> userEntityWrapper = memberRepository.findByUserid(userUserId);
        MemberEntity userEntity = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (("ADMIN").equals(userUserId)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getName(), userEntity.getPassword(), authorities);
    }

    @Transactional
    public MemberDto getUserid(String userUserid) {
        MemberDto memberDto;
        try {
            Optional<MemberEntity> memberEntityWrapper = memberRepository.findByUserid(userUserid);
            MemberEntity userEntity = memberEntityWrapper.get();
            memberDto = MemberDto.builder()
                    .userid(userEntity.getUserid())
                    .build();

        } catch (Exception e) {
            memberDto = MemberDto.builder()
                    .userid(null)
                    .build();

        }
        return memberDto;
    }
}
