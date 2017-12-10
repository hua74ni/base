package com.ssm.utils;

import com.ssm.pojo.Meeting;
import com.ssm.pojo.User;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
/**
 * 电子邮件 工具 测试类
 * @author lintao
 *
 */
public class EmailUtil {

    // 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
    public static String myEmailAccount = "591327356@qq.com";
    public static String myEmailPassword = "emwtjlpxyykdbcgd";

    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // 网易163邮箱的 SMTP 服务器地址为: smtp.163.com
    public static String myEmailSMTPHost = "smtp.qq.com";


//    public static void main(String[] args) throws Exception {
//        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
//        Properties props = new Properties();                    // 参数配置
//        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
//        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
//        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
//
//        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
//        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
//        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
//        /*
//        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
//        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
//        //                  QeQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
//       */
//        final String smtpPort = "465";
//        props.setProperty("mail.smtp.port", smtpPort);
//        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.setProperty("mail.smtp.socketFactory.fallback", "false");
//        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
//        
//
//        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
//        Session session = Session.getDefaultInstance(props);
//        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
//
//        // 3. 创建一封邮件
//        MimeMessage message = createMimeMessage(session, myEmailAccount,meeting,user);
//
//        // 4. 根据 Session 获取邮件传输对象
//        Transport transport = session.getTransport();
//
//        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
//        // 
//        //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
//        //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
//        //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
//        //
//        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
//        //           (1) 邮箱没有开启 SMTP 服务;
//        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
//        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
//        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
//        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
//        //
//        //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
//        transport.connect(myEmailAccount, myEmailPassword);
//
//        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
//        transport.sendMessage(message, message.getAllRecipients());
//
//        // 7. 关闭连接
//        transport.close();
//    }
    
    public static void main(String[] args) throws Exception {
		
    	Meeting meeting = new Meeting();
    	meeting.setMeetingTopics("会议主题");
    	meeting.setMeetingNature("网络会议");
    	meeting.setMeetingPlace("教学楼2A204教室");
    	meeting.setMeetingBeginTime("2017-06-22 10:15:59");
    	
    	User user1 = new User();
    	user1.setEmail("591327356@qq.com");
    	user1.setUsername("黄东华1");
    	
    	User user2 = new User();
    	user2.setEmail("591327356@qq.com");
    	user2.setUsername("黄东华2");
    	
    	List<User> userList = new ArrayList<User>();
    	
    	userList.add(user1);
    	userList.add(user2);
    	
    	sendMail(meeting, userList,true);
    	
	}
    
    public static void sendMail(Meeting meeting,List<User> userList,boolean cancel) throws Exception{
    	
    	// 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        /*
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QeQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
       */
        final String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
    	
    	for (User user : userList) {
			
    		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(session, myEmailAccount,meeting,user,cancel);

            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            // 
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(myEmailAccount, myEmailPassword);

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            transport.close();
    		
		}
    	
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 与服务交互通信的session
     * @param sendMail
     * @param meeting
     * @param user
     * @param cancel
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail,Meeting meeting,User user,boolean cancel) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "会议系统", "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(user.getEmail(), user.getUsername()+"", "UTF-8"));
        

        String [] dateString = DateUtil.DateStr2Array(meeting.getMeetingBeginTime());
        
        // 4. Subject: 邮件主题
//        message.setSubject("2017年6月22日营销部网络视频会议", "UTF-8");
        if(cancel){
        	message.setSubject(dateString[0]+meeting.getMeetingNature()+meeting.getMeetingTopics()+"取消", "UTF-8");
        }else{
        	message.setSubject(dateString[0]+meeting.getMeetingNature()+meeting.getMeetingTopics()+"通知", "UTF-8");
        }
        

        // 5. Content: 邮件正文（可以使用html标签）
//        message.setContent("黄童鞋，用户你好, 今天下午15:00分，在304会议室召开实训分享会，请查收。", "text/html;charset=UTF-8");
//        if(cancel){
//        	message.setContent(user.getRealName().substring(0,1)+"童鞋，用户你好, "+dateString[3]+dateString[1]+dateString[2]+"，在304会议室召开实训分享会，已取消。", "text/html;charset=UTF-8");
//        }else{
//        	message.setContent(user.getRealName().substring(0,1)+"童鞋，用户你好, "+dateString[3]+dateString[1]+dateString[2]+"，在304会议室召开实训分享会，请查收。", "text/html;charset=UTF-8");
//        }
        
        if(cancel){
        	message.setContent(user.getUsername()+"，用户你好, "+dateString[3]+dateString[1]+dateString[2]+"，在"+meeting.getMeetingPlace()+"召开"+meeting.getMeetingNature()+meeting.getMeetingTopics()+"，已取消。", "text/html;charset=UTF-8");
        }else{
        	message.setContent(user.getUsername()+"，用户你好, "+dateString[3]+dateString[1]+dateString[2]+"，在"+meeting.getMeetingPlace()+"召开"+meeting.getMeetingNature()+meeting.getMeetingTopics()+"，请查收。", "text/html;charset=UTF-8");
        }

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }

}