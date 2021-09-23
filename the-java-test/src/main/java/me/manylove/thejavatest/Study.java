package me.manylove.thejavatest;

import java.util.Date;

public class Study {
    private StudyStatus status;
    private int limit;
    private String name;
    private Member Owner;
    private Date openDateTime;

    public Study() {
        this.status = StudyStatus.DRAFT;
        this.limit = 1;
    }

    public Study(int limit) {
        this();
        this.limit = limit;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public StudyStatus getStatus() {
        return status;
    }

    public void setStatus(StudyStatus status) {
        this.status = status;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit < 1){
            throw new IllegalArgumentException("limit은 0보다 커야 한다.");
        }
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Member getOwner() {
        return Owner;
    }


    public void setOwner(Member owner) {
        Owner = owner;
    }


    public void open(){
        this.setStatus(StudyStatus.OPENED);
        this.setOpenDateTime();

    }

    public Date getOpenDateTime() {
        return openDateTime;
    }

    private void setOpenDateTime() {
        this.openDateTime = new Date();
    }

    @Override
    public String toString() {
        return "Study{" +
                "status=" + status +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
