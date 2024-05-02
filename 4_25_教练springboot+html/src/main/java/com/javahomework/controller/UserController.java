package com.javahomework.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author javahomework
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private INoticeService iNoticeService;

    @Autowired
    private IFeedbackService iFeedbackService;

    @Autowired
    private IReservationService iReservationService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/hi")
    @ResponseBody
    public String hi() {
        return "<div style=\"width: 100%;text-align: center;\"><h1 >hi</h1></div>";
    }

    @GetMapping("/list")
    public List<User> list() {
        return iUserService.list();
    }

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        List<User> list = iUserService.lambdaQuery().eq(User::getNo, user.getNo()).list();
        if (!list.isEmpty()){
            return Result.fail("The account change already exists, please change the account");
        }
        iUserService.save(user);
        return Result.suc();
    }

    //更新
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        if (user.getRoleId() == 1 && user.getStar()!=null){
            User userOld = iUserService.getById(user.getId());
            if (user.getStar() > userOld.getStar()){
                Notice notice = new Notice();
                notice.setForm("Level prompt!");
                notice.setToUser(user.getId());
                notice.setTxt("Congratulations! your star rating has been raised to level "+ user.getStar());
                iNoticeService.save(notice);
            }
            else if (user.getStar() < userOld.getStar()){
                Notice notice = new Notice();
                notice.setForm("Level decrease");
                notice.setToUser(user.getId());
                notice.setTxt("We regret to inform you that your level has been downgraded to level "+ user.getStar() +" by the administrator");
                iNoticeService.save(notice);
            }
        }
        return iUserService.updateById(user) ? Result.suc() : Result.fail();
    }

    //个体查询
    @GetMapping("/find")
    public Result find(@RequestParam String id) {
        if (iUserService.lambdaQuery().eq(User::getId, id).list().isEmpty())
            return Result.fail();
        User user = iUserService.lambdaQuery().eq(User::getId, id).list().get(0);
        List<Feedback> list = iFeedbackService.lambdaQuery().eq(Feedback::getCoachId, id).list();

        List<Reservation> reservationList;
        if (user.getRoleId()==1)
            reservationList = iReservationService.lambdaQuery().eq(Reservation::getCoachId, id).list();
        else if (user.getRoleId()==2)
            reservationList = iReservationService.lambdaQuery().eq(Reservation::getUserId, id).list();
        else
            reservationList = iReservationService.lambdaQuery().eq(Reservation::getCoachId, id).or().eq(Reservation::getUserId, id).list();

        List<Notice> notices = iNoticeService.lambdaQuery().eq(Notice::getToUser, id).or().eq(Notice::getToUser, -1).list();

        List<User> coach = iUserService.lambdaQuery().eq(User::getRoleId, 1).list();
        coach = sortCoachList(user.getStar(),coach);
        HashMap<String, Object> map = new HashMap<>();
        map.put("user",user);
        map.put("feedbacks",list);
        map.put("notices",notices);
        map.put("coach",coach);

        map.put("reservationList",reservationList);
        return Result.suc(map);
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        System.out.println(id);
        return iUserService.removeById(id) ? Result.suc() : Result.fail();
    }

    //登陆
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        List<User> list = iUserService.lambdaQuery()
                .eq(User::getNo, user.getNo())
                .eq(User::getPassword, user.getPassword()).list();

        if (!list.isEmpty()){
            User user1 = list.get(0);
            if (user1.getRoleId()==10){
                return Result.fail("Please wait for the management to review your coach account");
            }

            // 创建 SimpleDateFormat 对象，指定日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            // 创建一个当前日期对象
            Date currentDate = new Date();
            // 使用 SimpleDateFormat 格式化日期
            String formattedDate = sdf.format(currentDate);
            user1.setLastTime(formattedDate);

            if (user1.getExpiration()!=null&&user1.getExpiration()!=0){
                long currentTimeSeconds = System.currentTimeMillis() / 1000;
                long expirationTimeSeconds = user1.getExpiration();
                long sevenDaysInSeconds = 7 * 24 * 60 * 60; // 7天的秒数

                if(expirationTimeSeconds <= currentTimeSeconds){
                    user1.setStar(0);
                    user1.setNum(0);
                    Notice notice = new Notice();
                    notice.setForm("Membership expired");
                    notice.setToUser(user1.getId());
                    notice.setTxt("Your membership has expired and has been reset to zero. Please reorder");
                    iNoticeService.save(notice);
                }
                else if(expirationTimeSeconds - currentTimeSeconds <= sevenDaysInSeconds){
                    Notice notice = new Notice();
                    notice.setForm("Membership is about to expire");
                    notice.setToUser(user1.getId());
                    notice.setTxt("Your membership will expire in 7 days");
                    iNoticeService.save(notice);
                }
            }

            List<Notice> message = iNoticeService.lambdaQuery()
                    .eq(Notice::getToUser, user1.getId())
                    .or()
                    .eq(Notice::getToUser, -1).list();
            List<Notice> message2 = new ArrayList<>();
            for (Notice notice : message) {
                if(notice.getState() == 1)
                    continue;
                else if (notice.getToUser() != -1)
                    notice.setState(1);
                iNoticeService.updateById(notice);
                message2.add(notice);
            }
            HashMap<String, Object>  map = new HashMap<>();
            iUserService.updateById(user1);
            map.put("user",user1);
            map.put("message",message2);
            return Result.suc(map);
        }
        return Result.fail("Account or password error");
    }

    //头像
    @RequestMapping("/up/{userId}")
    public Result upload(@RequestParam("file") MultipartFile img, @PathVariable Integer userId ){
        String url=System.getProperty("user.dir");//获取项目绝对路径
        if(!img.isEmpty()){
            Integer id=userId;
            try {
                //保存上传的资源文件路径，路径在部署jar包同级目录。
                String path = url+"/static/img/";
                File dir = new File(path);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                //参数就是图片保存在服务器的本地地址
                img.transferTo(new File(path+id+".png"));
                String imgHtml = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath())+"/static/img/"+id+".png";
                System.out.println(imgHtml);
                User byId = iUserService.getById(userId);
                byId.setAvatar(imgHtml);
                iUserService.updateById(byId);
                return  Result.suc(iUserService.getById(userId));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Result.fail();
        }
        return Result.fail();
    }

    //账号唯一性请求
    @PostMapping("/findByNo")
    public Result findByNo(@RequestBody HashMap param) {
        String no = (String) param.get("no");
        List<User> list = iUserService.lambdaQuery().eq(User::getNo, no).list();
        return !list.isEmpty() ? Result.suc(list) : Result.fail();
    }

    //模糊查询
    @PostMapping("/listPageN")
    public Result listPageN(@RequestBody HashMap param) {
        String name = (String) param.get("name");
        Integer id = (Integer) param.get("id");
        String star = (String) param.get("star");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();

        if (StringUtils.isNotBlank(star)) {
            lambdaQueryWrapper.eq(User::getStar, star);
        }
        if (id != null) {
            lambdaQueryWrapper.eq(User::getId, id);
        }
        if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
            lambdaQueryWrapper.like(User::getName, name);
        }
        List<User> userList = iUserService.list(lambdaQueryWrapper);

        return Result.suc(userList);
    }

    public List<User> sortCoachList(int star, List<User> coachList) {
        // 首先过滤出星级为指定值的教练
        List<User> filteredByStar = coachList.stream()
                .filter(c -> c.getStar() == star)
                .collect(Collectors.toList());

        // 对星级为指定值的教练按分数从大到小排序
        Collections.sort(filteredByStar, Comparator.comparing(User::getScore).reversed());

        // 对其余教练按星级从小到大排序，同等星级内按分数从大到小排序
        List<User> rest = coachList.stream()
                .filter(c -> c.getStar() != star)
                .sorted(Comparator.comparing(User::getStar).thenComparing(User::getScore).reversed())
                .toList();

        // 合并两个排序后的列表
        filteredByStar.addAll(rest);

        return filteredByStar;
    }


}
