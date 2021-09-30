package com.csf.whoami.adm;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csf.base.constant.ConstantsParam;
import com.csf.base.constant.ConstantsString;
import com.csf.base.domain.SearchVO;
import com.csf.base.utilities.RequestUtils;
import com.csf.base.utilities.StringUtils;

public abstract class DashboardPage<T> {

    /**
     * Go to management page.
     * 
     * @param search
     * @param pageable
     * @param model
     * @return
     */
    @GetMapping(value = ConstantsURL.URI_MANAGEMENT)
    public ModelAndView managementPage(@ModelAttribute(ConstantsParam.SEARCH) SearchVO search, Pageable pageable, ModelAndView model) {

        Integer pageSize = SearchVO.DEFAULT_PAGING_SIZE;

        HttpServletRequest request = RequestUtils.currentRequest().orElse(null);
        if (request != null) {
            Map<String, String[]> requestParams = request.getParameterMap();
            if (requestParams.containsKey("p")) {
                if (!StringUtils.isNullOrEmpty(request.getParameter("p"))) {
                    pageSize = StringUtils.toIntegerOrNull(request.getParameter("p").trim());
                }
            }
        }
        if (pageSize == null) {
//            throw new CustomException(ErrorCode.INVALID_FORMAT.getMessage(), ErrorCode.INVALID_FORMAT.getCode(), HttpStatus.BAD_REQUEST);
        }

        search.setPageSize(pageSize);
        pageable = PageRequest.of(search.getPage() <= 0 ? 0 : pageable.getPageNumber(), pageable.getPageSize());
        Page<T> datas = searchData(search, pageable, model);
        model.addObject(ConstantsParam.DATAS, datas);
        model.addObject(ConstantsParam.PAGE_NO, 0);
        model.addObject(ConstantsParam.TOTAL_COUNT, datas.getTotalElements());
        model.addObject(ConstantsParam.SEARCH, search);
        model.setViewName(MessageFormat.format(ConstantsURL.PAGE_MANAGEMENT, originUrl()));

        return model;
    }
    protected Page<T> searchData(SearchVO search, Pageable pageable, ModelAndView model) { return Page.empty(); }

    /**
     * Search data.
     * 
     * @param search
     * @param pageable
     * @param model
     * @return
     */
    @PostMapping(value = ConstantsURL.URI_MANAGEMENT)
    public ModelAndView searchDatas(@ModelAttribute(ConstantsParam.SEARCH) SearchVO search, Pageable pageable, ModelAndView model) {
        String redirectPage = managementPage(search);
        return new ModelAndView(redirectPage + addSearchParams(search));
    }
    protected String addSearchParams(SearchVO search) { return ConstantsString.BLANK; }
    protected abstract String functionPath();

    /**
     * Go to register page.
     * 
     * @param model
     * @return
     */
    @GetMapping(value = ConstantsURL.URI_REGISTER)
    public ModelAndView registerPage(ModelAndView model) {
        model.setViewName(detailPage());
        T data = initialModel();
        initialData(data, model);
        model.addObject(ConstantsParam.DATAS, data);
        return model;
    }

    protected void initialData(T data, ModelAndView model) { }
    protected abstract T initialModel();

    /**
     * Register or Update datas information.
     * 
     * @param userinfo
     * @param model
     * @return
     */
    @PostMapping(value = ConstantsURL.URI_REGISTER)
    public ModelAndView registerOrUpdateModel(@ModelAttribute T data, ModelAndView model) {
        boolean modifyStatus = registerOrUpdate(data, model);
        if (modifyStatus) {
            // Redirect in case success.
            model = new ModelAndView(managementPage(new SearchVO()));
        } else {
            model.addObject(ConstantsParam.DATAS, initialModel());
            model.setViewName(detailPage());
        }
        return model;
    }
    protected boolean registerOrUpdate(T data, ModelAndView model) {
        return false;
    }

    /**
     * Go to detail page.
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = ConstantsURL.URI_DETAIL)
    public ModelAndView detailPage(@PathVariable("id") String id, ModelAndView model) {
        Long dataId = StringUtils.toLongOrNull(id);
        model.addObject(ConstantsParam.DATAS, detailInfo(dataId, model));
        model.setViewName(detailPage());
        return model;
    }
    protected T detailInfo(Long dataId, ModelAndView model) {
        return null;
    }

    /**
     * Deletes list IDs.
     * 
     * @param ids
     * @return
     */
    @PostMapping(value = ConstantsURL.URI_DELETE_LIST)
    @ResponseBody
    public List<Long> deleteContents(@RequestBody List<String> ids) {
        return deleteIds(ids);
    }

    protected List<Long> deleteIds(List<String> ids) {
        return null;
    }

    /**
     * Delete content by ID.
     * 
     * @param id
     * @return
     */
    @GetMapping(value = ConstantsURL.URI_DELETE)
    @ResponseBody
    public Long delete(@PathVariable("id") String id) {
        Long dataId = StringUtils.toLongOrNull(id);
        return deleteById(dataId);
    }
    protected Long deleteById(Long id) {
        return null;
    }

    private String originUrl() {
        String inputStr = functionPath();
        if (StringUtils.isNullOrEmpty(inputStr)) {
            return "";
        }
        int dashboardIndex = inputStr.indexOf(ConstantsURL.DASHBOARD_HOME);
        if (dashboardIndex >= 0) {
            inputStr = inputStr.substring(dashboardIndex + ConstantsURL.DASHBOARD_HOME.length() + 1);
        }
        int lastSlash = inputStr.indexOf("/");
        if (lastSlash > 0) {
            inputStr = inputStr.substring(0, lastSlash);
        }
        return inputStr.substring(0, inputStr.length() - 1);
    }

    protected String detailPage() {
        return MessageFormat.format(ConstantsURL.PAGE_DETAIL, originUrl());
    }

    protected String managementPage(SearchVO search) {
        String pageSize = search == null ? String.valueOf(SearchVO.DEFAULT_PAGING_SIZE) : String.valueOf(search.getPageSize());
        return MessageFormat.format(ConstantsURL.PAGE_REDIRECT, functionPath(), ConstantsURL.URI_MANAGEMENT, pageSize);
    }

    protected void redirectManagementPage(ModelAndView model) {
        model.setViewName(managementPage(null));
    }
}
