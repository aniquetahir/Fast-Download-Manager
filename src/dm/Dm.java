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
            byte[] segment = man.getSegment(url,0);
            FileOutputStream fos = new FileOutputStream(getFileName(url));
            fos.write(segment);
            
            
            
            
            
            
        }catch(Exception e){
            
        }finally{
            
            
            
        }
        
        dstats.close();
        
//        CloseableHttpClient client = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//        try{
//            CloseableHttpResponse response = client.execute(httpGet);
//            HttpEntity respEntity = response.getEntity();
//            
//            
//            Header[] responseHeaders = response.getAllHeaders();
//            
//            for(Header responseHeader:responseHeaders){
//                System.out.println(responseHeader.getName()+":"+responseHeader.getValue());
//            }
//            
//            long responseLength = respEntity.getContentLength();
//            InputStream respInput = respEntity.getContent();
//            
//            
//            
//            ArrayList<byte[]> bufferList = new ArrayList<>();
//            while(true){
//                byte[] buffer = new byte[DOWNLOAD_UNIT];
//                //Read DOWNLOAD_UNIT number of bytes
//                int readResp = 0;
//                int offset = 0;
//                while(offset<DOWNLOAD_UNIT){
//                    readResp = respInput.read(buffer, offset, DOWNLOAD_UNIT-offset);
//                    offset+=readResp;
//                    if(readResp==-1){
//                        break;
//                    }
//                }
//                if(readResp==-1){
//                    byte[] lastBuffer = new byte[offset+1];
//                    System.arraycopy(buffer, 0, lastBuffer, 0, offset+1);
//                    bufferList.add(lastBuffer);
//                    break;
//                }else{
//                    bufferList.add(buffer);
//                }
//            }
//            
//            int contentLength = ((bufferList.size()-1)*DOWNLOAD_UNIT) + bufferList.get(bufferList.size()-1).length;
//            byte[] mainBuffer = new byte[contentLength];
//            for(int j=0;j<bufferList.size();j++){
//                byte[] tBuffer = bufferList.get(j);
//                System.arraycopy(tBuffer, 0, mainBuffer, j * DOWNLOAD_UNIT, tBuffer.length);
//            }
//            
//            FileOutputStream fos = new FileOutputStream(getFileName(url));
//            try{
//                fos.write(mainBuffer);
//                fos.flush();
//                
//            }catch(Exception e){
//                System.out.println("Failed to write");
//            }finally{
//                fos.close();
//            }
//            
//            System.out.println(new String(mainBuffer,"UTF-8"));
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
        
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
