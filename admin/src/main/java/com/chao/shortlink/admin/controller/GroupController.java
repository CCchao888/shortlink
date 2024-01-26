package com.chao.shortlink.admin.controller;

import com.chao.shortlink.admin.common.convention.result.Result;
import com.chao.shortlink.admin.common.convention.result.Results;
import com.chao.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.chao.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.chao.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.chao.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author:chao
 * Date:2024-01-25
 * Description: 短链接分组控制层
 */
@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * 新增分组
     * @param shortLinkGroupSaveReqDTO
     * @return
     */
    @PostMapping("/api/short-link/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO shortLinkGroupSaveReqDTO){
        groupService.saveGroup(shortLinkGroupSaveReqDTO.getName());
        return Results.success();
    }

    /**
     * 查询分组集合
     * @return
     */
    @GetMapping("/api/short-link/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup(){
        return Results.success(groupService.listGroup());
    }

    /**
     * 修改分组
     * @param shortLinkGroupUpdateReqDTO
     * @return
     */
    @PutMapping("/api/short-link/v1/group")
    public Result<Void> update(@RequestBody ShortLinkGroupUpdateReqDTO shortLinkGroupUpdateReqDTO){
        groupService.updateGroup(shortLinkGroupUpdateReqDTO);
        return Results.success();
    }

    /**
     * 删除分组
     * @param gid
     * @return
     */
     @DeleteMapping("/api/short-link/v1/group")
     public Result<Void> delete(@RequestParam("gid") String gid){
         groupService.deleteGroup(gid);
         return Results.success();
     }

}
