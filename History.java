package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class History {
	public static void  addHistory(String Host,String url ,String Date) {
		try {
			FileWriter historyWriter=new FileWriter("history.txt",true);
			BufferedWriter br=new BufferedWriter(historyWriter);
			br.write(Host);
			br.newLine();
			br.write(url);
			br.newLine();
			br.write(Date);
			br.newLine();
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()+" fail to add history");
		}
	}
	public static void AddDownloadStory(String fileName,String link ,String Date) {
		try {
			FileWriter downloadHistoryWriter=new FileWriter("downloadHistory.txt",true);
			BufferedWriter br=new BufferedWriter(downloadHistoryWriter);
			br.write(fileName);
			br.newLine();
			br.write(link);
			br.newLine();
			br.write(Date);
			br.newLine();
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()+" fail to add download history");
		}
	}
	public static boolean AddBookMark(String webSiteName,String webSiteLink) {
		try {
			FileReader bookmarkReader=new FileReader("bookmark.txt");
			try (BufferedReader br = new BufferedReader(bookmarkReader)) {
				String line;
				while((line =br.readLine())!=null) {
					if(line.contentEquals(webSiteName)) {
						System.out.println("bookmark exist");
						return true;
					}

					System.out.println("search "+line+" "+webSiteName);
				}
			}
			FileWriter bookmarkWriter=new FileWriter("bookmark.txt",true);
			BufferedWriter bw=new BufferedWriter(bookmarkWriter);
			bw.write(webSiteName);
			bw.newLine();
			bw.write(webSiteLink);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage()+" fail to add Bookmark");
		}
		return false;
	}
}
