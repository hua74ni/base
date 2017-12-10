package com.ssm.scheduler;

import com.ssm.pojo.Meeting;
import com.ssm.pojo.User;
import com.ssm.utils.EmailUtil;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdonghua on 2017/11/18.
 */
@Component
public class TestJob {
    private static Logger logger = Logger.getLogger(TestJob.class);

    @Scheduled(cron = "0 18 15 * * ?")
    public void printSomething(){

        logger.info("<------------------------ job start -------------------------------->");

        Meeting meeting = new Meeting();
        meeting.setMeetingTopics("会议主题");
        meeting.setMeetingNature("网络会议");
        meeting.setMeetingPlace("教学楼2A204教室");
        meeting.setMeetingBeginTime("2017-11-21 9:30:00");

        User user1 = new User();
        user1.setEmail("591327356@qq.com");
        user1.setUsername("黄东华1");

        User user2 = new User();
        user2.setEmail("591327356@qq.com");
        user2.setUsername("黄东华2");

        List<User> userList = new ArrayList<User>();

        userList.add(user1);
        userList.add(user2);

        try {
            EmailUtil.sendMail(meeting, userList,false);
            logger.info(+userList.size()+"条邮箱发送完成");
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("<------------------------ job end -------------------------------->");

    }

}
