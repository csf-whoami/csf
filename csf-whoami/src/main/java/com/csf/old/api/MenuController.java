package com.csf.old.api;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

@RestController
//@RequestMapping(ConstantsURL.API_MENU)
@RequiredArgsConstructor
@Api(tags = "1. Menu functions")
public class MenuController {

//    private final MenuService menuService;
//
//    @ApiOperation(value = "1. Fetch menus by user")
//    @GetMapping(ConstantsURL.ME_PATH)
//    public ResponseEntity<ResponseDataAPI> fetchUserMenu() {
//
//        List<MenuDomain> menus = menuService.getMenus();
//        return ResponseEntity.ok(ResponseDataAPI.builder()
//                .data(menus)
//                .build());
//    }
//
//    @ApiOperation(value = "2. Register menu")
//    @PostMapping(ConstantsURL.ME_PATH)
//    public ResponseEntity<ResponseDataAPI> registerMenu(@RequestBody MenuDomain menu) {
//        Long menuId = menuService.registerMenu(menu);
//        return ResponseEntity.ok(ResponseDataAPI.builder()
//                .data(menuId)
//                .build());
//    }
}
