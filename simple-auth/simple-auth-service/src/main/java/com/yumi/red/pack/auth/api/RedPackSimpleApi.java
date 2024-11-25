package com.yumi.red.pack.auth.api;

import com.yumi.red.pack.common.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 简单授权系统
 */
public interface RedPackSimpleApi {

    @GetMapping("/create/{name}")
    Result createUser(@PathVariable(name = "name") String name);

    @GetMapping("/login/{id}")
    Result login(@PathVariable(name = "id") Long userId);

}
