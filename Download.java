package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import javafx.concurrent.Task;

/**
 * @author SWL
 *
 */
public class Download extends Task<Void> {
	private String Url;
	@Override
	protected Void call() throws Exception {
		String fileName=Url.substring(Url.lastIndexOf("/")+1, Url.lastIndexOf("."));
		String ext=Url.substring(Url.lastIndexOf("."), Url.length());
		URLConnection link= new URL(Url).openConnection();
		long fileLen=link.getContentLengthLong();
		try(InputStream inputStream=link.getInputStream()){
			Objects.requireNonNull(inputStream);
			OutputStream ostream = null;
			 try {
		            ostream = Files.newOutputStream(Paths.get("C:\\Users\\SWL\\Downloads\\"+fileName+ext), StandardOpenOption.CREATE_NEW,
		                                              StandardOpenOption.WRITE);
		        } catch (FileAlreadyExistsException x) {
		        	int c=1;
		        	boolean fileExist=true;
		            while(fileExist) {
		            	try {
		            		ostream = Files.newOutputStream(Paths.get("C:\\Users\\SWL\\Downloads\\"+fileName+"("+c+")"+ext), StandardOpenOption.CREATE_NEW,
		                            StandardOpenOption.WRITE);
		            		fileExist=false;
						} catch (FileAlreadyExistsException e) {
							c++;
							fileExist=true;
						}
		            }
		            
		        }
			 try (OutputStream out = ostream) {
		           Objects.requireNonNull(out, "out");
		           long transferred = 0;
		           byte[] buffer = new byte[8192];
		           int read;
		           while ((read = inputStream.read(buffer, 0, 8192)) >= 0) {
		               out.write(buffer, 0, read);
		               transferred += read;
		               this.updateProgress(transferred, fileLen);
		           }
		        }
			 
        }catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		return null;
	}
	public Download(String url) {
		super();
		this.Url = url;
	}
	@Override
	protected void running() {
		super.running();
		System.out.println("downloading");
	}
	@Override
	protected void succeeded() {
		super.succeeded();
		System.out.println("download done "+this.getTitle());
	}
	@Override
	protected void failed() {
		super.failed();
		System.out.println("download faild");
		removeProgres();
	}
	public void removeProgres() {
		
	}
	

}
