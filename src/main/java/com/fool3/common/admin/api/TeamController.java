package com.fool3.common.admin.api;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.common.Response;
import com.fool3.common.admin.entity.Team;
import com.fool3.common.admin.security.SecurityUtils;
import com.fool3.common.admin.service.ITeamService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("Team")
@RequestMapping("/api/admin/teams")
@RestController
public class TeamController {
    @Autowired
    private ITeamService teamService;

    @GetMapping("")
    public Response<PageInfo<Team>> queryPage(@RequestParam(required = false) String keyword,
                                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                              @RequestParam(value = "sort", required = false) String sort,
                                              @RequestParam(value = "order", required = false, defaultValue = "ASC") Order order) {
        PageInfo<Team> pageInfo = teamService.queryPage(keyword, pageNum, pageSize, sort, order);
        return Response.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Response<Team> getById(@PathVariable Long id) {
        Team team = teamService.getById(id);
        return Response.success(team);
    }

    @PostMapping("")
    public Response<Long> save(@RequestBody Team team) {
        team.setCreatedTime(new Date());
        team.setCreatedBy(SecurityUtils.getCurrentUserId());
        Long id = teamService.save(team);
        return Response.success(id);
    }

    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id, @RequestBody Team team) {
        team.setId(id);
        team.setUpdatedTime(new Date());
        team.setUpdatedBy(SecurityUtils.getCurrentUserId());
        int i = teamService.update(team);
        return Response.success(i > 0);
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteById(@PathVariable Long id) {
        int i = teamService.deleteById(id);
        return Response.success(i > 0);
    }
}
