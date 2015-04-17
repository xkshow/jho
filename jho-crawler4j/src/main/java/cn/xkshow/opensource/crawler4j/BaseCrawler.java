/*
 *Project: jho-crawler4j
 *File: cn.xkshow.opensource.crawler4j.BaseCrawler.java <2015��4��4��>
 ****************************************************************
 * ��Ȩ����@2015 С��������Ƽ�  ��������Ȩ��.
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
 * @Date 2015��4��4�� ����8:15:54
 * @version 1.0
 */
public class BaseCrawler extends WebCrawler {
	
	/**
	 * ������ʽ������Ƶ��ͼƬ��ѹ����
	 */
	private final Pattern LIMIT_FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g"
					+ "|png|tiff?|mid|mp2|mp3|mp4"
					+ "|wav|avi|mov|mpeg|ram|m4v|pdf"
					+ "|rm|smil|wmv|swf|wma|zip|rar|gz))$");
	
	/**
	 * Ĭ�ϲ������ܽ����ĸ�ʽ��������Ƶ��ͼƬ��ѹ����
	 */
	@Override
	public boolean shouldVisit(Page page, WebURL url) {
		return !LIMIT_FILTERS.matcher(url.getURL().toLowerCase()).matches();
	}
	
	/**
	 * ��ȡ��������ĵ�
	 * @Author hardy<2015��4��4��>
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
