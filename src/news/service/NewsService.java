package news.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

	private final String SOURCE_URL = "http://feeds.bbci.co.uk/news/world/rss.xml#";
	private final String FIRST_CUT_TITLE = "<title><![CDATA[";
	private final String LAST_CUT_TITLE = "]]></title>";
	private final String FIRST_CUT_LINK = "<link>";
	private final String LAST_CUT_LINK = "</link>";

	public String[] getOneTopic() {
		List<String[]> news = getNewsList();
		int random =(int)(Math.random() * (news.size() - 1) + 1);
		String[] result = new String[2];
		for(int i =0;i<2;i++){
			byte[] utf8 = news.get(random)[i].getBytes();
			byte[] latin1 = null;
			try {
				latin1 = new String(utf8, "UTF-8").getBytes("ISO-8859-1");
				result[i] = new String(latin1);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Cacheable("news")
	public List<String[]> getNewsList() {
		try {
			URL rssURL = new URL(SOURCE_URL);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					rssURL.openStream()));
			List<String[]> news = new LinkedList<String[]> ();
			String line;
			while ((line = in.readLine()) != null) {

				int firstPos = line.indexOf(FIRST_CUT_TITLE);
				if (firstPos > -1) {
					String title = line.substring(firstPos
							+ FIRST_CUT_TITLE.length());
					int lastPos = title.indexOf(LAST_CUT_TITLE);
					title = title.substring(0, lastPos);
					while(!(line = in.readLine()).contains("<link>")){
						
					}
					int firstPos1 = line.indexOf(FIRST_CUT_LINK);
					String link = line.substring(firstPos1
							+ FIRST_CUT_LINK.length());
					int lastPos1 = link.indexOf(LAST_CUT_LINK);
					link = link.substring(0, lastPos1);
					news.add(new String[]{title,link});
					
				}

			}
			in.close();
			return news;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
