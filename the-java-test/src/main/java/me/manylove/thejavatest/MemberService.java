package me.manylove.thejavatest;


import java.util.Optional;

public interface MemberService {

    Optional<Member> finById(Long id);

    void notify(Study newstudy);
}
