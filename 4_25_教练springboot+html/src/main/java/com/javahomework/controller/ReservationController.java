package com.javahomework.controller;

import com.javahomework.common.QueryPageParam;
import com.javahomework.common.Result;
import com.javahomework.entity.Feedback;
import com.javahomework.entity.Notice;
import com.javahomework.entity.Reservation;
import com.javahomework.entity.User;
import com.javahomework.service.IFeedbackService;
import com.javahomework.service.INoticeService;
import com.javahomework.service.IReservationService;
import com.javahomework.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com
 * @since 2024-04-25
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    private INoticeService iNoticeService;

    @Autowired
    private IFeedbackService iFeedbackService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IUserService iUserService;

    //添加且确认无冲突
    @PostMapping("/add")
    public Result add(@RequestBody Reservation reservation) {
        User user = iUserService.getById(reservation.getUserId());
        User Coach = iUserService.getById(reservation.getCoachId());
        if (user.getNum()<=0){
           return Result.fail("Your reservation attempts have been exhausted. Please recharge or subscribe to the plan");
        }
        if (user.getStar() < Coach.getStar()){
            return Result.fail("Please purchase a higher-level plan to unlock this coach");
        }

        List<Reservation> list = iReservationService.lambdaQuery().eq(Reservation::getUserId, reservation.getUserId()).list();
        List<Reservation> list2 = iReservationService.lambdaQuery().eq(Reservation::getCoachId, reservation.getCoachId()).list();
        list.addAll(list2);
        for (Reservation reservation1 : list) {
            if (reservation1.getState() == -1) continue;
            if (reservation.getStartTime()>=reservation1.getStartTime()&&reservation.getStartTime()<=reservation1.getEndTime())
                return Result.fail("You or the coach have a time conflict, please choose another time");
            if (reservation.getEndTime()>=reservation1.getStartTime()&&reservation.getEndTime()<=reservation1.getEndTime())
                return Result.fail("You or the coach have a time conflict, please choose another time");
        }

        iReservationService.save(reservation);
        user.setNum(user.getNum()-1);
        iUserService.updateById(user);

        Notice notice = new Notice();
        notice.setForm("Appointment Notification");
        notice.setToUser(reservation.getCoachId());
        String startTime = new Date(reservation.getStartTime()).toString();
        String endTime = new Date(reservation.getEndTime()).toString();
        notice.setTxt("You have a new course appointment at "+startTime+" to "+endTime+" please confirm immediately.");
        iNoticeService.save(notice);


        return Result.suc();
    }

    @PostMapping("/confirm")
    public Result confirm(@RequestBody HashMap req) {
        System.out.println(req);
        Integer id = (Integer) req.get("id");
        Integer state = (Integer) req.get("state");
        Integer scoreInteger = (Integer) req.get("score");
        float score = scoreInteger != null ? scoreInteger.floatValue() : 0.0f;


        Reservation reservation = iReservationService.getById(id);
        reservation.setState(state);
        iReservationService.updateById(reservation);
        if (state==-1){
            Notice notice = new Notice();
            notice.setForm("Appointment failed");
            notice.setToUser(reservation.getUserId());
            String startTime = new Date(reservation.getStartTime()).toString();
            String endTime = new Date(reservation.getEndTime()).toString();
            notice.setTxt("Your appointment from "+startTime+" to "+endTime+" was cancelled by the coach. Sorry, please make a new appointment");
            iNoticeService.save(notice);

            User user = iUserService.getById(reservation.getUserId());
            user.setNum(user.getNum() + 1);
            iUserService.updateById(user);
        }else if(state==1){
            Notice notice = new Notice();
            notice.setForm("Appointment successful");
            notice.setToUser(reservation.getCoachId());
            String startTime = new Date(reservation.getStartTime()).toString();
            String endTime = new Date(reservation.getEndTime()).toString();
            notice.setTxt("Your scheduled classes from "+startTime+" to "+endTime+" have been successfully booked. Please go to the designated location for classes immediately");
            iNoticeService.save(notice);
            Notice notice2 = new Notice();
            notice2.setForm("Appointment successful");
            notice2.setToUser(reservation.getUserId());
            notice2.setTxt("Your scheduled classes from "+startTime+" to "+endTime+" have been successfully booked. Please go to the designated location for classes immediately");
            iNoticeService.save(notice2);
        }else if(state == 2){
            Long startTime = reservation.getStartTime();
            Long endTime = reservation.getEndTime();
            // 将时间戳转换为 Date 对象
            Date startDate = new Date(startTime * 1000); // 将秒级时间戳转换为毫秒级
            Date endDate = new Date(endTime * 1000);     // 将秒级时间戳转换为毫秒级
            // 计算两个日期对象之间的时间差（单位为毫秒）
            long timeDifferenceInMillis = endDate.getTime() - startDate.getTime();
            // 将时间差转换为小时数
            float minutesDifference = (float) timeDifferenceInMillis / (60 * 60 * 1000);
            User coach = iUserService.getById(reservation.getCoachId());
            coach.setTime(coach.getTime()+minutesDifference);
            coach.setScore(coach.getScore()+score);
            iUserService.updateById(coach);

            if (req.get("feedback")!=null){
                Feedback feedback1 = new Feedback();
                feedback1.setTxt((String) req.get("feedback"));
                feedback1.setCoachId(reservation.getCoachId());
                feedback1.setUserId(reservation.getUserId());
                feedback1.setUserName(reservation.getUserName());
                iFeedbackService.save(feedback1);
            }

        }


        return Result.suc();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.suc(iReservationService.list());
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        System.out.println(id);
        return iReservationService.removeById(id) ? Result.suc() : Result.fail();
    }
}
