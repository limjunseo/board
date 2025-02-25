package com.jun.board_project.domain.member.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId);

        if( member == null ){
            throw new UsernameNotFoundException(memberId);
        }

        return new MemberDetails(member);
    }

    public void save(MemberForm memberForm){
        if(memberRepository.isExistMember(memberForm.getMemberId())){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }


        if(!memberForm.getMemberPw().equals(memberForm.getMemberConfirmPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = memberForm.toMember();

        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));
        memberRepository.save(member);
    }

    public Member findMemberById(String memberId){
        return memberRepository.findByMemberId(memberId);
    }

    public void updateSeqloginYn(){
        memberRepository.updateSeqloginYn();
    }

    public void updateNewYn() {
        memberRepository.updateNewYn();
    }

//    public void updateMembershipYn() {
//        memberRepository.updateMembershipYn();
//    }

//    public void updateMemberRank() {
//        memberRepository.updateMemberRank();
//    }


}