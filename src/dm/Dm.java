package dm;

import com.dm.helpers.DStatus;
import com.dm.helpers.Manager;
import java.io.FileOutputStream;

/**
 *
 * @author Anique Tahir<aniquetahir@gmail.com>
 */
public class Dm {
    private static final int DOWNLOAD_UNIT = 102400;//100kB
    
    public static void main(String[] args) {
        DStatus dstats = DStatus.getConfig();
        if(dstats == null){
            dstats = new DStatus();
        }
        
        Manager man = new Manager();
        
        
        String url = "http://d10rb0yh5vi21i.cloudfront.net/d/img/one.png?20140403001";
        try{
            byte[] segment = man.getSegment(url,0,0);
            FileOutputStream fos = new FileOutputStream(getFileName(url));
            fos.write(segment);
            fos.flush();
            fos.close();
        }catch(Exception e){
        }finally{
        }
        
        dstats.close();
        
    }
    
    private static String getFileName(String url){
        int start = url.lastIndexOf('/') + 1;
        int end  = url.lastIndexOf('?');
        
        if(end==-1 || end<start){
            return url.substring(start);
        }else{
            return url.substring(start, end);
        }
    }  
    
}
