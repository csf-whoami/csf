package com.csf.base.paging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.csf.base.core.ZValue;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Service
public class PageInfo implements IPageInfo {
	protected Log log = LogFactory.getLog(this.getClass());

    protected String lastPrevImg = "<span class='s'><span>처음 <span class='t'>목록</span></span></span>";
    protected String prev10Img = "<span class='s'><span>이전 <span class='t'>목록</span></span></span>";
    protected String next10Img = "<span class='s'><span>다음 <span class='t'>목록</span></span></span>";
    protected String lastNextImg = "<span class='s'><span>끝 <span class='t'>목록</span></span></span>";

	@Override
	public String getPageNavString( int pageSize, int total, int cpage, String link )
	{
		return getPageNavString(pageSize, 10, total, cpage, link);
	}

	@Override
	public String getPageNavString( int pageSize, int pageUnit, int total, int cpage, String link )
	{
		// 전체페이지
		int totalPage = (total - 1) / pageUnit + 1;
		// 이전10개, 다음10개
		// 이전 마지막 페이지 0 이면 이전10개 없음
		int prev10 = (int) Math.floor((cpage - 1) / pageSize) * pageSize;
		// 다음 첫페이지 totalPage 보다 크면 다음10개 없음
		int next10 = prev10 + pageSize + 1;
		StringBuffer sbuf = new StringBuffer();

		if( link.indexOf("?") == -1) link += "?";

		sbuf.append("<div class='paginationSet'>");
		sbuf.append("<ul class='pagination pagination-centered'>" );
		if (prev10 > 0)
		{
			sbuf.append("<li class='i first'><a title='처음 목록' href='").append(link).append("&amp;pageIndex=1'>").append(lastPrevImg).append("</a></li>");
			sbuf.append("<li class='i prev'><a title='이전 목록' href='").append(link).append("&amp;pageIndex=").append(prev10).append("'>").append(prev10Img).append("</a></li>");
		} // end if 이전10개
		else
		{
			//sbuf.append("<li class='i first disabled'>").append(lastPrevImg).append("</li>");
			//sbuf.append("<li class='i prev disabled'>").append(prev10Img).append("</li>");
			sbuf.append("<li class='i first disabled'><a title='처음 목록' href='").append(link).append("&amp;pageIndex=1' onclick='return false;'>").append(lastPrevImg).append("</a></li>");
			sbuf.append("<li class='i prev disabled'><a title='이전 목록' href='").append(link).append("&amp;pageIndex=").append(prev10).append("' onclick='return false;'>").append(prev10Img).append("</a></li>");
		}


		for (int i = 1 + prev10; i < next10 && i <= totalPage; i++)
		{
			if (i == cpage)
			{
				sbuf.append("<li class='active'><span><em title='현재목록'><span>").append(i).append("</span></em></span></li>");
			}
			else
			{
				sbuf.append("<li><a href='").append(link).append("&amp;pageIndex=").append(i).append("'><span>").append(i).append("</span></a></li>");
			} // end if 현재목록 링크제거
		} // end for

		if (totalPage >= next10)
		{

			sbuf.append("<li class='i next'><a title='다음 목록' href='").append(link).append("&amp;pageIndex=").append(next10).append("'>").append(next10Img).append("</a></li>");
			sbuf.append("<li class='i end'><a title='마지막 목록' href='").append(link).append("&amp;pageIndex=").append(totalPage).append("'>").append(lastNextImg).append("</a></li>");
		} // end if 다음10개
		else
		{
			//sbuf.append("<li class='i next disabled'>").append(next10Img).append("</li>");
			//sbuf.append("<li class='i end disabled'>").append(lastNextImg).append("</li>");
			sbuf.append("<li class='i next disabled'><a title='다음 목록' href='").append(link).append("&amp;pageIndex=").append(next10).append("' onclick='return false;'>").append(next10Img).append("</a></li>");
			sbuf.append("<li class='i end disabled'><a title='마지막 목록' href='").append(link).append("&amp;pageIndex=").append(totalPage).append("' onclick='return false;' data-endpage='"+totalPage+"'>").append(lastNextImg).append("</a></li>");
		}
		sbuf.append("</ul>" );
		sbuf.append("</div>" );

		return sbuf.toString();
	}

	@Override
	public String getPageNavStringToFunc( int pageSize, int total, int cpage, String funcName )
	{
		// 전체페이지
		int totalPage = (total - 1) / pageSize + 1;
		// 이전10개, 다음10개
		// 이전 마지막 페이지 0 이면 이전10개 없음
		int prev10 = (int) Math.floor((cpage - 1) / 10.0) * 10;
		// 다음 첫페이지 totalPage 보다 크면 다음10개 없음
		int next10 = prev10 + 11;
		StringBuffer sbuf = new StringBuffer();

		sbuf.append("<div class='paginationSet'>");
		sbuf.append("<ul class='pagination pagination-centered'>" );
		if (prev10 > 0)
		{
			sbuf.append("<li class='i first'><a href='javascript:"+funcName+"(1);'>").append(lastPrevImg).append("</a></li>");
			sbuf.append("<li class='i prev'><a href='javascript:"+funcName+"("+prev10+");'>").append(prev10Img).append("</a></li>");
		} // end if 이전10개
		else
		{
			sbuf.append("<li class='i first disabled'>").append(lastPrevImg).append("</li>");
			sbuf.append("<li class='i prev disabled'>").append(prev10Img).append("</li>");
		}


		for (int i = 1 + prev10; i < next10 && i <= totalPage; i++)
		{
			if (i == cpage)
			{
				sbuf.append("<li class='active'><span><em title='현재목록'>").append(i).append("</em></span></li>");
			}
			else
			{
				sbuf.append("<li><a href='javascript:"+funcName+"("+i+");'><span>").append(i).append("</span></a></li>");
			} // end if 현재목록 링크제거
		} // end for

		if (totalPage >= next10)
		{

			sbuf.append("<li class='i next'><a href='javascript:"+funcName+"("+next10+");'>").append(next10Img).append("</a></li>");
			sbuf.append("<li class='i end'><a href='javascript:"+funcName+"("+totalPage+");'>").append(lastNextImg).append("</a></li>");
		} // end if 다음10개
		else
		{
			sbuf.append("<li class='i next disabled'>").append(next10Img).append("</li>");
			sbuf.append("<li class='i end disabled'>").append(lastNextImg).append("</li>");
		}
		sbuf.append("</ul>" );
		sbuf.append("</div>" );
		return sbuf.toString();
	}

	public ZValue getPaginationInfo(ZValue zvl)
	{
		int pageUnit = zvl.getInt("pageUnit") == 0 ? 10 : zvl.getInt("pageUnit");
    	int pageSize = 10;
    	int pageIndex = zvl.getInt("pageIndex", 1);
    	int totalRecordCount = zvl.getInt("totCnt", 1);

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageIndex);
		paginationInfo.setRecordCountPerPage(pageUnit);
		paginationInfo.setPageSize(pageSize);
		paginationInfo.setTotalRecordCount(totalRecordCount);

    	zvl.put("pageIndex", pageIndex);
    	zvl.put("pageUnit", pageUnit);
    	zvl.put("pageSize", pageSize);
    	zvl.put("firstIndex", paginationInfo.getFirstRecordIndex());
    	zvl.put("lastIndex", paginationInfo.getLastRecordIndex());
    	zvl.put("recordCountPerPage", paginationInfo.getRecordCountPerPage());
    	zvl.put("totalPageCount", paginationInfo.getTotalPageCount());

    	return zvl;
	}

	public String getPageNavString(ZValue zvl){
		return getPageNavString( zvl.getInt("pageSize"), zvl.getInt("pageUnit"), zvl.getInt("totCnt"), zvl.getInt("pageIndex",1), zvl.getString("link") );
	}
}
