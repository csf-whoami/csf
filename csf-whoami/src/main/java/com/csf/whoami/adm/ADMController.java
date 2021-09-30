package com.csf.whoami.adm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.csf.base.MasterController;

@Controller
@RequestMapping("/{siteId}/{groupId}/{programId}")
public class ADMController extends MasterController {
}
