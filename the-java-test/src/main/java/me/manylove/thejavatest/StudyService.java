package me.manylove.thejavatest;


import java.util.Optional;

public class StudyService {

    MemberService memberService;
    StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long id, Study study) {
        Optional<Member> finById = memberService.finById(id);
        study.setOwner(finById.orElseThrow(()-> new IllegalArgumentException("Member doesn't exist for ")));
        Study saveStudy = studyRepository.save(study);
//        memberService.notify(study);
        return saveStudy;
    }

    public Study openStudy(Study study){
        study.open();
        Study openedStudy = studyRepository.save(study);
        memberService.notify(openedStudy);

        return openedStudy;
    }
}
