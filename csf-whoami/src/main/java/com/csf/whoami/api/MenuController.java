package com.csf.whoami.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.csf.base.constant.ConstantsURL;
import com.csf.base.domain.MenuDomain;
import com.csf.base.domain.ResponseDataAPI;
import com.csf.whoami.service.MenuService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(ConstantsURL.API_MENU)
@RequiredArgsConstructor
@Api
public class MenuController {

    private final MenuService menuService;

    @GetMapping(ConstantsURL.ME_PATH)
    public ResponseEntity<ResponseDataAPI> fetchUserMenu() {

        List<MenuDomain> menus = menuService.getMenus();
        return ResponseEntity.ok(ResponseDataAPI.builder()
                .data(menus)
                .build());
    }

    @PostMapping(ConstantsURL.ME_PATH)
    public ResponseEntity<ResponseDataAPI> registerMenu(@RequestBody MenuDomain menu) {
        Long menuId = menuService.registerMenu(menu);
        return ResponseEntity.ok(ResponseDataAPI.builder()
                .data(menuId)
                .build());
    }
}
