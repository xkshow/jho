/*
 *Project: jho-crawler4j
 *File: cn.xkshow.opensource.crawler4j.BaseCrawler.java <2015年4月4日>
 ****************************************************************
 * 版权所有@2015 小康秀网络科技  保留所有权利.
 ***************************************************************/
package cn.xkshow.opensource.crawler4j;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @Author hardy 
 * @Date 2015年4月4日 下午8:15:54
 * @version 1.0
 */
public class BaseCrawler extends WebCrawler {
	
	/**
	 * 正则表达式：音视频、图片、压缩包
	 */
	private final Pattern LIMIT_FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	/**
	 * 默认不爬不能解析的格式，如音视频、图片、压缩包
	 */
	@Override
	public boolean shouldVisit(Page page, WebURL url) {
		return !LIMIT_FILTERS.matcher(url.getURL().toLowerCase()).matches();
	}
	
	/**
	 * 获取爬虫解析文档
	 * @Author hardy<2015年4月4日>
	 * @param page
	 * @return
	 */
	public Document getDocument(Page page){
		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			return Jsoup.parse(htmlParseData.getHtml());
		}
		return null;
	}
}
