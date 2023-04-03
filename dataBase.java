package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class dataBase {
	public static Connection connectToDataBase() {
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/webbrowser","root","");
			return con;
		} catch (SQLException e) {
			System.out.println(e.getMessage()+" fail to conect to mysql");
		}
		return null;
	}
	public static void addHistory(String Id,String Host, String url,String Date) {
		String SQL="INSERT INTO `history`(`ID`, `HostName`, `Url`, `Date`, `Block`) VALUES (?,?,?,?,'0')";
		PreparedStatement ps;
		try {
			ps = connectToDataBase().prepareStatement(SQL);
			ps.setString(1, Id);
			ps.setString(2, Host);
			ps.setString(3, url);
			ps.setString(4, Date);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+" fail to add history to data base");
		}
	}
	public static void addDownload(String Id,String fileName, String url,String Date) {
		String SQL="INSERT INTO `download`(`ID`, `FileName`, `DownlodLink`, `Date`) VALUES (?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = connectToDataBase().prepareStatement(SQL);
			ps.setString(1, Id);
			ps.setString(2, fileName);
			ps.setString(3, url);
			ps.setString(4, Date);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+" fail to add history to data base");
		}
	}
	public static void addBookMark(String Id,String websitName, String webHost) {
		String SQL="INSERT INTO `bookmark`(`id`, `webSiteName`, `webSiteHost`) VALUES (?,?,?)";
		PreparedStatement ps;
		try {
			ps = connectToDataBase().prepareStatement(SQL);
			ps.setString(1, Id);
			ps.setString(2, websitName);
			ps.setString(3, webHost);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+" fail to add history to data base");
		}
	}
}
