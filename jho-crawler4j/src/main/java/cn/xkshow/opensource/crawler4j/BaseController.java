/*
 *Project: jho-crawler4j
 *File: cn.xkshow.opensource.crawler4j.BaseController.java <2015��4��2��>
 ****************************************************************
 * ��Ȩ����@2015 С��������Ƽ�  ��������Ȩ��.
 ***************************************************************/
package cn.xkshow.opensource.crawler4j;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import uk.org.lidalia.slf4jext.Logger;
import uk.org.lidalia.slf4jext.LoggerFactory;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * ������ƻ���
 * @Author hardy
 * @Date 2015��4��2�� ����3:03:12
 */
public abstract class BaseController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/** ������ϢĬ�ϴ洢Ŀ¼ */
	protected static final String CRAWL_STORAGE = "target/crawl/";
	
	/**
	 * ��ȡ���������
	 * @Author hardy<2015��4��13��>
	 * @return
	 * @throws Exception
	 */
	protected CrawlController getController() throws Exception{
		logger.info("crawler.io.tmpdir��" + CRAWL_STORAGE);
        return this.getController(CRAWL_STORAGE);
	}
	
	/**
	 * ��ȡ���������
	 * @param storageFolder�洢�ļ���
	 * @return
	 * @throws Exception
	 * @Author hardy
	 * 2015��4��4�� ����1:29:36
	 */
	protected CrawlController getController(String storageFolder) throws Exception{  
        return this.getController(storageFolder, false, -1);
	}
	
	/**
	 * ��ȡ���������
	 * @Author hardy<2015��4��15��>
	 * @param maxDepthOfCrawling�������
	 * @return
	 * @throws Exception
	 */
	protected CrawlController getController(int maxDepthOfCrawling) throws Exception{
		logger.info("crawler.io.tmpdir��" + CRAWL_STORAGE);
        return this.getController(CRAWL_STORAGE, false, maxDepthOfCrawling);
	}
		
	/**
	 * ��ȡ���������
	 * @Author hardy<2015��4��13��>
	 * @param storageFolder�洢�ļ���
	 * @param resumableCrawling��֧���ظ���ȡ
	 * @return
	 * @throws Exception
	 */
	protected CrawlController getController(String storageFolder, boolean resumableCrawling) throws Exception{  
        return this.getController(storageFolder, resumableCrawling, -1);
	}  
		
	/**
	 * ��ȡ���������
	 * @Author hardy<2015��4��15��>
	 * @param storageFolder�洢�ļ���
	 * @param resumableCrawling��֧���ظ���ȡ
	 * @param maxDepthOfCrawling�������
	 * @return
	 * @throws Exception
	 */
	protected CrawlController getController(String storageFolder, boolean resumableCrawling, int maxDepthOfCrawling) throws Exception{  
        CrawlConfig config = new CrawlConfig();  
        if(StringUtils.isNotEmpty(storageFolder))        	
        	config.setCrawlStorageFolder(storageFolder);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setResumableCrawling(resumableCrawling);

        return this.getController(config); 
	}  
	
	/**
	 * ��ȡ���������
	 * @Author hardy<2015��4��13��>
	 * @param config
	 * @return
	 * @throws Exception
	 */
	protected CrawlController getController(CrawlConfig config) throws Exception{         
        /* 
         * Instantiate the controller for this crawl. 
         */  
        PageFetcher pageFetcher = new PageFetcher(config);  
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();  
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);  
        return new CrawlController(config, pageFetcher, robotstxtServer); 
	}  
	
	/**
	 * �������
	 * @param lstUrls
	 * @throws Exception
	 * @Author hardy
	 * 2015��4��4�� ����2:00:35
	 */
	public abstract void door(List<String> lstUrls) throws Exception;
		
}
