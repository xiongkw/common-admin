package com.fool3.common.admin.api;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.common.Response;
import com.fool3.common.admin.entity.Role;
import com.fool3.common.admin.security.SecurityUtils;
import com.fool3.common.admin.service.IRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api("Role")
@RequestMapping("/api/admin/roles")
@RestController
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("")
    public Response<PageInfo<Role>> queryPage(@RequestParam(required = false) String keyword,
                                              @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                              @RequestParam(value = "sort", required = false) String sort,
                                              @RequestParam(value = "order", required = false, defaultValue = "ASC") Order order) {
        PageInfo<Role> pageInfo = roleService.queryPage(keyword, pageNum, pageSize, sort, order);
        return Response.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Response<Role> getById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return Response.success(role);
    }

    @PostMapping("")
    public Response<Long> save(@RequestBody Role role) {
        role.setCreatedTime(new Date());
        role.setCreatedBy(SecurityUtils.getCurrentUserId());
        Long id = roleService.save(role);
        return Response.success(id);
    }

    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        role.setUpdatedTime(new Date());
        role.setUpdatedBy(SecurityUtils.getCurrentUserId());
        int i = roleService.update(role);
        return Response.success(i > 0);
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteById(@PathVariable Long id) {
        int i = roleService.deleteById(id);
        return Response.success(i > 0);
    }
}
