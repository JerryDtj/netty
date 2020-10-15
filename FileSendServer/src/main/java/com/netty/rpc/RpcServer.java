package com.netty.rpc;

import com.sun.jndi.toolkit.url.UrlUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 * @Date 2020/10/15 7:18 上午
 */
public class RpcServer {
    private Map<String,Object> fileMap = new HashMap<String, Object>();

    public Map<String, Object> getFileMap() {
        return fileMap;
    }


    public void loadFile(String packageLocations) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        if (StringUtils.isBlank(packageLocations)){
            throw new RuntimeException("文件路径为空");
        }
        URL url = this.getClass().getClassLoader().getResource(packageLocations.replaceAll("\\.","/"));
        File dirs = new File(url.getFile());
        for (File file:dirs.listFiles()){
            if (file.isDirectory()){
                this.loadFile(packageLocations+"."+file.getName());
            }else if (file.getName().endsWith("class")){
                String fileName = (packageLocations+"."+file.getName()).replace(".class","");
                Class c = Class.forName(fileName);
                fileMap.put(fileName,c.newInstance());
            }
        }
    }
}
