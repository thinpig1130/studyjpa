package me.manylove.thejavatest;

public class Member {
    private Long id;

    private String Name;

    public Member(Long id, String name) {
        this.id = id;
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
