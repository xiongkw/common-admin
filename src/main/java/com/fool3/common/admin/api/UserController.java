package com.fool3.common.admin.api;

import com.fool3.common.admin.common.Order;
import com.fool3.common.admin.common.Response;
import com.fool3.common.admin.dto.UserDTO;
import com.fool3.common.admin.entity.User;
import com.fool3.common.admin.security.SecurityUtils;
import com.fool3.common.admin.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

@Api("User")
@RequestMapping("/api/admin/users")
@RestController
public class UserController {
    private final Consumer<UserDTO> hidePasswordConsumer = u -> u.setPassword(null);

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public Response<PageInfo<UserDTO>> queryPage(@RequestParam(required = false) String keyword,
                                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                 @RequestParam(value = "sort", required = false) String sort,
                                                 @RequestParam(value = "order", required = false, defaultValue = "ASC") Order order) {
        PageInfo<UserDTO> pageInfo = userService.queryPage(keyword, pageNum, pageSize, sort, order);
        Optional.ofNullable(pageInfo).map(PageInfo::getList).ifPresent(l -> l.forEach(hidePasswordConsumer));
        return Response.success(pageInfo);
    }

    @GetMapping("/{id}")
    public Response<UserDTO> getById(@PathVariable Long id) {
        UserDTO user = userService.getById(id);
        Optional.ofNullable(user).ifPresent(hidePasswordConsumer);
        return Response.success(user);
    }

    @PostMapping("")
    public Response<Long> save(@RequestBody User user) {
        user.setCreatedTime(new Date());
        user.setCreatedBy(SecurityUtils.getCurrentUserId());
        Long id = userService.save(user);
        return Response.success(id);
    }

    @PutMapping("/{id}")
    public Response<Boolean> update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        user.setUpdatedTime(new Date());
        user.setUpdatedBy(SecurityUtils.getCurrentUserId());
        int i = userService.update(user);
        return Response.success(i > 0);
    }

    @DeleteMapping("/{id}")
    public Response<Boolean> deleteById(@PathVariable Long id) {
        int i = userService.deleteById(id);
        return Response.success(i > 0);
    }
}
