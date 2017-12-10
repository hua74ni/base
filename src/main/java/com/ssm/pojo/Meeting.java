package com.ssm.pojo;

/**
 * Created by huangdonghua on 2017/11/18.
 */
public class Meeting {

    private String meetingTopics;
    private String meetingNature;
    private String meetingBeginTime;
    private String meetingPlace;

    public String getMeetingTopics() {
        return meetingTopics;
    }

    public void setMeetingTopics(String meetingTopics) {
        this.meetingTopics = meetingTopics;
    }

    public String getMeetingNature() {
        return meetingNature;
    }

    public void setMeetingNature(String meetingNature) {
        this.meetingNature = meetingNature;
    }

    public String getMeetingBeginTime() {
        return meetingBeginTime;
    }

    public void setMeetingBeginTime(String meetingBeginTime) {
        this.meetingBeginTime = meetingBeginTime;
    }

    public String getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }
}
