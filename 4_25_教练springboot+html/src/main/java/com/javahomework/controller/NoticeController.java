package com.javahomework.controller;

import com.javahomework.common.Result;
import com.javahomework.entity.Notice;
import com.javahomework.entity.User;
import com.javahomework.service.INoticeService;
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
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;

    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Notice notice) {
        iNoticeService.save(notice);
        return Result.suc();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Notice notice) {
        return iNoticeService.updateById(notice) ? Result.suc() : Result.fail();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.suc(iNoticeService.list());
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        System.out.println(id);
        return iNoticeService.removeById(id) ? Result.suc() : Result.fail();
    }

}
