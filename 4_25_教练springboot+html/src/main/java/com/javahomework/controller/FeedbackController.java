package com.javahomework.controller;

import com.javahomework.common.Result;
import com.javahomework.entity.Feedback;
import com.javahomework.entity.User;
import com.javahomework.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService iFeedbackService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Feedback feedback) {

        return iFeedbackService.save(feedback) ? Result.suc() : Result.fail();
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {

        return iFeedbackService.removeById(id) ? Result.suc() : Result.fail();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.suc(iFeedbackService.list());
    }





}
