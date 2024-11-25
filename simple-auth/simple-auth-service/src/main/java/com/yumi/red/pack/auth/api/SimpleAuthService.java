package com.yumi.red.pack.auth.api;



import com.yumi.red.pack.auth.mapper.UserInfoMapper;
import com.yumi.red.pack.auth.po.UserInfo;
import com.yumi.red.pack.common.dto.Result;
import com.yumi.red.pack.common.dto.ResultCodeEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/simple-auth")
@RequiredArgsConstructor
@Slf4j
public class SimpleAuthService implements RedPackSimpleApi {

    private final UserInfoMapper userInfoMapper;

    @Override
    public Result createUser(String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfoMapper.insert(userInfo);
        return Result.success(userInfo.getId());
    }

    @Override
    public Result login(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        if (userInfo != null) {
            String result = Jwts.builder()
                    .setHeaderParam("type", "JWT")
                    .setId(UUID.randomUUID().toString())
                    .setSubject(userId.toString())
                    .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "yumi123qqqqqqqqqqqqqqqqqqqqqqqqqqq1111111111111111111111111111dddddd")
                    .compact();
            return Result.success(result);
        }
        return Result.fromCode(ResultCodeEnum.FAIL.getCode(), null);
    }
}
